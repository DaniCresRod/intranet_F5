<script setup>
import { ref, onMounted, computed, defineProps, onBeforeMount } from 'vue';
import RequestServices from '@/services/RequestServices';

const requests = ref([]);

//const { filterBySchoolId, schoolId } = defineProps(['filterBySchoolId', 'schoolId']); 
const props=defineProps({
  filterBySchoolId: Boolean,
  schoolId:Number
});
const approvedRequests = ref([]);
const rejectedRequests = ref([]);


const getRequests = async () => {
  try {
    const response = await RequestServices.getAll();
    requests.value = response.data;
    console.log(requests.value);
  } catch (error) {
    console.error('Error al obtener las solicitudes:', error);
  }
};

// onMounted(() => {
//   getRequests(); 
// });

    const theUsers=ref([]);
    const theSchools=ref([]);
    const NamesOfUsers=ref([]);
    const NamesOfSchools=ref([]);

onBeforeMount(async () => { 
    await getRequests();

    for(var eachUser of requests.value){
      
      if(eachUser.userId && eachUser.userId.hasOwnProperty("id")){
        theUsers.value.push(eachUser.userId.id);
        NamesOfUsers.value.push(eachUser.userId.userName)
        if(eachUser.userId.schoolID.hasOwnProperty("id")){
          theSchools.value.push(eachUser.userId.schoolID.id);
          NamesOfSchools.value.push(eachUser.userId.schoolID.schoolName);          
        }
        else{
          theSchools.value.push(eachUser.userId.schoolID);
          NamesOfSchools.value.push("X");
        }
      }
    }

    for(let i=0; i<NamesOfSchools.value.length;i++){
      if(NamesOfSchools.value[i]==="X"){
        NamesOfSchools.value[i]=NamesOfSchools.value[theSchools.value.indexOf(theSchools.value[i])];
      }
      
    }

    console.log(theUsers.value);
    console.log(theSchools.value)
    console.log(NamesOfUsers.value);
    console.log(NamesOfSchools.value);
  
})

const filteredRequests = computed(() => {
  if (props.filterBySchoolId && props.schoolId) {
    console.log(props.filterBySchoolId);
    console.log(props.schoolId);
    console.log(requests.value);    

    return requests.value.filter((request) => {

      let mySchool;
      let myName; 

      if(request.userId.hasOwnProperty("id")){
        myName=request.userId.id;
        mySchool=theSchools.value[theUsers.value.indexOf(myName)];
      } 
      else{
        myName=request.userId;
        mySchool=theSchools.value[theUsers.value.indexOf(myName)];
      } 

      return isRequestWithStatus1(request) && mySchool===props.schoolId;

    });
  } else {
    return requests.value.filter((request) => isRequestWithStatus1(request));
  }
});

const isRequestWithStatus1 = (request) => {
  return request.status === 1;
};

const approveRequest = async (id) => {
  await RequestServices.updateUserRequestStatus(id, 2);
  console.log('Solicitud aprobada');
  const index = requests.value.findIndex((request) => request.id === id);
  if (index !== -1) {
    approvedRequests.value.push(requests.value.splice(index, 1)[0]);
  }
};

const rejectRequest = async (id) => {
  await RequestServices.updateUserRequestStatus(id, 3);
  console.log('Solicitud rechazada');
  const index = requests.value.findIndex((request) => request.id === id);
  if (index !== -1) {
    rejectedRequests.value.push(requests.value.splice(index, 1)[0]);
  }
};

</script>

<template>
  <v-card class="requestList">
    <v-card-title class="title">Listado de Solicitudes</v-card-title>
    <v-table class="requestTable">
      <thead>
        <th>Empleado</th>
        <th>Fecha de solicitud</th>
        <th>Escuela</th>
        <th>Acciones</th>
      </thead>
      <tbody>
        <tr v-for="(request, index) in filteredRequests" :key="index">
          <td>
            {{ request.userId && request.userId.username && request.userId.userSurName ? NamesOfUsers[theUsers.indexOf(request.userId.id)]  : NamesOfUsers[theUsers.indexOf(request.userId)] }}            
          </td>
          <td>De {{ request.startDate }} a {{ request.endDate }}</td>
          <td>
            {{ request.userId && request.userId.schoolID && request.userId.userSurName ? NamesOfSchools[theUsers.indexOf(request.userId.id)] : NamesOfSchools[theUsers.indexOf(request.userId)] }}

          </td>
          <td>
            <v-btn @click="approveRequest(request.id)">Aprobar</v-btn>
            <v-btn @click="rejectRequest(request.id)">Rechazar</v-btn>
          </td>
        </tr>
      </tbody>
    </v-table>
  </v-card>
</template>

<style scoped>
.requestList {
  margin-top: 50px;
  color: orangered;
  padding: 20px;
  margin-bottom: 50px;
}

.title {
  display: flex;
  justify-content: center;
  text-align: center;
  color: var(--orange);
  font-weight: 450;
  margin-top: 25px;
  text-decoration-line: underline;
  text-decoration-thickness: 2px; 
  text-decoration-color: darkgray;
  font-size: 23px;
}

.requestTable {
  padding: 20px;
}
</style>

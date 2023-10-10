<script setup>
import {ref, onMounted, computed} from 'vue'
import RequestServices from '@/services/RequestServices'; 

const requests = ref([]);
const getRequests = async () => {
    try {
        const response = await RequestServices.getAll();
        requests.value = response.data;
        console.log(requests.value);
        sortRequests();
    } catch (error) {
        console.error('Error al obtener las solicitudes:', error);
    }
};
onMounted(() => {
    getRequests();
});

const sortRequests = () => {
    requests.value.sort((a, b) => {
        if (a.userId && b.userId && a.userId.schoolID && b.userId.schoolID) {
            if (a.userId.schoolID.schoolName !== b.userId.schoolID.schoolName) {
                return a.userId.schoolID.schoolName.localeCompare(b.userId.schoolID.schoolName);
            }

            if (a.userId.username !== b.userId.username) {
                return a.userId.username.localeCompare(b.userId.username);
            }
        const dateA = new Date(a.startDate);
        const dateB = new Date(b.startDate);

        if (dateA !== dateB) {
            return dateA - dateB;
        } else {
            return a.userId.username.localeCompare(b.userId.username);
        }
    }
    });
};

const isRequestWithStatus1 = (request) => {
  return request.status === 1;
};

const filteredRequests = computed(() => {
  return requests.value.filter((request) => isRequestWithStatus1(request));
});



</script>
<template>
    <v-card class="requestList">
      <v-card-title>Listado de Solicitudes</v-card-title>
      <v-table class="requestTable">
        <thead>
          <th> Nombre Empleado </th>
          <th> Fecha de solicitud </th>
          <th> Escuela </th> 
         
        </thead>
        <tbody v-for="(request, index) in filteredRequests" :key="request.Id">
          <tr>
          <td>{{ request.userId ? request.userId.username : 'Usuario no definido' }}</td>
          <td>De {{ request.startDate }} a {{ request.endDate }}</td>
          <td>
            {{ request.userId && request.userId.schoolID
              ? request.userId.schoolID.schoolName
              : 'Escuela no definida' }}
          </td>
          <td>
            <RouterLink v-if="request.userId" :to="{ name: 'HrReview', props: { userId: request.Id } }">Revisar</RouterLink>
            </td>
          </tr>
        </tbody>
      </v-table>
    </v-card>
  </template>
  


<style scoped>
.requestList{
    margin-top: 50px;
    color: orangered;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.5);
}

.requestTable{
    
    padding:20px;
}

</style>
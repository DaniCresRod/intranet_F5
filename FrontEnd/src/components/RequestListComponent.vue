<script setup>
import { ref, onMounted, computed } from 'vue';
import RequestServices from '@/services/RequestServices';

const requests = ref([]);
const { filterBySchoolId, schoolId } = defineProps(['filterBySchoolId', 'schoolId']); 
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

onMounted(() => {
  getRequests(); 
});

const filteredRequests = computed(() => {
  if (filterBySchoolId && schoolId) {
    return requests.value.filter((request) => {
      return isRequestWithStatus1(request) && request.userId.id && request.userId.schoolID.id === schoolId;
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
        <tr v-for="(request) in requests" :key="request.id">
          <td>
            {{ request.userId && request.userId.username && request.userId.userSurName ? (request.userId.username + ' ' + request.userId.userSurName) : '' }}
          </td>
          <td>De {{ request.startDate }} a {{ request.endDate }}</td>
          <td>
            {{ request.userId && request.userId.schoolID && request.userId.schoolID.schoolName ? request.userId.schoolID.schoolName : '' }}
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

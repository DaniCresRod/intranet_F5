<script setup>
import { ref, onMounted, computed } from 'vue';
import RequestServices from '@/services/RequestServices';

const requests = ref([]);
const { filterBySchoolId, schoolId } = defineProps(['filterBySchoolId', 'schoolId']); 

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


const filteredRequests = computed(() => {
  if (filterBySchoolId && schoolId) {
    // Filtra las solicitudes por estado igual a 1 y por schoolID del usuario actual
    return requests.value.filter((request) => {
      return isRequestWithStatus1(request) && request.userId.id && request.userId.schoolID.id === schoolId;
    });
  } else {
    // En otras vistas, no apliques el filtro
    return requests.value.filter((request) => isRequestWithStatus1(request));
  }
});

const sortRequests = () => {
  requests.value.sort((a, b) => {
    if (a.userId && b.userId && a.userId.schoolID && b.userId.schoolID) {
      const usernameA = a.userId.username;
      const usernameB = b.userId.username;

      if (usernameA === usernameB) {
        return usernameA.localeCompare(usernameB);
      } else {
        const dateA = new Date(a.startDate);
        const dateB = new Date(b.startDate);
        return dateA - dateB;
      }
    } else {
      return 0;
    }
  });
};

const isRequestWithStatus1 = (request) => {
  return request.status === 1;
};

const approveRequest = async (id) => {
  await RequestServices.updateUserRequestStatus(id, 2);
  console.log('Solicitud aprobada');
};

const rejectRequest = async (id) => {
  await RequestServices.updateUserRequestStatus(id, 3);
  console.log('Solicitud rechazada');
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
        <tr v-for="(request) in filteredRequests" :key="request.Id">
          <td>{{ request.userId ? (request.userId.username + ' ' + request.userId.userSurName) : '' }}</td>
          <td>De {{ request.startDate }} a {{ request.endDate }}</td>
          <td>{{ request.userId && request.userId.schoolID ? request.userId.schoolID.schoolName : '' }}</td>
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
}

.requestTable {
  padding: 20px;
}
</style>

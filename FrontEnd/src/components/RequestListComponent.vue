<script setup>
import {ref, onMounted} from 'vue'
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
        if (a.userId.schoolID.schoolName !== b.userId.schoolID.schoolName) {
            return a.userId.schoolID.schoolName.localeCompare(b.userId.schoolID.schoolName);
        }

        if (a.userId.userName !== b.userId.userName) {
            return a.userId.userName.localeCompare(b.userId.userName);
        }

        const dateA = new Date(a.startDate);
        const dateB = new Date(b.startDate);

        if (dateA !== dateB) {
            return dateA - dateB;
        } else {
            return a.userId.userName.localeCompare(b.userId.userName);
        }
    });
};
</script>

<template>
    <v-card class="requestList">
        <v-card-title>Listado de Solicitudes</v-card-title>
            <v-table class="requestTable">
            <thead>
                <th> Escuela </th>
                <th> Nombre Empleado </th>
                <th> Fecha de solicitud </th>
            </thead>
            <tbody v-for="(request, index) in requests" :key="request.id">
                <tr v-if="index === 0 || request.userId.userName !== requests[index - 1].userId.userName">
                    <td>{{ request.userId.schoolID.schoolName }}</td>
                    <td>{{ request.userId.userName }}</td>
                </tr>
                <tr>
                    <td></td> 
                    <td></td> 
                    <td>De {{ request.startDate }} a {{ request.endDate }}</td> 
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
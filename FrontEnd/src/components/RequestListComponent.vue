<script setup>
import RequestService from '@/services/RequestService'; 

const requests = ref([]);

const fetchRequests = async () => {
    try {
        const response = await RequestService.getAllRequests();
        requests.value = response.data;
    } catch (error) {
        console.error('Error al obtener las solicitudes:', error);
    }
};

onMounted(() => {
    fetchRequests();
});
</script>

<template>
    <h2>Listado de solicitudes</h2>
    <ul>
        <li v-for="request in requests" :key="request.id">
            <p>Empleado: {{ request.userId.name }}</p>
            <p>Fecha de inicio: {{ request.startDate }}</p>
            <p>Fecha de fin: {{ request.endDate }}</p>
        </li>
    </ul>
    <!--            <button @click="$emit('delete', request)">Eliminar solicitud</button>-->
</template>

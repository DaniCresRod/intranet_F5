<template>
  <div>
    <h2>Historial de solicitudes</h2>

    <div class="grid">
      <div>
        <h3>Solicitudes creadas</h3>
        <v-card v-for="(log, index) in createdLogs" :key="index" class="mb-5 pa-3">
          <v-card-title class="headline">{{ log.logDate }}</v-card-title>
          <v-card-text>{{ log.logText }}</v-card-text>
          <p v-if="acceptedLogsById[log.logId]">Aceptado en {{ acceptedLogsById[log.logId].logDate }}</p>
          <p v-else-if="rejectedLogsById[log.logId]">Rechazado en {{ rejectedLogsById[log.logId].logDate }}</p>
          <p v-else>No ha sido aprobado ni rechazado</p>
        </v-card>
      </div>
    </div>
  </div>
</template>
<script setup>
import { ref, onMounted } from 'vue';
import LogsService from '../services/LogsService';

const logs = ref([]);
const acceptedLogs = ref([]);
const rejectedLogs = ref([]);
const createdLogs = ref([]);
const acceptedLogsById = ref({});
const rejectedLogsById = ref({});

onMounted(async () => {
  try {
    const response = await LogsService.getAll();
    console.log(response.data);
    logs.value = response.data;
    acceptedLogs.value = logs.value.filter(log => log.logText.includes('Se ha aceptado'));
    rejectedLogs.value = logs.value.filter(log => log.logText.includes('Se ha rechazado'));
    createdLogs.value = logs.value.filter(log => log.logText.includes('Se crea la petición'));

    // Crear diccionarios para mapear logs por ID
    acceptedLogs.value.forEach(log => {
      acceptedLogsById.value[log.logId] = log;
    });
    rejectedLogs.value.forEach(log => {
      rejectedLogsById.value[log.logId] = log;
    });
  } catch (error) {
    console.error(error);
  }
});
</script>
<style scoped>
.grid {
  display: grid;
  grid-template-columns: repeat(1, 1fr);
  gap: 20px;
}

.v-card {
  margin-top: 20px;
}
</style>
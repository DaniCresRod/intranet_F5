
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

const getStatusText = (log) => {
  if (acceptedLogs.value.find(acceptedLog => acceptedLog.requestId === log.requestId)) {
    return 'Aprobada';
  } else if (rejectedLogs.value.find(rejectedLog => rejectedLog.requestId === log.requestId)) {
    return 'Rechazada';
  } else {
    return 'Pendiente';
  }
};
</script>

<template>
  <div class="grid">
    <div>
      <h3>Solicitudes creadas</h3>
      <v-card v-for="(log, index) in createdLogs" :key="index" class="mb-5 pa-3">
        <v-card-title class="headline">{{ log.logText }}</v-card-title>
        <v-card-text>{{ log.logDate }}</v-card-text>
      </v-card>
    </div>

    <div>
      <h3>Estado de las solicitudes</h3>
      <v-card v-for="(log, index) in createdLogs" :key="index" class="mb-5 pa-3">
        <v-card-title class="headline">{{ getStatusText(log) }}</v-card-title>
        <v-card-text>{{ log.logDate }}</v-card-text>
      </v-card>
    </div>

  </div>
</template>
<style scoped>
.grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

.v-card {
  margin-top: 20px;
}
</style>
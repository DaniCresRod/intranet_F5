<script setup>
import { ref, onBeforeMount } from 'vue';
import { defineProps } from 'vue';
import RequestService from '../services/RequestR';

const props = defineProps({
  id: {
    type: Number,
    required: true
  }
});

const data = ref(null);
const loading = ref(true);
const diffDays = ref(0); 
async function getHoldData() {
  loading.value = true;
  const response = await RequestService.getHolidaysData(props.id);
  data.value = await response.data;
  console.log(response);
  const startDate = new Date(data.value.userRequests[0].startDate);
  const endDate = new Date(data.value.userRequests[0].endDate);
  const diffTime = Math.abs(endDate - startDate);
  diffDays.value = Math.ceil(diffTime / (1000 * 60 * 60 * 24))+1; 
  
  console.log(`La solicitud abarca ${diffDays.value} días.`);
  
  loading.value = false;
}

onBeforeMount(getHoldData);

async function approveRequest() {
  await RequestService.updateUserRequestStatus(props.id, 2);
  console.log("solicitud aprobada");
}

async function rejectRequest() {
  await RequestService.updateUserRequestStatus(props.id, 3);
  console.log("solicitud rechazada");

}
</script>

<template>
  <div>
    <v-card>
      <v-card-text>
        <div v-if="data" class="container">
          <div class="item">
            <p>Fecha de solicitud:</p>
            <p>{{ data.userRequests[0].startDate }}</p>
          </div>
          <div class="item">
            <p>La solicitud abarca {{ diffDays }} días.</p>
          </div>
          <div class="item">
            <p>Solicitud de vacaciones desde el {{ data.userRequests[0].startDate }}</p>
            <p>Hasta el {{ data.userRequests[0].endDate }}</p>
          </div>
        </div>
      </v-card-text>
    </v-card>
    <div class="button-container">
      <v-btn class="btnAcepted"  @click="approveRequest">Aprobar</v-btn>
      <v-btn class="btnRefuse"  @click="rejectRequest">Rechazar</v-btn>
    </div>
  </div>
</template>

<style scoped>
.container {
  display: flex;
  justify-content: space-between;
}

.item {
  flex-basis: 30%;
}

.button-container {
  display: flex;
  justify-content: center;
  gap: 20px;
  margin-top: 20px;
}
.btnAcepted, .btnRefuse {
    width: 8.5625rem;
    height: 2.375rem;
    cursor: pointer;
    font-weight: 600;
    border-radius: 1.875rem;
    border: 1px solid #616161;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
    margin: 1.5rem auto;
}

.btnAcepted :hover {
    width: 8.5625rem;
    height: 2.375rem;
    cursor: pointer;
    border-radius: 1.875rem;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
    border: 1px solid #616161;
    background: #C8EB66;}
.btnRefuse :hover {
    width: 8.5625rem;
    height: 2.375rem;
    cursor: pointer;
    border-radius: 1.875rem;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
    border: 1px solid #616161;
    background: #F98787;}
</style>

<script setup>
import { ref, onBeforeMount } from 'vue';
import { defineProps } from 'vue';
import CardInfoService from '../services/CardInfoService';

const props = defineProps({
  id: {
    type: Number,
    required: true
  }
});

const data = ref(null);
const loading = ref(true);

async function getWorkerData() {
  loading.value = true;
  const response = await CardInfoService.getWorkerData(props.id);
  data.value = await response.data;
  console.log(response.data)
  loading.value = false;
}

onBeforeMount(getWorkerData);

</script>
<template>
  <h2>    Autorizando a</h2>
  <div class="cardInfo">
    <v-card class="custom-card">
      <div class="card-content">
      <div class="card-image-container">
        <v-avatar size="150">
          <v-img class="card_info_img" src="/img_prueba.jpg"/>
        </v-avatar>
      </div>
      <div class="card-info-content">
    <v-card-title class="card_data" v-if="data">
      <h1>{{ data.userName }}</h1>
      <div class="info-line">
      <p> {{ data.userType }}</p> 
      <p>School Name: {{ data.schoolID.schoolName }}</p>
      </div>
      <div class="info-line">
      <p>Start Date: {{ data.userStartDate }}</p> 
    </div>
      
    </v-card-title>
    
    </div>
    </div>
  </v-card>
</div>
</template>

<style scoped>
.card_info_img {
  border-radius: 50px;
  border: 1px solid var(--orange);
}

.cardInfo {
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.3);
  width: 59rem;
  height: auto;
  display: flex;
  position: relative;
  background-color: #D9D9D9;
  border: 2px solid var(--orange);
}

.card-content {
  display: flex;
  flex-direction: row;
}

.card-image-container {
  width: 150px; /* Ancho de la imagen */
  height: 150px; /* Altura de la imagen */
  border-radius: 50%;
  margin-right: 20px; /* Ajusta el espacio a la derecha de la imagen */
}
.card-data{
  display: inline-flex;
}
.info-line {
  flex-grow: 1;
  display: flex;
  flex-direction: column;
}

</style>
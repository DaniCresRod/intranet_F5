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
  <v-card class="custom-card">
    <div class="card-content">
      <div class="card-image-container">
        <v-avatar class="card_avatar" size="150">
          <v-img class="card_info_img" src="/img_prueba.jpg" />
        </v-avatar>
      </div>
      
      <v-card-title class="card_data" v-if="data">
        <div class="card-info-content">
          <h2>{{ data.userName }}</h2>
          <div class="info-line">
            <p> {{ data.userType }}</p>
            <p>{{ data.schoolID.schoolName }}</p>
          </div>
          <div class="info-line">
            <p>Fecha de inicio: {{ data.userStartDate }}</p>
          </div>
        </div>
      </v-card-title>
    </div>
  </v-card>
</template>

<style scoped>
* {
  font-family: Poppins;
}

.card_avatar {
  border: 2px solid var(--orange);
  position: absolute;
  left: -75px;
  top: -10px;
  width: 150px;
  height: 150px;
  z-index: 500;
  border-radius: 50%;
  border: 2px solid var(--orange);
}



.card-content {
  display: flex;
  flex-direction: row;
}

.card-image-container {
  width: 150px;
  height: 150px;
  border-radius: 50%;
  margin-right: 20px;
  position: relative;
  /* Ajusta el posicionamiento relativo */
}

.card-data {
  padding-left: 5rem;
  display: inline-flex;
  flex-wrap: wrap;
}

.info-line {
  flex-grow: 1;
  display: flex;
  flex-direction: row;
  gap: 10rem;
  padding: 1rem 7rem 1rem 5rem;
  padding-right: 15rem;
}

.custom-card {
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.3);
  width: 59rem;
  height: auto;
  display: flex;
  position: relative;
  background-color: #D9D9D9;
  border: 2px solid var(--orange);
  z-index: -500;
  margin: 50px;
}
</style>
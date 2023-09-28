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
  <div class="cardInfo">
    <v-card>
      <div class="card-content">
        <div class="text-content">
          <v-card-subtitle v-if="data">
            <p class="info-text"><span class="bold-text">Nombre:</span> {{ data.userName }}</p>
            <p class="info-text"><span class="bold-text">Puesto:</span> {{ data.userType }}</p> 
            <p class="info-text"><span class="bold-text">D.N.I:</span> {{ data.userNif }}</p> 
            <p class="info-text"><span class="bold-text">Fecha de nacimiento:</span> {{ data.userBirthDate }}</p>
            <p class="info-text"><span class="bold-text">E-mail:</span> {{ data.userEmail }}</p>
            <p class="info-text"><span class="bold-text">Fecha de inicio:</span> {{ data.userStartDate }}</p>  
          </v-card-subtitle>
        </div>
        <div class="image-content">
          <v-avatar size="180"> 
            <v-img class="card_info_img" src="/img_prueba.jpg"/>
          </v-avatar>
        </div>
      </div>
    </v-card>
  </div>
</template>

<style scoped>
*{
  font-family: Poppins;}
.card_info_img {
  border-radius: 50px;
  width: 100%;
  height: auto;
}

.bold-text {
  font-weight: bold;
}

.info-text {
  line-height: 2;
}

.cardInfo {
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.3);
  width: 45rem;
  height: 75%; 
}

.card-content {
  display: flex;
  padding-left: 10%; 
  width: 100%; 
  margin-bottom: 10%; /* Margen abajo */
  margin-top: 10%; /* Margen arriba */
}

.text-content {
  flex: 2;
}

.image-content {
  display: flex;
  justify-content: center;
  align-items: center;
  padding-right: 15%;
}

@media (max-width: 600px) {
.card-content {
    flex-direction: column;
    align-items: center;
    padding: 5%;
}

.text-content, .image-content {
    width: 100%;
    text-align: center;
}
}
</style>

<script setup>
import { ref, onBeforeMount } from 'vue';
import { defineProps } from 'vue';
import CardInfoService from '../services/CardInfoService';
import axios from 'axios';

const props = defineProps({
  id: {
    type: Number,
    required: true
  }
});

const data = ref(null);
const loading = ref(true);
const userImage = ref(""); // Inicializamos userImage

function getUserImageSrc(base64ImageData) {
  return `data:image/jpeg;base64,${base64ImageData}`;
}


async function getWorkerData() {
  loading.value = true;
  const response = await CardInfoService.getWorkerData(props.id);
  data.value = await response.data;
  userImage.value = data.value ? data.value.userImage : ""; // Asignamos userImage solo si data.value no es nulo
  console.log(response.data);
  loading.value = false;
}

onBeforeMount(() => {
  getWorkerData();
})

</script>

<template>
  <div class="cardInfo">
    <v-card>
      <div class="card-content">
        <div class="text-content">
          <v-card-subtitle v-if="data">
            <p class="info-text"><span class="bold-text">Nombre:</span> {{ data.username }}</p>
            <p class="info-text"><span class="bold-text">Puesto:</span> {{ data.userType }}</p> 
            <p class="info-text"><span class="bold-text">D.N.I:</span> {{ data.userNif }}</p> 
            <p class="info-text"><span class="bold-text">Fecha de nacimiento:</span> {{ data.userBirthDate }}</p>
            <p class="info-text"><span class="bold-text">E-mail:</span> {{ data.userEmail }}</p>
            <p class="info-text"><span class="bold-text">Fecha de inicio:</span> {{ data.userStartDate }}</p>
            <p class="info-holidays">*Revisa <a href="..\assets\Thymeleaf.pdf" download="Thymeleaf.pdf">aquí</a> la política de vacaciones.</p> 
          </v-card-subtitle>
        </div>
        <div class="image-content">
          <v-avatar size="180"> 
            <v-img class="card_info_img" v-if="data" :src="getUserImageSrc(data.userImage)" />

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
  margin-top: 3rem;
  margin-bottom: 4rem; 
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

.info-holidays{
  margin-top: 2rem;
  margin-left: 1rem;
  font-style: italic;
  color: black;

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

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
    <v-card-title>
      <v-avatar size="150">
        <v-img class="card_info_img" src="/img_prueba.jpg"/>
      </v-avatar>
    </v-card-title>
    <v-card-subtitle v-if="data">
        <h1>User Name: {{ data.userName }}</h1>
        <p>User Type: {{ data.userType }}</p> 
        <p>Start Date: {{ data.userStartDate }}</p> 
        <p>School Name: {{ data.schoolID.schoolName }}</p> 
      </v-card-subtitle>
  </v-card>
</div>
</template>

<style scope>
.card_info_img{border-radius: 50px;}

.cardInfo{
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.3);
}

</style>

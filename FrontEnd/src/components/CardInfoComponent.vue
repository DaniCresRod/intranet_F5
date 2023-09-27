<script setup>
import { ref } from 'vue';
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

getWorkerData();

</script>
<template>
  <v-card>
    <h1>prueba</h1>
    <v-card-title>
      <v-avatar size="60">
        <!-- <v-img :src="data.value.image" /> -->
        <v-img class="card_info_img" src="/img_prueba.jpg"/>
      </v-avatar>
      <v-card-subtitle>
        <h1>Subtitulo</h1>
        {{ data && data.userName }}
      </v-card-subtitle>
    </v-card-title>
    <!-- Resto del código -->
  </v-card>
</template>


<style scope>
.card_info_img{border-radius: 50px;
}
</style>


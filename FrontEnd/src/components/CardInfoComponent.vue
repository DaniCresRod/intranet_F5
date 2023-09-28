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
  <v-card>
    <v-card-subtitle v-if="data">
        <h1>{{ data.userName }}</h1>
      </v-card-subtitle>
    <v-card-title>
      <v-avatar size="150">
        <v-img class="card_info_img" src="/img_prueba.jpg"/>
      </v-avatar>
    </v-card-title>
  </v-card>
</template>


<style scope>
.card_info_img{border-radius: 50px;
}
</style>

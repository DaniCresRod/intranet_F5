<script setup>
import { ref, onBeforeMount } from 'vue';
import { defineProps } from 'vue';
import RequestService from '../services/CardInfoService';

const props = defineProps({
  id: {
    type: Number,
    required: true
  }
});

const data = ref([]);
const years = ref([]);
const loading = ref(true);
const diffDays = (startDate, endDate) => {
  const start = new Date(startDate);
  const end = new Date(endDate);
  
  if (start.getTime() === end.getTime()) {
    return 0;
  } else {
    const diffTime = Math.abs(end - start);
    return Math.ceil(diffTime / (1000 * 60 * 60 * 24)) + 1;
  }
};

async function getHoldData() {
  loading.value = true;
    const response = await RequestService.getWorkerData(props.id);
    data.value = await response.data.userRequests;
    years.value = [...new Set(data.value.map(item => new Date(item.startDate).getFullYear()))].map(year => ({ year, show: false }));
    loading.value = false;
}

onBeforeMount(getHoldData);
</script>

<template>
  <div class="container">
    <v-card class="outer-item">
      <v-card-text>
        <v-card class="item" v-for="yearObj in years" :key="yearObj.year">
          <v-card-text>
            <button @click="yearObj.show = !yearObj.show">{{ yearObj.year }} ▼</button>
            <transition name="fade">
              <div v-if="yearObj.show">
                <div v-for="item in data.filter(item => new Date(item.startDate).getFullYear() === yearObj.year)" :key="item.id" class="card-content">
                  <div>
                    <p>Fecha de solicitud:</p>
                    <p>{{ item.startDate }}</p>
                  </div>
                  <div :class="{ 'status-1': item.status === 1, 'status-2': item.status === 2, 'status-3': item.status === 3 }" class="status">
                    <p>Días solicitados: {{ diffDays(item.startDate, item.endDate) }} </p>
                  </div>
                  <div>
                    <p>Solicitud de vacaciones desde el {{ item.startDate }}</p>
                    <p>Hasta el {{ item.endDate }}</p>
                  </div>
                </div>
              </div>
            </transition>
          </v-card-text>
        </v-card>
      </v-card-text>
    </v-card>
  </div>
</template>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
}

.outer-item {
  margin-bottom: 20px;
}

.item {
  margin-bottom: 20px;
  margin-top:10px; 
}

.card-content {
  display: flex;
  justify-content: space-between;
  padding: 10px; 
}

.card-content > div {
  margin-right: 50px;
}

.card-content > div:first-child p:first-child {
  margin-top: 8px; 
}

.status {
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 15px;
}

.status-1 {
  background-color: rgba(217, 217, 217, 0.6);
}

.status-2 {
  background-color: rgba(62, 187, 12, 0.6);
}

.status-3 {
  background-color: rgba(251, 85, 85, 0.6);
}

.fade-enter-active, .fade-leave-active {
   transition: opacity .5s;
}
.fade-enter, .fade-leave-to{
   opacity: 0;
}
</style>

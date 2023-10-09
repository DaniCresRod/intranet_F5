<script setup>
import CardInfoWorkerComponent from '../components/CardInfoWorkerComponent.vue';
import CalendarRequestComponent from '../components/CalendarRequestComponent.vue';
import VacationHistoryComponent from '../components/VacationHistoryComponent.vue';
import { ref, onBeforeMount } from 'vue'

const userId=ref();

onBeforeMount(() => {
  userId.value=window.localStorage.getItem("myUser_Key");
})
</script>

<template>
  <CardInfoWorkerComponent  :id="parseInt(userId)"/>
  <VacationHistoryComponent  :id="parseInt(userId)"/>
  <CalendarRequestComponent  :id="parseInt(userId)"/>
</template>

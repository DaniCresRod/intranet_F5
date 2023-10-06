<script setup>
import CardInfoWorkerComponent from '../components/CardInfoWorkerComponent.vue';
import CalendarComponent_Request from '../components/CalendarComponent_Request.vue';
import VacationHistoryComponent from '../components/VacationHistoryComponent.vue';
import { ref, onBeforeMount } from 'vue'

const userId=ref();

onBeforeMount(() => {
  userId.value=window.localStorage.getItem("myUser_Key");
})

</script>

<template>
  <CardInfoWorkerComponent :id="userId"/>
  <VacationHistoryComponent :id="userId"/>
  <CalendarComponent_Request :id="userId"/>
</template>

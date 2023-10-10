<script setup>
import CalendarComponent_Review from '../components/CalendarComponent_Review.vue';
import CardInfoComponent from '../components/CardInfoComponent.vue';
import RequestListComponent from '../components/RequestListComponent.vue';
import { ref, onBeforeMount } from 'vue'

const userId=ref();

onBeforeMount(() => {
  userId.value=window.localStorage.getItem("myUser_Key");
})

</script>
<template>
    <CardInfoComponent :id="parseInt(userId)"/>
    <RequestListComponent/>
    <CalendarComponent_Review :id="parseInt(userId)"/>
</template>
<script setup>
import RequestReviewComponent from '../components/RequestReviewComponent.vue';
import CalendarComponent_Review from '../components/CalendarComponent_Review.vue';
import { ref, onBeforeMount } from 'vue'

const userId=ref();

onBeforeMount(() => {
  userId.value=window.localStorage.getItem("myUser_Key");
})


</script>
<template>
    <RequestReviewComponent :id="parseInt(userId)"/>
    <CalendarComponent_Review/>
</template>
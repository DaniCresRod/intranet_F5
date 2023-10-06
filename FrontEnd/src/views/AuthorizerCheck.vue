<script setup>
import RequestReviewComponent from '../components/RequestReviewComponent.vue';
import { ref, onBeforeMount } from 'vue'

const userId=ref();

onBeforeMount(() => {
  userId.value=window.localStorage.getItem("myUser_Key");
})

</script>

<template>
  <div>
    <RequestReviewComponent :id="parseInt(userId)"/>
  </div>
</template>

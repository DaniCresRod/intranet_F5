<script setup>
import RequestListComponent from '../components/RequestListComponent.vue';
import CardInfoComponent from '../components/CardInfoComponent.vue';
import { ref, onBeforeMount } from 'vue'

const userId=ref();

onBeforeMount(() => {
  userId.value=window.localStorage.getItem("myUser_Key");
})

</script>

<template>
  <CardInfoComponent :id="parseInt(userId)"/>
  <RequestListComponent/>
</template>

<script setup>
import { RouterView } from 'vue-router'
import NavbarComponent from './components/NavbarComponent.vue';
// import FooterComponent from './components/FooterComponent.vue';
</script>

<template>
  <NavbarComponent />
  <main>
    <RouterView />
  </main>
  <FooterComponent />
  
</template>

<style scoped>

</style>

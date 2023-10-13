<script setup>
</script>

<template>
    <router-link to="/hrGeneral">
        <button class="back">Atrás</button>
    </router-link>
</template>

<style scoped>
.back {
    width: 8.5625rem;
    height: 2.375rem;
    flex-shrink: 0;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
    border-radius: 1.875rem;
    border: 1px solid #333;
    background: #FBF5F5;
    padding: 1.5rem;
    color:black;
    margin: 4rem auto;
    display: flex;
    justify-content: center;
    align-items: center; 
    font-weight: bolder;
    }

.back:hover {
    background-color: #333;
    color: #FBF5F5;
}
a {
    text-decoration: none;
    }


</style>
<script setup>
import CardInfoComponent from '../components/CardInfoComponent.vue'
</script>

<template>
    <CardInfoComponent />
    <div>
        <div class="container-options">
            <div class="item">Revisar solicitudes</div>
            <div class="item">Crear Perfil</div>
            <div class="item">Crear Escuela</div>
            <div class="item">Administrar Perfiles</div>
            <div class="item">Historial de vacaciones</div>
        </div>
    </div>
</template>
  
<style scoped>
.container-options {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
}

.item {
    width: calc(50% - 10px);
    /* El 10px es el espacio entre elementos */
    height: 5.625rem;
    background-color: #FBF5F5;
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    font-family: Poppins;
    font-size: 0.9375rem;
    font-weight: 400;
    border: 1px solid var(--orange);
    margin-bottom: 10px;
    box-sizing: border-box;
}

.item:last-child {
    margin-left: auto;
    margin-right: auto;
}
</style>

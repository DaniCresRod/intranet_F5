<script setup>
import CardInfoComponent from '../components/CardInfoComponent.vue';

</script>

<template>
    <CardInfoComponent />
   
    <div>
        <div class="container-options">
            <router-link to="/" class="item"><button>Revisar solicitudes</button></router-link>
            <router-link to="/createuser" class="item"><button>Crear Perfil</button></router-link>
            <router-link to="/createschool" class="item"><button>Crear Escuela</button></router-link>
            <router-link to="/edituser" class="item"><button>Administrar Perfiles</button></router-link>
            <router-link to="/" class="item"><button>Historial de vacaciones</button></router-link>
        </div>
    </div>
</template>

<style scoped>
.container-options {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
    margin: 5rem;
}

.item {
    width: calc(50% - 4rem);
    height: 5.625rem;
    background-color: #FBF5F5;
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    font-family: Poppins;
    font-size: 0.9375rem;
    font-weight: 600;
    border: 1px solid var(--orange);
    margin-bottom: 4rem;
    box-sizing: border-box;
    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.3);
    text-decoration: none;
    color: black;




}

.item:last-child {
    margin-left: auto;
    margin-right: auto;
}

</style>

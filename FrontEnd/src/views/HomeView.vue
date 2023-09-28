<script setup>
import EmployeeViewVue from './EmployeeView.vue';
</script>

<template>
<div id="banner">
        <img src="/img_banner.png" alt="networking">
        <h1>¡Somos <span class="destacat">ágiles, creativos/as</span> y comprometidos/as!</h1>

    </div>

    <div class="login_form">
        <h2>Acceso Usuarios</h2>
        <form @submit.prevent="submitForm">
            <div class="form-group">
                <label for="usuario"></label>
                <input type="text" id="usuario" v-model="user" required :placeholder="'USUARIO'" class="custom-placeholder">
            </div>
            <div class="form-group">
                <label for="contrasena"></label>
                <input type="password" id="password" v-model="password" required :placeholder="'CONTRASEÑA'" class="custom-placeholder">
            </div>
            <button class="button" type="submit">LOGIN</button>
          </form>
    </div>

    <EmployeeViewVue/>
</template>

<style scoped>
#banner {
    width: 100%;
    background-color: var(--orange);
    padding: 0 0 3% 0;
}

#banner img {
    background-size: cover;
}

h1 {
    text-align: center;
    font-family: Poppins;
    font-size: 2.5rem;
    font-weight: normal;
    margin-top: 2%;
}

.destacat {
    font-weight: bold;
}


.login_form {
    width: 29.75rem;
    height: 20.25rem;
    background-color: var(--orange);
    margin: 5% auto;
    border-radius: 0.625rem;
}

h2 {
    text-transform: uppercase;
    color: #fff;
    text-align: center;
    padding-top: 4%;
}

input[type="text"],
input[type="password"] {
    width: 60%;
    padding: 5px;
    border: 1px solid #D5D8DE;
    font-size: 16px;
    background-color: #fff;
    width: 20.4375rem;
    height: 2.75rem;
}

.form-group {
    padding-top: 6%;
    text-align: center;
}

.button {
    background-color: #fff;
    margin-top: 11%;
    margin-left: 35%;
    justify-content: center;
}

.custom-placeholder::placeholder {
    color: #323941;
    font-family: PT Mono;
    font-size: 1rem;
    font-style: normal;
    font-weight: 400;
    line-height: normal;
    padding: 1%;
}

.login_form{
    width: 29.75rem;
    height: 20.25rem;
    background-color: var(--orange);

}
</style>

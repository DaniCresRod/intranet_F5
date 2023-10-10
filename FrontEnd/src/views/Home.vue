<script setup>
import { ref } from 'vue'
import loggear from "../services/LogInService"
import router from '../router/index'
import { getToken } from "../services/TokenService";
import axios from "axios";

window.localStorage.setItem("myToken_Key", "");
window.localStorage.setItem("myUser_Key", "");

const user=ref("");
const password=ref("");

const payload=ref({
    "username": "",
    "password": ""
});

async function sendInfo(){

    payload.value.username=user.value;
    payload.value.password=password.value;
    console.log(payload.value);
    const token=await loggear.doLogIn(payload.value);
    console.log(token);
    console.log(getToken());  
    console.log(window.localStorage.getItem("myToken_Key"));

    if(token && (window.localStorage.getItem("myUser_Key")>0)){
        console.log("El token se ha traido y guardado en el LocalStorage");
        redirectMe(window.localStorage.getItem("myToken_Key"),window.localStorage.getItem("myUser_Key") );        
    }
    else{
        console.log("No se ha obtenido Token");
    }        
}

// async function redirect(){
//     let myRoute=await loggear.whereToGo();
//         console.log(myRoute);
//         if(myRoute==="Formador"){
//             router.push("/Employee");
//         }
//         else if(myRoute==="Supervisor"){
//             router.push("/Authorizer");
//         }
//         else if(myRoute==="HR"){
//             router.push("/HrGeneral");
//         }  
// }

async function redirectMe(token, id){
    const config={
        headers:{
            "Content-type": "application/json",
            'Authorization': `Bearer ${token}`
        }
    }

    const response=await axios.get(`http://localhost:8080/users/${id}`, config);
    console.log(response.data);

    let myRoute=response.data.userType;

    if(myRoute==="Formador"){
            router.push("/Employee");
        }
        else if(myRoute==="Supervisor"){
            router.push("/Authorizer");
        }
        else if(myRoute==="HR"){
            router.push("/HrGeneral");
        }  
}


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
                <input type="text" id="usuario" v-model="user" required placeholder="USUARIO" class="custom-placeholder">
            </div>
            <div class="form-group">
                <label for="contrasena"></label>
                <input type="password" id="password" v-model="password" required placeholder="CONTRASEÑA" class="custom-placeholder">
            </div>            
                <button class="button" type="submit" @click="sendInfo">LOGIN</button>            
        </form>
    </div>
</template>

<style scoped>
#banner {
    margin-top: -5%;
    width: 100vw;
    background-color: var(--orange);
    padding: 0 0 3% 0;
}

#banner img {
    background-size: cover;
    width: 100vw;
    height: auto;
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

@media screen and (max-width: 768px) {
#banner {
        padding: 0 0 1.5% 0;
}

#banner img {
        width: 100%;
}

h1 {
        font-size: 1.5rem;
        margin-top: 1%;
}

.login_form {
        width: 80%;
        height:auto;
        margin :2% auto;
}

input[type="text"],
input[type="password"] {
        width :80%;
        height :2rem;
}

.button{
        margin-top :5%;
        margin-left :30%;
}
}
</style>

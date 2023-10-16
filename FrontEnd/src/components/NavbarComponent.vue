<script setup>
import { RouterLink } from 'vue-router';
import { ref, onMounted } from 'vue'

const userId = ref();
userId.value = window.localStorage.getItem("myUser_Key");

function LogOut() {
  window.localStorage.clear();
}

//Funcion que define lo que pasara si hay cambios
function handleMutations(mutationsList) {
  for (const mutation of mutationsList) {
    if (mutation.type === 'childList') {
      //console.log('Se ha producido un cambio en el DOM.');
      userId.value = window.localStorage.getItem("myUser_Key");
    }
  }
}

onMounted(() => {
  // Crea una instancia de MutationObserver con la función de manejo
  const observer = new MutationObserver(handleMutations);

  // Configura las opciones para observar los cambios que te interesan
  const options = { childList: true, attributes: false, subtree: true };

  // Inicia la observación en el documento o elemento que desees
  observer.observe(document.body, options);
});

</script>

<template>
  <header class="header">
    <ul class="iconsGroup">
      <li>
        <a href="https://www.linkedin.com/company/factor%C3%ADaf5/" target="_blank">
          <img src="/public/logo-linkedin.png">
        </a>
      </li>
      <li>
        <a href="https://twitter.com/i/flow/login?redirect_after_login=%2Ffactoriaf5" target="_blank">
          <img src="/public/logo-twitter.png">
        </a>
      </li>
      <li>
        <a href="https://www.instagram.com/factoria_f5/" target="_blank">
          <img src="/public/logo-instagram.png">
        </a>
      </li>
      <li>
        <a href="https://www.youtube.com/channel/UCazHbN7ChOJxRXW0-K1zczw" target="_blank">
          <img src="/public/logo-youtube.png">
        </a>
      </li>
      <li>
        <a href="https://www.facebook.com/factoriaf5/" target="_blank">
          <img src="/public/logo-facebook.png">
        </a>
      </li>
    </ul>
    <nav class="navbar">
      <img src="/public/logo_f5.png" class="img-fluid navbar__img" alt="Logo Factoria F5">

      <ul class="navbar__menu">
        <li class="navbar__menu--item">
          <a href="https://factoriaf5.org/" target="_blank">HOME</a>
        </li>
        <li class="navbar__menu--item">
          <a href="https://factoriaf5.org/aprende/" target="_blank">APRENDE</a>
        </li>
        <li class="navbar__menu--item">
          <a href="https://factoriaf5.org/colabora/" target="_blank">COLABORA</a>
        </li>
        <li class="navbar__menu--item">
          <a href="https://factoriaf5.org/somos/" target="_blank">SOMOS</a>
        </li>
        <li class="navbar__menu--item">
          <a href="https://factoriaf5.org/blog/" target="_blank">BLOG</a>
        </li>
        <li class="navbar__menu--item">
          <a href="https://factoriaf5.org/contacto/" target="_blank">CONTACTO</a>
        </li>
        <li v-if="userId !== ''" class="navbar__menu--item" @click="LogOut()">
          <RouterLink to="/">LOGOUT</RouterLink>
        </li>
        <li v-else class="navbar__menu--item">
          <RouterLink to="/">LOGIN</RouterLink>
        </li>

      </ul>
    </nav>
  </header>
  <hr class="underline">
</template>

<style scoped>
.header {
  height: 19vh;
  width: 100vw;
}

hr {
  border: none;
  height: 2px;
  background-color: #d9d9d9;
  width: 100%;
}

.iconsGroup {
  display: flex;
  justify-content: end;
  align-items: center;
  gap: 1.5rem;
  padding: 1rem;
  margin: 0 15rem;
  list-style: none;
}

.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem;
  margin: 0 15rem;
  transition: 0.5s;
}

.navbar__img {
  width: 9vw;
  margin-left: -11rem;
  margin-right: 8vw;
  margin-top: -3.5vh;
}

.navbar__menu {
  display: flex;
  list-style: none;
  gap: 3rem;
}

.navbar__menu--item {
  font-size: 1.2rem;
  font-weight: 600;
  letter-spacing: 0.1rem;
  display: table-cell;
  position: relative;
  padding: 15px 0;
}

.navbar__menu--item a {
  color: black;
  text-transform: uppercase;
  text-decoration: none;
  letter-spacing: 0.15em;
  display: inline-block;
  padding: 15px 20px;
  position: relative;
  transition: 0.3s;
}

.navbar__menu--item a:after {
  background: none repeat scroll 0 0 var(--orange);
  bottom: 0;
  content: "";
  display: block;
  height: 4px;
  left: 50%;
  position: absolute;
  transition: width 0.3s ease 0s, left 0.3s ease 0s;
  width: 0;
}

.navbar__menu--item a:hover {
  color: var(--orange);
}

.navbar__menu--item a:hover:after {
  width: 100%;
  left: 0;
}

@media (max-width: 1000px) {}

@media (max-width: 1500px) {
  .navbar__menu {
    gap: 1rem;
  }

  .navbar__menu--item {
    font-size: 1rem;
  }
}</style>
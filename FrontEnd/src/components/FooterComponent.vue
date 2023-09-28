<script setup>
import { RouterLink } from 'vue-router';
</script>

<template>
  <footer class="footer">
    <h3 class="footer__title">rompemos los códigos</h3>
    <div class="footer__middle">
      <a href="https://factoriaf5.org/" target="_blank"><img src="/public/logo-footer.png" alt="Logo Factoria F5"></a>
      <ul class="footer__iconsGroup">
        <li>
          <RouterLink to="/">
            <img src="/public/logo-linkedin.png">
          </RouterLink>
        </li>
        <li>
          <RouterLink to="/">
            <img src="/public/logo-twitter.png">
          </RouterLink>
        </li>
        <li>
          <RouterLink to="/">
            <img src="/public/logo-instagram.png">
          </RouterLink>
        </li>
        <li>
          <RouterLink to="/">
            <img src="/public/logo-youtube.png">
          </RouterLink>
        </li>
        <li>
          <RouterLink to="/">
            <img src="/public/logo-facebook.png">
          </RouterLink>
        </li>
      </ul>
    </div>

		<div class="footer__links">
      Todos los derechos reservados 2021 |  
      <RouterLink to="/">Home</RouterLink> | 
      <RouterLink to="/">Aprende</RouterLink> | 
      <RouterLink to="/">Colabora</RouterLink> | 
      <RouterLink to="/">Somos</RouterLink> | 
      <RouterLink to="/">Blog</RouterLink> | 
      <RouterLink to="/">Contacto</RouterLink> | 
      <RouterLink to="/">Usuarios</RouterLink>	
    </div>
  </footer>
</template>

<style scoped>
.footer {
  background-color: var(--orange);

  position: absolute;
  bottom: 0;
  width: 100%;
  height: 25rem;
  padding: 2rem;  
  display: flex;
  flex-direction: column;
  align-items: center;
}
.footer__title {
  color: whitesmoke;
  text-transform: uppercase;
  font-size: 3rem;
  font-weight: 700;
  text-align: center;
  padding: 1rem 0;
}
.footer__middle {
  display: flex;
  justify-content: space-around;
  align-items: center;
  gap: 10rem;
  margin-top: 2.5rem;
}
.footer__iconsGroup{
  display: flex;
  justify-content: end;
  align-items: center;
  gap: 1.5rem;
  list-style: none;
}
.footer__links {
  color: whitesmoke;
  margin-top: auto;
}
.footer__links a{
  text-decoration: none;
  color: whitesmoke;
  transition: 0.3s;
}
.footer__links a:hover{
  color: black;
}
</style>
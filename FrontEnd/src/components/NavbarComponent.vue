<script setup>
import { RouterLink } from 'vue-router';

</script>

<template>
  <header class="header">
    <ul class="iconsGroup">
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
    <nav class="navbar">
      <img src="/public/logo_f5.png" class="img-fluid navbar__img" alt="Logo Factoria F5">
      
      <ul class="navbar__menu">
        <li class="navbar__menu--item">
          <RouterLink to="/">HOME</RouterLink>
        </li>
        <li class="navbar__menu--item">
          <RouterLink to="/">APRENDE</RouterLink>
        </li>
        <li class="navbar__menu--item">
          <RouterLink to="/">COLABORA</RouterLink>
        </li>
        <li class="navbar__menu--item">
          <RouterLink to="/">SOMOS</RouterLink>
        </li>
        <li class="navbar__menu--item">
          <RouterLink to="/">BLOG</RouterLink>
        </li>
        <li class="navbar__menu--item">
          <RouterLink to="/">CONTACTO</RouterLink>
        </li>
        <li class="navbar__menu--item">
          <RouterLink to="/">USUARIOS</RouterLink>
        </li>
      </ul>
    </nav>
  </header>
</template>

<style>
.header {
  height: 12vh;
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
.navbar{
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem;
  margin: 0 15rem;
  transition: 0.5s;
}
.navbar__img{
  width: 6vw;
}
.navbar__menu{
  display: flex;
  list-style: none;
  gap: 3rem;
}
.navbar__menu--item{
  font-size: 1.2rem;
  font-weight: 600;
  letter-spacing: 0.1rem;
  display: table-cell; 
  position: relative; 
  padding: 15px 0;
}
.navbar__menu--item a{
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
.navbar__menu--item a:hover{
  color: var(--orange);
}
.navbar__menu--item a:hover:after { 
  width: 100%; 
  left: 0; 
}

@media (max-width: 1000px) {
  
}

@media (max-width: 1500px) {
  .navbar__menu {
    gap: 1rem;
  }
  .navbar__menu--item {
    font-size: 1rem;
  }
}
</style>
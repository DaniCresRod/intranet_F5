<script setup>
// import CalendarHolidaysComponent from '../components/CalendarHolidaysComponent.vue';

</script>

<template>
    <div id="banner">
        <img src="/img_banner.png" alt="networking">
        <h1>¡Somos <span :class="destacat">ágiles, creativos/as</span> y comprometidos/as!</h1>
    </div>
    <!-- <CalendarComponent/> -->
    <!-- <CalendarHolidaysComponent/> -->
</template>

<style scoped>
/* PRUEBA PARA COLOCACIÓN FOOTER */
main {
    height: 900rem;
}

#banner {
    position: relative;
    text-align: center;
    color: #000;
    text-align: center;
    font-family: Poppins;
    font-size: 2.5rem;
    font-style: normal;
    font-weight: normal; 
    line-height: normal;
    background-color: #FF4700;
    margin-bottom: 2rem;
}
.destacat{
    font-weight:bolder;
}
</style>

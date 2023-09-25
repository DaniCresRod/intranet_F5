<script setup>
// import CalendarHolidaysComponent from '../components/CalendarHolidaysComponent.vue';

</script>

<template>
    <div id="banner">
        <img src="/img_banner.png" alt="networking">
        <h1>¡Somos <span class="destacat">ágiles, creativos/as</span> y comprometidos/as!</h1>
    </div>
    <!-- <CalendarComponent/> -->
    <!-- <CalendarHolidaysComponent/> -->
</template>

<style scoped>

#banner{
    width: 100%;
    background-color:var(--orange);
    padding: 0 0 3% 0;
}
#banner img{
background-size: cover;
}

h1 {
    text-align: center;
    font-family: Poppins;
    font-size: 2.5rem;
    font-weight:normal;
    margin-top: 2%;
}

.destacat {
    font-weight: bold;
}

</style>

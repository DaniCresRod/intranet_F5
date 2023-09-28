<script setup>


</script>

<template>
    <h2> Dar de alta una nueva escuela</h2>

    <section class="newSchool">
        <form id="schoolFrom">
            <label for="schoolName">Nombre de la Escuela:</label>
            <input type="text" id="schoolName" name="schoolName" required>
            
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
            
            <label for="phoneNumber">Número de Teléfono:</label>
            <input type="tel" id="phoneNumber" name="phoneNumber" required>
            
            <label for="stateCode">Código de Estado:</label>
            <select id="stateCode" name="stateCode">
                <option value="AN">Andalucía</option>
                <option value="AR">Aragón</option>
                <option value="AS">Asturias</option>
                <option value="CB">Cantabria</option>
                <option value="CE">Ceuta</option>
                <option value="CL">Castilla y León</option>
                <option value="CM">Castilla La Mancha</option>
                <option value="CN">Islas Canarias</option>
                <option value="CT">Catalunya</option>
                <option value="EX">Extremadura</option>
                <option value="GA">Galicia</option>
                <option value="IB">Islas Baleares</option>
                <option value="MC">Murcia</option>
                <option value="MD">Madrid</option>
                <option value="ML">Melilla</option>
                <option value="NC">Navarra</option>
                <option value="PV">País Vasco</option>
                <option value="RI">La Rioja</option>
                <option value="VC">Comunidad Valenciana</option>

            </select>
            
            <input type="submit" value="Crear Escuela">
        </form>

    </section>
</template>
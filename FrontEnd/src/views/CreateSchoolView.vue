<script setup>
import { ref, onMounted } from 'vue';
import PostSchool from '../services/PostSchool';

// Define refs para los campos del formulario
const schoolName = ref('');
const email = ref('');
const phoneNumber = ref('');
const stateCode = ref('');

// Función para manejar el envío del formulario
const createSchool = async () => {
    try {
        const postData = {
            schoolName: schoolName.value,
            email: email.value,
            phoneNumber: phoneNumber.value,
            stateCode: stateCode.value,
        };

        // Llamar al servicio para crear la escuela
        const response = await PostSchool.post(postData);

        // Manejar la respuesta del servidor, por ejemplo, mostrar un mensaje de éxito
        console.log('Escuela creada con éxito:', response.data);

        // Restablecer los campos del formulario después de crear la escuela
        schoolName.value = '';
        email.value = '';
        phoneNumber.value = '';
        stateCode.value = '';
    } catch (error) {
        // Manejar cualquier error que ocurra durante la creación de la escuela
        console.error('Error al crear la escuela:', error);
    }
};

// Manejar el envío del formulario cuando se presiona el botón "Crear Escuela"
const handleSubmit = (event) => {
    event.preventDefault();
    createSchool();
};

// Ejecutar alguna lógica adicional si es necesario cuando la vista se monta
onMounted(() => {
    // Por ejemplo, cargar datos iniciales o configurar eventos
});

</script>


<template>
    <h2> Dar de alta una nueva escuela</h2>

    <section class="newSchool">
        <form id="schoolFrom">
            <label for="schoolName">Nombre de la Escuela:</label>
            <input type="text" id="schoolName" name="schoolName" v-model="schoolName" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" v-model="email" required>

            <label for="phoneNumber">Número de Teléfono:</label>
            <input type="tel" id="phoneNumber" name="phoneNumber" v-model="phoneNumber" required>

            <label for="stateCode">Código de Estado:</label>
            <select id="stateCode" name="stateCode" v-model="stateCode">
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
            <input type="submit" value="Crear Escuela" @click="handleSubmit">
        </form>

    </section>
</template>
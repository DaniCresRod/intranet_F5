<script setup>
import { ref, onMounted } from 'vue';
import PostSchool from '../services/PostSchool';
import BtnBackHr from '../components/BtnBackHr.vue';

const schoolName = ref('');
const address = ref('');
const phoneNumber = ref('');
const stateCode = ref('');

const createSchool = async () => {
    try {
        const postData = {
            schoolName: schoolName.value,
            schoolAddress: address.value,
            schoolPhone: phoneNumber.value,
            schoolStateCode: stateCode.value,
        };

        const response = await PostSchool.post(postData);

        
        console.log('Escuela creada con éxito:', response.data);

        schoolName.value = '';
        address.value = '';
        phoneNumber.value = '';
        stateCode.value = '';
    } catch (error) {
        console.error('Error al crear la escuela:', error);
    }
};

const handleSubmit = (event) => {
    event.preventDefault();
    createSchool();
};


onMounted(() => {
    
});

</script>


<template>
    <h2> Dar de alta una nueva escuela</h2>

    <section class="newSchool">
        <form id="schoolFrom">
            <div class="form-group">
            <label for="schoolName">Nombre de la Escuela:</label>
            <input type="text" id="schoolName" name="schoolName" v-model="schoolName" required>
            </div>

            <div class="form-group">
            <label for="adress">Dirección:</label>
            <input type="address" id="address" name="address" v-model="address" required>
            </div>

            <div class="form-group">
            <label for="phoneNumber">Número de Teléfono:</label>
            <input type="tel" id="phoneNumber" name="phoneNumber" v-model="phoneNumber" required>
            </div>

            <div class="form-group">
            <label for="stateCode">Provincia:</label>
            <select id="stateCode" name="stateCode" v-model="stateCode">
                <option value="AN">Andalucía</option>
                <option value="AR">Aragón</option>
                <option value="AS">Asturies</option>
                <option value="CB">Cantabria</option>
                <option value="CE">Ceuta</option>
                <option value="CL">Castilla y León</option>
                <option value="CM">Castilla La Mancha</option>
                <option value="CN">Islas Canarias</option>
                <option value="CT">Catalunya</option>
                <option value="EX">Extremadura</option>
                <option value="GA">Galiza Calidade</option>
                <option value="IB">Islas Baleares</option>
                <option value="MC">Murcia</option>
                <option value="MD">Madrid</option>
                <option value="ML">Melilla</option>
                <option value="NC">Navarra</option>
                <option value="PV">Euskalherria</option>
                <option value="RI">La Rioja</option>
                <option value="VC">Comunidad Valenciana</option>
            </select>
            </div>
            <input type="submit" value="Crear Escuela" @click="handleSubmit">
        </form>

    </section>
    <BtnBackHr/>
</template>

<style scoped>
*{
    font-family: 'Poppins';
}

h2{
    margin-top: 5rem;
    text-align: center;
}
.newSchool{
    width: 100%;
    margin: 2rem auto;
    padding: 2rem 4rem 1rem 4rem;
    border: 1px solid #FF4700;
    background: #FBF5F5;
    border-radius: 1rem;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.26);
}

#schoolFrom{
    width: 100%;
}
.form-group {
    margin-bottom: 1rem;
    display: flex;
    flex-direction: row; 
    align-items: center;
}
label {
    flex: 2;
    margin-right: 1rem; 
    font-weight: 600;
}


input, select {
    flex: 2; 
    background-color: rgba(255, 163, 127, 0.6);
    
}

label, input, select {
    margin-bottom: 0.5rem;
    display: block;
    width: 100%; 
}

input[type="submit"] {
    width: 8.5625rem;
    height: 2.375rem;
    cursor: pointer;
    font-weight: 600;
    border-radius: 1.875rem;
    border: 1px solid #616161;
    background: var(--orange);
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
    margin: 1.5rem auto;
}

input:focus {
    width: 8.5625rem;
    height: 2.375rem;
    cursor: pointer;
    font-weight: 600;
    border: 1px solid #616161;
    background: rgba(200,235,102, 0.6);
    border-radius: none !important;
}

select:focus {
    background: rgba(200,235,102, 0.6);
    border-radius: none;
}
</style>
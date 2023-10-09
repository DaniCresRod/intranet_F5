<script setup>
import { ref, onMounted, watch, onBeforeMount } from 'vue';
import { getById } from '../services/EditUser';
import { updateById } from '../services/EditUser';
import schoolService from '../services/schoolService'

// Define las referencias para los campos del formulario
const user = ref(null);
const showPassword = ref(false);
const user_name = ref('');
const user_surname = ref('');
const user_nif = ref('');
const user_email = ref('');
const user_phone = ref('');
const user_birthday = ref('');
const user_startDate = ref('');
const user_endDate = ref('');
const user_pass = ref('');
const user_type = ref('');
const user_img = ref('');
const user_school = ref('');
const user_id = ref('');
const schools = ref([]);
const user_dpto = ref('');
user_email.value = "@factoriaf5.com";
const changes = ref([]);
//const changesWithNewValues = ref([]);


// Carga los datos del usuario
const loadUserData = async () => {
    try {
        const userId = 3; // ELIMINAR
        const response = await getById(userId); 
        user.value = response.data; 
        
        user_id.value = user.value.id;
        user_name.value = user.value.username;
        user_surname.value = user.value.userSurName;
        user_nif.value = user.value.userNif;
        user_email.value = user.value.userEmail;
        user_phone.value = user.value.userPhone;
        user_birthday.value = user.value.userBirthDate;
        user_startDate.value = user.value.userStartDate;
        user_endDate.value = user.value.userEndDate;
        user_pass.value = user.value.userPass;
        user_type.value = user.value.userType;
        user_img.value = user.value.userImage;
        user_school.value = user.value.schoolID.id;
    } catch (error) {
        console.error('Error al cargar los datos del usuario:', error);
    }
};

const validateSpanishDNIOrNIE = (dniOrNIE) => {
    const dniRegex = /^[0-9]{8}[A-Z]$/;
    const nieRegex = /^[XYZ][0-9]{7}[A-Z]$/;

    if (dniRegex.test(dniOrNIE)) {
        // DNI format
        const letter = dniOrNIE.charAt(8);
        const number = parseInt(dniOrNIE.substr(0, 8), 10);
        const letters = 'TRWAGMYFPDXBNJZSQVHLCKE';
        const expectedLetter = letters.charAt(number % 23);
        return letter === expectedLetter;
    } else if (nieRegex.test(dniOrNIE)) {
        // NIE format (starting with X, Y, or Z)
        const nieLetterMapping = { X: 0, Y: 1, Z: 2 };
        const firstChar = dniOrNIE.charAt(0);
        const number = parseInt(dniOrNIE.substr(1, 7), 10);
        const letters = 'TRWAGMYFPDXBNJZSQVHLCKE';
        const expectedLetter = letters.charAt((nieLetterMapping[firstChar] + number) % 23);
        return dniOrNIE.charAt(8) === expectedLetter;
    } else {
        // Neither DNI nor NIE format
        return false;
    }
};

// Watch the user_nif value for changes and validate it
watch(user_nif, (newValue) => {
    if (validateSpanishDNIOrNIE(newValue)) {
        // The DNI or NIE is valid
        document.getElementById('dniValidationError').textContent = '';
    } else {
        // The DNI or NIE is not valid
        document.getElementById('dniValidationError').textContent = '';
    }
});

// Función para validar y ajustar el campo de correo electrónico
const validateAndAdjustEmail = () => {
    let email = user_email.value.toLowerCase();

    // Si el correo electrónico no contiene una "@" o contiene más de una, o si no termina en "factoriaf5.com".
    if (email.indexOf('@') === -1 || email.indexOf('@') !== email.lastIndexOf('@') || !email.endsWith('@factoriaf5.com')) {
        // Reemplaza cualquier dominio existente con "factoriaf5.com" y asegura que haya solo una "@".
        email = email.replace(/@.*$/, '') + '@factoriaf5.com';
        user_email.value = email;
    }
};

// Mostrar/Ocultar contraseña
const togglePassword = () => {
    showPassword.value = !showPassword.value;
};

const getPasswordInputType = () => {
    return showPassword.value ? 'text' : 'password';
};

const updateUser = async () => {
    try {
        const updatedUserData = {
            id: user_id.value,
            userName: user_name.value,
            userSurName: user_surname.value,
            userNif: user_nif.value,
            userEmail: user_email.value,
            userPhone: user_phone.value,
            userBirthDate: user_birthday.value,
            userStartDate: user_startDate.value,
            userEndDate: user_endDate.value,
            userPass: user_pass.value,
            userType: user_type.value,
            userImage: user_img.value,
            schoolID: {
                id: user_school.value,
                userDept: user_dpto.value,
            }
        };
        const modifiedFields = [];

       // Compara los valores antiguos con los nuevos
       if (user_name.value !== user.value.userName) {
            modifiedFields.push('Nombre');
        }
        if (user_surname.value !== user.value.userSurName) {
            modifiedFields.push('Apellidos');
        }
        if (user_nif.value !== user.value.userNif) {
            modifiedFields.push('Documento Identidad(DNI o NIE)');
        }
        if (user_email.value !== user.value.userEmail) {
            modifiedFields.push('Email');
        }
        if (user_phone.value !== user.value.userPhone) {
            modifiedFields.push('Número de Teléfono');
        }
        if (user_birthday.value !== user.value.userBirthDate) {
            modifiedFields.push('Fecha de nacimiento');
        }
        if (user_startDate.value !== user.value.userStartDate) {
            modifiedFields.push('Fecha de alta');
        }
        if (user_endDate.value !== user.value.userEndDate) {
            modifiedFields.push('Fecha de finalización');
        }
        if (user_type.value !== user.value.userType) {
            modifiedFields.push('Puesto');
        }
        if (user_img.value !== user.value.userImage) {
            modifiedFields.push('Adjuntar foto');
        }
        if (user_school.value !== user.value.schoolID.id) {
            modifiedFields.push('Escuela');
        }

        // Llama a la función updateById para actualizar los datos del usuario
        await updateById(user_id.value, updatedUserData);
            // Actualiza la propiedad changes con los campos modificados
            changes.value = modifiedFields;
        // Calcula las modificaciones realizadas y actualiza la propiedad changes
    } catch (error) {
        console.error('Error al actualizar los datos del usuario:', error);
    }
};


// Función para manejar el envío del formulario
const handleSubmit = async (event) => {
    event.preventDefault();
    validateAndAdjustEmail();
    updateUser();

};


onBeforeMount(async () => {
    try {
        // Llama al servicio para obtener las escuelas
        schools.value = await schoolService.getSchools();
    } catch (error) {
        console.error('Error al obtener las escuelas:', error);
    }
});

// Carga los datos del usuario cuando se monta el componente
onMounted(() => {
    loadUserData();
});

</script>


<template>
    <h2> Modificar datos de usuario</h2>

    <section class="newUser">
        <form id="userForm" @submit.prevent="handleSubmit">

            <div class="form-group">
                <label for="user_name">Nombre:</label>
                <input type="text" id="user_name" name="user_name" v-model="user_name">
            </div>

            <div class="form-group">
                <label for="user_surname">Apellidos:</label>
                <input type="text" id="user_surname" name="user_surname" v-model="user_surname">
            </div>
            <div class="form-group">
                <label for="user_nif">Documento Identidad(DNI o NIE):</label>
                <input type="text" id="user_nif" name="user_nif" v-model="user_nif" readonly>
                <span id="dniValidationError"></span>
            </div>
            <div class="form-group warning">
                <span id="dniValidationError">
                    {{ validateSpanishDNIOrNIE(user_nif) ? '' : 'El documento no es válido' }}
                </span>
            </div>

            <div class="form-group">
                <label for="user_email">Email:</label>
                <input type="email" id="user_email" name="user_email" v-model="user_email">
            </div>

            <div class="form-group">
                <label for="user_phone">Número de Teléfono:</label>
                <input type="tel" id="user_phone" name="user_phone" v-model="user_phone">
            </div>

            <div class="form-group">
                <label for="user_birthday">Fecha de nacimiento:</label>
                <input type="date" id="user_birthday" name="user_birthday" v-model="user_birthday">
            </div>

            <div class="form-group">
                <label for="user_startDate">Fecha de alta:</label>
                <input type="date" id="user_startDate" name="user_startDate" v-model="user_startDate">
            </div>

            <div class="form-group">
                <label for="user_endDate">Fecha de finalización:</label>
                <input type="date" id="user_endDate" name="user_endDate" v-model="user_endDate">
            </div>

            <div class="form-group">
                <label for="user_pass">Contraseña:</label>
                <input :type="getPasswordInputType()" id="user_pass" name="user_pass" v-model="user_pass">
            </div>
            <div class="form-group user_pass">
                <button id="togglePassword" @click="togglePassword">{{ showPassword ? 'Ocultar contraseña' : 'Mostrar contraseña' }}</button>
            </div>

            <div class="form-group">
                <label for="user_type">Puesto:</label>
                <select id="user_type" name="user_type" v-model="user_type">
                    <option value="Supervisor">Supervisor</option>
                    <option value="HR">HR</option>
                    <option value="Employee">Empleado</option>
                </select>
            </div>

            <div class="form-group">
                <label for="user_img">Adjuntar foto:</label>
                <input type="file" id="user_img" name="user_img" accept="image/*">
            </div>

            <div class="form-group">
                <label for="user_school">Escuela:</label>
                <select id="user_school" name="user_school" v-model="user_school">
                    <option value="" disabled>Selecciona una escuela</option>
                    <option v-for="school in schools" :value="school.id" :key="school.id">{{ school.schoolName }}</option>
                </select>
            </div>
            <input type="submit" value="Modificar datos" @click="handleSubmit">
        </form>
        <div v-if="changes.length > 0" class="popup">
            <h3>Campos modificados:</h3>
            <ul>
                <li v-for="field in changes" :key="field">{{ field }}</li>
            </ul>
        </div>
    </section>
</template>

<style scoped>
* {
    font-family: 'Poppins';
}

h2 {
    margin-top: 5rem;
    text-align: center;
}

.newUser {
    width: 100%;
    margin: 2rem auto;
    padding: 2rem 4rem 1rem 4rem;
    border: 1px solid #FF4700;
    background: #FBF5F5;
    border-radius: 1rem;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.26);
}

#userForm {
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


input,
select {
    flex: 2;
    background-color: rgba(255, 163, 127, 0.6);

}

label,
input,
select {
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
    background: rgba(200, 235, 102, 0.6);
    border-radius: none !important;
}

select:focus {
    background: rgba(200, 235, 102, 0.6);
    border-radius: none;
}

.warning {
    display: flex;
    align-items: flex-end;
    justify-content: flex-end;
    margin-top: -1rem;
    font-size: smaller;
    color: var(--orange);
    font-style: italic;
}

.user_pass {
    display: flex;
    align-items: flex-end;
    justify-content: flex-end;
    margin-top: -1rem;
    font-size: smaller;
    color: var(--orange);
    text-decoration: underline;
}

.popup {
    background-color: rgba(255, 163, 127, 0.2);
    padding: 10px;
    border: 2px solid var(--orange);
    border-radius: 5px;
    margin-bottom: 10px;
    display: flex;
    flex-direction: column; 
    align-items: center; 
    text-align: center;
}
h3{
    display: flex;
    align-items: center;
    justify-content: center;
    
}
ul {
    list-style: none;
    padding: 0; 
    margin: 0; 
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between; 
    width: 100%; 
}

li {
    flex-basis: calc(25% - 2vw); 
    margin: 1vw 0; 
}
</style>
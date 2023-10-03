<script setup>
import { ref, onMounted, watch } from 'vue';
import { getById } from '../services/EditUser'; 
import { updateById } from '../services/EditUser';

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

// Carga los datos del usuario
const loadUserData = async () => {
    try {
        const userId = 12; // ELIMINAR
        const response = await getById(userId); 
        user.value = response.data; 
        
        user_id.value = user.value.id;
        user_name.value = user.value.userName;
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

// Validación del DNI 
const validateSpanishDNI = (dni) => {
    const dniRegex = /^[0-9]{8}[A-Z]$/;
    if (!dniRegex.test(dni)) {
        return false;
    }
    const letter = dni.charAt(8);
    const number = parseInt(dni.substr(0, 8), 10);
    const letters = 'TRWAGMYFPDXBNJZSQVHLCKE';
    const expectedLetter = letters.charAt(number % 23);
    return letter === expectedLetter;
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
            schoolID: { id: user_school.value }
        };

        // Llama a la función updateById para actualizar los datos del usuario
        await updateById(user_id.value, updatedUserData);
    } catch (error) {
        console.error('Error al actualizar los datos del usuario:', error);
    }
};

// Escucha el cambio en user_nif para validar 
watch(user_nif, (newValue) => {
    if (validateSpanishDNI(newValue)) {
        // El DNI es válido
        document.getElementById('dniValidationError').textContent = '';
    } else {
        // El DNI no es válido
        document.getElementById('dniValidationError').textContent = 'El DNI no es válido';
    }
});

// Función para manejar el envío del formulario
const handleSubmit = async () => {
    event.preventDefault();
    updateUser(); // Llama a la función updateUser para actualizar los datos del usuario
};

// Carga los datos del usuario cuando se monta el componente
onMounted(() => {
    loadUserData();
});
</script>


<template>
    <h2> Dar de alta una nuevo usuario</h2>

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
                <label for="user_nif">Documento Identidad:</label>
                <input type="text" id="user_nif" name="user_nif" v-model="user_nif">
                <span id="dniValidationError"></span>
            </div>
            <div class="form-group warning">
        <span id="dniValidationError">
            {{ validateSpanishDNI(user_nif) ? '' : 'El DNI  no es válido' }}
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
                <option value="1">1</option>
                <option value="3">3</option>
                <option value="2">2</option>
            </select>
            </div>

            <input type="submit" value="Modificar datos" @click="handleSubmit">
        </form>

    </section>
</template>

<style scoped>
*{
    font-family: 'Poppins';
}

h2{
    margin-top: 5rem;
    text-align: center;
}
.newUser{
    width: 100%;
    margin: 2rem auto;
    padding: 2rem 4rem 1rem 4rem;
    border: 1px solid #FF4700;
    background: #FBF5F5;
    border-radius: 1rem;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.26);
}

#userForm{
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
.warning {
    display: flex;
    align-items: flex-end;
    justify-content: flex-end;
    margin-top: -1rem;
    font-size: smaller;
    color: var(--orange);
    font-style: italic; 
}

.user_pass{
    display: flex;
    align-items: flex-end;
    justify-content: flex-end;
    margin-top: -1rem;
    font-size: smaller;
    color: var(--orange);
    text-decoration: underline;
    }
</style>
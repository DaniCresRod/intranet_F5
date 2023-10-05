<script setup>
import { ref, onMounted, watch, onBeforeMount } from 'vue';
import PostUser from '../services/PostUser';
import schoolService from '../services/schoolService'


// Define refs para los campos del formulario
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
const user_dpto = ref('');

const showPassword = ref(false);
user_email.value = "@factoriaf5.com";
const schools = ref([]);


// Función para manejar el envío del formulario
const createUser = async () => {
    try {
        const email = user_email.value; // Valor ingresado por el usuario
        const fullEmail = email + "@factoriaf5.com"; // Email completo con el dominio
        const postData = {
            userName: user_name.value,
            userSurName: user_surname.value,
            userNif: user_nif.value,
            userEmail: fullEmail, // Utiliza el email completo en el objeto de datos
            userPhone: user_phone.value,
            userBirthDate: user_birthday.value,
            userStartDate: user_startDate.value,
            userEndDate: user_endDate.value,
            userPass: user_pass.value,
            userType: user_type.value,
            userImage: user_img.value,
            schoolID: {
                id: user_school.value,
                userDept: user_dpto.value
            }
        };
        console.log(postData);
        // Llamar al servicio para crear la escuela
        const response = await PostUser.post(postData);

        // Manejar la respuesta del servidor, por ejemplo, mostrar un mensaje de éxito
        console.log('Empleado con éxito:', response.data);

        // Restablecer los campos del formulario después de crear la escuela
        user_name.value = '';
        user_surname.value = '';
        user_nif.value = '';
        user_email.value = '';
        user_phone.value = '';
        user_birthday.value = '';
        user_startDate.value = '';
        user_endDate.value = '';
        user_pass.value = '';
        user_type.value = '';
        user_img.value = '';
        user_dpto.value = '';
    } catch (error) {
        // Manejar cualquier error que ocurra durante la creación de la escuela
        console.error('Error al dar de alta al empleado:', error);
    }
};


// Validación del DNI
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
        document.getElementById('dniValidationError').textContent = 'El DNI o NIE no es válido';
    }
});

// Mostrar/Ocultar contraseña
// Function to toggle password visibility
const togglePassword = () => {
    showPassword.value = !showPassword.value;
};

// Function to get the input type based on password visibility
const getPasswordInputType = () => {
    return showPassword.value ? 'text' : 'password';
};


const handleSubmit = async (event) => {
    event.preventDefault();
    createUser();
};

//Logica para cargar y actualizar las escuelas
// onMounted(async () => {
//     try {
//         // Llama al servicio para obtener las escuelas
//         schools.value = await schoolService.getSchools();
//     } catch (error) {
//         console.error('Error al obtener las escuelas:', error);
//     }
// });

onBeforeMount(async () => {
    try {
        // Llama al servicio para obtener las escuelas
        schools.value = await schoolService.getSchools();
    } catch (error) {
        console.error('Error al obtener las escuelas:', error);
    }
    console.log(schools.value)
});
</script>

<template>
    <h2> Dar de alta un nuevo usuario</h2>

    <section class="newUser">
        <form id="userForm" @submit.prevent="handleSubmit">

            <div class="form-group">
                <label for="user_name">Nombre:</label>
                <input type="text" id="user_name" name="user_name" v-model="user_name" required>
            </div>

            <div class="form-group">
                <label for="user_surname">Apellidos:</label>
                <input type="text" id="user_surname" name="user_surname" v-model="user_surname" required>
            </div>

            <div class="form-group">
                <label for="user_nif">Documento Identidad(DNI o NIE):</label>
                <input type="text" id="user_nif" name="user_nif" v-model="user_nif" required>
                <span id="dniValidationError"></span>
            </div>
            <div class="form-group warning">
                <span id="dniValidationError">
                    {{ validateSpanishDNIOrNIE(user_nif) ? '' : 'El documento no es válido' }}
                </span>
            </div>

            <div class="form-group">
                <label for="user_email">Email:</label>
                <input type="email" id="user_email" name="user_email" v-model="user_email" required class="correito">
            </div>

            <div class="form-group">
                <label for="user_phone">Número de Teléfono:</label>
                <input type="tel" id="user_phone" name="user_phone" v-model="user_phone" required>
            </div>

            <div class="form-group">
                <label for="user_birthday">Fecha de nacimiento:</label>
                <input type="date" id="user_birthday" name="user_birthday" v-model="user_birthday" required>
            </div>

            <div class="form-group">
                <label for="user_startDate">Fecha de alta:</label>
                <input type="date" id="user_startDate" name="user_startDate" v-model="user_startDate" required>
            </div>

            <div class="form-group">
                <label for="user_endDate">Fecha de finalización:</label>
                <input type="date" id="user_endDate" name="user_endDate" v-model="user_endDate" required>
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
                    <option value="Formador">Formador</option>
                </select>
            </div>

            <div class="form-group">
                <label for="user_dpto">Departamento asociado:</label>
                <select id="user_dpto" name="user_dpto" v-model="user_dpto">
                    <option value="Pedagógico">Pedagógico</option>
                    <option value="RRHH">RRHH</option>
                    <option value="Supervisor">Supervisión</option>
                </select>
            </div>

            <div class="form-group">
                <label for="user_img">Adjuntar foto:</label>
                <input type="file" id="user_img" name="user_img" accept="image/*" required>
            </div>

            <div class="form-group">
    <label for="user_school">Escuela:</label>
    <select id="user_school" name="user_school" v-model="user_school">
        <option value="" disabled>Selecciona una escuela</option>
        <option v-for="school in schools" :value="school.id" :key="school.id">{{ school.schoolName }}</option>
    </select>
</div>

            <input type="submit" value="Alta Emplead@" @click="handleSubmit">
        </form>

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

.correito {
    text-align: right;
}
</style>
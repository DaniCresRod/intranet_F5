<script setup>
import { ref, watch, onBeforeMount } from 'vue';
import PostUser from '../services/PostUser';
import schoolService from '../services/schoolService'
import BtnBackHr from '../components/BtnBackHr.vue';


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
const user_school = ref('');
const user_dpto = ref('');
const selectedImage = ref('');

const showPassword = ref(false);
user_email.value = "@factoriaf5.com";
const schools = ref([]);
const MAX_IMAGE_SIZE_MB = 8; 
const MAX_IMAGE_SIZE_BYTES = MAX_IMAGE_SIZE_MB * 1024 * 1024; 



const createUser = async () => {
    try {
        const email = user_email.value; 
        const fullEmail = email; 
        const postData = {
            userName: user_name.value,
            userSurName: user_surname.value,
            userNif: user_nif.value,
            userEmail: fullEmail, 
            userPhone: user_phone.value,
            userBirthDate: user_birthday.value,
            userStartDate: user_startDate.value,
            userEndDate: user_endDate.value,
            userPass: user_pass.value,
            userType: user_type.value,
            userImage: selectedImage.value,
            schoolID: {
                id: user_school.value,
                userDept: user_dpto.value
            }
        };

        if (selectedImage.value) {
            const imageBase64 = await fileToBase64(selectedImage.value);
            postData.userImage = imageBase64;
        }
        const response = await PostUser.post(postData);

        console.log('Empleado con éxito:', response.data);

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
        user_dpto.value = '';
    } catch (error) {
        console.error('Error al dar de alta al empleado:', error);
    }
};

const fileToBase64 = (file) => {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();

        reader.onload = () => {
            resolve(reader.result.split(',')[1]); 
        };

        reader.onerror = (error) => {
            reject(error);
        };

        reader.readAsDataURL(file);
    });
};

const validateSpanishDNIOrNIE = (dniOrNIE) => {
    const dniRegex = /^[0-9]{8}[A-Z]$/;
    const nieRegex = /^[XYZ][0-9]{7}[A-Z]$/;

    if (dniRegex.test(dniOrNIE)) {
        const letter = dniOrNIE.charAt(8);
        const number = parseInt(dniOrNIE.substr(0, 8), 10);
        const letters = 'TRWAGMYFPDXBNJZSQVHLCKE';
        const expectedLetter = letters.charAt(number % 23);
        return letter === expectedLetter;
    } else if (nieRegex.test(dniOrNIE)) {
        const nieLetterMapping = { X: 0, Y: 1, Z: 2 };
        const firstChar = dniOrNIE.charAt(0);
        const number = parseInt(dniOrNIE.substr(1, 7), 10);
        const letters = 'TRWAGMYFPDXBNJZSQVHLCKE';
        const expectedLetter = letters.charAt((nieLetterMapping[firstChar] + number) % 23);
        return dniOrNIE.charAt(8) === expectedLetter;
    } else {
        return false;
    }
};

watch(user_nif, (newValue) => {
    if (validateSpanishDNIOrNIE(newValue)) {
        document.getElementById('dniValidationError').textContent = '';
    } else {
        document.getElementById('dniValidationError').textContent = '';
    }
});

const validateAndAdjustEmail = () => {
    let email = user_email.value.toLowerCase();

    if (email.indexOf('@') === -1 || email.indexOf('@') !== email.lastIndexOf('@') || !email.endsWith('@factoriaf5.com')) {
        email = email.replace(/@.*$/, '') + '@factoriaf5.com';
        user_email.value = email;
    }
};

const togglePassword = () => {
    showPassword.value = !showPassword.value;
};

const getPasswordInputType = () => {
    return showPassword.value ? 'text' : 'password';
};

const guardarImagen = () => {
    const input = document.getElementById('imagenInput');
    const file = input.files[0];

    if (file) {
        if (file.size <= MAX_IMAGE_SIZE_BYTES) {
            if (file.type.startsWith('image/')) {
                selectedImage.value = file;
            } else {
                alert('Por favor, seleccione un archivo de imagen válido.');
            }
        } else {
            alert('El tamaño del archivo excede el límite de 8 MB.');
        }
    } else {
        alert('Por favor, seleccione una imagen antes de guardar.');
    }
}


const handleSubmit = async (event) => {
    event.preventDefault();
    validateAndAdjustEmail();
    guardarImagen();
    createUser();
};


onBeforeMount(async () => {
    try {
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
        <form id="userForm" @submit.prevent="handleSubmit" enctype="multipart/form-data">

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
                <input type="text" id="user_email placeholder_mail" name="user_email" v-model="user_email" required
                    class="correito" placeholder="@factoriaf5.com">
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
                <input type="date" id="user_endDate" name="user_endDate" v-model="user_endDate">
            </div>

            <div class="form-group">
                <label for="user_pass">Contraseña:</label>
                <input :type="getPasswordInputType()" id="user_pass" name="user_pass" v-model="user_pass">
            </div>
            <div class="form-group user_pass">
                <button id="togglePassword" @click="togglePassword">{{ showPassword ? 'Ocultar contraseña' : 'Mostrar                                    contraseña' }}</button>
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
                <label for="user_school">Escuela:</label>
                <select id="user_school" name="user_school" v-model="user_school">
                    <option value="" disabled>Selecciona una escuela</option>
                    <option v-for="school in schools" :value="school.id" :key="school.id">{{ school.schoolName }}</option>
                </select>
            </div>

            <div class="form-group">
                <label for="user_img">Adjuntar foto:</label>
                <input type="file" id="imagenInput" accept="image/*">
            </div>
            <div class="form-group warning">
                <p>*Imagen en formato JPG y peso máximo 8Mb</p>
            </div>

            <input type="submit" value="Alta Emplead@" @click="handleSubmit">
        </form>

    </section>

    <BtnBackHr />
</template>

<style scoped>
* {
    font-family: 'Poppins';
}

h2 {
    display: flex;
    justify-content: center;
    text-align: center;
    color: var(--orange);
    font-weight: 450;
    margin-top: 25px;
    text-decoration-line: underline;
    text-decoration-thickness: 2px; 
    text-decoration-color: darkgray;
    font-size: 23px;
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
    flex: 3;
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
    color: #7b7878;
    text-align: right;
}
</style>
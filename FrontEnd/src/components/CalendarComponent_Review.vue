<script setup>
import { ref, computed, onMounted, onBeforeMount } from 'vue';
import schoolService from '../services/schoolService';

const selectedSchool = ref('');
const schools = ref([]);
const selectedMonth = ref('');
const months = {
    enero: 31,
    febrero: 28,
    marzo: 31,
    abril: 30,
    mayo: 31,
    junio: 30,
    julio: 31,
    agosto: 31,
    septiembre: 30,
    octubre: 31,
    noviembre: 30,
    diciembre: 31
};

const daysOfMonth = computed(() => {
    return Array.from({ length: months[selectedMonth.value] }, (_, index) =>
        (index + 1).toString().padStart(2, '0')
    );
});


function showEvent(user, day) {
    // Comprueba si el día está en las solicitudes de días libres del usuario
    const request = user.userRequests.find((request) => {
        const startDate = new Date(request.startDate);
        const endDate = new Date(request.endDate);
        const currentDay = new Date(`${selectedMonth.value} ${day}, ${new Date().getFullYear()}`);
        return currentDay >= startDate && currentDay <= endDate;
    });

    if (request) {
        return 'X';
    } else {
        return ''; // De lo contrario, muestra un espacio en blanco
    }
}

function updateCalendar() {
    // Limpia las celdas de la tabla
    const cells = document.querySelectorAll('td.dia-mes');
    cells.forEach((cell) => {
        cell.textContent = '';
    });

    if (selectedMonth.value) {
        // Obtiene el mes seleccionado
        const month = months[selectedMonth.value];

        // Obtiene los días del mes seleccionado
        const daysOfMonth = Array.from({ length: month }, (_, index) =>
            (index + 1).toString().padStart(2, '0')
        );

        // Obtiene la escuela seleccionada
        const selectedSchoolObj = schools.value.find((school) => school.id === selectedSchool.value);

        if (selectedSchool) {
            // Itera sobre los usuarios de la escuela seleccionada
            selectedSchoolObj.schoolUserList.forEach((user) => {
                // Itera sobre las solicitudes de vacaciones del usuario
                user.userRequests.forEach((request) => {
                    // Obtiene la fecha de inicio y final de la solicitud de vacaciones
                    const startDate = new Date(request.startDate);
                    const endDate = new Date(request.endDate);

                    // Itera sobre los días del mes seleccionado
                    daysOfMonth.forEach((day) => {
                        // Obtiene la fecha completa del día actual
                        const currentDay = new Date(`${mesSeleccionado.value} ${day}, ${new Date().getFullYear()}`);

                        // Comprueba si el día actual está en la solicitud de vacaciones del usuario
                        if (currentDay >= startDate && currentDay <= endDate) {
                            // Marca la celda como reservada
                            const cells = document.querySelectorAll('td.dia-mes');
                            cells.forEach((celda) => {
                            cell.textContent = '';
                            });
                        }
                    });
                });
            });
        }
    }
}


onBeforeMount(async () => {
    try {
        // Llama al servicio para obtener las escuelas
        schools.value = await schoolService.getSchools();

    } catch (error) {
        console.error('Error al obtener las escuelas:', error);
    }
    console.log(schools.value);
});

onMounted(async () => {
    // Llama a actualizarCalendario() para inicializar el calendario
    updateCalendar();
});


</script>

<template>
    <div class="calendar_container">
        <h2>Calendario de vacaciones solicitadas</h2>
        <label for="school">Selecciona Escuela:</label>
        <select id="user_school" class="custom-select" name="user_school" v-model="selectedSchool">
            <option value="" disabled>Selecciona una escuela</option>
            <option v-for="school in schools" :value="school.id" :key="school.id">{{ school.schoolName }}</option>
        </select>
        <label for="month">Selecciona Mes:</label>
        <select id="month" class="custom-select" v-model="selectedMonth" @change="actualizarCalendario">
            <option value="">Selección mes</option>
            <option value="enero">Enero</option>
            <option value="febrero">Febrero</option>
            <option value="marzo">Marzo</option>
            <option value="abril">Abril</option>
            <option value="mayo">Mayo</option>
            <option value="junio">Junio</option>
            <option value="julio">Julio</option>
            <option value="agosto">Agosto</option>
            <option value="septiembre">Septiembre</option>
            <option value="octubre">Octubre</option>
            <option value="noviembre">Noviembre</option>
            <option value="diciembre">Diciembre</option>
        </select>

        <div v-if="selectedMonth" class="calendar_view">
            <div class="last" v-for="school in schools" :key="school.schoolName">
                <div v-if="selectedSchool === school.id" :class="school.id">
                    <h4>Calendario de {{ school.schoolName }}</h4>
                    <table class="calendar" cellspacing="0">
                        <tbody>
                            <tr title="daysNum">
                                <th></th>
                                <th v-for="day in daysOfMonth">{{ day }}</th>
                            </tr>

                            <tr v-for="(user, index) in school.schoolUserList" :key="index" :title="user.userSurName">
                                <th>{{ user.username }} {{ user.userSurName }}</th>
                                <td v-for="day in daysOfMonth">
                                    {{ showEvent(user, day) }}
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
.calendar_container {
    max-width: 100%;
    overflow: auto;
    display: flex;
    flex-direction: column;
    align-items: center;
    text-align: center;
   }

h2 {
    text-align: center;
   
}

h4 {
    margin: 2.5rem 35%;
    text-decoration: underline;
    color: var(--orange);
}

.custom-select {
    background-color: #FFA37F;
    padding: 5px;
    display: block;
    margin-bottom: 5px;
}

.calendar_view {
    max-width: 100%;
    overflow: auto;
}

table.calendar {
    border-collapse: collapse;
    empty-cells: show;
    font-family: Arial, sans-serif;
}

table.calendar tr {
    vertical-align: bottom;
}

table.calendar th {
    border: 2px solid #f3f3f3;
    font-size: 14px;
    font-weight: bold;
    padding: 2px;
    vertical-align: middle;
    white-space: nowrap;
}

table.calendar td {
    border: 2px solid #f3f3f3;
    color: var(--orange-light);
    font-size: 14px;
    font-weight: bold;
    height: 14px;
    padding: 1px;
    text-align: center;
    text-transform: uppercase;
    vertical-align: middle;
    width: 14px;
}

.last{
    margin-bottom: 3.5rem;
}
h2 {
  text-align: center;
  color: var(--orange);
  font-weight: 450;
  margin-top: 25px;
  text-decoration-line: underline;
  text-decoration-thickness: 2px; 
  text-decoration-color: darkgray;
}
</style>

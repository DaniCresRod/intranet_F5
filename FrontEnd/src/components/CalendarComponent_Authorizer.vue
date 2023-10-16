<script setup>
import { ref, computed, onMounted, onBeforeMount, } from 'vue';

const { schoolId, schoolData, userId } = defineProps(['schoolId', 'schoolData', 'userId']); 

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

onMounted(() => {
    initializeCalendar();
    updateCalendar();
});

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
        const currentDay = new Date(
            `${selectedMonth.value} ${day}, ${new Date().getFullYear()}`
        );
        return currentDay >= startDate && currentDay <= endDate;
    });
    return request ? 'X' : '';
}

function initializeCalendar() {
    const currentDate = new Date();
    const currentMonth = currentDate.toLocaleString('es-ES', { month: 'long' }).toLowerCase();
    selectedMonth.value = currentMonth;    
}

function updateCalendar() {
    if (schoolData && schoolData.id === schoolId) {
        console.log(schoolData);
        const month = months[selectedMonth.value];
        const daysOfMonth = Array.from({ length: month }, (_, index) =>
            (index + 1).toString().padStart(2, '0')
        );

        if (schoolData.schoolUserList) {
            
            const cells = document.querySelectorAll('td.dia-mes');
             cells.forEach((cell) => {
                cell.textContent = ''; 
             })
            schoolData.schoolUserList.forEach((user) => {
                if (user.id !== userId.value){
                user.userRequests.forEach((request) => {
                    if (request.status === 2){
                    const startDate = new Date(request.startDate);
                    const endDate = new Date(request.endDate);

                    daysOfMonth.forEach((day) => {
                        const currentDay = new Date(
                            `${selectedMonth.value} ${day}, ${new Date().getFullYear()}`
                        );
                        if (currentDay >= startDate && currentDay <= endDate) {
                            cells.textContent = 'X';
                            }
                    });
                    }
                });
                }
            });
        } 
    }
}

</script>

<template>
    <div class="calendar_container">
        <label for="mes">Selecciona Mes:</label>
            <select id="mes"
                class="custom-select"
                v-model="selectedMonth"
                @change="updateCalendar">
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

            <div class="calendario_vista">
                <div class="last">
                    <h4 v-if="schoolData">Calendario de {{ schoolData.schoolName }}</h4>
                    <table class="calendar" cellspacing="0">
                        <tbody>
                                <tr title="daysNum">
                                    <th></th>
                                    <th v-for="day in daysOfMonth">{{ day }}</th>
                                </tr>
                                <tr v-for="(user, index) in schoolData.schoolUserList"
                                    :key="index"
                                    :title="user.userSurName">
                                <th>{{user.username + ' ' + user.userSurName }}</th>
                                <td v-for="day in daysOfMonth">
                                    <span v-html="showEvent(user, day)"></span>
                                </td>
                            </tr>
                        </tbody>
                    </table>
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

.calendario_vista {
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

</style>

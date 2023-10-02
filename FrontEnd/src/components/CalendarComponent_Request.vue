<script setup>
import { ref, computed, onBeforeMount } from 'vue';
import RequestServices from '../services/RequestServices'
import DaysRequestComponent from './DaysRequestComponent.vue';

const today=new Date();
const userId = 2;

const range = ref({
    start: today,
    end: new Date(),
});

let holidaysData = null;
const showConfirmation = ref(false);
const dragValue = ref(null);
const currentDate = ref();

const selectDragAttribute = computed(() => ({
    popover: {
        visibility: 'hover',
        isInteractive: false,
    },
}));

const attributes = ref([
    {
        key: 'approved',
        highlight:{
            color: 'orange',
            fillmode: 'solid',
        },
        date: new Date(currentDate),
    },
    {
        key: 'pending',
        highlight:{
            color: 'green',
            fillmode: 'light',
            contentClass: 'italic',
        },
        date: new Date(currentDate),
    },
]);

const startDate = ref(); 
const endDate = ref();

const openConfirmationDialog = () => {
    showConfirmation.value = true;
    startDate.value = range.value.start;
    endDate.value = range.value.end;
};

async function getData() {
    try {
        const response = await RequestServices.getById(userId);
        holidaysData = response.data;
        console.log(holidaysData);
    } catch (error) {
        console.log(error);
    }
}
onBeforeMount(getData);

const dayStyle = (date) => {
    if (!holidaysData || !holidaysData.userRequests) {
        return {}; 
    }
    
    const dayHoliday = range.value.start.getDate();
    const monthHoliday = range.value.start.getMonth() + 1; // Sumar 1 para ajustar a 1-12
    const fullYearHoliday = range.value.start.getFullYear();
    console.log(dayHoliday);
    // Clonar la fecha para no modificar la original
    const currentDate = new Date(date);

    // Configurar la fecha al primer día del mes
    currentDate.setDate(1);

    const dayStyles = {};

    holidaysData.userRequests.forEach((request) => {
        const startDate = new Date(request.startDate);
        const endDate = new Date(request.endDate);

        if (
            currentDate.getFullYear() === fullYearHoliday && 
            currentDate.getMonth() + 1 === monthHoliday &&  
            currentDate.getDate() === dayHoliday && 
            startDate <= currentDate &&
            endDate >= currentDate
        ) {
            console.log('Status:', request.status);

            if (request.status === 1) {
                dayStyles[currentDate.getDate()] = { key:'approved', cursor: 'not-allowed' };
            } else if (request.status === 2) {
                dayStyles[currentDate.getDate()] = { key: 'pending', cursor: 'not-allowed' };
            }
        }
        currentDate.setDate(currentDate.getDate() + 1);
    });
    console.log('dayStyles:', dayStyles);
    return dayStyles;
};



const eventHandler = (value) => {
    console.log('Rango seleccionado:', value.start, ' - ', value.end);
    dragValue.value = value;
};
</script>


<template>
   <div class="calendarContainer">
        <VDatePicker class="vDate" expanded title-position="left" v-model.range="range"
            v-model:start="range.start"
            v-model:end="range.end"
            :select-attribute="selectDragAttribute" 
            :attributes="attributes"
            :day-style="dayStyle"
            :drag-attribute="selectDragAttribute"
            @drag="eventHandler($event)"
        >
            <template #day-popover="{ format }">
                <div class="text-sm">
                    {{ format(dragValue ? dragValue.start : range.start, 'MMM D') }}
                    {{ format(dragValue ? dragValue.end : range.end, 'MMM D') }}
                </div>
            </template>
        </VDatePicker>
    </div>
    <v-btn class="me-2" @click="openConfirmationDialog">Solicitar días</v-btn> 
    <DaysRequestComponent v-if="showConfirmation"
    :startDate="range.start"
    :endDate="range.end"
    />

</template>


<style scoped>

.calendarContainer{
    display: flex;
    justify-content: center;
}

.me-2{
    float: right;
    margin-top: 50px;
    background-color: orangered;
    color: whitesmoke;
    font-weight: bold;
    border-radius: 10px;
    box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.5);
}

</style>



<script setup>
import { ref, computed, onBeforeMount } from 'vue';
import RequestServices from '../services/RequestServices'

const today=new Date();

const range = ref({
    start: today,
    end: new Date(),
});

let holidaysData = null;

const selectDragAttribute = computed(() => ({
    popover: {
        visibility: 'hover',
        isInteractive: false,
    },
    
}));

const dragValue = ref(null);
const userId = 2;

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

    const currentMonth = range.value.start.getMonth();
    
    // Clonar la fecha para no modificar la original
    const currentDate = new Date(date);
    
    // Configurar la fecha al primer día del mes
    currentDate.setDate(1);
    
    const dayStyles = {};

    holidaysData.userRequests.forEach((request) => {
        const startDate = new Date(request.startDate);
        const endDate = new Date(request.endDate);
        if (
            currentDate.getMonth() === currentMonth &&
            startDate <= currentDate &&
            endDate >= currentDate
        ) {
            if (request.status === 0) {
                dayStyles[currentDate.getDate()] = { backgroundColor: 'orange', cursor: 'not-allowed' };
            } else if (request.status === 1) {
                dayStyles[currentDate.getDate()] = { backgroundColor: 'green', cursor: 'not-allowed' };
            }
        }
         currentDate.setDate(currentDate.getDate() + 1);
    });
    return dayStyles;
};

const eventHandler = (value) => {
    console.log('Rango seleccionado:', value.start, ' - ', value.end);
    dragValue.value = value;
};

const requestHolidays = async () => {
  try {
    const request = {
      startDate: range.value.start,
      endDate: range.value.end,
      status: 1,
      userReason: "Holidays",
      userId: { id:userId }
    };

    const response = await RequestServices.post(request);

    console.log('Solicitud exitosa:', response.data);

    range.value.start = today;
    range.value.end = today;
  } catch (error) {
    console.error('Error al solicitar días:', error);
  }
};


/* 
try{
        const response = await RequestServices.getById(userId);
        const holidaysData = response.data;
        console.log(holidaysData);
        if (
        range >= (holidaysData && holidaysData.startDate) &&
        range <= (holidaysData && holidaysData.endDate)
        ) {
        if (holidaysData && holidaysData.status === 0) {
            return { backgroundColor: 'orange', cursor: 'not-allowed' };
        } else if (holidaysData && holidaysData.status === 1) {
            return { backgroundColor: 'green', cursor: 'not-allowed' };
        }
    }


const selectedSchool = ref(null);

const approvedHolidays = ref([]);
const pendingHolidays = ref([]);

const schoolHandler = (schoolId) => {
    console.log(schoolId);
    selectedSchool.value=schoolId;
    } 
//adaptar a bbdd
const eventHandler =(event) => {
    console.log(event);
    dragValue.value = event;
    //status: va con numeros (1=pendientes de aprobar, 2= aprobadas, 3= rechazadas, etc etc)
    const pendingStatus = 1; 
    
    const dateHolidays = {
        user_id: null,
        startDate: event.start, 
        endDate: event.end,
        status: pendingStatus,
        type: null,
        log_id: null,
    }
    holidaysData.value=dateHolidays;
}   */
</script>


<template>
   <div class="calendarContainer">
    <VDatePicker v-model.range="range"
        v-model:start="range.start"
        v-model:end="range.end"
        :select-attribute="selectDragAttribute" 
        :drag-attribute="selectDragAttribute"
        @drag="eventHandler($event)"
       
       >
        <template #day-popover="{ format }" :day-style="dayStyle">
            <div class="text-sm">
                {{ format(dragValue ? dragValue.start : range.start, 'MMM D') }}
                {{ format(dragValue ? dragValue.end : range.end, 'MMM D') }}
            </div>
        </template>
    </VDatePicker>
    <v-btn class="me-2" @click="requestHolidays">Solicitar días</v-btn> 

</div>
</template>


<style scoped>
.custom-select{
    background-color: #fabada;
}

.calendarContainer{
    display: flex;
    justify-content: center;
}


</style>



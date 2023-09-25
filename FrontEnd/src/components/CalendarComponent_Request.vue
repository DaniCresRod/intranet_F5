<script setup>
import { ref, computed } from 'vue';

const range = ref({
    start: new Date(2020, 0, 6),
    end: new Date(2020, 0, 10),
});
//const dragValue = ref(null);
const holidaysData = ref({
    user_id: null,
    startDate: null, 
    endDate: null,
    status: null,
    type: null,
    log_id: null,
});
const schools = ref([{
    name: "Asturias",
    id: 1,
    }, 
    {name: "Madrid",
    id: 2,}
])

const selectDragAttribute = computed(() => ({
    popover: {
        visibility: 'hover',
        isInteractive: false,
    },
}));
const dragValue = ref(null);
const selectedSchool = ref(null);

const schoolHandler = (school) => {
    console.log(school);
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
}

</script>


<template>
    <select id="school" class="custom-select" v-model="selectedSchool" @change="schoolHandler">
    
            <option v-for="(school, index) in schools" :value="school.id" placeholder="Escuelas">{{school.name}} </option>
            
    </select>
    <VDatePicker v-model.range="range" :select-attribute="selectDragAttribute" :drag-attribute="selectDragAttribute"
        @drag="eventHandler">
        <template #day-popover="{ format }">
            <div class="text-sm">
                {{ format(dragValue ? dragValue.start : range.start, 'MMM D') }}
        -
                {{ format(dragValue ? dragValue.end : range.end, 'MMM D') }}
            </div>
        </template>
    </VDatePicker>
</template>


<style scoped>
.custom-select{
    background-color: #fabada;
}
</style>
<script setup>
import { ref, computed, onBeforeMount } from 'vue';
import RequestServices from '../services/RequestServices'
import DaysRequestComponent from './DaysRequestComponent.vue';

const today=new Date();

const props = defineProps({
    id: {
        type: Number,
        required: true
    },

});
const user = ref(null);

const range = ref({
    start: today,
    end: new Date(),
});

let holidaysData = null;
const showConfirmation = ref(false);
const dragValue = ref(null);

const selectDragAttribute = computed(() => ({
    popover: {
        visibility: 'hover',
        isInteractive: false,
    },
}));

const attributes = ref([]);
const startDate = ref(); 
const endDate = ref();

const openConfirmationDialog = () => {
    showConfirmation.value = true;
    startDate.value = range.value.start;
    endDate.value = range.value.end;
};

async function getData() {
    try {
        const response = await RequestServices.getById(props.id);
        holidaysData = response.data;
        user.value = response.data.user;
        console.log(holidaysData);
        statusHolidays();
    } catch (error) {
        console.log(error);
    }
    
}
onBeforeMount(getData);

function statusHolidays() {
    attributes.value = [];
    if (!holidaysData || !holidaysData.userRequests) {
        return;
    }
    const currentMonth = range.value.start.getMonth();
    const currentYear = range.value.start.getFullYear();

    holidaysData.userRequests.forEach((userRequest) => {
        const startDate = new Date(userRequest.startDate);
        const endDate = new Date(userRequest.endDate);

        if (
            startDate.getFullYear() === currentYear &&
            startDate.getMonth() === currentMonth 
        ) {
            const currentDate = new Date(startDate);
            while(currentDate <= endDate){
            if (userRequest.status === 2) {
                attributes.value.push({
                    key: 'approved',
                    highlight: {
                        color: 'orange',
                        fillmode: 'solid',
                    },
                    date: new Date(currentDate),
                });
            } else if (userRequest.status === 1) {
                attributes.value.push({
                    key: 'pending',
                    highlight: {
                        color: 'green',
                        fillmode: 'light',
                        contentClass: 'italic',
                    },
                    date: new Date(currentDate),
                });
            }
            currentDate.setDate(currentDate.getDate() + 1);
        }
        }
    });
}

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
    :id="id"
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



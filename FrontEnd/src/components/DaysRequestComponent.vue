<script setup>
import { ref } from 'vue';
import RequestServices  from '../services/RequestServices'

const showConfirmation = ref(true);
const showSendForm = ref(false);
const { startDate, endDate } = defineProps(['startDate', 'endDate']);

const userId = 2;
const today=new Date();

let dayStart = startDate.getDate();
let monthStart = startDate.getMonth() + 1;
let fullYearStart = startDate.getFullYear();

let dayEnd = endDate.getDate();
let monthEnd = endDate.getMonth() + 1;
let fullYearEnd = endDate.getFullYear();


const confirmRequest = async () => {
    try {
      const request = {
        startDate: `${fullYearStart}-${monthStart}-${dayStart}`,
        endDate:`${fullYearEnd}-${monthEnd}-${dayEnd}`,
        status: 1,
        userReason: "Holidays",
        userId: { id:userId }
      };
  
    const response = await RequestServices.post(request);
  
    console.log('Solicitud exitosa:', response.data);
    startDate.value = today;
    endDate.value = today;

    } catch (error) {
      console.error('Error al solicitar días:', error);
    }
    showConfirmation.value = false;
    showSendForm.value=true;
  };

const cancelRequest = () => {
  showConfirmation.value = false;
}
</script>

<template>
    <v-dialog v-model="showConfirmation" class="customDialog">
      <v-card>
        <div class="py-2 text-center">
            <p style="font-weight: bold;">
                Vas a solicitar {{ Math.floor((endDate - startDate) / (1000 * 60 * 60 * 24)+1) === 1 ? " 1 día" : (Math.floor((endDate - startDate) / (1000 * 60 * 60 * 24)+1) + " días") }} </p>
            <p style="font-weight: bold;">¿Confirmar?</p>
        </div>
        <v-card-actions class="d-flex justify-center">
            <v-btn style="font-weight: bold;" @click="confirmRequest">Aceptar</v-btn>
            <v-btn style="font-weight: bold;" @click="cancelRequest">Rechazar</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-dialog v-model="showSendForm" class="customDialog">
        <v-card>
            <div class="py-2 text-center">
                <p style="font-weight: bold;">¡Solicitud enviada!</p>
            </div>
        </v-card>
    </v-dialog>
</template>


<style scoped>
.customDialog{
    max-width: 800px;
    height: auto;
}
</style>
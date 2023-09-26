<script setup>
import { ref } from 'vue';
import { VCard, VCardTitle, VCardSubtitle, VCardText } from "vuetify/dist/vuetify.min";
import CardInfoService from '../services/CardInfoService';

const data = ref(null);

async function getWorkerData() {
  const response = await CardInfoService.getWorkerData(props.id);
  data.value = response.data;
}

getWorkerData();
</script>

<template>
  <h1>HOLI</h1>
  <v-card>
    <v-card-title>
      <v-avatar size="60">
        <v-img :src="data.value.image" />
      </v-avatar>
      <v-card-subtitle>
        {{ data.value.name }}
      </v-card-subtitle>
    </v-card-title>
    <v-card-text>
      <v-row>
        <v-col cols="6">
          <strong>Puesto:</strong> {{ data.value.position }}
        </v-col>
        <v-col cols="6">
          <strong>Fecha de inicio:</strong> {{ data.value.start_date }}
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="6">
          <strong>Departamento:</strong> {{ data.value.department }}
        </v-col>
        <v-col cols="6">
          <strong>Escuela de procedencia:</strong> {{ data.value.school }}
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12">
          <strong>Supervisor:</strong> {{ data.value.supervisor }}
        </v-col>
      </v-row>
    </v-card-text>
  </v-card>
</template>

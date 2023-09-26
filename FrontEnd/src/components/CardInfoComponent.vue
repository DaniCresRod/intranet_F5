<script setup>
import axios from 'axios'
import { VCard, VCardTitle, VCardSubtitle, VCardText } from "vuetify/dist/vuetify.min";

const image = ref(null);

async function getImage() {
  const response = await axios.get(props.data.image);
  image.value = response.data;
}

getImage();
</script>

<template>
  <v-card>
    <v-card-title>
      <v-avatar size="60">
        <v-img :src="image" />
      </v-avatar>
      <v-card-subtitle>
        {{ data.name }}
      </v-card-subtitle>
    </v-card-title>
    <v-card-text>
      <v-row>
        <v-col cols="6">
          <strong>Puesto:</strong> {{ data.position }}
        </v-col>
        <v-col cols="6">
          <strong>Fecha de inicio:</strong> {{ data.start_date }}
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="6">
          <strong>Departamento:</strong> {{ data.department }}
        </v-col>
        <v-col cols="6">
          <strong>Escuela de procedencia:</strong> {{ data.school }}
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12">
          <strong>Supervisor:</strong> {{ data.supervisor }}
        </v-col>
      </v-row>
    </v-card-text>
  </v-card>
</template>

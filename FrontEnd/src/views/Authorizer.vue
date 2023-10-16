<script setup>
import CardInfoComponent from '../components/CardInfoComponent.vue';
import RequestListComponent from '../components/RequestListComponent.vue';
import CalendarComponent_Authorizer from '../components/CalendarComponent_Authorizer.vue';
import RequestServices from '@/services/RequestServices';
import schoolService from '../services/schoolService';

import { ref, onBeforeMount } from 'vue'

const userId = ref();
const schoolId = ref();
const data = ref([]);
const schoolInfo = ref([]);
const schoolData = ref([]);

onBeforeMount(async () => {
  userId.value = window.localStorage.getItem("myUser_Key");
  try {
    const response = await RequestServices.getById(userId.value);
    data.value = response.data;

    schoolInfo.value = await schoolService.getSchools();
    console.log(schoolInfo.value);
    searchSchoolID();
  } catch (error) {
    console.error('Error al obtener las escuelas:', error);
  }
});

const searchSchoolID = () => {
  schoolId.value = data.value.schoolID.id;
  schoolData.value = schoolInfo.value.find((school) => school.id === schoolId.value);
  console.log(schoolData.value);
  console.log(schoolId.value);
}
</script>

<template>
  <CardInfoComponent :id="parseInt(userId)"/>
  <RequestListComponent :schoolId="schoolId" :filterBySchoolId="true"/>
  <CalendarComponent_Authorizer :id="userId" :schoolId="schoolId" :schoolData="schoolData" />
</template>

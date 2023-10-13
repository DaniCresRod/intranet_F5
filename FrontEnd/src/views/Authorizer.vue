<script setup>
import CardInfoComponent from '../components/CardInfoComponent.vue';
import RequestListComponent from '../components/RequestListComponent.vue';
import CalendarComponent_Review from '../components/CalendarComponent_Review.vue';
import RequestServices from '@/services/RequestServices';
import { ref, onBeforeMount } from 'vue'

const userId=ref();
const schoolId=ref();
const schools = ref([]);
const data = ref([]);

onBeforeMount(async () => {
  userId.value=window.localStorage.getItem("myUser_Key");
  console.log(userId.value);
  try {
        schools.value = await RequestServices.getById(userId.value);
        data.value=schools.value;
        console.log(data.value);
        searchSchoolID();
    } catch (error) {
        console.error('Error al obtener las escuelas:', error);
    }
  });

  const searchSchoolID = () =>{
    const schoolID = data.value.data.schoolID.id;
    schoolId.value = schoolID;
    console.log(schoolId.value);
}

</script>

<template>
  <CardInfoComponent :id="parseInt(userId)"/>
  <RequestListComponent :schoolId="schoolId" :filterBySchoolId="true"/>
  <CalendarComponent_Review :userId="parseInt(userId)" :schoolId="schoolId" />
</template>

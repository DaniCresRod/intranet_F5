<script setup>
import RequestReviewComponent from '../components/RequestReviewComponent.vue';
import CalendarComponent_Review from '../components/CalendarComponent_Review.vue';

const props = defineProps({
  userId: {
    type: Number,
    required: true
  }
});

</script>
<template>
    <RequestReviewComponent :userId="props.userId"/>
    <CalendarComponent_Review/>
</template>
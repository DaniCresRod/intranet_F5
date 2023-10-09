<script setup>
import { ref, defineProps, defineEmits } from 'vue';

const { isOpen, data } = defineProps(['isOpen', 'data']);
const { close } = defineEmits(['closeDialog']);

const closeDialog = () => {
  close();
};
</script>

<template>
    <div class="dialog-overlay" v-if="isOpen">
      <div class="dialog">
        <h2>Informe de Modificación</h2>
        <div>
          <p>Se han modificado los siguientes datos:</p>
          <ul>
            <li v-for="(change, key) in changes" :key="key">
              {{ key }}: {{ change.oldValue }} → {{ change.newValue }}
            </li>
          </ul>
        </div>
        <button @click="closeDialog">Aceptar</button>
      </div>
    </div>
  </template>
  
  <style scoped>
  .dialog-overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 1000;
  }
  
  .dialog {
    background: white;
    padding: 2rem;
    border-radius: 5px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.26);
    max-width: 80%;
    text-align: center;
  }
  
  button {
    margin-top: 1rem;
    padding: 0.5rem 1rem;
    background: #007bff;
    color: white;
    border: none;
    cursor: pointer;
    border-radius: 3px;
  }
  </style>
  
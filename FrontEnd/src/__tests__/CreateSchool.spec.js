import { test, expect } from 'vitest'
import { shallowMount } from '@vue/test-utils'
import store from '../store' 
import CreateSchool from '../views/CreateSchool.vue' 

test('debería montarse correctamente', () => {
  const wrapper = shallowMount(CreateSchool, {
    global: {
      plugins: [store]
    }
  })
  expect(wrapper.exists()).toBe(true)
})

test('debería tener los campos del formulario correctos', () => {
  const wrapper = shallowMount(CreateSchool, {
    global: {
      plugins: [store]
    }
  })
  const schoolNameInput = wrapper.find('#schoolName')
  const addressInput = wrapper.find('#address')
  const phoneNumberInput = wrapper.find('#phoneNumber')
  const stateCodeSelect = wrapper.find('#stateCode')
  
  expect(schoolNameInput.exists()).toBe(true)
  expect(addressInput.exists()).toBe(true)
  expect(phoneNumberInput.exists()).toBe(true)
  expect(stateCodeSelect.exists()).toBe(true)
})

test('debería manejar el envío del formulario', async () => {
  const wrapper = shallowMount(CreateSchool, {
    global: {
      plugins: [store]
    }
  })
  
  // Simular la entrada de datos en los campos del formulario
  await wrapper.find('#schoolName').setValue('Escuela de Prueba')
  await wrapper.find('#address').setValue('Calle de Prueba, 123')
  await wrapper.find('#phoneNumber').setValue('123456789')
  await wrapper.find('#stateCode').setValue('AN')

  // Simular el envío del formulario
  await wrapper.find('form').trigger('submit.prevent')

  // Aquí puedes agregar expectativas para verificar si la función createSchool se llamó correctamente
})

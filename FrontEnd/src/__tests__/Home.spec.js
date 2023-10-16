import { test, expect } from 'vitest'
import { shallowMount } from '@vue/test-utils'
import store from '../store' 
import Home from '../views/Home.vue' 

test('debería montarse correctamente', () => {
  const wrapper = shallowMount(Home, {
    global: {
      plugins: [store]
    }
  })
  expect(wrapper.exists()).toBe(true)
})

test('debería tener los campos del formulario correctos', () => {
  const wrapper = shallowMount(Home, {
    global: {
      plugins: [store]
    }
  })
  const userField = wrapper.find('#usuario')
  const passwordField = wrapper.find('#password') 

  expect(userField.exists()).toBe(true)
  expect(passwordField.exists()).toBe(true)
})

test('debería manejar el envío del formulario', async () => {
  const wrapper = shallowMount(Home, {
    global: {
      plugins: [store]
    }
  })
  
  // Simular la entrada de datos en los campos del formulario
  await wrapper.find('#usuario').setValue('usuario de prueba')
  await wrapper.find('#password').setValue('contraseña de prueba')

  // Simular el envío del formulario
  await wrapper.find('form').trigger('submit.prevent')

  // Aquí puedes agregar expectativas para verificar si la función sendInfo se llamó correctamente
})

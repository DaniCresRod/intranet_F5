import { test, expect } from 'vitest'
import { shallowMount } from '@vue/test-utils'
import store from '../store' 
import AuthorizerCheck from '../views/AuthorizerCheck.vue' 

test('debería montarse correctamente', () => {
  const wrapper = shallowMount(AuthorizerCheck, {
    global: {
      plugins: [store]
    }
  })
  expect(wrapper.exists()).toBe(true)
})

test('debería obtener el userId del localStorage', () => {
  window.localStorage.setItem("myUser_Key", "12345");
  const wrapper = shallowMount(AuthorizerCheck, {
    global: {
      plugins: [store]
    }
  })
  expect(wrapper.vm.userId).toBe("12345")
})

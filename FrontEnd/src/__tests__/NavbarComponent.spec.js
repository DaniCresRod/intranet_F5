import { test, expect } from 'vitest'
import { shallowMount } from '@vue/test-utils'
import store from '../store' 
import NavbarComponent from '../components/NavbarComponent.vue' 

test('debería montarse correctamente', () => {
  const wrapper = shallowMount(NavbarComponent, {
    global: {
      plugins: [store]
    }
  })
  expect(wrapper.exists()).toBe(true)
})

test('debería renderizar el título correctamente', () => {
  const wrapper = shallowMount(NavbarComponent, {
    global: {
      plugins: [store]
    }
  })
  expect(wrapper.find('.navbar__img').exists()).toBe(true)
})

test('debería tener los enlaces correctos', () => {
  const wrapper = shallowMount(NavbarComponent, {
    global: {
      plugins: [store]
    }
  })
  const links = wrapper.findAll('a')
  expect(links.length).toBe(11) 
})

test('debería tener el estado inicial correcto', () => {
  const wrapper = shallowMount(NavbarComponent, {
    global: {
      plugins: [store]
    }
  })
  expect(wrapper.vm.userId).toBe(window.localStorage.getItem("myUser_Key"))
})


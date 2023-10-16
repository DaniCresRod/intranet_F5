import { test, expect } from 'vitest'
import { shallowMount } from '@vue/test-utils'
import store from '../store' 
import RequestListComponent from '../components/RequestListComponent.vue' 

test('debería montarse correctamente', () => {
  const wrapper = shallowMount(RequestListComponent, {
    global: {
      plugins: [store]
    }
  })
  expect(wrapper.exists()).toBe(true)
})

test('debería renderizar el título correctamente', () => {
  const wrapper = shallowMount(RequestListComponent, {
    global: {
      plugins: [store]
    }
  })
  expect(wrapper.find('.title').text()).toBe('Listado de Solicitudes')
})

test('debería tener los enlaces correctos', () => {
  const wrapper = shallowMount(RequestListComponent, {
    global: {
      plugins: [store]
    }
  })
  const links = wrapper.findAll('a')
  expect(links.length).toBe(0)
})

test('debería tener el estado inicial correcto', () => {
  const wrapper = shallowMount(RequestListComponent, {
    global: {
      plugins: [store]
    }
  })
  expect(wrapper.vm.requests).toEqual([])
})


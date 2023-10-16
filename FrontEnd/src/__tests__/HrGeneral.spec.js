import { test, expect } from 'vitest'
import { shallowMount } from '@vue/test-utils'
import store from '../store' 
import HrGeneral from '../views/HrGeneral.vue' 

test('debería montarse correctamente', () => {
  const wrapper = shallowMount(HrGeneral, {
    global: {
      plugins: [store]
    }
  })
  expect(wrapper.exists()).toBe(true)
})

test('debería renderizar el componente CardInfoComponent', () => {
  const wrapper = shallowMount(HrGeneral, {
    global: {
      plugins: [store]
    }
  })
  const cardInfoComponent = wrapper.findComponent({ name: 'CardInfoComponent' })
  expect(cardInfoComponent.exists()).toBe(true)
})

test('debería tener los enlaces correctos', () => {
  const wrapper = shallowMount(HrGeneral, {
    global: {
      plugins: [store]
    }
  })
  const links = wrapper.findAll('a')
  expect(links.length).toBe(0) 
})

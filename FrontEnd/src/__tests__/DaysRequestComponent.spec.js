import { test, expect } from 'vitest'
import { shallowMount } from '@vue/test-utils'
import store from '../store' 
import DaysRequestComponent from '../components/DaysRequestComponent.vue' 

test('debería montarse correctamente', () => {
  const wrapper = shallowMount(DaysRequestComponent, {
    props: {
      id: 1,
      startDate: new Date(),
      endDate: new Date(),
    },
    global: {
      plugins: [store]
    }
  })
  expect(wrapper.exists()).toBe(true)
})

test('debería tener el estado inicial correcto', () => {
  const wrapper = shallowMount(DaysRequestComponent, {
    props: {
      id: 1,
      startDate: new Date(),
      endDate: new Date(),
    },
    global: {
      plugins: [store]
    }
  })
  expect(wrapper.vm.showConfirmation).toBe(true)
  expect(wrapper.vm.showSendForm).toBe(false)
})


import { test, expect } from 'vitest'
import { shallowMount } from '@vue/test-utils'
import store from '../store' 
import HrReview from '../views/HrReview.vue' 

test('debería montarse correctamente', () => {
  const wrapper = shallowMount(HrReview, {
    global: {
      plugins: [store]
    }
  })
  expect(wrapper.exists()).toBe(true)
})

test('debería renderizar el componente CardInfoComponent', () => {
  const wrapper = shallowMount(HrReview, {
    global: {
      plugins: [store]
    }
  })
  const cardInfoComponent = wrapper.findComponent({ name: 'CardInfoComponent' })
  expect(cardInfoComponent.exists()).toBe(true)
})

test('debería renderizar el componente RequestListComponent', () => {
  const wrapper = shallowMount(HrReview, {
    global: {
      plugins: [store]
    }
  })
  const requestListComponent = wrapper.findComponent({ name: 'RequestListComponent' })
  expect(requestListComponent.exists()).toBe(true)
})

test('debería renderizar el componente CalendarComponent_Review', () => {
  const wrapper = shallowMount(HrReview, {
    global: {
      plugins: [store]
    }
  })
  const calendarComponentReview = wrapper.findComponent({ name: 'CalendarComponent_Review' })
  expect(calendarComponentReview.exists()).toBe(true)
})
 
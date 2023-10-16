import { test, expect } from 'vitest'
import { shallowMount } from '@vue/test-utils'
import store from '../store' 
import EmployeeView from '../views/Employee.vue' 

test('debería montarse correctamente', () => {
  const wrapper = shallowMount(EmployeeView, {
    global: {
      plugins: [store]
    }
  })
  expect(wrapper.exists()).toBe(true)
})

test('debería renderizar el componente CardInfoWorkerComponent', () => {
  const wrapper = shallowMount(EmployeeView, {
    global: {
      plugins: [store]
    }
  })
  const cardInfoWorkerComponent = wrapper.findComponent({ name: 'CardInfoWorkerComponent' })
  expect(cardInfoWorkerComponent.exists()).toBe(true)
})

test('debería renderizar el componente VacationHistoryComponent', () => {
  const wrapper = shallowMount(EmployeeView, {
    global: {
      plugins: [store]
    }
  })
  const vacationHistoryComponent = wrapper.findComponent({ name: 'VacationHistoryComponent' })
  expect(vacationHistoryComponent.exists()).toBe(true)
})

test('debería renderizar el componente CalendarRequestComponent', () => {
  const wrapper = shallowMount(EmployeeView, {
    global: {
      plugins: [store]
    }
  })
  const calendarRequestComponent = wrapper.findComponent({ name: 'CalendarRequestComponent' })
  expect(calendarRequestComponent.exists()).toBe(true)
})
 
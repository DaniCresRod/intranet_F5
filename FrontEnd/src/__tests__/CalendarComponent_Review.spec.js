import { test, expect } from 'vitest'
import { mount } from '@vue/test-utils'
import Component from '../components/CalendarComponent_Review.vue' 

test('debería montarse correctamente', () => {
  const wrapper = mount(Component)
  expect(wrapper.exists()).toBe(true)
})

test('debería tener el estado inicial correcto', () => {
  const wrapper = mount(Component)
  expect(wrapper.vm.escuelaSeleccionada).toBe('')
  expect(wrapper.vm.schools).toEqual([])
  expect(wrapper.vm.user_school).toBe('')
  expect(wrapper.vm.mesSeleccionado).toBe('')
})

test('debería calcular correctamente los días del mes', () => {
  const wrapper = mount(Component)
  wrapper.vm.mesSeleccionado = 'enero'
  expect(wrapper.vm.diasDelMes).toEqual(Array.from({ length: 31 }, (_, index) => (index + 1).toString().padStart(2, '0')))
})

test('mostrarEvento debería devolver "X" si el día está en las solicitudes de días libres del usuario', () => {
  const wrapper = mount(Component)
  const usuario = {
    userRequests: [
      { startDate: new Date(2023, 0, 1), endDate: new Date(2023, 0, 31) }
    ]
  }
  expect(wrapper.vm.mostrarEvento(usuario, '01')).toBe('X')
})

test('mostrarEvento debería devolver "" si el día no está en las solicitudes de días libres del usuario', () => {
  const wrapper = mount(Component)
  const usuario = {
    userRequests: [
      { startDate: new Date(2023, 1, 1), endDate: new Date(2023, 1, 28) }
    ]
  }
  expect(wrapper.vm.mostrarEvento(usuario, '01')).toBe('')
})

import { test, expect } from 'vitest'
import { shallowMount } from '@vue/test-utils'
import CalendarRequestComponent from '../components/CalendarRequestComponent.vue' 

test('debería montarse correctamente', () => {
  const wrapper = shallowMount(CalendarRequestComponent, {
    props: {
      id: 1
    }
  })
  expect(wrapper.exists()).toBe(true)
})

test('debería tener el estado inicial correcto', () => {
  const wrapper = shallowMount(CalendarRequestComponent, {
    props: {
      id: 1
    } 
  })
  expect(wrapper.vm.user).toBe(null)
  expect(wrapper.vm.range.start).toBeInstanceOf(Date)
  expect(wrapper.vm.range.end).toBeInstanceOf(Date)
  expect(wrapper.vm.showConfirmation).toBe(false)
})

test('openConfirmationDialog debería cambiar showConfirmation a true y establecer las fechas de inicio y fin', () => {
  const wrapper = shallowMount(CalendarRequestComponent, {
    props: {
      id: 1
    }
  })
  wrapper.vm.openConfirmationDialog()
  expect(wrapper.vm.showConfirmation).toBe(true)
  expect(wrapper.vm.startDate).toBe(wrapper.vm.range.start)
  expect(wrapper.vm.endDate).toBe(wrapper.vm.range.end)
})

// Aquí puedes añadir más tests según tus necesidades
// Por ejemplo, podrías comprobar si ciertos elementos se renderizan correctamente en función de los datos proporcionados

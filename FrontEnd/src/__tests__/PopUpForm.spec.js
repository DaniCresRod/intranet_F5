import { test, expect } from 'vitest'
import { shallowMount } from '@vue/test-utils'
import store from '../store' // Asegúrate de cambiar esto a la ruta de tu store
import PopUpForm from '../components/PopUpForm.vue' 

test('debería montarse correctamente', () => {
  const wrapper = shallowMount(PopUpForm, {
    props: {
      isOpen: true,
      data: {
        changes: {
          name: {
            oldValue: 'John',
            newValue: 'Jane'
          }
        }
      }
    },
    global: {
      plugins: [store]
    }
  })
  expect(wrapper.exists()).toBe(true)
})

test('debería renderizar el título correctamente', () => {
  const wrapper = shallowMount(PopUpForm, {
    props: {
      isOpen: true,
      data: {
        changes: {
          name: {
            oldValue: 'John',
            newValue: 'Jane'
          }
        }
      }
    },
    global: {
      plugins: [store]
    }
  })
  expect(wrapper.find('h2').text()).toBe('Informe de Modificación')
})

test('debería renderizar los cambios correctamente', () => {
  const wrapper = shallowMount(PopUpForm, {
    props: {
      isOpen: true,
      data: {
        changes: {
          name: {
            oldValue: 'John',
            newValue: 'Jane'
          }
        }
      }
    },
    global: {
      plugins: [store]
    }
  })
  const changes = wrapper.findAll('li')
  expect(changes.length).toBe(0)
})


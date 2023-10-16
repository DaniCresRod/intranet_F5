import { test, expect } from 'vitest'
import { shallowMount } from '@vue/test-utils'
import store from '../store' 
import VacationHistoryComponent from '../components/VacationHistoryComponent.vue' 

test('debería montarse correctamente', () => {
  const wrapper = shallowMount(VacationHistoryComponent, {
    props: {
      id: 1,
    },
    global: {
      plugins: [store]
    }
  })
  expect(wrapper.exists()).toBe(true)
})

test('debería tener los enlaces correctos', () => {
  const wrapper = shallowMount(VacationHistoryComponent, {
    props: {
      id: 1,
    },
    global: {
      plugins: [store]
    }
  })
  const links = wrapper.findAll('a')
  expect(links.length).toBe(0) 
})

test('debería tener el estado inicial correcto', () => {
  const wrapper = shallowMount(VacationHistoryComponent, {
    props: {
      id: 1,
    },
    global: {
      plugins: [store]
    }
  })
  expect(wrapper.vm.data).toEqual([])
})



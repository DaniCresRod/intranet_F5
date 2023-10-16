import { test, expect } from 'vitest'
import { shallowMount } from '@vue/test-utils'
import store from '../store'
import CardInfoComponent from '../components/CardInfoComponent.vue' 

test('debería montarse correctamente', () => {
  const wrapper = shallowMount(CardInfoComponent, {
    props: {
      id: 1
    },
    global: {
      plugins: [store]
    }
  })
  expect(wrapper.exists()).toBe(true)
})

test('debería tener el estado inicial correcto', () => {
  const wrapper = shallowMount(CardInfoComponent, {  
    props: {
      id: 1
    }, 
    global: {
      plugins: [store]
    }  
  })
  expect(wrapper.vm.data).toBe(undefined)
  expect(wrapper.vm.loading).toBe(true)
})

test('getUserImageSrc debería devolver una cadena de imagen base64', () => {
  const wrapper = shallowMount(CardInfoComponent, {
    props: {
      id: 1
    },
    global: {
      plugins: [store]
    }
  })
  const base64ImageData = 'iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg=='
  expect(wrapper.vm.getUserImageSrc(base64ImageData)).toBe(`data:image/jpeg;base64,${base64ImageData}`)
})

// Aquí puedes añadir más tests según tus necesidades


import { test, expect } from 'vitest'
import { shallowMount } from '@vue/test-utils'
import store from '../store' 
import AuthorizerView from '../views/Authorizer.vue' 

test('debería montarse correctamente', () => {
  const wrapper = shallowMount(AuthorizerView, {
    global: {
      plugins: [store]
    }
  })
  expect(wrapper.exists()).toBe(true)
})

test('debería renderizar el componente CardInfoComponent', () => {
  const wrapper = shallowMount(AuthorizerView, {
    global: {
      plugins: [store]
    }
  })
  const cardInfoComponent = wrapper.findComponent({ name: 'CardInfoComponent' })
  expect(cardInfoComponent.exists()).toBe(true)
})

test('debería renderizar el componente RequestListComponent', () => {
  const wrapper = shallowMount(AuthorizerView, {
    global: {
      plugins: [store]
    }
  })
  const requestListComponent = wrapper.findComponent({ name: 'RequestListComponent' })
  expect(requestListComponent.exists()).toBe(true)
})

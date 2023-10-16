import { test, expect } from 'vitest'
import { shallowMount } from '@vue/test-utils'
import store from '../store' 
import FooterComponent from '../components/FooterComponent.vue' 

test('debería montarse correctamente', () => {
  const wrapper = shallowMount(FooterComponent, {
    global: {
      plugins: [store]
    }
  })
  expect(wrapper.exists()).toBe(true)
})

test('debería renderizar el título correctamente', () => {
  const wrapper = shallowMount(FooterComponent, {
    global: {
      plugins: [store]
    }
  })
  expect(wrapper.find('.footer__title').text()).toBe('rompemos los códigos')
})

 
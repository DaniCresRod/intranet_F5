import { test, expect } from 'vitest'
import { shallowMount } from '@vue/test-utils'
import store from '../store' 
import EditUserView from '../views/EditUser.vue' 

test('debería montarse correctamente', () => {
  const wrapper = shallowMount(EditUserView, {
    global: {
      plugins: [store]
    }
  })
  expect(wrapper.exists()).toBe(true)
})

test('debería tener los campos del formulario correctos', () => {
  const wrapper = shallowMount(EditUserView, {
    global: {
      plugins: [store]
    }
  })
  const userNameInput = wrapper.find('#user_name')
  const userSurnameInput = wrapper.find('#user_surname')
  const userNifInput = wrapper.find('#user_nif')
  const userEmailInput = wrapper.find('#user_email')
  const userPhoneInput = wrapper.find('#user_phone')
  const userBirthdayInput = wrapper.find('#user_birthday')
  const userStartDateInput = wrapper.find('#user_startDate')
  const userEndDateInput = wrapper.find('#user_endDate')
  const userPassInput = wrapper.find('#user_pass')
  const userTypeInput = wrapper.find('#user_type')
  const userSchoolSelect = wrapper.find('#user_school')

  
  expect(userNameInput.exists()).toBe(true)
  expect(userSurnameInput.exists()).toBe(true)
  expect(userNifInput.exists()).toBe(true)
  expect(userEmailInput.exists()).toBe(true)
  expect(userPhoneInput.exists()).toBe(true)
  expect(userBirthdayInput.exists()).toBe(true)
  expect(userStartDateInput.exists()).toBe(true)
  expect(userEndDateInput.exists()).toBe(true)
  expect(userPassInput.exists()).toBe(true)
  expect(userTypeInput.exists()).toBe(true)
  expect(userSchoolSelect.exists()).toBe(true)
})



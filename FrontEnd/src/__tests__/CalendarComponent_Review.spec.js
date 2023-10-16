import { test, expect } from 'vitest'
import { mount } from '@vue/test-utils'
import Component from '../components/CalendarComponent_Review.vue' 

test('should mount correctly', () => {
  const wrapper = mount(Component)
  expect(wrapper.exists()).toBe(true)
})

test('showEvent should return "" if the day is not in the user\'s free day requests', () => {
  const wrapper = mount(Component)
  const user = {
    userRequests: [
      { startDate: new Date(2023, 1, 1), endDate: new Date(2023, 1, 28) }
    ]
  }
  expect(wrapper.vm.showEvent(user, '01')).toBe('')
})

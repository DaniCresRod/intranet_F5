import { createRouter, createWebHistory } from 'vue-router'
import AuthorizerView from '../views/AuthorizerView.vue'
import EmployeeView from '../views/EmployeeView.vue'
import HomeView from '../views/HomeView.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/AuthorizerView',
      name: 'authorizer',
      component: AuthorizerView
    },
    {
      path: '/EmployeeView',
      name: 'employee',
      component: EmployeeView
    },
  ]
})

export default router

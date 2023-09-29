import { createRouter, createWebHistory } from 'vue-router'
import AuthorizerView from '../views/AuthorizerView.vue'
import EmployeeView from '../views/EmployeeView.vue'
import HomeView from '../views/HomeView.vue'
import HrGeneral from '../views/HrGeneral.vue'
import CreateSchoolView from '../views/CreateSchoolView.vue'


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
    {
      path: '/HrGeneral',
      name: 'hr',
      component: HrGeneral
    },
    {
      path: '/createschool',
      name: 'createschool',
      component: CreateSchoolView
    },
  ]
})

export default router

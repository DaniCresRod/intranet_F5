import { createRouter, createWebHistory } from 'vue-router'
import AuthoriserView from '../views/AuthorizerView.vue'
import WorkerView from '../views/EmployeeView.vue'
import HomeView from '../views/HomeView.vue'
import HrGeneral from '../views/HrGeneral.vue'


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
      component: AuthoriserView
    },
    {
      path: '/EmployeeView',
      name: 'employee',
      component: WorkerView
    },
    {
      path: '/HrGeneral',
      name: 'hr',
      component: HrGeneral
    },
  ]
})

export default router

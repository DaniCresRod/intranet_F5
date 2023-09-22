import { createRouter, createWebHistory } from 'vue-router'
import AuthoriserView from '../views/AuthoriserView.vue'
import WorkerView from '../views/WorkerView.vue'
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
      path: '/AuthoriserView',
      name: 'Authoriser',
      component: AuthoriserView
    },
    {
      path: '/WorkerView',
      name: 'Worker',
      component: WorkerView
    },
  ]
})

export default router

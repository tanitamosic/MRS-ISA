import { createRouter, createWebHistory } from 'vue-router'
import Registration from '../view/Registration-Component.vue'
import CottageOverview from '../view/CottageOverview.vue'


const routes = [
  {
    path: '/',
    name: 'CottageOverview',
    component: CottageOverview
  },
  {
    path: '/register',
    name: 'Registration',
    component: Registration
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router

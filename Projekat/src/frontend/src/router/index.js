import { createRouter, createWebHashHistory } from 'vue-router'
import Registration from '../view/Registration-Component.vue'
import CottageOverview from '../view/CottageOverview.vue'
import Login from '../view/Login.vue'
import AdminProfileView from '../view/AdminProfileView.vue'
import CottageDetails from '@/view/CottageDetails.vue'


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
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/admin',
    name: 'AdminProfileView',
    component: AdminProfileView
  },
  {
    path: '/CottageDetails/:id?',
    name: 'CottageDetails',
    component: CottageDetails
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router

import { createRouter, createWebHashHistory } from 'vue-router'
import Registration from '../view/Registration-Component.vue'
import CottageOverview from '../view/CottageOverview.vue'
import Login from '../view/Login.vue'
import AdminProfileView from '../view/AdminProfileView.vue'
import CottageDetails from '@/view/CottageDetails.vue'
import BoatsOverview from '@/view/BoatsOverview.vue'
import BoatDetails from '@/view/BoatDetails.vue'
import AdventureOverview from '@/view/AdventureOverview.vue'


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
  },
  {
    path: '/BoatsOverview',
    name: 'BoatsOverview',
    component: BoatsOverview
  },
  {
    path: '/BoatDetails/:id?',
    name: 'BoatDetails',
    component: BoatDetails
  },
  {
    path: '/AdventureOverview',
    name: 'AdventureOverview',
    component: AdventureOverview
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router

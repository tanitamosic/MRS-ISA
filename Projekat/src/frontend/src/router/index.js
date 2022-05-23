import { createRouter, createWebHashHistory } from 'vue-router'
import Registration from '../view/Registration-Component.vue'
import CottageOverview from '../view/CottageOverview.vue'
import Login from '../view/Login.vue'
import Proba from '../view/ProbaView.vue'
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
    path: '/novaKomponenta',
    name: 'NekaNovaKomponenta',
    component: Proba
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

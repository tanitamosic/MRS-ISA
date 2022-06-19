import { createRouter, createWebHashHistory } from 'vue-router'
import Registration from '../view/Registration-Component.vue'
import CottageOverview from '../view/CottageOverview.vue'
import Login from '../view/Login.vue'
import AdminProfileView from '../view/AdminProfileView.vue'
import CottageDetails from '@/view/CottageDetails.vue'
import BoatsOverview from '@/view/BoatsOverview.vue'
import BoatDetails from '@/view/BoatDetails.vue'
import AdventureOverview from '@/view/AdventureOverview.vue'
import AdventureDetails from '@/view/AdventureDetails.vue'
import AdminPendingRegs from '@/view/AdminPendingRegs.vue'
import AdminDelRequests from '@/view/AdminDelRequests.vue'
import AdminComplaints from '@/view/AdminComplaints.vue'
import AdminCottages from '@/view/AdminCotOverview.vue'
import AdminBoats from '@/view/AdminBoatOverview.vue'
import AdminAdventures from '@/view/AdminAdvOverview.vue'
import COProfileView from '@/view/CottageOwnerProfileView.vue'
import COCottages from '@/view/CottageOwnerOverview.vue'
import CottageProfileOverview from '@/view/CottageProfileOverview'


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
    path: '/admin/profile',
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
  },
  {
    path: '/AdventureDetails/:id?',
    name: 'AdventureDetails',
    component: AdventureDetails
  },
  {
    path: '/admin/pendingreg',
    name: 'AdminPendingRegs',
    component: AdminPendingRegs
  },
  {
    path: '/admin/delreq',
    name: 'AdminDelRequests',
    component: AdminDelRequests
  },
  {
    path: '/admin/complaints',
    name: 'AdminComplaints',
    component: AdminComplaints
  },
  {
    path: '/admin/cottage-overview',
    name: 'AdminCottages',
    component: AdminCottages
  },
  {
    path: '/admin/boat-overview',
    name: 'AdminBoats',
    component: AdminBoats
  },
  {
    path: '/admin/adventure-overview',
    name: 'AdminAdventures',
    component: AdminAdventures
  },
  {
    path: '/co/profile/',
    name: 'COProfileView',
    component: COProfileView
  },
  {
    path: '/co/cottage-overview/',
    name: 'COCottages',
    component: COCottages
  },
  {
    path: '/co/cottage-profile/:id?',
    name: 'CottageProfileOverview',
    component: CottageProfileOverview
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router

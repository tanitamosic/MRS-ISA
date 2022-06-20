import { createRouter, createWebHashHistory } from 'vue-router'
import Registration from '../view/Registration-Component.vue'
import CottageOverview from '../view/CottageOverview.vue'
import Login from '../view/Login.vue'
import CottageDetails from '@/view/CottageDetails.vue'
import BoatsOverview from '@/view/BoatsOverview.vue'
import BoatDetails from '@/view/BoatDetails.vue'
import AdventureOverview from '@/view/AdventureOverview.vue'
import AdventureDetails from '@/view/AdventureDetails.vue'

import ProfileView from '@/components/ProfileView.vue';

import AdminSidebar from "@/components/AdminSidebar.vue";
import PendingRegistrations from '@/components/PendingRegistrations.vue'
import AccountDelRequests from '@/components/AccountDelRequests.vue'
import AdminRegistration from '@/components/AdminRegistration.vue'
import ComplaintResponse from '@/components/ComplaintResponse.vue'
import ProfitsComponent from '@/components/ProfitsComponent.vue'
import UserOverview from '@/components/UserOverview.vue'

import InstructorSidebar from '@/components/InstructorSidebar.vue'
import AdventureCreator from '@/components/CreateAdventure.vue'
import InstructorsAdventures from '@/components/InstructorsAdventures.vue'
import InstructorAdventureDetails from '@/components/InstructorAdventureDetails.vue'

const routes = [
  {
    path: '/',
    name: 'CottageOverview',
    components: {
      UnloggedContent: CottageOverview
    }
  },
  {
    path: '/register',
    name: 'Registration',
    components: {
      UnloggedContent: Registration
    }
  },
  {
    path: '/login',
    name: 'Login',
    components: {
      UnloggedContent: Login
    }
  },
  {
    path: '/CottageDetails/:id?',
    name: 'CottageDetails',
    components: {UnloggedContent: CottageDetails}
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
    path: '/admin/profile',
    components: {
      LeftSidebar: AdminSidebar,
      MainContent: ProfileView
    } // DONE
  },
  {
    path: '/admin/pendingreg',
    components: {
      LeftSidebar: AdminSidebar,
      MainContent: PendingRegistrations
    } // DONE
  },
  {
    path: '/admin/delreq',
    components: {
      LeftSidebar: AdminSidebar,
      MainContent: AccountDelRequests
    } // DONE 
  },
  {
    path: '/admin/complaints',
    components: {
      LeftSidebar: AdminSidebar,
      MainContent: ComplaintResponse
    } // Done
  },
  {
    path: '/admin/cottage-overview',
    components: {
      LeftSidebar: AdminSidebar,
      MainContent: CottageOverview
    } // DONE
  },
  {
    path: '/admin/boat-overview',
    components: {
      LeftSidebar: AdminSidebar,
      MainContent: BoatsOverview
    } // DONE
  },
  {
    path: '/admin/adventure-overview',
    components: {
      LeftSidebar: AdminSidebar,
      MainContent: AdventureOverview
    } // DONE
  },
  {
    path: '/admin/admin-registration',
    components: {
      LeftSidebar: AdminSidebar,
      MainContent: AdminRegistration
    } // DONE
  },
  {
    path: '/admin/new-admin-pass-reset',
    components: {
      UnloggedContent: AccountDelRequests
    } // DONE
  },
  {
    path: '/admin/users',
    components: {
      LeftSidebar: AdminSidebar,
      MainContent: UserOverview
    } // DONE
  },
  {
    path: '/admin/profits',
    components: {
      LeftSidebar: AdminSidebar,
      MainContent: ProfitsComponent
    } // DONE
  },
  {
    path: '/instructor/profile',
    components: {
      LeftSidebar: InstructorSidebar,
      MainContent: ProfileView
    }
  },
  {
    path: '/instructor/profits',
    components: {
      LeftSidebar: InstructorSidebar,
      MainContent: ProfitsComponent
    },
  },
  {
    path: '/instructor/create-adventure',
    components: {
      LeftSidebar: InstructorSidebar,
      MainContent: AdventureCreator
    }
  },
  {
    path: '/instructor/view-adventures',
    components: {
      LeftSidebar: InstructorSidebar,
      MainContent: InstructorsAdventures
    }
  },
  {
    path: '/instructor/view-adventure-details/:id?',
    components: {
      LeftSidebar: InstructorSidebar,
      MainContent: InstructorAdventureDetails
    }
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router

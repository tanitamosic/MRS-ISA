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
import ClientSidebar from '@/components/ClientSidebar.vue'
import PendingRegistrations from '@/components/PendingRegistrations.vue'
import AccountDelRequests from '@/components/AccountDelRequests.vue'
import AdminRegistration from '@/components/AdminRegistration.vue'
import ComplaintResponse from '@/components/ComplaintResponse.vue'
import ProfitsComponent from '@/components/ProfitsComponent.vue'
import UserOverview from '@/components/UserOverview.vue'
import ReservationsOverview from '@/view/ReservationsOverview.vue'
import HistoricalReservationsOverview from '@/view/HistoricalReservationsOverview.vue'


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
    components: {
      UnloggedContent: CottageDetails
    }
  },
  {
    path: '/BoatsOverview',
    name: 'BoatsOverview',
    components: {
      UnloggedContent: BoatsOverview
    }
  },
  {
    path: '/BoatDetails/:id?',
    name: 'BoatDetails',
    components: {
      UnloggedContent: BoatDetails
    }
  },
  {
    path: '/AdventureOverview',
    name: 'AdventureOverview',
    components: {
      UnloggedContent: AdventureOverview
    }
  },
  {
    path: '/AdventureDetails/:id?',
    name: 'AdventureDetails',
    components: {
      UnloggedContent: AdventureDetails
    } 
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
    path: '/client/profile',
    components: {
      LeftSidebar: ClientSidebar,
      MainContent: ProfileView
    }
  },
  {
    path: '/client/cottages',
    components: {
      LeftSidebar: ClientSidebar,
      MainContent: CottageOverview
    }
  },
  {
    path: '/client/boats',
    components: {
      LeftSidebar: ClientSidebar,
      MainContent: BoatsOverview
    }
  },
  {
    path: '/client/adventures',
    components: {
      LeftSidebar: ClientSidebar,
      MainContent: AdventureOverview
    }
  },
  {
    path: '/client/CottageDetails/:id?',
    components: {
      LeftSidebar: ClientSidebar,
      MainContent: CottageDetails
    }
  },
  {
    path: '/client/AdventureDetails/:id?',
    components: {
      LeftSidebar: ClientSidebar,
      MainContent: AdventureDetails
    }
  },
  {
    path: '/client/BoatDetails/:id?',
    components: {
      LeftSidebar: ClientSidebar,
      MainContent: BoatDetails
    }
  },
  {
    path: '/client/activeReservations',
    components: {
      LeftSidebar: ClientSidebar,
      MainContent: ReservationsOverview
    }
  },
  {
    path: '/client/historicalReservations',
    components: {
      LeftSidebar: ClientSidebar,
      MainContent: HistoricalReservationsOverview
    }
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router

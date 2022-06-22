import { createRouter, createWebHashHistory } from 'vue-router'
import UnloggedNavBar from '@/components/Navbars/UnloggedNavBar.vue'
import LoggedNavBar from '@/components/Navbars/LoggedNavbar.vue'

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

import InstructorSidebar from '@/components/InstructorSidebar.vue'
import AdventureCreator from '@/components/CreateAdventure.vue'
import InstructorsAdventures from '@/components/InstructorsAdventures.vue'
import InstructorAdventureDetails from '@/components/InstructorAdventureDetails.vue'
import InstCompletedReservations from '@/components/InstructorHistory.vue'
import InstructorGraphicProfits from '@/components/InstructorGraphicProfits.vue'

const routes = [
  {
    path: '/',
    name: 'CottageOverview',
    components: {
      Navbar: UnloggedNavBar,
      UnloggedContent: CottageOverview
    }
  },
  {
    path: '/register',
    name: 'Registration',
    components: {
      Navbar: UnloggedNavBar,
      UnloggedContent: Registration
    }
  },
  {
    path: '/login',
    name: 'Login',
    components: {
      Navbar: UnloggedNavBar,
      UnloggedContent: Login
    }
  },
  {
    path: '/CottageDetails/:id?',
    name: 'CottageDetails',
    components: {
      Navbar: UnloggedNavBar,
      UnloggedContent: CottageDetails
    }
  },
  {
    path: '/BoatsOverview',
    name: 'BoatsOverview',
    components: {
      Navbar: UnloggedNavBar,
      UnloggedContent: BoatsOverview
    }
  },
  {
    path: '/BoatDetails/:id?',
    name: 'BoatDetails',
    components: {
      Navbar: UnloggedNavBar,
      UnloggedContent: BoatDetails
    }
  },
  {
    path: '/AdventureOverview',
    name: 'AdventureOverview',
    components: {
      Navbar: UnloggedNavBar,
      UnloggedContent: AdventureOverview
    }
  },
  {
    path: '/AdventureDetails/:id?',
    name: 'AdventureDetails',
    components: {
      Navbar: UnloggedNavBar,
      UnloggedContent: AdventureDetails
    } 
  },
  {
    path: '/admin/profile',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: AdminSidebar,
      MainContent: ProfileView
    } // DONE
  },
  {
    path: '/admin/pendingreg',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: AdminSidebar,
      MainContent: PendingRegistrations
    } // DONE
  },
  {
    path: '/admin/delreq',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: AdminSidebar,
      MainContent: AccountDelRequests
    } // DONE 
  },
  {
    path: '/admin/complaints',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: AdminSidebar,
      MainContent: ComplaintResponse
    } // Done
  },
  {
    path: '/admin/cottage-overview',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: AdminSidebar,
      MainContent: CottageOverview
    } // DONE
  },
  {
    path: '/admin/boat-overview',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: AdminSidebar,
      MainContent: BoatsOverview
    } // DONE
  },
  {
    path: '/admin/adventure-overview',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: AdminSidebar,
      MainContent: AdventureOverview
    } // DONE
  },
  {
    path: '/admin/admin-registration',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: AdminSidebar,
      MainContent: AdminRegistration
    } // DONE
  },
  {
    path: '/admin/new-admin-pass-reset',
    components: {
      Navbar: LoggedNavBar,
      UnloggedContent: AccountDelRequests
    } // DONE
  },
  {
    path: '/admin/users',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: AdminSidebar,
      MainContent: UserOverview
    } // DONE
  },
  {
    path: '/admin/profits',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: AdminSidebar,
      MainContent: ProfitsComponent
    } // DONE
  },
  {
    path: '/client/profile',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: ClientSidebar,
      MainContent: ProfileView
    }
  },
  {
    path: '/instructor/profile',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: InstructorSidebar,
      MainContent: ProfileView
    }
  },
  {
    path: '/client/cottages',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: ClientSidebar,
      MainContent: CottageOverview
    }
  },
  {
    path: '/client/boats',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: ClientSidebar,
      MainContent: BoatsOverview
    }
  },
  {
    path: '/client/adventures',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: ClientSidebar,
      MainContent: AdventureOverview
    }
  },
  {
    path: '/client/CottageDetails/:id?',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: ClientSidebar,
      MainContent: CottageDetails
    }
  },
  {
    path: '/client/AdventureDetails/:id?',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: ClientSidebar,
      MainContent: AdventureDetails
    }
  },
  {
    path: '/client/BoatDetails/:id?',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: ClientSidebar,
      MainContent: BoatDetails
    }
  },
  {
    path: '/instructor/profits',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: InstructorSidebar,
      MainContent: ProfitsComponent
    },
  },
  {
    path: '/instructor/create-adventure',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: InstructorSidebar,
      MainContent: AdventureCreator
    }
  },
  {
    path: '/instructor/view-adventures',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: InstructorSidebar,
      MainContent: InstructorsAdventures
    }
  },
  {
    path: '/instructor/view-adventure-details/:id?',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: InstructorSidebar,
      MainContent: InstructorAdventureDetails
    }
  },
  {
    path: '/instructor1/view-history',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: InstructorSidebar,
      MainContent: InstCompletedReservations
    }
  },
  {
    path: '/instructor/graphic-profits',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: InstructorSidebar,
      MainContent: InstructorGraphicProfits
    }
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router

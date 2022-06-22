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

import BoatProfile from '@/components/BoatProfile.vue'
import CreateBoat from '@/components/CreateBoat.vue'
import BoatOwnerSidebar from '@/components/BoatOwnerSidebar.vue'
import BOCompletedReservations from '@/components/BoatOwnerHistory.vue'
import BoatOwnerReport from '@/components/BoatOwnerGraphicProfits.vue'

import CottageProfile from '@/components/CottageProfile'
import CottageOwnerSidebar from '@/components/CottageOwnerSidebar.vue'
import CreateCottage from '@/components/CreateCottage.vue'
import COCompletedReservations from '@/components/CottageOwnerHistory.vue'
import CottageOwnerReport from '@/components/CottageOwnerGraphicProfits.vue'

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
    path: '/bo/profile/',
    name: 'BOProfileView',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: BoatOwnerSidebar,
      MainContent: ProfileView
    } // DONE
  },
  {
    path: '/bo/history/',
    name: 'BOHistory',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: BoatOwnerSidebar,
      MainContent: BOCompletedReservations
    } // DONE
  },
  {
    path: '/bo/boat-overview/',
    name: 'BOBoats',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: BoatOwnerSidebar,
      MainContent: BoatsOverview
    } // DONE
  },
  {
    path: '/bo/boat-profile/:id?',
    name: 'BoatProfileOverview',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: BoatOwnerSidebar,
      MainContent: BoatProfile
    } // FLASH SALE UNFINISHED
  },
  {
    path: '/bo/add-boat',
    name: 'AddBoat',
    components:{
      Navbar: LoggedNavBar,
      LeftSidebar: BoatOwnerSidebar,
      MainContent: CreateBoat
    }
  },
  {
    path: '/bo/profits',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: BoatOwnerSidebar,
      MainContent: ProfitsComponent
    },
  },
  {
    path: '/bo/reports',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: BoatOwnerSidebar,
      MainContent: BoatOwnerReport
    },
  },
  {
    path: '/co/profile/',
    name: 'COProfileView',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: CottageOwnerSidebar,
      MainContent: ProfileView
    } // DONE
  },
  {
    path: '/co/cottage-overview/',
    name: 'COCottages',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: CottageOwnerSidebar,
      MainContent: CottageOverview
    } // DONE
  },
  {
    path: '/co/cottage-profile/:id?',
    name: 'CottageProfileOverview',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: CottageOwnerSidebar,
      MainContent: CottageProfile
    } 
  },
  {
    path: '/co/add-cottage',
    name: 'AddCottage',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: CottageOwnerSidebar,
      MainContent: CreateCottage
    }
  },
  {
    path: '/co/profits',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: CottageOwnerSidebar,
      MainContent: ProfitsComponent
    }
  },
  {
    path: '/co/history/',
    name: 'COHistory',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: CottageOwnerSidebar,
      MainContent: COCompletedReservations
    } // DONE
  },
  {
    path: '/co/reports',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: CottageOwnerSidebar,
      MainContent: CottageOwnerReport
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
    path: '/client/profile',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: ClientSidebar,
      MainContent: ProfileView
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
  path: '/instructor/view-history',
  components: {
    Navbar: LoggedNavBar,
    LeftSidebar: InstructorSidebar,
    MainContent: InstCompletedReservations}},

  {
    path: '/client/activeReservations',
    components: {
      Navbar: LoggedNavBar,
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
  },
  {
    path: '/instructor/graphic-profits',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: InstructorSidebar,
      MainContent: InstructorGraphicProfits
    }
  },
  {
    path: '/client/activeReservations',
    components: {
      Navbar: LoggedNavBar,
      LeftSidebar: ClientSidebar,
      MainContent: ReservationsOverview
    }
  },
  {
    path: '/client/historicalReservations',
    components: {
      Navbar: LoggedNavBar,
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

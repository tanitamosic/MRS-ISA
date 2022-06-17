import { createApp} from 'vue'
import { createStore } from 'vuex'
import App from './App.vue'
import router from './router'
import 'bootstrap/dist/css/bootstrap.css'
//import createPersistedState from "vuex-persistedstate"


const store = createStore({
  //plugins: [createPersistedState()],
    state () {
      return {
        accessToken: localStorage.getItem('accessToken'),
        role: localStorage.getItem('role'),
        User: localStorage.getItem('User'),
        username: localStorage.getItem('username'),
        currentPassword: localStorage.getItem('currentPassword'),
        lastPasswordResetDate: null
      }
    },
    
  });

const app = createApp(App);
app.use(store);
app.use(router);
app.mount('#app');

import 'bootstrap/dist/js/bootstrap.js'
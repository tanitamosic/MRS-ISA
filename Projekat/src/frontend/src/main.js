import { createApp } from 'vue'
import { createStore } from 'vuex'
import App from './App.vue'
import router from './router'
import 'bootstrap/dist/css/bootstrap.css'

const store = createStore({
    state () {
      return {
        accessToken: '',
        role: ''
      }
    }
  })

const app = createApp(App);
app.use(store);
app.use(router);
app.mount('#app');

import 'bootstrap/dist/js/bootstrap.js'
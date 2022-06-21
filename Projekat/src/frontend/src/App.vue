<template>
  <div id="app">
    <NavigationBar></NavigationBar>
    <router-view name="UnloggedContent"></router-view>
    <div class="sidebar-left">
      <router-view name="LeftSidebar"></router-view>
    </div>
    <div class="content-right">
      <router-view name="MainContent"></router-view>
    </div>
  </div>
</template>

<script>
import NavigationBar from './components/NavigationBar.vue'

export default {
  name: 'App',
  components: {
    NavigationBar,
}
}
</script>

<style scoped>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  height:100%; 
  min-height:100%;
}

.sidebar-left {
  display: table;
  position: fixed;
  float: left;
  width: 15%;
}

.content-right {
  /* margin-top: 50px; */
  z-index: -1;
  float: right;
  width: 85%;
}
</style>

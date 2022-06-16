<template>
  <div class="sidebar-left">
    <AdminSidebar></AdminSidebar>
  </div>
  <div class="content-right">
    <BoatsOverview></BoatsOverview>
  </div>
  
  
</template>

<style scoped>
.sidebar-left {
  display: table;
  position: fixed;
  float: left;
  width: 15%;
}

.content-right {
  margin-top: 50px;
  z-index: -1;
  float: right;
  width: 85%;
}

</style>

<script>
import AdminSidebar from "@/components/AdminSidebar.vue";
import BoatsOverview from "./BoatsOverview.vue";

export default {
  name: 'AdminBoats',

  components: {
    AdminSidebar,
    BoatsOverview
}
}

</script>

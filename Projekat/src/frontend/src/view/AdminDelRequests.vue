<template>
  <div class="sidebar-left">
    <AdminSidebar></AdminSidebar>
  </div>
  <div class="content-right">
    <AccountDelRequests></AccountDelRequests>
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
import AccountDelRequests from '@/components/AccountDelRequests.vue'

export default {
  name: 'AdminDelRequests',

  components: {
    AdminSidebar,
    AccountDelRequests
}
}

</script>

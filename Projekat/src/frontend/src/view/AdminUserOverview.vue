<template>
  <div class="sidebar-left" style="height:100%; min-height:100%;">
    <AdminSidebar></AdminSidebar>
  </div>
  <div class="content-right" style="height:100%; min-height:100%;">
    <UserOverview></UserOverview>
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
  /* margin-top: 50px; */
  top: 50px;
  z-index: -1;
  float: right;
  width: 85%;
  background-color: #659dbd;
  
}

</style>
<script>
import AdminSidebar from "@/components/AdminSidebar.vue"
import UserOverview from "@/components/UserOverview.vue"

export default {
    name: 'AdminUserOverview',
    components: {
        AdminSidebar,
        UserOverview
    }
}

</script>
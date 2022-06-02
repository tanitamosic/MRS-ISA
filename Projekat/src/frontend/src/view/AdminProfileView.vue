<template>
  <div class="sidebar-left">
    <AdminSidebar></AdminSidebar>
  </div>
  <ProfileView></ProfileView>
  
</template>

<style scoped>
.sidebar-left {
  display: table;
  position: fixed;
  float: left;
  width: 15%;
}

</style>

<script>
import AdminSidebar from "@/components/AdminSidebar.vue";
import ProfileView from "@/components/ProfileView.vue";

export default {
  name: 'AdminProfileView',

  components: {
    AdminSidebar,
    ProfileView
}
}

</script>

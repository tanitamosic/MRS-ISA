<template>
  <div class="sidebar-left">
    <InstructorSidebar></InstructorSidebar>
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
import InstructorSidebar from "@/components/InstructorSidebar.vue";
import ProfileView from "@/components/ProfileView.vue";

export default {
  name: 'InstructorProfileView',

  components: {
    InstructorSidebar,
    ProfileView
}
}

</script>

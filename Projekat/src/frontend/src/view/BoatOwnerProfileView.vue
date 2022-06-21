<template>
  <div class="sidebar-left">
    <BoatOwnerSidebar></BoatOwnerSidebar>
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
import BoatOwnerSidebar from "@/components/BoatOwnerSidebar.vue";
import ProfileView from "@/components/ProfileView.vue";

export default {
  name: 'COProfileView',

  components: {
    BoatOwnerSidebar,
    ProfileView
}
}

</script>

<template>
  <div class="sidebar-left">
    <CottageOwnerSidebar></CottageOwnerSidebar>
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
import CottageOwnerSidebar from "@/components/CottageOwnerSidebar.vue";
import ProfileView from "@/components/ProfileView.vue";

export default {
  name: 'COProfileView',

  components: {
    CottageOwnerSidebar,
    ProfileView
}
}

</script>

<template>
  <div class="sidebar-left">
    <CottageOwnerSideBar></CottageOwnerSideBar>
  </div>
 <div class="content-right">
    <CottageProfile></CottageProfile>
  </div></template>


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
import CottageOwnerSideBar from "@/components/CottageOwnerSidebar.vue";
import CottageProfile from "@/components/CottageProfile.vue";

export default {
  name: 'CottageProfileOverview',

  components: {
    CottageOwnerSideBar,
    CottageProfile
}
}

</script>

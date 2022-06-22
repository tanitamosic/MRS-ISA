<template>
  <div class="sidebar-left">
    <BoatOwnerSideBar></BoatOwnerSideBar>
  </div>
 <div class="content-right">
    <BoatOverview></BoatOverview>
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
import BoatOwnerSideBar from "@/components/BoatOwnerSidebar.vue";
import BoatOverview from "./BoatOverview.vue";

export default {
  name: 'COBoats',

  components: {
    BoatOwnerSideBar,
    BoatOverview
}
}

</script>

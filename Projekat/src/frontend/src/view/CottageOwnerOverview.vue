<template>
  <div class="sidebar-left">
    <CottageOwnerSideBar></CottageOwnerSideBar>
  </div>
 <div class="content-right">
    <CottageOverview></CottageOverview>
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
import CottageOverview from "./CottageOverview.vue";

export default {
  name: 'COCottages',

  components: {
    CottageOwnerSideBar,
    CottageOverview
}
}

</script>

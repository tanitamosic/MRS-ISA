<template>
  <div class="sidebar-left">
    <AdminSidebar></AdminSidebar>
  </div>
  <div class="content-right">
    <ComplaintResponse></ComplaintResponse>
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
import ComplaintResponse from '@/components/ComplaintResponse.vue'

export default {
  name: 'AdminComplaints',

  components: {
    AdminSidebar,
    ComplaintResponse
}
}

</script>

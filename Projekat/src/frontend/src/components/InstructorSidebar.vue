<template>
  <b-sidebar id="sidebar-border" class="sidebar-bg d-flex flex-column text-white min-vh-100">
    <table>
      <tr class="fs-5">
        <td>Profil</td>
      </tr>
      <tr class="fs-5">
        <td>Pregled časova pecanja</td>
      </tr>
      <tr class="fs-5">
        <td>Pregled zauzeća</td>
      </tr>
      <tr class="fs-5">
        <td>Izveštaji</td>
      </tr>
      <tr class="fs-5">
        <td>Kreiraj termine</td>
      </tr>
    </table>
  </b-sidebar>
</template>

<style scoped>
.sidebar-bg {
  background-color: #bc986a;
}

#sidebar-border {
  display: table;
  flex-shrink: 0;
  top: 50px;
  width: 100%;
  position: relative;
  float: left;
}

tr {
  border-bottom: 1px solid #ffffff;
  height: 60px;
  vertical-align: bottom;
}

td {
  padding-left: 10px;
}

tr:hover {
  background-color: #bcd8ab;
}
</style>

<script>

export default {
    name: 'InstructorSidebar'
}
</script>

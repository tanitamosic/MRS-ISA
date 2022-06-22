<template>
  <b-sidebar id="sidebar-border" class="sidebar-bg d-flex flex-column text-white min-vh-100">
    <table>
      <tr class="fs-5"  v-on:click="goToProfile">
        <td>Profil</td>
      </tr>
      <tr class="fs-5" v-on:click="goToCottages">
        <td>Pregled vikendica</td>
      </tr>
      <tr class="fs-5" v-on:click="goToAddCottage">
        <td>Dodaj novu vikendicu</td>
      </tr>
      <tr class="fs-5" v-on:click="goToHistory">
        <td>Istorija rezervacija</td>
      </tr>
      <tr class="fs-5" v-on:click="goToReports">
        <td>Izveštaj o profitima</td>
      </tr>
      <tr class="fs-5" v-on:click="goToProfits">
        <td>Prihodi</td>
      </tr>
    </table>
  </b-sidebar>
</template>

<style scoped>
.sidebar-bg {
  background-color: #bc986a;
}

#sidebar-border {
  z-index: 1;
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
  name: 'CottageOwnerSidebar',

  methods: {
    goToProfile: function() {
      this.$router.push('/co/profile');
    },
    goToCottages: function() {
      this.$router.push('/co/cottage-overview')
    },
    goToAddCottage: function(){
        this.$router.push('/co/add-cottage')
    },
    goToHistory: function(){
        this.$router.push('/co/history')
    },
    goToProfits: function() {
      this.$router.push('/co/profits');
    },
    goToReports: function(){
        this.$router.push('/co/reports')
    },
    goToCalendar: function(){
        this.$router.push('/co/calendar')
    }
  }
}

</script>

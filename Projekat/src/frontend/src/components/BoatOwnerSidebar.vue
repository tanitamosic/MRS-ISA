<template>
  <b-sidebar id="sidebar-border" class="sidebar-bg d-flex flex-column text-white min-vh-100">
    <table>
      <tr class="fs-5"  v-on:click="goToProfile">
        <td>Profil</td>
      </tr>
      <tr class="fs-5" v-on:click="goToBoats">
        <td>Pregled brodova</td>
      </tr>
      <tr class="fs-5" v-on:click="goToAddBoat">
        <td>Dodaj novi brod</td>
      </tr>
      <tr class="fs-5" v-on:click="goToHistory">
        <td>Istorija rezervacija</td>
      </tr>
      <tr class="fs-5" v-on:click="goToReports">
        <td>Izveštaji</td>
      </tr>
      <tr class="fs-5" v-on:click="goToCalendar">
        <td>Kalendar zauzetosti</td>
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
  name: 'BoatOwnerSidebar',

  methods: {
    goToProfile: function() {
      this.$router.push('/bo/profile');
    },
    goToBoats: function() {
      this.$router.push('/bo/boat-overview')
    },
    goToAddBoat: function(){
        this.$router.push('/bo/add-boat')
    },
    goToHistory: function(){
        this.$router.push('/bo/history')
    },
    goToSale: function(){
        this.$router.push('/bo/add-sale')
    },
    goToReports: function(){
        this.$router.push('/bo/reports')
    },
    goToCalendar: function(){
        this.$router.push('/bo/calendar')
    }
  }
}

</script>

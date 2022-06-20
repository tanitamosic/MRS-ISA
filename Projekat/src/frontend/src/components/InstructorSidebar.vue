<template>
  <b-sidebar id="sidebar-border" class="sidebar-bg d-flex flex-column text-white min-vh-100">
    <table>
      <tr class="fs-5" v-on:click="goToProfile">
        <td>Profil</td>
      </tr>
      <tr class="fs-5" v-on:click="goToAdventures">
        <td>Pregled časova pecanja</td>
      </tr>
      <tr class="fs-5">
        <td>Istorija rezervacija</td>
      </tr>
      <tr class="fs-5" v-on:click="goToProfits">
        <td>Izveštaj o prihodima</td>
      </tr>
      <tr class="fs-5" v-on:click="goToAdvCreator">
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
  name: 'InstructorSidebar',

  methods: {
    goToProfile: function () {
      this.$router.push('/instructor/profile');
    },
    goToProfits: function() {
      this.$router.push('/instructor/profits');
    },
    goToAdvCreator: function() {
      this.$router.push('/instructor/create-adventure');
    },
    goToAdventures: function() {
      this.$router.push('/instructor/view-adventures');
    }
  }
}
</script>

<template>
  <b-sidebar id="sidebar-border" class="sidebar-bg d-flex flex-column text-white min-vh-100">
    <table>
      <tr class="fs-5"  v-on:click="goToProfile">
        <td>Profil</td>
      </tr>
      <tr class="fs-5" v-on:click="goToUsers">
        <td>Pregled korisnika</td>
      </tr>
      <tr class="fs-5" v-on:click="goToCottages">
        <td>Pregled vikendica</td>
      </tr>
      <tr class="fs-5" v-on:click="goToBoats">
        <td>Pregled brodova</td>
      </tr>
      <tr class="fs-5" v-on:click="goToAdventures">
        <td>Pregled časova pecanja</td>
      </tr>
      <tr class="fs-5">
        <td>Ocene na čekanju</td>
      </tr>
      <tr class="fs-5" v-on:click="goToComplaints">
        <td>Žalbe</td>
      </tr>
      <tr class="fs-5">
        <td>Loyalty program</td>
      </tr>
      <tr class="fs-5" v-on:click="goToPendingRegistrations">
        <td>Registracije na čekanju</td>
      </tr>
      <tr class="fs-5" v-on:click="goToDeletionRequests">
        <td>Zahtevi za brisanje naloga</td>
      </tr>
       <tr class="fs-5" v-on:click="goToAdminRegistration">
        <td>Registracija novog admina</td>
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
  background-color: #daad86;
}
</style>

<script>

export default {
  name: 'AdminSidebar',

  methods: {
    goToProfile: function() {
      this.$router.push('/admin/profile');
    },
    goToUsers: function() {
      this.$router.push('/admin/users');
    },
    goToPendingRegistrations: function() {
      this.$router.push('/admin/pendingreg');
    },
    goToDeletionRequests: function() {
      this.$router.push('/admin/delreq');
    },
    goToComplaints: function() {
      this.$router.push('/admin/complaints');
    },
    goToCottages: function() {
      this.$router.push('/admin/cottage-overview')
    },
    goToBoats: function() {
      this.$router.push('/admin/boat-overview')
    },
    goToAdventures: function() {
      this.$router.push('/admin/adventure-overview')
    },
    goToAdminRegistration: function() {
      this.$router.push('/admin/admin-registration');
    }
  }
}

</script>

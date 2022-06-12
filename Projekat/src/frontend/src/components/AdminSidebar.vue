<template>
  <b-sidebar id="sidebar-border" class="sidebar-bg d-flex flex-column text-white min-vh-100">
    <table>
      <tr class="fs-5"  v-on:click="goToProfile">
        <td>Profil</td>
      </tr>
      <tr class="fs-5" v-on:click="goToUsers">
        <td>Pregled korisnika</td>
      </tr>
      <tr class="fs-5">
        <td>Pregled vikendica</td>
      </tr>
      <tr class="fs-5">
        <td>Pregled brodova</td>
      </tr>
      <tr class="fs-5">
        <td>Pregled časova pecanja</td>
      </tr>
      <tr class="fs-5">
        <td>Ocene na čekanju</td>
      </tr>
      <tr class="fs-5">
        <td>Žalbe</td>
      </tr>
      <tr class="fs-5">
        <td>Loyalty program</td>
      </tr>
      <tr class="fs-5">
        <td>Registracije na čekanju</td>
      </tr>
      <tr class="fs-5">
        <td>Zahtevi za brisanje naloga</td>
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
  name: 'AdminSidebar',

  methods: {
    goToProfile: function() {
      this.$router.push('/admin/profile');
    },
    goToUsers: function() {
      this.$router.push('/admin/users');
    }
  }
}

</script>

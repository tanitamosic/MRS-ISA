<template>
    <b-sidebar id="sidebar-border" class="sidebar-bg d-flex flex-column text-white min-vh-100">
            <table>
                <tr class="fs-5">
                    <td>Profil</td>
                </tr>
                <tr class="fs-5">
                    <td>Orders</td>
                </tr>
                <tr class="fs-5">
                    <td>Loyalty program</td>
                </tr>
                <tr class="fs-5">
                    <td>Registracije na čekanju</td>
                </tr>
            </table>
    </b-sidebar>
<!-- u navigationBar-u position je fixed, pa ovde position mora biti relative.
prekopirajte ceo ovaj template i style i menjajte/dodajte redove kako vam sta zatreba.
boje su takodje opcione.

kada ubacite sidebar.
wrappujte b-sidebar tag u div componentu. ovo kod mene sad izgleda ovako u App.vue

<NavigationBar></NavigationBar>
      <div class="sidebar-left">
        <SideBar></SideBar>
      </div>
      <div class="content-right">
        <router-view></router-view>
      </div>
<style scoped>

.sidebar-left {
  display: table;
  position: fixed;
  float: left;
  width: 15%;
}

.content-right {
  float: right;
  width: 85%;
}
</style>

vi cete da navedete vas SideBar kod u ovaj div.sidebar-left i da prepisete ovaj style css.
dakle. div koji sadrzi b-sidebar mora biti position: fixed, 
dok sam b-sidebar mora biti position: relative;
 -->
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

tr{
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
    name: 'SideBar',

}

</script>


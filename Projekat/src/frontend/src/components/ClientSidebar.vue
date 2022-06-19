<template>
    <b-sidebar id="sidebar-border" class="sidebar-bg d-flex flex-column text-white min-vh-100">
        <table>
            <tr class="fs-5" v-on:click="goToCottages">
                <td>Vikendice</td>
            </tr>
            <tr class="fs-5" v-on:click="goToBoats">
                <td>Brodovi</td>
            </tr>
            <tr class="fs-5" v-on:click="goToAdventures">
                <td>Avanture</td>
            </tr>
            <tr class="fs-5" v-on:click="goToProfile">
                <td>Profil</td>
            </tr>
            <tr class="fs-5">
                <td>Pregled rezervacija</td>
            </tr>
            <tr class="fs-5">
                <td>Istorija rezervacija</td>
            </tr>
            <tr class="fs-5">
                <td>Uvid u penale</td>
            </tr>
            <tr class="fs-5">
                <td>Akcije</td>
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
    name: 'ClientSidebar',

    methods: {
        goToCottages: function () {
            this.$router.push('/client/cottages');
        },
        goToBoats: function () {
            this.$router.push('/client/boats');
        },
        goToAdventures: function () {
            this.$router.push('/client/adventures');
        },
        goToProfile: function () {
            this.$router.push('/client/profile');
        }
    }
}

</script>

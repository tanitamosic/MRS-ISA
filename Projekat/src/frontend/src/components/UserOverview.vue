<template>
    <div v-if="!this.usersLoaded" id="ucitavanje">Učitavanje!</div>
    <div v-else-if="this.noUsers" id="nema-podataka">Trenutno nema korisnika (bankrot)!</div>
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <div
            class="card shadow-lg border-dark col"
            v-for="(user, i) in this.users"
            :key="i"
            style="width: 18rem; margin:20px; margin-top: 100px"
        >
            <div class="card-header">{{ user.name }} {{ user.surname }}</div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item">{{ user.phone }}</li>
                <li
                    class="list-group-item"
                >{{ user.address.state }} {{ user.address.city }} {{ user.address.street }}</li>
                <li class="list-group-item">Loyalty score: {{ user.loyaltyScore }}</li>
                <li class="list-group-item">Penali: {{ user.penalties }}</li>
            </ul>
            <div class="card-body">
                <button class="btn btn-danger" v-on:click="deleteUser(user.id)">Obriši</button>
            </div>
        </div>
    </div>
</template>

<style scoped>
</style>

<script>

import axios from 'axios'

export default {
    name: 'UserOverview',
    data() {
        return {
            usersLoaded: false,
            noUsers: true,
            users: []
        }
    },
    mounted() {
        this.get_data(this);
    },
    methods: {
        get_data: function (container) {
            axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + container.$store.accessToken;
            axios.get('/api/admin/get-active-clients').
                then((response) => {
                    container.users = response.data;
                    container.usersLoaded = true;
                    if (container.users.length === 0) {
                        container.noUsers = true;
                    } else {
                        container.noUsers = false;
                    }
                }).catch((err) => {
                    console.log(err);
                    alert("Doslo je do greske!");
                });
        },
        deleteUser: function (usr_id) {
            let self = this;
            axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + this.$store.accessToken;
            axios
                .delete('/api/admin/delete-client/' + usr_id)
                .then((response) => {
                    if (response.status === 200) {
                        alert(response.data);

                        // REDRAW USERS
                        self.usersLoaded = false;
                        self.noUsers = true;
                        self.users = [];
                        self.get_data(self);
                    }
                }).catch((err) => {
                    alert("Došlo je do greške");
                    console.log(err);
                });

        }
    }

}

</script>

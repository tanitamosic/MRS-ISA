<template>
    <div v-if="!this.delRequestsLoaded" id="ucitavanje">Učitavanje!</div>
    <div v-else-if="this.noRequests" id="nema-podataka">Nema zahteva za brisanje naloga!</div>
    <div class="row row-cols-1 row-cols-md-3 g-4 mt-4">
        <div
            class="card col mt-5"
            style="width: 18rem; margin: 20px"
            v-for="(request, i) in this.DeletionRequests"
            :key="i"
        >
            <div class="card-body">
                <p class="card-text">Korisnik {{request.user.name}} {{request.user.surname}} želi obrisati svoji nalog.</p>
                <p class="card-text">Kontakt korisnika je: {{request.user.phone}}</p>
            </div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item">Razlog: {{ request.deletionRequest }}</li>
                <li class="list-group-item">{{ request.requestDate }}</li>
                <li class="list-group-item" :id="username_id(i)">{{ request.account.username }}</li>
            </ul>
            <div class="card-body">
                <button
                    :id="deletion_reject(i)"
                    class="btn btn-danger"
                    type="button"
                    v-on:click="reject_del(username_id(i))"
                >Odbij</button>
                <button
                    :id="deletion_accept(i)"
                    class="btn btn-primary"
                    type="button"
                    style="margin-left: 20px"
                    v-on:click="accept_del(username_id(i))"
                >Potvrdi</button>
            </div>
        </div>
    </div>
</template>

<style scoped>
#ucitavanje {
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    min-height: 100vh;
    font-size: 28px;
    color: royalblue;
    background-color: white;
}

#nema-podataka {
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    min-height: 100vh;
    font-size: 28px;
    color: red;
    background-color: white;
}
</style>

<script>

import axios from 'axios'

export default {
    name: 'AccountDelRequests',
    data() {
        return {
            DeletionRequests: [],
            noRequests: true,
            delRequestsLoaded: false
        }
    },
    mounted() {
        this.get_data(this);

    },
    methods: {
        get_data: function (container) {
            axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + container.$store.accessToken;
            axios.get('/api/admin/deletion-requests').
                then((response) => {
                    container.DeletionRequests = response.data;
                    container.delRequestsLoaded = true;
                    if (container.DeletionRequests.length === 0) {
                        container.noRequests = true;
                    } else {
                        container.noRequests = false;
                    }
                }).catch((err) => {
                    console.log(err);
                    alert("Doslo je do greske!");
                });
        },
        username_id: function (i) {
            return "username" + i;
        },
        deletion_reject: function (i) {
            return "deletion_reject" + i;
        },
        deletion_accept: function (i) {
            return "deletion_accept" + i;
        },

        accept_del: function (usr_id) {
            let i = usr_id.slice(8); // usernameI, slice 'username' out and get integer;
            let request = this.DeletionRequests[i];
            let accountDTO = {
                'username': request.account.username,
                'id': request.account.id,
                'password': request.account.password,
                'isActivated': request.account.activated,
                'isDeleted': request.account.deleted
            }
            axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + this.$store.accessToken;
            let data = JSON.stringify(accountDTO);
            let self = this;
            axios.post('/api/admin/accept-deletion', data, { headers: { 'Content-Type': 'application/json' } })
                .then((response) => {
                    if (response.status === 200) {
                        alert(response.data);
                        self.DeletionRequests = [];
                        self.noRequests = true;
                        self.delRequestsLoaded = false
                        self.get_data(self);
                    }
                }).catch((err) => {
                    alert(err);
                })
        },
        reject_del: function (usr_id) {
            let explanation = prompt("Unesite razlog za odbijanje zahteva za brisanje");
            if (explanation === '') {
                alert("Morate uneti razlog za odbijanje!");
                return;
            }
            let i = usr_id.slice(8); // usernameI, slice 'username' out and get integer;
            let request = this.DeletionRequests[i];
            let acc_id = request.account.id;
            let rejection = {
                'acc_id': acc_id,
                'Explanation': explanation
            };
            let data = JSON.stringify(rejection);
            let self = this;
            axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + this.$store.accessToken;
            axios.post('/api/admin/reject-request', data, { headers: { 'Content-Type': 'application/json' } }).then((response) => {
                if (response.status === 200) {
                    alert(response.data);
                    self.DeletionRequests = [];
                    self.noRequests = true;
                    self.delRequestsLoaded = false
                    self.get_data(self);
                }
            }).catch((err) => {
                alert(err);
            });
        }
    }
}

</script>

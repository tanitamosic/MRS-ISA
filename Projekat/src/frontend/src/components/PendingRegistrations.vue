<template>
    <div v-if="!this.registrationsLoaded" id="ucitavanje">Učitavanje!</div>
    <div v-else-if="this.noRequests" id="nema-podataka">Nema registracija na čekanju!</div>
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <div
            class="card col mt-5"
            style="width: 18rem; margin: 20px"
            v-for="(request, i) in this.RegistrationRequests"
            :key="i"
        >
            <div class="card-body">
                <p class="card-text">{{ request.registrationParams }}</p>
            </div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item">{{ request.requestDate }}</li>
                <li class="list-group-item" :id="username_id(i)">{{ request.username }}</li>
            </ul>
            <div class="card-body">
                <button
                    :id="registration_reject(i)"
                    class="btn btn-danger"
                    type="button"
                    v-on:click="reject_reg(username_id(i))"
                >Odbij</button>
                <button
                    :id="registration_accept(i)"
                    class="btn btn-primary"
                    type="button"
                    style="margin-left: 20px"
                    v-on:click="accept_reg(username_id(i))"
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
    name: 'PendingRegistrations',
    data() {
        return {
            RegistrationRequests: [],
            noRequests: true,
            registrationsLoaded: false
        }
    },
    mounted() {
        this.get_data(this);
    },

    methods: {
        get_data: function (container) {
            axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + container.$store.accessToken;
            axios.get('/api/admin/pendingRegistrations').
                then((response) => {
                    container.RegistrationRequests = response.data;
                    container.registrationsLoaded = true;
                    if (container.RegistrationRequests.length === 0) {
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
        registration_reject: function (i) {
            return "registration_reject" + i;
        },

        registration_accept: function (i) {
            return "registration_accept" + i;
        },
        accept_reg: function (usr_id) {
            let i = usr_id.slice(8); // usernameI, slice 'username' out and get integer;
            let request = this.RegistrationRequests[i];
            let accountDTO = {
                'username': request.username,
                'id': request.account.id,
                'password': request.account.password,
                'isActivated': request.account.activated,
                'isDeleted': request.account.deleted
            }
            axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + this.$store.accessToken;
            let data = JSON.stringify(accountDTO);
            let self = this;
            axios.post('/api/admin/accept-request', data, { headers: { 'Content-Type': 'application/json' } })
                .then((response) => {
                    if (response.status === 200) {
                        alert(response.data);
                        // RE-GET FOR PAGE REDRAW
                        self.RegistrationRequests = []
                        self.noRequests = true
                        self.registrationsLoaded = false
                        self.get_data(self);
                    }
                }).catch((err) => {
                    alert(err);
                })
        },
        reject_reg: function (usr_id) {
            let explanation = prompt("Unesite razlog za odbijanje registracije.");
            if (explanation === '') {
                alert("Morate uneti razlog za odbijanje!");
                return;
            }
            let i = usr_id.slice(8); // usernameI, slice 'username' out and get integer;
            let request = this.RegistrationRequests[i];
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
                    // RE-GET FOR PAGE REDRAW
                    self.RegistrationRequests = []
                    self.noRequests = true
                    self.registrationsLoaded = false
                    self.get_data(self);
                }
            }).catch((err) => {
                alert(err);
            });
        }
    }
}
</script>

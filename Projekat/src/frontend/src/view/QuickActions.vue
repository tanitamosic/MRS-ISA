<template>
    <div v-if="!this.complaintsLoaded" id="ucitavanje">Učitavanje!</div>
    <div v-else-if="this.noComplaints" id="nema-podataka">Trenutno nema žalbi!</div>
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <div class="card col mt-5" style="width: 18rem; margin: 20px" v-for="(service, i) in this.Services"
            :key="i">
            <div class="card-body">
                <p class="card-text">
                    Servis:
                    {{ service.name }}
                </p>
                <p class="card-text">
                    Zaposleni:
                    {{ complaint.reservation.service.owner.name }} {{ complaint.reservation.service.owner.surname }}
                </p>
                <p class="card-text">{{ complaint.description }}</p>
            </div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item">
                    Kontakt klijenta:
                    {{ complaint.reservation.client.phone }}
                </li>
                <li class="list-group-item">
                    Kontakt zaposlenog:
                    {{ complaint.reservation.service.owner.phone }}
                </li>
            </ul>
            <div class="card-body">
                <button class="btn btn-primary" type="button" v-on:click="respond(complaint)">Odgovori</button>
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
    name: 'ComplaintResponse',
    data() {
        return {
            complaintsLoaded: false,
            noComplaints: true,
            Complaints: []
        }
    },
    mounted() {
        this.get_data(this);
    },
    methods: {
        get_data: function (container) {
            axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + container.$store.accessToken;
            axios.get('/api/admin/get-complaints')
                .then((response) => {
                    container.Complaints = response.data;
                    console.log(response.data);
                    container.complaintsLoaded = true;
                    if (container.Complaints.length === 0) {
                        container.noComplaints = true;
                        return;
                    } else {
                        container.noComplaints = false;
                    }
                })
                .catch((err) => {
                    console.log(err);
                    alert("Došlo je do greške.");
                });

        },
        respond: function (complaint) {
            let client_response = prompt("Odgovorite klijentu:");
            let emoloyee_response = prompt("Odgovorite zaposlenom:");

            let data = {
                'complaintId': complaint.id,
                'answerToClient': client_response,
                'targetClient': complaint.reservation.client.id,
                'answerToEmployee': emoloyee_response,
                'targetEmployee': complaint.reservation.service.owner.id
            }
            let self = this;
            axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + this.$store.accessToken;
            axios.post('/api/admin/answer-complaint', data)
                .then((response) => {
                    if (response.status === 200) {
                        alert(response.data);
                        // RESET WINDOW
                        self.complaintsLoaded = false;
                        self.noComplaints = true;
                        self.Complaints = [];
                        self.get_data(self);
                    }

                })
                .catch((err) => {
                    console.log(err);
                    alert("Doslo je do greske.");
                });

        }

    }

}

</script>

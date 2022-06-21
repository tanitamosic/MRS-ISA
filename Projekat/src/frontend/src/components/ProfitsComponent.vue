<template>
    <div class="card mx-4 mx-md-5 shadow-lg">
        <div class="card-body py-5 px-md-5">
            <div class="row d-flex justify-content-center">
                <div class="col-lg-8">
                    <h2 class="fw-bold mb-5">Prihodi</h2>
                    <div class="row gx-3 mb-3" id="customDays">
                        <label class="small mb-1" for="fromDate">Odaberite prvi datum (od)</label>
                        <input
                            type="date"
                            name="fromDate"
                            id="Customization"
                            class="form-control"
                            v-model="dateFrom"
                        />
                    </div>
                    <div class="row gx-3 mb-3" id="customDays">
                        <label class="small mb-1" for="dateTo">Odaberite prvi datum (do)</label>
                        <input 
                            type="date" 
                            id="Customization" 
                            class="form-control" 
                            name="dateTo"
                            v-model="dateTo" />
                    </div>
                    <div class="row gx-3 mb-3">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">Usluga</th>
                                    <th scope="col">Profit</th>
                                </tr>
                            </thead>

                            <tr v-if="isAdmin || isInstructor">
                                <td>Avanture</td>
                                <td>{{ adventureProfits }}</td>
                            </tr>
                            <tr v-if="isAdmin || isBoatOwner">
                                <td>Brodovi</td>
                                <td>{{ boatProfits }}</td>
                            </tr>
                            <tr v-if="isAdmin || isCottageOwner">
                                <td>Vikendice</td>
                                <td>{{ cottageProfits }}</td>
                            </tr>
                            <tr class="table-info" v-if="isAdmin">
                                <td>Ukupno</td>
                                <td>{{ adventureProfits + boatProfits + cottageProfits }}</td>
                            </tr>
                        </table>
                    </div>
                    <button class="btn btn-primary" v-on:click.prevent="calcProfits">Izračunaj prihode</button>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
.card {
    margin-top: 100px !important;
    background-color: #fbeec1 !important;
}
</style>
<script>

import axios from 'axios'

export default {
    name: 'ProfitsComponent',
    data() {
        return {
            dateFrom: null,
            dateTo: null,

            isAdmin: false,
            isCottageOwner: false,
            isInstructor: false,
            isBoatOwner: false,

            adventureProfits: 0,
            boatProfits: 0,
            cottageProfits: 0
        }
    },
    mounted() {
        switch (this.$store.role) {
            case "ROLE_ADMIN": this.isAdmin = true; break;
            case "ROLE_INSTRUCTOR": this.isInstructor = true; break;
            case "ROLE_COTTAGE_OWNER": this.isCottageOwner = true; break;
            case "ROLE_BOAT_OWNER": this.isBoatOwner = true; break;
            default: { alert("Došlo je do greške"); return; }
        }
    },
    methods: {
        checkInput: function () {
            let from = Date.parse(this.dateFrom);
            let to = Date.parse(this.dateTo);
            if ((from >= to) || isNaN(from) || isNaN(to)) {
                return false;
            }
            return true;
        },

        // MATICNI METOD
        calcProfits: function () {
            
            if (!this.checkInput()) {
                alert("Niste pravilno uneli datume!");
                return;
            }

            axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + this.$store.accessToken;

            if (this.isAdmin) {
                this.calcAdventureProfits();
                this.calcCottageProfits();
                this.calcBoatProfits();
            } else if (this.isInstructor) {
                this.calcMyAdventureProfits();
            } else if (this.isCottageOwner) {
                this.calcMyCottageProfits();
            } else if (this.isBoatOwner) {
                this.calcMyBoatProfits();
            } else {
                alert("Došlo je do greške");
                return;
            }
        },

        // FUNKCIJE ZA ADMINA
        calcAdventureProfits: function () {
            let self = this;
            axios.get('/api/admin/adventure-profits/'+ this.dateFrom + '/' + this.dateTo)
            .then((response) => {
                self.adventureProfits += response.data;
                console.log(response);
            }).catch((err) => {
                console.log(err);
                alert("Došlo je do greške prilikom kalkulisanja profita sa avantura");
            })
        },
        calcCottageProfits: function () {
            let self = this;
            axios.get('/admin/boat-profits/'+ this.dateFrom + '/' + this.dateTo)
            .then((response) => {
                self.boatProfits += response.data;
                console.log(response);
            }).catch((err) => {
                console.log(err);
                alert("Došlo je do greške prilikom kalkulisanja profita sa brodova");
            })
        },
        calcBoatProfits: function () {
            let self = this;
            axios.get('/admin/cottage-profits/'+ this.dateFrom + '/' + this.dateTo)
            .then((response) => {
                self.cottageProfits += response.data;
                console.log(response);
            }).catch((err) => {
                console.log(err);
                alert("Došlo je do greške prilikom kalkulisanja profita sa vikendica");
            })
        },



        // FUNKCIJE ZA ZAPOSLENE
        calcMyAdventureProfits: function () {
            let self = this;
            axios.get('/api/instructor/adventure-profits/'+ this.dateFrom + '/' + this.dateTo + '/' + this.$store.User.id)
            .then((response) => {
                self.adventureProfits += response.data;
                console.log(response.data)
                console.log(response);
            }).catch((err) => {
                console.log(err);
                alert("Došlo je do greške prilikom kalkulisanja profita sa vikendica");
            });
        },
        calcMyCottageProfits: function () {

        },
        calcMyBoatProfits: function () {

        }
    }
}

</script>

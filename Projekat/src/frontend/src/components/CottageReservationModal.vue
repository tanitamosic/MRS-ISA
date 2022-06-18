<template>

    <div class="modal-dialog modal-dialog-centered">
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Rezervacija</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form id="searchForm">

                            <div class="row gx-3 mb-1">
                                <div class="col-md-12">
                                    <label class="small mb-1" for="availabilityStartDate">Datum početka
                                        rezervacije:</label>
                                    <input class="form-control" id="availabilityStartDate" name="availabilityStartDate"
                                        type="date" placeholder="" v-model="availabilityStart" />
                                </div>
                            </div>
                            <div class="row gx-3 mb-1">
                                <div class="col-md-12">
                                    <label class="small mb-1" for="availabilityEndDate">Datum kraja rezervacije:</label>
                                    <input class="form-control" id="availabilityEndDate" name="availabilityEndDate"
                                        type="date" placeholder="" v-model="availabilityEnd" />
                                </div>
                            </div>
                            <hr />

                            <div class="row gx-3 mb-1">
                                <div class="col-md-12">
                                    <label class="small mb-1" for="capacity">Broj ljudi:</label>
                                    <input class="form-control" id="capacity" name="capacity" type="number"
                                        placeholder="" v-model="capacity" />
                                </div>
                            </div>
                            <hr />

                            <div v-if="!(this.serviceProp.additionalServices?.length === 0)">
                                <p class="text-center">Dodatne usluge</p>

                                <b-form-group label="" v-slot="{ ariaDescribedby }">
                                    <b-form-checkbox-group id="checkbox-group-1" v-model="selected" :options="options"
                                        :aria-describedby="ariaDescribedby" name="flavour-1" v-on:change="kliknuto"
                                        v-on:input="kliknuto">
                                    </b-form-checkbox-group>
                                </b-form-group>

                                <hr />
                            </div>



                            <div class="row gx-3 mb-1">
                                <div class="col-md-6">
                                    <h5>Ukupno: </h5>
                                </div>
                                <div class="col-md-6">
                                    <div class="float-end" v-if="!(this.price === undefined)">${{ this.price }}</div>
                                    <div class="float-end" v-if="(this.price === undefined)">${{ this.priceProp }}</div>
                                </div>

                            </div>


                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Zatvori</button>
                        <button type="button" class="btn btn-primary" v-on:click="reservate">Rezerviši</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    name: 'CottageReservationModal',
    props: ['serviceProp', 'options', 'priceProp'],
    async mounted() {
        console.log(this.price);
        await this.setPrice();
        console.log(this.price);
    },
    data() {
        return {
            service: null,

            availabilityStart: '',
            availabilityEnd: '',
            availabilityStartStr: '',
            availabilityEndStr: '',

            capacity: '',
            additionalServices: [],


            selected: [], // Must be an array reference!
            price: this.serviceProp?.price
        }
    },
    methods: {
        kliknuto() {
            console.log('kliknuto');
            console.log(this.price);
            if (this.selected == null)
                return;
            this.price = this.serviceProp.price;
            for (let i = 0; i < this.selected?.length; i++) {
                this.price += this.selected[i].price;
            }
            console.log(this.price);
        },
        async setPrice() {
            if (this.priceProp != null)
                this.price = this.priceProp;
        },
        async reservate() {
            if (this.fieldsEmpty()) {
                alert('Morate popuniti sva polja!');
            }
            else if (!this.datesCorrectRelation()) {
                alert('Datum početka treba da bude pre datuma kraja rezervacije!');
            }
            else {
                axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
                axios.defaults.headers.common['Authorization'] = 'Bearer ' + this.$store.accessToken;

                await this.formatForSendingDates();
                let lista = await this.additionalServicesConvert();
                let jsonData = JSON.stringify({
                    startDate: this.availabilityStartStr,
                    endDate: this.availabilityEndStr,
                    capacity: this.capacity,
                    serviceId: this.serviceProp.id,
                    additionalServices: lista
                });

                await axios
                    .post('api/reservation/cottage',
                        jsonData,
                        { headers: { 'Content-Type': 'application/json' } })
                    .then(response => (
                        alert(response.data)
                    ))
                    .catch(function (err) {
                        if (err.response.status === 400) {
                            alert(err.response.data);
                        }
                        else {
                            alert(err);
                        }
                        console.log(err);
                    }
                    );


                this.availabilityStartStr = '';
                this.availabilityEndStr = '';

            }
        },
        async formatForSendingDates() {
            if (this.availabilityStart != '') {
                if (!this.availabilityStartStr.includes('T'))
                    this.availabilityStartStr = this.availabilityStart.toString() + 'T00:00:00';
            }
            if (this.availabilityEnd != '') {
                if (!this.availabilityEndStr.includes('T'))
                    this.availabilityEndStr = this.availabilityEnd.toString() + 'T00:00:00';
            }
        },
        fieldsEmpty() {
            if (this.availabilityStart == '' ||
                this.availabilityEnd == '' ||
                this.capacity == '') {
                return true;
            }
            else {
                return false;
            }
        },
        datesCorrectRelation() {
            let d1 = new Date(this.availabilityStart);
            let d2 = new Date(this.availabilityEnd);

            if (d1 < d2)
                return true;
            else
                return false;

        },
        async additionalServicesConvert() {
            let list = []
            for(let i = 0; i < this.selected.length; i++)
                list.push(this.selected[i].id);
            return list;
        }
    }
}

</script>

<style scoped>
.center-screen {
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    min-height: 100vh;
}
</style>

<template>
    <div v-if="!this.ReservationsLoaded" id="ucitavanje">Učitavanje!</div>
    <div v-else-if="this.ReservationsLoaded && this.ReservationsEmpty" id="nema-podataka">Nema podataka za prikaz!</div>
    <div v-else-if="this.ReservationsLoaded && this.ReservationsEmpty === false" pb-5>
        <div class="container mt-5 mb-0">
            <div class="d-flex justify-content-center row">
                <div class="col-md-10 mt-5">
                    <div class="row p-2 bg-white border rounded mt-2">
                        <div class="row gx-3 mb-1">
                            <div class="col-md-3">
                                <button type="button" class="btn btn-primary w-100"
                                    v-on:click="sortingByDateDescending">
                                    Sortiraj po datumu rastuće
                                </button>
                            </div>
                            <div class="col-md-3">
                                <button type="button" class="btn btn-primary w-100" v-on:click="sortingByDateAscending">
                                    Sortiraj po datumu opadajuće
                                </button>
                            </div>
                            <div class="col-md-3">
                                <button type="button" class="btn btn-primary w-100"
                                    v-on:click="sortingByPriceDescending">
                                    Sortiraj po ceni opadajuće
                                </button>
                            </div>
                            <div class="col-md-3">
                                <button type="button" class="btn btn-primary w-100"
                                    v-on:click="sortingByPriceAscending">
                                    Sortiraj po ceni rastuće
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container mt-0 mb-5">
            <div class="d-flex justify-content-center row">
                <div class="col-md-10 mt-5">
                    <div class="row p-2 bg-white border rounded mt-2" v-for="(reservation, i) in Reservations" :key="i">
                        <div class="col-md-3 col- mt-1">

                            <div :id="generateIdSlider(i)" class="carousel slide" data-bs-ride="carousel">
                                <div class="carousel-inner">
                                    <div class="carousel-item active" v-if="!(reservation.photos[0] === undefined)">
                                        <img :src="reservation.photos[0].assetPath"
                                            class="img-fluid img-responsive rounded product-image" alt="image">
                                    </div>
                                    <div v-for="(photo, i) in reservation.photos.slice(1)" :key="i"
                                        class="carousel-item">
                                        <img :src="photo.assetPath"
                                            class="img-fluid img-responsive rounded product-image" alt="image">
                                    </div>
                                </div>
                                <button class="carousel-control-prev" type="button"
                                    :data-bs-target="generateIdSliderWithHashTag(i)" data-bs-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Previous</span>
                                </button>
                                <button class="carousel-control-next" type="button"
                                    :data-bs-target="generateIdSliderWithHashTag(i)" data-bs-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Next</span>
                                </button>
                            </div>

                        </div>
                        <div class="col-md-6 mt-1">
                            <h5>{{ reservation.serviceName }}</h5>
                            <br />
                            <p class="text-justify para mb-0">Datum početka rezervacije: {{
                                    this.transformDates(reservation.startDate)
                            }}</p>
                            <p class="text-justify para mb-0">Datum kraja rezervacije: {{
                                    this.transformDates(reservation.endDate)
                            }}</p>
                            <p class="text-justify para mb-0">Broj osoba: {{ reservation.capacity }}</p>
                            <br />
                            <p class="text-justify para mb-0">Adresa: {{ this.transformAddress(reservation.address) }}
                            </p>
                        </div>
                        <div class="align-items-center align-content-center col-md-3 border-left pb-3">
                            <div class="d-flex flex-row align-items-center">
                                <h4 class="mr-1">${{ reservation.price }}</h4>
                            </div>
                            <div class="d-flex flex-column mt-2 pr-4">
                                <!-- <star-rating rating="3"></star-rating> -->
                                <star-rating v-model:rating="reservation.rating" :increment="0.01" :show-rating="false"
                                    read-only></star-rating>
                            </div>
                            <div class="d-flex flex-column mt-4 mb-3">
                                <button type="button" class="btn btn-primary w-100" data-bs-toggle="modal"
                                    data-bs-target="#exampleModal" v-if="!(this.$store.accessToken == null)">
                                    Oceni
                                </button>
                            </div>
                            <div class="d-flex flex-column mt-4">
                                <button type="button" class="btn btn-danger w-100" data-bs-toggle="modal"
                                    data-bs-target="#exampleModalReview" v-if="!(this.$store.accessToken == null)"
                                    v-on:click="this.setSelectedReservation(reservation.id)">
                                    Žalba
                                </button>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Komponenta za paginaciju -->
        <div v-if="this.totalPages > 1" mt-5 mb-0>
            <paginate v-model="page" :page-count=totalPages :page-range="3" :margin-pages="2"
                :click-handler="clickCallback" :prev-text="'Nazad'" :next-text="'Napred'"
                :container-class="'pagination'" :page-class="'page-item'">
            </paginate>
        </div>
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal fade" id="exampleModalReview" tabindex="-1" aria-labelledby="exampleModalLabel"
                aria-hidden="true">
                <div class="modal-dialog modal-dialog-scrollable">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Otkazivanje rezervacije</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"
                                v-on:click="modalClosing"></button>
                        </div>
                        <div class="modal-body">
                            <form id="searchForm">

                                <div class="row gx-3 mb-4">
                                    <div class="col-md-12">
                                        <label class="small mb-1">Unesite žalbu:</label>
                                    </div>
                                </div>

                                <div class="row gx-3 mb-4">
                                    <div class="col-md-12">
                                        <div class="d-flex aligns-items-center justify-content-center">
                                            <textarea v-model="this.complaintText" cols="45" rows="5"></textarea>
                                        </div>                                        
                                    </div>
                                </div>

                                <div class="row gx-3 mb-1">
                                    <div class="col-md-12">
                                        <div v-if="!(this.returnMessage === undefined || this.returnMessage === '')"
                                            :class="this.klasa">{{ this.returnMessage }}</div>
                                    </div>
                                </div>

                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"
                                v-on:click="modalClosing">Zatvori</button>
                            <button type="button" class="btn btn-primary" v-on:click="sendComplaint">Pošalji
                                žalbu</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</template>

<script>
import axios from 'axios';
import Paginate from "vuejs-paginate-next";
import StarRating from 'vue-star-rating';

export default {
    name: 'HistoricalReservationsOverview',
    data() {
        return {
            Reservations: [],
            ReservationsEmpty: true,
            page: 1,
            size: 5,
            totalPages: 0,
            responseData: {},
            photosExists: false,
            ReservationsLoaded: false,

            sortByDateAscending: false,
            sortByDateDescending: true,
            sortByPriceAscending: false,
            sortByPrisceDescending: false,

            idSelectedReservation: -1,
            returnMessage: '',
            klasa: 'text-primary',

            complaintText: ''
        }
    },
    mounted() {
        this.ReservationsLoaded = false;
        this.loadReservations();
        window.scrollTo(0, 0);
    },
    methods: {
        async clickCallback(pageNum) {
            console.log(pageNum);
            this.page = pageNum;
            this.ReservationsLoaded = false;

            await this.loadReservations();
            window.scrollTo(0, 0);
        },
        async loadReservations() {
            axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + this.$store.accessToken;

            let jsonData = JSON.stringify({
                sortByDateAscending: this.sortByDateAscending,
                sortByDateDescending: this.sortByDateDescending,
                sortByPriceAscending: this.sortByPriceAscending,
                sortByPrisceDescending: this.sortByPrisceDescending
            });

            await axios
                .post('/api/client/historicalreservations?size=' + this.size + '&page=' + this.page,
                    jsonData,
                    { headers: { 'Content-Type': 'application/json' } })
                .then(response => (
                    this.responseData = response.data,
                    this.Reservations = this.responseData.content,
                    this.totalPages = this.responseData.totalPages,
                    this.ReservationsEmpty = this.Reservations.length === 0 ? true : false,
                    this.ReservationsLoaded = true
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
            window.scrollTo(0, 0);
        },
        async sortingByDateAscending() {
            this.sortByDateAscending = true;
            this.sortByDateDescending = false;
            this.sortByPriceAscending = false;
            this.sortByPrisceDescending = false;

            await this.loadReservations();

        },
        async sortingByDateDescending() {
            this.sortByDateAscending = false;
            this.sortByDateDescending = true;
            this.sortByPriceAscending = false;
            this.sortByPrisceDescending = false;

            await this.loadReservations();

        },
        async sortingByPriceDescending() {
            this.sortByDateAscending = false;
            this.sortByDateDescending = false;
            this.sortByPriceAscending = false;
            this.sortByPrisceDescending = true;

            await this.loadReservations();

        },
        async sortingByPriceAscending() {
            this.sortByDateAscending = false;
            this.sortByDateDescending = false;
            this.sortByPriceAscending = true;
            this.sortByPrisceDescending = false;

            await this.loadReservations();

        },
        transformDates(date) {
            //  2020, 5, 6, 0, 0
            return date[2].toString() + '.' + date[1].toString() + '.' + date[0].toString() + '.';
        },
        transformAddress(address) {
            return address.street + ', ' + address.city + ', ' + address.state;
        },
        getNextPath(id) {               //************************************************************************* */
            if (!(this.$store.accessToken == null))
                return '/client/CottageDetails/' + id;
            else
                return '/CottageDetails/' + id;
        },
        generateIdSlider(id) {
            return 'carouselExampleIndicators' + id;
        },
        generateIdSliderWithHashTag(id) {
            return '#carouselExampleIndicators' + id;
        },
        modalClosing() {
            this.returnMessage = '';
            this.complaintText = '';
            this.klasa = 'text-primary';
        },
        setSelectedReservation(id) {
            this.idSelectedReservation = id;
        },
        async sendComplaint() {
            axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + this.$store.accessToken;
            
            let jsonData = JSON.stringify({
                reservationId: this.idSelectedReservation,
                complaintText: this.complaintText
            });

            await axios
                .post('api/client/makecomplaint',
                    jsonData,
                    { headers: { 'Content-Type': 'application/json' } })
                .then(response => (
                    this.returnMessage = response.data,
                    this.klasa = 'text-success'
                ))
                .catch(err => {
                    if (err.response.status === 400) {
                        // alert(err.response.data);
                        this.returnMessage = err.response.data,
                        this.klasa = 'text-danger'
                    }
                    else {
                        // alert(err);
                        this.returnMessage = err.response.data,
                        this.klasa = 'text-danger'
                    }
                    console.log(err);
                }
                );
        }
    },
    components: {
        paginate: Paginate,
        StarRating: StarRating
    }
}
</script>


<style scoped>
body {
    background: #eee;
}

.product-image {
    width: 100%
}

.dot {
    height: 7px;
    width: 7px;
    margin-left: 6px;
    margin-right: 6px;
    margin-top: 3px;
    background-color: blue;
    border-radius: 50%;
    display: inline-block
}

.spec-1 {
    color: #938787;
    font-size: 15px
}

h5 {
    font-weight: 400
}

.para {
    font-size: 16px
}

#nema-podataka {
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    min-height: 100vh;
    font-size: 28px;
    color: royalblue;
    background-color: white;
}

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

.pagination {
    /* border: 1px solid red; */
    width: 100%;
    display: flex;
    justify-content: center;
}

.form-control {
    display: block;
    width: 100%;
    padding: 0.875rem 1.125rem;
    font-size: 0.875rem;
    font-weight: 400;
    line-height: 1;
    color: #69707a;
    background-color: #fff;
    background-clip: padding-box;
    border: 1px solid #c5ccd6;
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
    border-radius: 0.35rem;
    transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}
</style>



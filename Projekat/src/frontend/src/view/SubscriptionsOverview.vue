<template>
    <div v-if="!this.ActionsLoaded" id="ucitavanje">Učitavanje!</div>
    <div v-else-if="this.ActionsLoaded && this.ActionsEmpty" id="nema-podataka">Nema podataka za prikaz!</div>
    <div v-else-if="this.ActionsLoaded && this.ActionsEmpty === false" pb-5>
        <div class="container mt-0 mb-5">
            <div class="d-flex justify-content-center row">
                <div class="col-md-10 mt-5">
                    <div class="row p-2 bg-white border rounded mt-2" v-for="(subscription, i) in Actions" :key="i">
                        <div class="col-md-3 col- mt-1">

                            <div :id="generateIdSlider(i)" class="carousel slide" data-bs-ride="carousel">
                                <div class="carousel-inner">
                                    <div class="carousel-item active" v-if="!(subscription.photos[0] === undefined)">
                                        <img :src="subscription.photos[0].assetPath"
                                            class="img-fluid img-responsive rounded product-image" alt="image">
                                    </div>
                                    <div v-for="(photo, i) in subscription.photos.slice(1)" :key="i" class="carousel-item">
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
                            <h5>{{ subscription.name }}</h5>
                            <br />
                            <p class="text-justify para mb-0">{{ subscription.description }}<br><br></p>
                            <br />
                            <p class="text-justify para mb-0">Adresa: {{ this.transformAddress(subscription.address) }}</p>
                        </div>
                        <div class="align-items-center align-content-center col-md-3 border-left pb-3">
                            <div class="d-flex flex-row align-items-center">
                                <h4 class="mr-1">${{ subscription.price }}</h4>
                            </div>
                            <div class="d-flex flex-column mt-2 pr-4">
                                <star-rating v-model:rating="subscription.rating" :increment="0.01" :show-rating="false"
                                    read-only></star-rating>
                            </div>
                            <div class="d-flex flex-column mt-4">

                                <button type="button" class="btn btn-danger w-100" data-bs-toggle="modal"
                                    data-bs-target="#exampleModalUnSubscription" v-if="!(this.$store.accessToken == null)"
                                    v-on:click="this.setSelectedSubscription(subscription.serviceId)">
                                    Otkaži pretplatu
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
            <div class="modal fade" id="exampleModalUnSubscription" tabindex="-1" aria-labelledby="exampleModalLabel"
                aria-hidden="true">
                <div class="modal-dialog modal-dialog-scrollable">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Otkazivanje pretplate</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" v-on:click="modalClosing"></button>
                        </div>
                        <div class="modal-body">
                            <form id="searchForm">

                                <div class="row gx-3 mb-4">
                                    <div class="col-md-12">
                                        <label class="small mb-1">Da li ste sigurni da želite da otkažete pretplatu:</label>
                                    </div>
                                </div>

                                <div class="row gx-3 mb-1">
                                    <div class="col-md-12">
                                        <div v-if="!(this.returnMessage===undefined || this.returnMessage==='')" :class="this.klasa">{{ this.returnMessage }}</div>
                                    </div>
                                </div>

                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" v-on:click="modalClosing">Zatvori</button>
                            <button type="button" class="btn btn-primary" v-on:click="cancelSubscription">Otkaži
                                pretplatu</button>
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
    name: 'SubscriptionsOverview',
    data() {
        return {
            Actions: [],
            ActionsEmpty: true,
            ActionsLoaded: false,
            page: 1,
            size: 5,
            totalPages: 0,
            responseData: {},
            photosExists: false,

            idSelectedSubscription: -1,
            returnMessage: '',
            klasa: 'text-primary'
        }
    },
    mounted() {
        this.ActionsLoaded = false;
        this.loadSubscriptionsGet();
        window.scrollTo(0, 0);
    },
    methods: {
        async cancelSubscription() {
            axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + this.$store.accessToken;

            await axios
                .delete('api/subscription/remove-subscription/' + this.idSelectedSubscription)
                .then(response => (
                    this.returnMessage = response.data,
                    this.klasa = 'text-success'
                ))
                .catch(err => {
                        if (err.response.status === 400) {
                            this.returnMessage = err.response.data,
                            this.klasa = 'text-danger'
                        }
                        else {
                            this.returnMessage = err.response.data,
                            this.klasa = 'text-danger'
                        }
                        console.log(err);
                    }
                );
            this.loadSubscriptionsGet();
        },
        async clickCallback(pageNum) {
            this.page = pageNum;
            this.ActionsLoaded = false;

            await this.loadSubscriptionsGet();
            window.scrollTo(0, 0);
        },
        async loadSubscriptionsGet() {
            axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + this.$store.accessToken;

            await axios
                .get('/api/subscription/get-all-client-subscriptions/?size=' + this.size + '&page=' + this.page)
                .then(response => (
                    this.responseData = response.data,
                    this.Actions = this.responseData.content,
                    this.totalPages = this.responseData.totalPages,
                    this.ActionsEmpty = this.Actions.length === 0 ? true : false,
                    this.ActionsLoaded = true
                ));
        },
        transformAddress(address) {
            return address.street + ', ' + address.city + ', ' + address.state;
        },
        getNextPath(id) {                                   // **********************
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
        setSelectedSubscription(id) {
            this.idSelectedSubscription = id;
        },
        modalClosing() {
            this.returnMessage = ''
            this.klasa = 'text-primary'
            this.idSelectedSubscription = -1
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



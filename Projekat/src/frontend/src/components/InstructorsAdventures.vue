<template>
    <div v-if="!this.adventuresLoaded" id="ucitavanje">Učitavanje!</div>
    <div v-else-if="this.adventuresLoaded && this.AdventuresEmpty" id="nema-podataka">Nema podataka za prikaz!</div>
    <div v-else-if="this.adventuresLoaded && this.AdventuresEmpty === false" pb-5>
        <div class="container mt-5 mb-5">
            <div class="d-flex justify-content-center row">
                <div class="col-md-10 mt-5">
                    <div class="row p-2 bg-white border rounded mt-2" v-for="(adventure, i) in Adventures" :key="i">
                        <div class="col-md-3 col- mt-1">
                            <div :id="generateIdSlider(i)" class="carousel slide" data-bs-ride="carousel">
                                <div class="carousel-inner">
                                    <div class="carousel-item active" v-if="!(adventure.photos[0] === undefined)">
                                        <img :src="adventure.photos[0].assetPath"
                                            class="img-fluid img-responsive rounded product-image" alt="image" />
                                    </div>
                                    <div v-for="(photo, i) in adventure.photos.slice(1)" :key="i" class="carousel-item">
                                        <img :src="photo.assetPath"
                                            class="img-fluid img-responsive rounded product-image" alt="image" />
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
                            <h5>{{ adventure.name }}</h5>
                            <br />
                            <p class="text-justify para mb-0">
                                {{ adventure.description }}
                                <br />
                                <br />
                            </p>
                            <br />
                            <p class="text-justify para mb-0">Adresa: {{ this.transformAddress(adventure.address) }}</p>
                        </div>
                        <div class="align-items-center align-content-center col-md-3 border-left pb-3">
                            <div class="d-flex flex-row align-items-center">
                                <h4 class="mr-1">${{ adventure.price }}</h4>
                            </div>
                            <div class="d-flex flex-column mt-4">
                                <!-- <button class="btn btn-primary btn-sm" type="button" onclick="#/novaKomponenta">Detalji</button> -->
                                <!-- <a href="#/novaKomponenta">Detalji</a> -->
                                <router-link class="btn btn-primary btn-sm" :to="getNextPath(adventure.id)">Detalji
                                </router-link>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
</style>

<script>

import axios from 'axios';

export default {
    name: 'InstructorsAdventures',
    data() {
        return {
            Adventures: [],
            AdventuresEmpty: true,
            photosExists: false,
            adventuresLoaded: false,
        }
    },
    mounted() {
        this.adventuresLoaded = false;
        this.loadAdventuresGet();
    },
    methods: {
        async loadAdventuresGet() {
            let self = this;
            axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + this.$store.accessToken;
            axios
                .get('/api/instructor/'+ this.$store.User.id +'/adventures/all/')
                .then((response) => {
                    self.Adventures = response.data,
                    self.AdventuresEmpty = self.Adventures.length === 0 ? true : false,
                    self.adventuresLoaded = true
                    console.log(response);
                }).catch((err) => {
                    console.log(err);
                    alert(err.response.status);
                });
        },
        generateIdSlider(id) {
            return 'carouselExampleIndicators' + id;
        },
        generateIdSliderWithHashTag(id) {
            return '#carouselExampleIndicators' + id;
        },
        getNextPath(id) {
            return '/instructor/view-adventure-details/' + id
        },
        transformAddress(address) {
            return address.street + ', ' + address.city + ', ' + address.state;
        },
    }
}

</script>
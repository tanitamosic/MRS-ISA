<template>
    <div v-if="!this.adventuresLoaded" id="ucitavanje">Učitavanje!</div>
    <div v-else-if="this.adventuresLoaded && this.AdventuresEmpty" id="nema-podataka">Nema podataka za prikaz!</div>
    <div v-else class="container mt-5 mb-5">
        <div class="d-flex justify-content-center row">
            <div class="col-md-10 mt-5">
                <div class="row p-2 bg-white border rounded mt-2" v-for="(adventure, i) in Adventures" :key="i">
                    <div class="col-md-3 col- mt-1">

                        <div :id="generateIdSlider(i)" class="carousel slide" data-bs-ride="carousel">
                            <div class="carousel-inner">
                                <div class="carousel-item active">
                                    <img :src="adventure.photos[0].assetPath"
                                        class="img-fluid img-responsive rounded product-image" alt="image">
                                </div>
                                <div v-for="(photo, i) in adventure.photos.slice(1)" :key="i" class="carousel-item">
                                    <img :src="photo.assetPath" class="img-fluid img-responsive rounded product-image"
                                        alt="image">
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
                        <p class="text-justify para mb-0">{{ adventure.description }}<br><br></p>
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
    <div>
        <paginate v-model="page" :page-count=totalPages :page-range="3" :margin-pages="2" :click-handler="clickCallback"
            :prev-text="'Nazad'" :next-text="'Napred'" :container-class="'pagination'" :page-class="'page-item'">
        </paginate>
    </div>
</template>

<script>
import axios from 'axios';
import Paginate from "vuejs-paginate-next";

export default {
    name: 'AdventureOverview',
    data() {
        return {
            Adventures: [],
            AdventuresEmpty: true,
            page: 1,
            size: 5,
            totalPages: 0,
            responseData: {},
            photosExists: false,
            adventuresLoaded: false
        }
    },
    mounted() {
        this.adventuresLoaded = false;
        axios
            .get('/api/adventures/all/withPagination?size=' + this.size + '&page=' + this.page)
            .then(response => (
                this.responseData = response.data,
                this.Adventures = this.responseData.content,
                this.totalPages = this.responseData.totalPages,
                this.AdventuresEmpty = this.Adventures.length === 0 ? true : false,
                this.adventuresLoaded = true
            ));
        window.scrollTo(0, 0);
    },
    methods: {
        clickCallback(pageNum) {
            //console.log(pageNum);
            this.adventuresLoaded = false;
            this.page = pageNum
            axios
                .get('/api/adventures/all/withPagination?size=' + this.size + '&page=' + pageNum)
                .then(response => (
                    this.responseData = response.data,
                    this.Adventures = this.responseData.content,
                    this.totalPages = this.responseData.totalPages,
                    this.AdventuresEmpty = this.Adventures.length === 0 ? true : false,
                    this.adventuresLoaded = true
                ));
            window.scrollTo(0, 0);
        },
        transformAddress(address) {
            return address.street + ', ' + address.city + ', ' + address.state;
        },
        getNextPath(id) {
            return '/AdventureDetails/' + id;
        },
        generateIdSlider(id) {
            return 'carouselExampleIndicators' + id;
        },
        generateIdSliderWithHashTag(id) {
            return '#carouselExampleIndicators' + id;
        }
    },
    components: {
        paginate: Paginate
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
</style>



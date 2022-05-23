<template>
    <div v-if="CottagesEmpty" id="nema-podataka">Nema podataka za prikaz!</div>
    <div v-else class="container mt-5 mb-5">
        <div class="d-flex justify-content-center row">
            <div class="col-md-10 mt-5">
                <div class="row p-2 bg-white border rounded mt-2" v-for="(cotage, i) in Cottages" :key="i">
                    <div class="col-md-3 col- mt-1">
                        <img class="img-fluid img-responsive rounded product-image" :src="cotage.primaryPhoto.assetPath">
                        <!-- <img class="img-fluid img-responsive rounded product-image" src="/img/cottages/vikendica1.jpg"> -->
                    </div>
                    <div class="col-md-6 mt-1">
                        <h5>{{ cotage.name }}</h5>
                        <br />
                        <p class="text-justify para mb-0">{{cotage.description}}<br><br></p>
                        <br />
                        <p class="text-justify para mb-0">Adresa: {{ this.transformAddress(cotage.address) }}</p>
                    </div>
                    <div class="align-items-center align-content-center col-md-3 border-left pb-3">
                        <div class="d-flex flex-row align-items-center">
                            <h4 class="mr-1">${{ cotage.price }}</h4>
                        </div>
                        <div class="d-flex flex-column mt-4">
                            <!-- <button class="btn btn-primary btn-sm" type="button" onclick="#/novaKomponenta">Detalji</button> -->
                            <!-- <a href="#/novaKomponenta">Detalji</a> -->
                            <router-link class="btn btn-primary btn-sm" :to="getNextPath(cotage.id)">Detalji</router-link>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div>
        <paginate
            v-model="page"
            :page-count=totalPages
            :page-range="3"
            :margin-pages="2"
            :click-handler="clickCallback"
            :prev-text="'Nazad'"
            :next-text="'Napred'"
            :container-class="'pagination'"
            :page-class="'page-item'"
        >
        </paginate>
    </div>
</template>

<script>
import axios from 'axios';
import Paginate from "vuejs-paginate-next";

export default {
    name: 'CardComponent',
    data() {
        return {
            Cottages : [],
            CottagesEmpty : true,
            page: 1,
            size: 5,
            totalPages: 0,
            responseData: {}
        }
    },
    mounted() {
        axios
            .get('/api/cottages/all/withPagination?size=' + this.size + '&page=' + this.page)
            .then(response => (
                this.responseData = response.data,
                this.Cottages = this.responseData.content,
                this.totalPages = this.responseData.totalPages,
                this.CottagesEmpty = this.Cottages.length === 0 ? true : false
                ));
        // this.Cottages = response.data.content
        // console.log("Pre " + this.CottagesEmpty);
        // console.log("Posle " + this.CottagesEmpty);
        // console.log("Duzina this.Cottages.length: " + this.Cottages.length);
    },
    methods: {
        clickCallback (pageNum) {
            console.log(pageNum);
            this.page = pageNum
            axios
                .get('/api/cottages/all/withPagination?size=' + this.size + '&page=' + pageNum)
                .then(response => (
                        this.responseData = response.data,
                        this.Cottages = this.responseData.content,
                        this.totalPages = this.responseData.totalPages,
                        this.CottagesEmpty = this.Cottages.length === 0 ? true : false
                    ));
            // this.Cottages = response.data.content
            // console.log("Pre " + this.CottagesEmpty);
            // console.log("Posle " + this.CottagesEmpty);
            // console.log("Duzina this.Cottages.length: " + this.Cottages.length);
        },
        transformAddress(address) {
            return address.street + ', ' + address.city + ', ' + address.state;
        },
        getNextPath(id) {
            return '/CottageDetails/' + id;
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
    background-color: beige;
}

.pagination {
    /* border: 1px solid red; */
    width: 100%;
    display: flex;
    justify-content: center;
}


</style>



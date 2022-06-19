<template>
    <div>
        <!-- Kartica za formu koja sluzi za pretragu -->
        <div class="container mt-5 mb-0">
            <div class="d-flex justify-content-center row">
                <div class="col-md-10 mt-5">
                    <div class="row p-2 bg-white border rounded mt-2">
                        <form id="searchForm">
                            <!-- Form Row-->
                            <div class="row gx-3 mb-1">
                                <!-- Form Group (Pretraga)-->
                                <div class="col-md-12">
                                    <label class="small mb-1" for="GeneralSearch">Pretraga</label>
                                    <input class="form-control" id="GeneralSearch" name="GeneralSearch" type="text"
                                        v-model="generalSearchField" placeholder="" />
                                </div>
                            </div>
                            <div class="row gx-3 mb-1">
                                <!-- Form Group (DPD i DKD)-->
                                <div class="col-md-6">
                                    <label class="small mb-1" for="availabilityStartDate">Datum početka
                                        dostupnosti</label>
                                    <input class="form-control" id="availabilityStartDate" name="availabilityStartDate"
                                        v-model="availabilityStart" type="date" placeholder="" />
                                </div>
                                <div class="col-md-6">
                                    <label class="small mb-1" for="availabilityEndDate">Datum kraja dostupnosti</label>
                                    <input class="form-control" id="availabilityEndDate" name="availabilityEndDate"
                                        v-model="availabilityEnd" type="date" placeholder="" />
                                </div>
                            </div>
                            <hr />
                            <div class="row gx-3 mb-1">
                                <!-- Form Group (Drzava, Grad)-->
                                <div class="col-md-6">
                                    <label class="small mb-1" for="state">Država</label>
                                    <input class="form-control" id="state" name="state" type="text" placeholder=""
                                        v-model="state" />
                                </div>
                                <div class="col-md-6">
                                    <label class="small mb-1" for="city">Grad</label>
                                    <input class="form-control" id="city" name="city" type="text" placeholder=""
                                        v-model="city" />
                                </div>
                            </div>
                            <hr />
                            <div class="row gx-3 mb-1">
                                <!-- Form Group (Cena od do)-->
                                <div class="col-md-3">
                                    <label class="small mb-1" for="priceFrom">Cena od:</label>
                                    <input class="form-control" id="priceFrom" name="priceFrom" type="number" step="1"
                                        v-model="priceFrom" placeholder="" />
                                </div>
                                <div class="col-md-3">
                                    <label class="small mb-1" for="priceTo">Cena do:</label>
                                    <input class="form-control" id="priceTo" name="priceTo" type="number" step="1"
                                        v-model="priceTo" placeholder="" />
                                </div>
                                <!-- Form Group (Ocena od do)-->
                                <div class="col-md-6">
                                    <label class="small mb-1" for="review">Ocena:</label>
                                    <input class="form-control" id="review" name="review" type="number" step="1"
                                        v-model="rating" placeholder="" />
                                </div>
                            </div>
                            <hr />
                            <div class="row gx-3 mb-1">
                                <!-- Form Group (Ime Broda)-->
                                <div class="col-md-12">
                                    <label class="small mb-1" for="boatName">Ime Broda</label>
                                    <input class="form-control" id="boatName" name="boatName" type="text" placeholder=""
                                        v-model="boatName" />
                                </div>
                            </div>
                            <div class="row gx-4 mb-3">
                                <!-- Form Group (Tip broda, duzina, broj osoba)-->
                                <div class="col-md-6">
                                    <label class="small mb-1" for="boatType">Tip broda</label>
                                    <input class="form-control" id="boatType" name="boatType" type="text" placeholder=""
                                        v-model="boatType" />
                                </div>
                                <div class="col-md-6">
                                    <label class="small mb-1" for="numberPersons">Broj osoba:</label>
                                    <input class="form-control" id="numberPersons" name="numberPersons" type="number"
                                        v-model="capacity" step="1" placeholder="" />
                                </div>
                            </div>
                            <hr />
                            <div class="row gx-3 mb-3">
                                <!-- Form Group (Navigaciona oprema, Oprema za pecanje)-->
                                <div class="col-md-6">
                                    <label class="small mb-1" for="navigationalEquipment">Navigaciona oprema</label>
                                    <input class="form-control" id="navigationalEquipment" name="navigationalEquipment"
                                        v-model="navigationalEquipment" type="text" placeholder="" />
                                </div>
                                <div class="col-md-6">
                                    <label class="small mb-1" for="fishingEquipment">Oprema za pecanje</label>
                                    <input class="form-control" id="fishingEquipment" name="fishingEquipment"
                                        v-model="fishingEquipment" type="text" placeholder="" />
                                </div>
                            </div>
                            <div class="row gx-3 mb-1">
                                <!-- Form Group (Dugme Pretraži)-->
                                <div class="col-md-6">
                                </div>
                                <div class="col-md-6 text-center">
                                    <button class="btn btn-primary" type="button"
                                        v-on:click="searchBoats">Pretraži</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div v-if="!this.boatsLoaded" id="ucitavanje">Učitavanje!</div>
    <div v-else-if="this.boatsLoaded && this.BoatsEmpty" id="nema-podataka">Nema podataka za prikaz!</div>
    <div v-else-if="this.boatsLoaded && this.BoatsEmpty === false" pb-5>
        <div class="container mt-5 mb-5">
            <div class="d-flex justify-content-center row">
                <div class="col-md-10 mt-5">
                    <div class="row p-2 bg-white border rounded mt-2" v-for="(boat, i) in Boats" :key="i">
                        <div class="col-md-3 col- mt-1">

                            <div :id="generateIdSlider(i)" class="carousel slide" data-bs-ride="carousel">
                                <div class="carousel-inner">
                                    <div class="carousel-item active" v-if="!(boat.photos[0] === undefined)">
                                        <img :src="boat.photos[0].assetPath"
                                            class="img-fluid img-responsive rounded product-image" alt="image">
                                    </div>
                                    <div v-for="(photo, i) in boat.photos.slice(1)" :key="i" class="carousel-item">
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
                            <h5>{{ boat.name }}</h5>
                            <br />
                            <p class="text-justify para mb-0">{{ boat.description }}<br><br></p>
                            <br />
                            <p class="text-justify para mb-0">Adresa: {{ this.transformAddress(boat.address) }}</p>
                        </div>
                        <div class="align-items-center align-content-center col-md-3 border-left pb-3">
                            <div class="d-flex flex-row align-items-center">
                                <h4 class="mr-1">${{ boat.price }}</h4>
                            </div>
                            <div class="d-flex flex-column mt-2 pr-4">
                                <star-rating v-model:rating="boat.rating" :increment="0.01" :show-rating="false" read-only></star-rating>
                            </div>
                            <div class="d-flex flex-column mt-4">
                                <!-- <button class="btn btn-primary btn-sm" type="button" onclick="#/novaKomponenta">Detalji</button> -->
                                <!-- <a href="#/novaKomponenta">Detalji</a> -->
                                <router-link v-if="!isAdmin" class="btn btn-primary btn-sm" :to="getNextPath(boat.id)">Detalji
                                </router-link>
                                <button v-if="isAdmin" class="btn btn-danger btn-sm mt-1" v-on:click="deleteBoat(boat.id)">Obriši</button>
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
    </div>

</template>

<script>
import axios from 'axios';
import Paginate from "vuejs-paginate-next";
import StarRating from 'vue-star-rating';

export default {
    name: 'BoatsOverview',
    data() {
        return {
            Boats: [],
            BoatsEmpty: true,
            page: 1,
            size: 5,
            totalPages: 0,
            responseData: {},
            photosExists: false,
            boatsLoaded: false,

            generalSearchField: '',
            availabilityStart: '',
            availabilityEnd: '',
            state: '',
            city: '',
            priceFrom: '',
            priceTo: '',
            rating: '',

            boatName: '',
            boatType: '',
            capacity: '',
            navigationalEquipment: '',
            fishingEquipment: '',

            availabilityStartStr: '',
            availabilityEndStr: '',

            isAdmin: false

        }
    },
    mounted() {
        this.boatsLoaded = false;
        this.loadBoatsGet();
        window.scrollTo(0, 0);

        if(this.$store.role === "ROLE_ADMIN") {
            this.isAdmin = true;
        }
    },
    methods: {
        async deleteBoat(id) {
            let self = this;
            axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + this.$store.accessToken;
            axios.delete('/api/admin/delete-boat/' + id)
            .then((response) => {
                if (response.status === 200) {
                    alert(response.data)
                    // RE-GET BOATS
                    self.boatsLoaded = false;
                    self.loadBoatsGet();
                    window.scrollTo(0, 0);
                }
            }).catch((err) => {
                console.log(err);
                alert(err);
            });

         
        
        },
        async clickCallback(pageNum) {
            //console.log(pageNum);
            this.boatsLoaded = false;
            this.page = pageNum

            if (this.searchFieldsEmpty()) {
                await this.loadBoatsGet();
            }
            else {
                await this.searchBoats();
            }

            window.scrollTo(0, 0);
        },
        async loadBoatsGet() {
            axios
                .get('/api/boats/all/withPagination?size=' + this.size + '&page=' + this.page)
                .then(response => (
                    this.responseData = response.data,
                    this.Boats = this.responseData.content,
                    this.totalPages = this.responseData.totalPages,
                    this.BoatsEmpty = this.Boats.length === 0 ? true : false,
                    this.boatsLoaded = true
                ));
        },
        transformAddress(address) {
            return address.street + ', ' + address.city + ', ' + address.state;
        },
        getNextPath(id) {
            if(!(this.$store.accessToken==null))
                return '/client/BoatDetails/' + id;
            else
                return '/BoatDetails/' + id;
        },
        generateIdSlider(id) {
            return 'carouselExampleIndicators' + id;
        },
        generateIdSliderWithHashTag(id) {
            return '#carouselExampleIndicators' + id;
        },
        async searchBoats() {
            // provera da li je korisnik uneo nesto
            if (this.searchFieldsEmpty()) {
                alert('Neophodno je da popunite neka polja!');
            }
            else {
                this.boatsLoaded = false;
                await this.formatForSendingDates();
                let jsonData = JSON.stringify({
                    "generalSearchField": this.generalSearchField,
                    "availabilityStart": this.availabilityStartStr,
                    "availabilityEnd": this.availabilityEndStr,
                    "state": this.state,
                    "city": this.city,
                    "priceFrom": this.priceFrom,
                    "priceTo": this.priceTo,
                    "rating": this.rating,
                    "boatName": this.boatName,
                    "boatType": this.boatType,
                    "capacity": this.capacity,
                    "navigationalEquipment": this.navigationalEquipment,
                    "fishingEquipment": this.fishingEquipment
                });

                await axios
                    .post('api/search/boats?size=' + this.size + '&page=' + this.page,
                        jsonData,
                        { headers: { 'Content-Type': 'application/json' } })
                    .then(response => (
                        this.responseData = response.data,
                        this.Boats = this.responseData.content,
                        this.totalPages = this.responseData.totalPages,
                        this.BoatsEmpty = this.Boats.length === 0 ? true : false,
                        this.boatsLoaded = true
                    ))
                    .catch(function (err) {
                        if (err.response.status === 400) {
                            alert(err.response.data);
                            location.reload();
                        }
                        else {
                            alert(err);
                        }
                        console.log(err);
                    }
                    );

                this.availabilityStartStr = '';
                this.availabilityEndStr = '';

                window.scrollTo(0, 0);



            }
        },
        searchFieldsEmpty() {
            if (this.generalSearchField == '' &&
                this.availabilityStart == '' &&
                this.availabilityEnd == '' &&
                this.state == '' &&
                this.city == '' &&
                this.priceFrom == '' &&
                this.priceTo == '' &&
                this.rating == '' &&
                this.boatName == '' &&
                this.boatType == '' &&
                this.capacity == '' &&
                this.navigationalEquipment == '' &&
                this.fishingEquipment == '') {
                return true;
            }
            else {
                return false;
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
</style>



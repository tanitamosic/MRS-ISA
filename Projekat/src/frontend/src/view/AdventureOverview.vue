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
                                        v-model="generalSearchField"
                                        placeholder="" />
                                </div>
                            </div>
                            <div class="row gx-3 mb-1">
                                <!-- Form Group (DPD i DKD)-->
                                <div class="col-md-6">
                                    <label class="small mb-1" for="availabilityStartDate">Datum početka
                                        dostupnosti</label>
                                    <input class="form-control" id="availabilityStartDate" name="availabilityStartDate"
                                        v-model="availabilityStart"
                                        type="date" placeholder="" />
                                </div>
                                <div class="col-md-6">
                                    <label class="small mb-1" for="availabilityEndDate">Datum kraja dostupnosti</label>
                                    <input class="form-control" id="availabilityEndDate" name="availabilityEndDate"
                                        v-model="availabilityEnd"
                                        type="date" placeholder=""/>
                                </div>
                            </div>
                            <hr />
                            <div class="row gx-3 mb-1">
                                <!-- Form Group (Drzava, Grad)-->
                                <div class="col-md-6">
                                    <label class="small mb-1" for="state">Država</label>
                                    <input class="form-control" id="state" name="state" type="text" placeholder=""
                                        v-model="state"
                                        />
                                </div>
                                <div class="col-md-6">
                                    <label class="small mb-1" for="city">Grad</label>
                                    <input class="form-control" id="city" name="city" type="text" placeholder=""
                                        v-model="city"
                                        />
                                </div>
                            </div>
                            <hr />
                            <div class="row gx-3 mb-1">
                                <!-- Form Group (Cena od do)-->
                                <div class="col-md-3">
                                    <label class="small mb-1" for="priceFrom">Cena od:</label>
                                    <input class="form-control" id="priceFrom" name="priceFrom" type="number" step="1"
                                        v-model="priceFrom"
                                        placeholder="" />
                                </div>
                                <div class="col-md-3">
                                    <label class="small mb-1" for="priceTo">Cena do:</label>
                                    <input class="form-control" id="priceTo" name="priceTo" type="number" step="1"
                                        v-model="priceTo"
                                        placeholder="" />
                                </div>
                                <!-- Form Group (Ocena od do)-->
                                <div class="col-md-6">
                                    <label class="small mb-1" for="review">Ocena:</label>
                                    <input class="form-control" id="review" name="review" type="number" step="1"
                                        v-model="rating"
                                        placeholder="" />
                                </div>
                            </div>
                            <hr />
                            <div class="row gx-3 mb-1">
                                <!-- Form Group (Ime Avanture)-->
                                <div class="col-md-12">
                                    <label class="small mb-1" for="adventureName">Ime Avanture</label>
                                    <input class="form-control" id="adventureName" name="adventureName" type="text" placeholder=""
                                        v-model="adventureName"
                                        />
                                </div>
                            </div>
                            <div class="row gx-3 mb-3">
                                <!-- Form Group (Broj kreveta i soba)-->
                                <div class="col-md-6">
                                    <label class="small mb-1" for="numberPersons">Broj osoba:</label>
                                    <input class="form-control" id="numberPersons" name="numberPersons" type="number"
                                        v-model="numberOfPersons"
                                        step="1" placeholder="" />
                                </div>
                                <div class="col-md-6">
                                    <label class="small mb-1" for="fishingEquipment">Oprema za pecanje</label>
                                    <input class="form-control" id="fishingEquipment" name="fishingEquipment" type="text"
                                        v-model="fishingEquipment"
                                        placeholder="" />
                                </div>
                            </div>
                            <div class="row gx-3 mb-1">
                                <!-- Form Group (Dugme Pretraži)-->
                                <div class="col-md-6">
                                </div>
                                <div class="col-md-6 text-center">
                                        <button class="btn btn-primary" type="button"
                                            v-on:click="searchAdventures"
                                            >Pretraži</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div v-if="!this.adventuresLoaded" id="ucitavanje">Učitavanje!</div>
    <div v-else-if="this.adventuresLoaded && this.AdventuresEmpty" id="nema-podataka">Nema podataka za prikaz!</div>
    <div v-else-if="this.adventuresLoaded && this.AdventuresEmpty===false" class="container mt-5 mb-5">
        <div class="d-flex justify-content-center row">
            <div class="col-md-10 mt-5">
                <div class="row p-2 bg-white border rounded mt-2" v-for="(adventure, i) in Adventures" :key="i">
                    <div class="col-md-3 col- mt-1">

                        <div :id="generateIdSlider(i)" class="carousel slide" data-bs-ride="carousel">
                            <div class="carousel-inner">
                                <div class="carousel-item active" v-if="!(adventure.photos[0]===undefined)">
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
            adventuresLoaded: false,

            generalSearchField: '',
            availabilityStart: '',
            availabilityEnd: '',
            state: '',
            city: '',
            priceFrom: '',
            priceTo: '',
            rating: '',
            adventureName: '',
            numberOfPersons: '',
            fishingEquipment: '',

            availabilityStartStr: '',
            availabilityEndStr: ''
        }
    },
    mounted() {
        this.adventuresLoaded = false;
        this.loadAdventuresGet();
        window.scrollTo(0, 0);
    },
    methods: {
        async clickCallback(pageNum) {
            console.log(pageNum);
            this.page = pageNum
            this.adventuresLoaded = false;
            
            if(this.searchFieldsEmpty()) {
                await this.loadAdventuresGet();
            }
            else {
                await this.searchAdventures();
            }
            window.scrollTo(0, 0);
        },
        async loadAdventuresGet() {
            axios
                .get('/api/adventures/all/withPagination?size=' + this.size + '&page=' + this.page)
                .then(response => (
                    this.responseData = response.data,
                    this.Adventures = this.responseData.content,
                    this.totalPages = this.responseData.totalPages,
                    this.AdventuresEmpty = this.Adventures.length === 0 ? true : false,
                    this.adventuresLoaded = true
                ));
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
        },
        async searchAdventures() {
            // provera da li je korisnik uneo nesto
            if(this.searchFieldsEmpty()) {
                alert('Neophodno je da popunite neka polja!');
            }
            else {
                this.adventuresLoaded = false;
                
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
                    "adventureName": this.adventureName,
                    "numberOfPersons": this.numberOfPersons,
                    "fishingEquipment": this.fishingEquipment
                });

                await axios
                    .post('api/search/adventures?size=' + this.size + '&page=' + this.page,
                        jsonData,
                        { headers: { 'Content-Type': 'application/json'} })
                    .then(response => (
                        this.responseData = response.data,
                        this.Adventures = this.responseData.content,
                        this.totalPages = this.responseData.totalPages,
                        this.AdventuresEmpty = this.Adventures.length === 0 ? true : false,
                        this.adventuresLoaded = true
                    ))
                    .catch(function (err){
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
            this.adventureName == '' &&
            this.numberOfPersons == '' &&
            this.fishingEquipment == '') {
                return true;
            }
            else {
                return false;
            }
        },
        async formatForSendingDates() {
            console.log('Start pocetak:' + this.availabilityStart)
                if(this.availabilityStart != '') {
                    console.log('start usao');
                    if(!this.availabilityStartStr.includes('T'))
                        this.availabilityStartStr = this.availabilityStart.toString() + 'T00:00:00';
                }
                console.log('Start kraj ' + this.availabilityStart);
                console.log('End pocetak ' + this.availabilityEnd);
                if(this.availabilityEnd != '') {
                    console.log('end usao');
                    if(!this.availabilityEndStr.includes('T'))
                        this.availabilityEndStr = this.availabilityEnd.toString() + 'T00:00:00';
                }
                console.log('End kraj ' + this.availabilityEnd);
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



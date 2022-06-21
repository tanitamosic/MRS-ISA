<template>
    <div class="card mx-4 mx-md-5 shadow-lg">
        <div class="card-body py-5 px-md-5">
            <div class="row d-flex justify-content-center">
                <div class="col-lg-8">
                    <h2 class="fw-bold mb-1">Nova avantura</h2>
                    <hr>
                    <div class="row gx-3 mb-3">
                        <div class="col-md-12">
                            <input placeholder="Naziv" v-model="name" type="text" class="form-control"
                                style="font-size: 24px;">
                        </div>

                    </div>
                    <div class="row gx-3 mb-3" id="customDays">
                        <div class="col-md-6">
                            <label class="small mb-1" for="fromDate">Odaberite od kada pocinje termin (datum)</label>
                            <input type="date" name="fromDate" class="form-control" v-model="dateFrom" />
                        </div>
                        <div class="col-md-6">
                            <label class="small mb-1" for="timeFrom">Odaberite od kada pocinje termin (vreme)</label>
                            <input type="time" name="timeFrom" class="form-control" v-model="timeFrom" />
                        </div>
                    </div>
                    <div class="row gx-3 mb-3" id="customDays">
                        <div class="col-md-6">
                            <label class="small mb-1" for="dateTo">Odaberite do kada traje termin (datum)</label>
                            <input type="date" class="form-control" name="dateTo" v-model="dateTo" />
                        </div>
                        <div class="col-md-6">
                            <label class="small mb-1" for="timeTo">Odaberite do kada traje termin (vreme)</label>
                            <input type="time" class="form-control" name="timeTo" v-model="timeTo" />
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4 mb-4">
                            <div class="form-outline">
                                <label for="addressInput">Država</label>
                                <input type="text" class="form-control" v-model="state" />
                            </div>
                        </div>
                        <div class="col-md-4 mb-4">
                            <div class="form-outline">
                                <label for="addressInput">Grad</label>
                                <input type="text" class="form-control" v-model="city" />
                            </div>
                        </div>
                        <div class="col-md-4 mb-4">
                            <div class="form-outline">
                                <label for="addressInput">Ulica i broj</label>
                                <input type="text" class="form-control" v-model="street" />
                            </div>
                        </div>
                    </div>
                    <div class="row mb-4">
                        <div class="col-md-4">
                            <input placeholder="Cena($)" type="number" id="cut" max="999999" min="0"
                                onkeydown="return /^[0-9]$/i.test(event.key)" maxlength="6" class="form-control"
                                v-model="price" />
                        </div>
                        <div class="col-md-4">
                            <textarea rows="1" class="form-control" placeholder="Pravila" v-model="rules"></textarea>
                        </div>
                        <div class="col-md-4">
                            <textarea rows="1" class="form-control" placeholder="Uslovi otkazivanja"
                                v-model="cancelTerms"></textarea>
                        </div>
                    </div>
                    <div class="row mb-4">
                        <div class="col-md-4">
                            <input placeholder="Kapacitet" type="number" id="cut" max="30" min="0" v-model="capacity"
                                onkeydown="return /^[0-9]$/i.test(event.key)" maxlength="6" class="form-control" />
                        </div>
                        <div class="col-md-8">
                            <textarea rows="1" class="form-control" placeholder="Oprema za pecanje"
                                v-model="fishingEq"></textarea>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-4">
                            <input type="number" min="0" max="100" v-model="addedServicePrice"
                                placeholder="Cena dodatne usluge ($)" onkeydown="return false" id="addedServicePrice"
                                class="form-control mb-1" />
                            <input type="text" v-model="addedService" id="addedServiceName" maxlength="20"
                                placeholder="Dodatna usluga" class="form-control mb-1" />
                            <button v-on:click="addService" class="btn btn-primary">Dodaj uslugu</button>
                        </div>
                        <div class="col-md-6 mb-4">
                            <select name="services" id="services" class="form-control" multiple>
                                <!-- <option value="Merceders"> Merceders </option>
                                <option value="BMW"> BMW </option>
                                <option value="Jaguar"> Jaguar </option>
                                <option value="Lamborghini"> Lamborghini </option>
                                <option value="Ferrari"> Ferrari </option>
                                <option value="Ford"> Ford </option>-->
                            </select>
                        </div>
                    </div>
                    <div class="row mt-1">
                        <div class="col-lg-5 col-sm-12 left-side-product-box pb-3" v-if="this.images.length > 0">
                            <div :id="generateIdSlider(i)" class="carousel slide" data-bs-ride="carousel"
                                data-bs-interval="false">
                                <div class="carousel-indicators">
                                    <button v-for="(photo, i) in this.images" :key="i" type="button"
                                        :data-bs-target="generateIdSliderWithHashTag(i)" :data-bs-slide-to="i"
                                        class="active" aria-current="true" :aria-label="createAriaLabel(i)"></button>
                                </div>
                                <div class="carousel-inner">
                                    <div class="carousel-item active">
                                        <img :src="this.images[0]" class="d-block w-100 img-fluid img-responsive"
                                            alt="image" />
                                    </div>
                                    <div v-for="(photo, i) in this.images.slice(1)" :key="i" class="carousel-item">
                                        <img :src="photo" class="d-block w-100 img-fluid img-responsive" alt="image" />
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
                        <div class="col-lg-7 col-sm-12">
                            <textarea placeholder="Opisite sta ce se raditi na avanturi" class="form-control"
                                v-model="description"></textarea>
                        </div>
                    </div>
                    <div class="row mt-1">
                        <div class="col-lg-4">
                            <input class="form-control" type="file" accept="image/*" @change="addImg($event)"
                                id="file-input" />
                        </div>
                        <div class="col-lg-1">
                            <button class="btn btn-danger" v-on:click="removeImages">Izbaci</button>
                        </div>
                    </div>
                    <hr />
                    <button class="btn btn-primary mt-3" v-on:click="createAdventure">Kreiraj termin</button>
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
import FormData from 'form-data'


export default {
    name: 'AdventureCreator',
    data() {
        return {
            previewImage: null,
            dateFrom: null,
            dateTo: null,

            timeFrom: null,
            timeTo: null,

            fishingEq: null,
            cancelTerms: null,
            capacity: null,
            rules: null,
            description: null,
            price: null,

            name: null,
            // ADDRESS
            state: null,
            city: null,
            street: null,


            // ADDITIONAL SERVICES
            addedService: null,
            addedServicePrice: null,
            services: [],


            images: [],
            imageFiles: []
        }
    },
    methods: {
        addImg(e) {
            const image = e.target.files[0];
            this.imageFiles.push(e.target.files[0]);
            console.log(image)

            const reader = new FileReader();
            reader.readAsDataURL(image);
            reader.onload = e => {
                this.previewImage = e.target.result;

                this.images.push(this.previewImage);
                console.log(this.previewImage);
            };
        },
        removeImages() {
            this.images = []
        },
        generateIdSlider(id) {
            return 'carouselExampleIndicators' + id;
        },
        generateIdSliderWithHashTag(id) {
            return '#carouselExampleIndicators' + id;
        },
        createTargetId(i) {
            return 'hashId' + i
        },
        createAriaLabel(index) {
            return 'Slide ' + index;
        },

        addService() {
            if (!this.addedService) {
                return;
            }
            let newS = {'name': this.addedService, 'price': this.addedServicePrice}
            this.services.push(newS);


            let newServiceText = this.addedService + ' - $' + this.addedServicePrice
            let newOption = new Option(newServiceText, newServiceText);
            let listbox = document.getElementById('services');
            listbox.options[this.services.length - 1] = newOption;
            if (listbox.size < 5) {
                listbox.size = this.services.length
            }

            this.addedServicePrice = null;
            this.addedService = null;

        },


        uploadImages(adventure_id) {

            let config = { header: { 'Content-Type': 'multipart/form-data' } }
            axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + this.$store.accessToken;

            for (let i = 0; i < this.imageFiles.length; i++) {
                let data = new FormData();
                data.append('adventureId', adventure_id);
                data.append('file', this.imageFiles[i]);
                axios.post(
                    '/api/instructor/post-adventure-image',
                    data,
                    config
                ).then(
                    (response) => {
                        console.log(response);
                        alert("Uspesno kreiranje casa pecanja");
                    }
                );
            }
        },

        createAdventure() {
            let newAdventure = {
                'owner_id': this.$store.User.id,
                'availabilityStart': this.dateFrom + 'T' + this.timeFrom,
                'availabilityEnd': this.dateTo + 'T' + this.timeTo,
                'fishingEq': this.fishingEq,
                'cancelTerms': this.cancelTerms,
                'capacity': this.capacity,
                'rules': this.rules,
                'price': this.price,
                'name': this.name,
                'description': this.description,
                'state': this.state,
                'city': this.city,
                'street': this.street
            }
            if (this.checkProperties(newAdventure)) {
                alert("Morate popuniti sva polja.");
                return;
            }
            newAdventure['additionalServices'] = this.services;
            axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + this.$store.accessToken;
            axios.post('/api/instructor/create-new-adventure', newAdventure)
                .then((response) => {
                    console.log(response);
                    this.uploadImages(response.data);

                }).catch((err) => {
                    alert(err.response.data.error);
                    console.log(err);
                })


        },
        checkProperties(obj) {
            for (var key in obj) {
                if (obj[key] === null || obj[key] == "") {
                    return true;
                }
            }
            return false;
        }

    }
}

</script>
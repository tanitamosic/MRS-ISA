<template>
    <div class="card mx-4 mx-md-5 shadow-lg">
        <div class="card-body py-5 px-md-5">
            <div class="row d-flex justify-content-center">
                <div class="col-lg-8">
                    <h2 class="fw-bold mb-5">Nova avantura</h2>
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
                                <input type="text" name="Address" class="form-control" id="addressInputState"
                                    v-model="State" required />
                            </div>
                        </div>
                        <div class="col-md-4 mb-4">
                            <div class="form-outline">
                                <label for="addressInput">Grad</label>
                                <input type="text" name="Address" class="form-control" id="addressInputCity"
                                    v-model="City" required />
                            </div>
                        </div>
                        <div class="col-md-4 mb-4">
                            <div class="form-outline">
                                <label for="addressInput">Ulica i broj</label>
                                <input type="text" name="Address" class="form-control" id="addressInputStreet"
                                    v-model="Street" required />
                            </div>
                        </div>
                    </div>
                    <div class="row mb-4">
                        <div class="col-md-4">
                            <input placeholder="Cena($)" type="number" id="cut" max="999999" min="0"
                                onkeydown="return /^[0-9]$/i.test(event.key)" maxlength="6" class="form-control" />
                        </div>
                        <div class="col-md-4">
                            <textarea rows="1" class="form-control" placeholder="Pravila"></textarea>
                        </div>
                        <div class="col-md-4">
                            <textarea rows="1" class="form-control" placeholder="Uslovi otkazivanja"></textarea>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-4">
                            <input type="number" min="0" max="100" v-model="addedServicePrice" placeholder="Cena dodatne usluge ($)"
                                onkeydown="return false" id="addedServicePrice" class="form-control mb-1">
                            <input type="text" v-model="addedService" id="addedServiceName" maxlength="20"
                                placeholder="Dodatna usluga" class="form-control mb-1">
                            <button v-on:click="addService" class="btn btn-primary">Dodaj uslugu</button>
                        </div>
                        <div class="col-md-6 mb-4">
                            <select name="services" id="services" class="form-control" multiple>
                                <!-- <option value="Merceders"> Merceders </option>
                                <option value="BMW"> BMW </option>
                                <option value="Jaguar"> Jaguar </option>
                                <option value="Lamborghini"> Lamborghini </option>
                                <option value="Ferrari"> Ferrari </option>
                                <option value="Ford"> Ford </option> -->
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
                            <textarea placeholder="Opisite sta ce se raditi na avanturi" class="form-control"></textarea>
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

            // ADDITIONAL SERVICES
            addedService: null,
            addedServicePrice: null,
            services: [],

            // ADDRESS
            State: '',
            City: '',
            Street: '',

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
            let newService = this.addedService + ' - $' + this.addedServicePrice 
            this.services.push(newService);

            let newOption = new Option(newService, newService);
            let listbox = document.getElementById('services');
            listbox.options[this.services.length-1] = newOption;
            if (listbox.size < 5) {
                listbox.size = this.services.length
            }
            
            this.addedServicePrice = null;
            this.addedService = null;
            
        },


        uploadImages() {

            let config = { header: { 'Content-Type': 'multipart/form-data' } }
            axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + this.$store.accessToken;

            for (let i = 0; i < this.imageFiles.length; i++) {
                let data = new FormData();
                data.append('file', this.imageFiles[i]);
                axios.post(
                    '/api/instructor/post-adventure-image',
                    data,
                    config
                ).then(
                    (response) => {
                        console.log('image upload response > ', response)
                    }
                );
            }
        },

        createAdventure() {
            this.uploadImages();
            // TODO: UPLOADUJ OSTALE PODATKE TERMINA
        }

    }
}

</script>
<template>
    <div class="card mx-4 mx-md-5 shadow-lg">
        <div class="card-body py-5 px-md-5">
            <div class="row d-flex justify-content-center">
                <div class="col-lg-8">
                    <h2 class="fw-bold mb-1">Novi brod</h2>
                    <hr>
                    <div class="row gx-3 mb-3">
                        <div class="col-md-12">
                            <input placeholder="Naziv" v-model="name" type="text" class="form-control"
                                style="font-size: 24px;">
                        </div>

                    </div>
                    <div class="row gx-3 mb-3" id="customDays">
                        <div class="col-md-6">
                            <label class="small mb-1" for="fromDate">Odaberite početak dostupnosti (datum)</label>
                            <input type="date" name="fromDate" class="form-control" v-model="dateFrom" />
                        </div>
                        <div class="col-md-6">
                            <label class="small mb-1" for="timeFrom">Odaberite početak dostupnosti (vreme)</label>
                            <input type="time" name="timeFrom" class="form-control" v-model="timeFrom" />
                        </div>
                    </div>
                    <div class="row gx-3 mb-3" id="customDays">
                        <div class="col-md-6">
                            <label class="small mb-1" for="dateTo">Odaberite kraj dostupnosti (datum)</label>
                            <input type="date" class="form-control" name="dateTo" v-model="dateTo" />
                        </div>
                        <div class="col-md-6">
                            <label class="small mb-1" for="timeTo">Odaberite kraj dostupnosti (vreme)</label>
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
                                 maxlength="6" class="form-control"
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
                    <div class="row mt-5">
                            <div class="col-sm-4">
                                <input class="form-control" id="type" name="type" type="text"
                                    placeholder="Tip broda" v-model="type" />
                            </div>
                            <div class="col-sm-3">
                                <input class="form-control" id="length" name="lenth" type="text"
                                    placeholder="Dužina broda" v-model="length" />
                            </div>
                            <div class ="col-sm-3">
                                <input placeholder="Kapacitet" type="number" id="cut" max="30" min="0"
                                    v-model="capacity"
                                    maxlength="6" class="form-control" />
                            </div>
                            </div>
                            <div class ="row mt-2">
                            <div class="col-sm-3">
                                <input class="form-control" id="numberOfEngines" name="numberOfEngines" type="text"
                                    placeholder="Broj motora" v-model="numberOfEngines" />
                            </div>
                            <div class="col-sm-3">
                                <input class="form-control" id="enginePower" name="enginePower" type="text"
                                    placeholder="Snaga motora" v-model="enginePower" />
                            </div>
                            <div class="col-sm-5">
                                <input class="form-control" id="maxSpeed" name="maxSpeed" type="text"
                                    placeholder="Maksimalna brzina broda" v-model="maxSpeed" />
                            </div>

                        </div>
                        <div class="row mt-5 mb-5">
                            <div class="col-sm-6">
                                <span>Navigaciona oprema: </span>
                                <textarea rows="1" class="form-control" placeholder="Navigaciona oprema"
                                    v-model="navigationalEquipment"></textarea>
                            </div>
                            <div class="col-sm-6">
                                <span>Oprema za pecanje: </span>
                                <textarea rows="1" class="form-control" placeholder="Oprema za pecanje"
                                    v-model="fishingEquipment"></textarea>
                            </div>
                        </div>
                    <div class="row">
                        <div class="col-md-6 mb-4">
                            <input type="number" min="0" max="100" v-model="addedServicePrice"
                                placeholder="Cena dodatne usluge ($)" id="addedServicePrice"
                                class="form-control mb-1" />
                            <input type="text" v-model="addedService" id="addedServiceName" maxlength="20"
                                placeholder="Dodatna usluga" class="form-control mb-1" />
                            <button v-on:click="addService" class="btn btn-primary">Dodaj uslugu</button>
                        </div>
                        <div class="col-md-6 mb-4">
                            <select name="services" id="services" class="form-control" multiple>
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
                            <textarea placeholder="Opišite vaš brod" class="form-control"
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
                    <button class="btn btn-primary mt-3" v-on:click="createBoat">Dodaj brod</button>
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
    name: 'CreateBoat',
    data() {
        return {
            previewImage: null,
            dateFrom: null,
            dateTo: null,

            timeFrom: null,
            timeTo: null,

            cancelTerms: null,
            rooms: null,
            beds: null,
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
            let newService = this.addedService + ' - $' + this.addedServicePrice
            this.services.push(newService);

            let newOption = new Option(newService, newService);
            let listbox = document.getElementById('services');
            listbox.options[this.services.length - 1] = newOption;
            if (listbox.size < 5) {
                listbox.size = this.services.length
            }

            this.addedServicePrice = null;
            this.addedService = null;

        },


        uploadImages(boat_id) {

            let config = { header: { 'Content-Type': 'multipart/form-data' } }
            axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + this.$store.accessToken;

            for (let i = 0; i < this.imageFiles.length; i++) {
                let data = new FormData();
                data.append('boatId', boat_id);
                data.append('file', this.imageFiles[i]);
                axios.post(
                    '/api/bo/post-boat-image',
                    data,
                    config
                ).then(
                    (response) => {
                        console.log(response);
                        alert("Uspesno dodavanje broda");
                    }
                );
            }
        },

        createBoat() {
            let newBoat = {
                'id': this.id,
                    'owner_id': this.$store.User.id,
                    'availabilityStart': this.dateFrom + 'T' + this.timeFrom,
                    'availabilityEnd': this.dateTo + 'T' + this.timeTo,
                    'cancellationTerms': this.cancelTerms,
                    'rules': this.rules,
                    'price': this.price,
                    'name': this.name,
                    'description': this.description,
                    'state': this.state,
                    'city': this.city,
                    'street': this.street,
                    'type': this.type,
                    'length': this.length,
                    'numberOfEngines':this.numberOfEngines,
                    'enginePower': this.enginePower,
                    'maxSpeed': this.maxSpeed,
                    'navigationalEquipment': this.navigationalEquipment,
                    'capacity': this.capacity,
                    'fishingEquipment': this.fishingEquipment
            }

            if (!checkInput(this)){
                return;
            }
            newBoat['additionalServices'] = this.services;
            axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + this.$store.accessToken;
            axios.post('/api/bo/add-boat', newBoat)
                .then((response) => {
                    console.log(response);
                    this.uploadImages(response.data);

                }).catch((err) => {
                    alert(err.response.data.error);
                    console.log(err);
                })


        }
    }
}

function checkInput(boat) {
    if (!checkPrice(boat)) {
        alert('Cena mora biti broj između 1 i 999999');
        return false;
    }
    if (!checkName(boat)) {
        alert('Ime sme sadržati samo slova i brojeve.');
        return false;
    }
    if (!checkAddress(boat)){
        alert('Država i grad smeju sadržati samo slova.\nUlica sme sadžati samo slova i brojeve.');
        return false;
    }

    return true;
}
function checkPrice(boat) {
    let regExp = /[1-9][0-9]{0,5}/
    let price = boat.price;
    if (!regExp.test(price)) {
        return false; // nesto sem cifara u ceni ili previsoka cena
    } else if (price === "") {
        return false; // no phone input
    }
    return true;
}

function checkName(boat) {
    let regExp = /[!@#$%^&*()]]/

    let name = boat.name;
    if (regExp.test(name)) {
        return false; // found numbers in the name
    } else if (name === "") {
        return false;
    }
    return true;
}

function checkAddress(boat){
    let street = /[!@#$%^&*()]]/
    let statecity=/[!@#$%^&*()]]/
    if (street.test(boat.street)) {
        return false; // found numbers in the name
    } else if (statecity.test(boat.city) || statecity.test(boat.state)) {
        return false;
    }
    return true;
}

</script>
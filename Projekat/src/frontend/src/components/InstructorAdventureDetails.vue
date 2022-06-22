<template>
    <div class="container mt-5 mb-5">
        <div class="d-flex justify-content-center row">

            <div class="col-lg-8 border p-3 main-section bg-white">


                <div class="row m-0 pt-3">
                    <!-- <div class="col-lg-5 col-sm-12 left-side-product-box pb-3">
                        <img class="border p-3 img-thumbnail" :src="this.adventure.primaryPhoto.assetPath" v-if="this.primaryPhotoExists">
                    </div> -->

                    <div class="col-lg-5 col-sm-12 left-side-product-box pb-3" v-if="this.photosExists">
                        <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel"
                            data-bs-interval="false">
                            <div class="carousel-indicators">
                                <button v-for="(photo, i) in this.adventure.photos" :key="i" type="button"
                                    data-bs-target="#carouselExampleIndicators" :data-bs-slide-to="i" class="active"
                                    aria-current="true" :aria-label="createAriaLabel(i)"></button>
                                <!-- <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"
                                    aria-label="Slide 2"></button>
                                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"
                                    aria-label="Slide 3"></button> -->
                            </div>
                            <div class="carousel-inner">
                                <div class="carousel-item active">
                                    <img :src="this.adventure.photos[0].assetPath"
                                        class="d-block w-100 img-fluid img-responsive" alt="image" />
                                </div>
                                <div v-for="(photo, i) in this.adventure.photos.slice(1)" :key="i"
                                    class="carousel-item">
                                    <img :src="photo.assetPath" class="d-block w-100 img-fluid img-responsive"
                                        alt="image" />
                                </div>
                                <!-- <div class="carousel-item active">
                                    <img src="img/adventures/vikendica11.jpg" class="d-block w-100 img-fluid img-responsive"
                                        alt="image">
                                </div>
                                <div class="carousel-item">
                                    <img src="img/adventures/vikendica12.jpg" class="d-block w-100 img-fluid img-responsive"
                                        alt="image">
                                </div> -->
                            </div>
                            <button class="carousel-control-prev" type="button"
                                data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Previous</span>
                            </button>
                            <button class="carousel-control-next" type="button"
                                data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Next</span>
                            </button>
                        </div>
                    </div>

                    <div class="col-lg-7 col-sm-12">
                        <div class="right-side-pro-detail border p-3 m-0">
                            <div class="row">
                                <div class="col-md-6">
                                    <h5 class="mb-1" for="Name">Ime</h5>
                                    <input class="form-control" id="Name" name="Name" type="text"
                                        placeholder="Unesite ime vikendice" v-model="adventure.name" />
                                </div>
                                <div class="col-md-6">
                                    <h5 class="mb-1 price-pro" for="Name">Cena ($)</h5>
                                    <input class="form-control" id="Price" name="Price" type="number"
                                        onkeydown="return /^[0-9]$/i.test(event.key)"
                                        placeholder="Unesite cenu vikendice" v-model="adventure.price" />
                                </div>
                                <div class="col-lg-12 col-sm-12 pt-2">
                                    <h5>Opis</h5>
                                    <div class="col-lg-12">
                                        <textarea class="form-control" id="Description" name="Description" type="text"
                                            placeholder="Unesite opis vikendice"
                                            v-model="adventure.description"></textarea>
                                    </div>
                                </div>
                                <div class="row gx-3 mb-3">
                                    <h5 class="mt-3">Adresa </h5>
                                    <!-- <span>Marka Kraljevića 18, Novi Sad, Srbija</span> -->
                                    <div class="col-md-4">
                                        <input v-if="this.addressExists" class="form-control" id="State" name="State"
                                            type="text" placeholder="Unesite državu vikendice"
                                            v-model="this.adventure.address.state" />
                                    </div>
                                    <div class="col-md-4">
                                        <input v-if="this.addressExists" class="form-control" id="City" name="City"
                                            type="text" placeholder="Unesite grad vikendice"
                                            v-model="this.adventure.address.city" />
                                    </div>
                                    <div class="col-md-4">
                                        <input v-if="this.addressExists" class="form-control" id="Street" name="Street"
                                            type="text" placeholder="Unesite ulicu i broj vikendice"
                                            v-model="this.adventure.address.street" />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6 pb-2">
                                        <span>Datum kada pocinje</span>
                                        <input type="date" class="form-control" v-model="dateFrom" />
                                    </div>
                                    <div class="col-md-6">
                                        <span>Datum kada se zavrsava</span>
                                        <input type="date" class="form-control" v-model="dateTo" />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label class="small mb-1" for="timeFrom">Vreme kada pocinje</label>
                                        <input type="time" class="form-control" name="timeFrom" v-model="timeFrom" />
                                    </div>
                                    <div class="col-md-6">
                                        <label class="small mb-1" for="timeTo">Vreme kada se zavrsava</label>
                                        <input type="time" class="form-control" name="timeTo" v-model="timeTo" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row gx-3 mb-3">
                    <!-- Detalji deo -->
                    <div class="col-lg-12 col-sm-12 text-left pt-5">
                        <h4>Detalji</h4>
                    </div>
                    <div class="row">
                        <div class="col-md-3 text-left pt-3">
                            <span>Pravila: </span>
                            <textarea class="form-control" id="Rules" name="Rules" type="text"
                                placeholder="Unesite pravila vikendice" v-model="adventure.rules"></textarea>
                        </div>
                        <div class="col-md-3 text-left pt-3">
                            <span>Uslovi otkazivanja: </span>
                            <textarea class="form-control" id="Rules" name="Rules" type="text"
                                placeholder="Unesite uslove otkazivanja rezervacije vikendice"
                                v-model="adventure.cancellationTerms"></textarea>
                        </div>
                        <div class="col">
                            <div class="card shadow-lg border-dark" style="width: 18rem; margin:20px;">
                                <div class="card-header">Klijent: {{ this.client.name }} {{ this.client.surname }}
                                </div>
                                <div class="card-body">
                                    <p class="card-text">Kontakt telefon: {{ this.client.phone }}</p>
                                </div>
                                <div class="card-body">
                                    <button class="btn btn-success" v-if="!this.client.id" @click="showClientResModal = true">Napravi rezervaciju za klijenta</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row mt-5">
                        <div class="col-sm-6">
                            <span>Kapacitet: </span>
                            <input placeholder="Kapacitet" type="number" id="cut" max="30" min="0"
                                v-model="this.adventure.capacity" onkeydown="return /^[0-9]$/i.test(event.key)"
                                maxlength="6" class="form-control" />
                        </div>
                        <div class="col-sm-6">
                            <span>Oprema za pecanje: </span>
                            <textarea rows="1" class="form-control" placeholder="Oprema za pecanje"
                                v-model="this.adventure.fishingEquipment"></textarea>
                        </div>
                    </div>
                </div>

                <div class="row gx-3 mb-3">
                    <div class="col-md-6 mb-4">
                        <input type="text" v-model="addedService" id="addedServiceName" maxlength="20"
                            placeholder="Dodatna usluga" class="form-control mb-1" />
                        <input type="number" min="0" max="100" v-model="addedServicePrice"
                            placeholder="Cena dodatne usluge ($)" onkeydown="return false" id="addedServicePrice"
                            class="form-control mb-1" />
                        <button v-on:click.prevent="addService" class="btn btn-primary">Dodaj uslugu</button>
                        <button v-on:click.prevent="removeServices" class="btn btn-danger mx-1">Ukloni
                            usluge</button>
                    </div>
                    <div class="col-md-6 mb-4">
                        <select name="services" id="services" class="form-control" multiple>
                            <option v-for="(s, i) in this.services" :key="i">{{ s.name }} - ${{ s.price }}</option>
                        </select>
                    </div>
                </div>
                <hr />
                <button class="btn btn-primary m-1" type="button" v-on:click.prevent="update_adventure">
                    Sačuvaj izmene
                </button>
                <button id="account-deletion" class="btn btn-danger m-1" type="button"
                    v-on:click.prevent="delete_adventure">
                    Obriši vikendicu
                </button>
                <button id="show-modal" class="btn btn-success m-1" @click="showQuickActionModal = true">
                    Napravi akciju
                </button>


            </div>
        </div>
    </div>

    <!-- MODAL IMPLEMENTATION -->
    <Teleport to="body">
        <!-- use the modal component, pass in the prop -->
        <QuickActionModal :show="showQuickActionModal" :dateFrom="dateFrom" :dateTo="dateTo" :adventureId="id" @close="showQuickActionModal = false">
            <template #header>
                <h3>custom header</h3>
            </template>
        </QuickActionModal>
    </Teleport>
    <!-- MODAL IMPLEMENTATION -->
    <Teleport to="body">
        <!-- use the modal component, pass in the prop -->
        <ClientReservationModal :show="showClientResModal" :dateFrom="dateFrom" :dateTo="dateTo" :adventureId="id" @close="showClientResModal = false">
            <template #header>
                <h3>custom header</h3>
            </template>
        </ClientReservationModal>
    </Teleport>
</template>


<script>
import axios from "axios";
import QuickActionModal from '@/components/QuickReservationModal.vue'
import ClientReservationModal from '@/components/ClientReservationModa.vue'

export default {
    name: "AdventureDetails",
    components: {
        QuickActionModal,
        ClientReservationModal
    },
    data() {
        return {
            showQuickActionModal: false,
            showClientResModal: false,

            id: null,
            adventure: {},
            additionalServicesExists: false,
            primaryPhotoExists: false,
            addressExists: false,
            photosExists: false,

            addedService: null,
            addedServicePrice: null,
            services: [],

            dateFrom: null,
            dateTo: null,
            timeFrom: null,
            timeTo: null,

            client: { name: 'Niko nije rezervisao ovu avanturu', surname: '', phone: '/', id: null }
        };
    },
    mounted() {
        this.id = this.$route.params.id;
        this.set_details();
        this.find_client();
    },
    methods: {
        find_client() {
            let self = this;
            axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + this.$store.accessToken;
            axios.get('/api/instructor/' + this.id + '/find-client')
                .then((response) => {
                    if (response.data) {
                        self.client = response.data;
                    }
                }).catch((err) => {
                    console.log(err);
                    alert("Doslo je do greske");
                })
        },
        set_details() {

            axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + this.$store.accessToken;
            axios
                .get("/api/instructor/" + this.$store.User.id + "/adventures/getadventure/" + this.id)
                .then(
                    (response) => {
                        (this.adventure = response.data),
                            (this.primaryPhotoExists =
                                this.adventure.primaryPhoto === undefined ? false : true),
                            (this.photosExists = this.adventure.photos.length === 0 ? false : true),
                            (this.addressExists =
                                this.adventure.address === undefined ? false : true),
                            (this.address = this.transformAddress(this.adventure.address)),
                            (this.additionalServicesText = this.transformServices(
                                this.adventure.additionalServices
                            )),
                            this.services = this.adventure.additionalServices;
                        this.set_endDate();
                        this.set_startDate();
                        this.set_startTime();
                        this.set_endTime();



                        console.log(this.adventure);
                    }
                ).catch((err) => {
                    console.log(err);
                    alert(err.response.status);
                });
        },
        set_startTime() {
            let hours = this.adventure.availabilityStart[3];
            let minutes = this.adventure.availabilityStart[4];
            if (hours < 10) { hours = '0' + hours; }
            if (minutes < 10) { minutes = '0' + minutes; }

            this.timeFrom = hours + ':' + minutes;
        },
        set_endTime() {
            let hours = this.adventure.availabilityEnd[3];
            let minutes = this.adventure.availabilityEnd[4];
            if (hours < 10) { hours = '0' + hours; }
            if (minutes < 10) { minutes = '0' + minutes; }

            this.timeTo = hours + ':' + minutes;
        },
        set_startDate() {
            let year = this.adventure.availabilityStart[0];
            let month = this.adventure.availabilityStart[1];
            if (month < 10) { month = "0" + month; }
            let day = this.adventure.availabilityStart[2];
            if (day < 10) { day = "0" + day; }
            this.dateFrom = year + "-" + month + "-" + day;
        },
        set_endDate() {
            let year = this.adventure.availabilityEnd[0];
            let month = this.adventure.availabilityEnd[1];
            if (month < 10) { month = "0" + month; }
            let day = this.adventure.availabilityEnd[2];
            if (day < 10) { day = "0" + day; }
            this.dateTo = year + "-" + month + "-" + day;
        },
        addService() {
            if (!this.addedService) {
                return;
            }
            let newService = { name: this.addedService, price: this.addedServicePrice }

            this.services.push(newService);

            let newOption = new Option('', newService);
            let listbox = document.getElementById('services');
            listbox.options[this.services.length - 1] = newOption;
            if (listbox.size < 5) {
                listbox.size = this.services.length
            }

            this.addedServicePrice = null;
            this.addedService = null;
        },
        removeServices() {
            this.services = []
        },

        transformAddress(address) {
            if (!(address === null))
                return address.street + ", " + address.city + ", " + address.state;
            else return "";
        },
        createAriaLabel(index) {
            return "Slide " + index;
        },
        transformServices(services) {
            let result = "";
            services.forEach((service) => {
                result = result + service.name + "; ";
            });
            return result;
        },


        update_adventure: function () {
            if (this.client.id !== null) {
                alert('Ne mozete menjati rezervisanu avanturu.');
            }

            if (this.adventure.name === '' ||
                this.adventure.price === '' ||
                this.adventure.description === '' ||
                this.adventure.rules === '' ||
                this.adventure.cancellationTerms === '' ||
                this.adventure.address.state === '' ||
                this.adventure.address.city === '' ||
                this.adventure.address.street === '' ||
                this.adventure.fishingEquipment === '' ||
                this.adventure.capacity === '') {
                alert("Polja sa osnovnim podacima avanture ne smeju biti prazna.")
                return;
            }


            if (checkInput(this.adventure)) {
                let updatedAdventure = {
                    'adv_id': this.id,
                    'owner_id': this.$store.User.id,
                    'availabilityStart': this.dateFrom + 'T' + this.timeFrom,
                    'availabilityEnd': this.dateTo + 'T' + this.timeTo,
                    'fishingEq': this.adventure.fishingEquipment,
                    'cancelTerms': this.adventure.cancellationTerms,
                    'capacity': this.adventure.capacity,
                    'rules': this.adventure.rules,
                    'price': this.adventure.price,
                    'name': this.adventure.name,
                    'description': this.adventure.description,
                    'state': this.adventure.address.state,
                    'city': this.adventure.address.city,
                    'street': this.adventure.address.street
                }
                updatedAdventure['additionalServices'] = this.services;

                // let self = this;
                axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
                axios.defaults.headers.common['Authorization'] = 'Bearer ' + this.$store.accessToken;

                axios.post('/api/instructor/update-adventure',
                    updatedAdventure).then(function (requestResponse) {
                        if (requestResponse.status === 200) {
                            alert("Promena podataka je uspesna.")
                        }
                    }).catch(function (err) {
                        if (err)
                            alert("Serverska greska");
                    });
            } else {
                return;
            }

        },
        delete_adventure: function () {
            if (this.client.id !== null) {
                alert('Ne mozete brisati rezervisanu avanturu');
                return;
            }
            let self = this;
            axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + this.$store.accessToken;
            axios.delete('/api/instructor/delete-adventure/' + this.$store.User.id + '/' + this.id)
                .then((response) => {
                    alert(response.data);
                    self.$router.push('/instructor/view-adventures');

                }).catch((err) => {
                    console.log(err);
                    alert(err.response.data);
                })
        },
        makeClientReservation() {

        }
    },
};



function checkInput(adventure) {
    if (!checkPrice(adventure)) {
        alert('Cena mora biti broj između 1 i 999999');
        return false;
    }
    if (!checkName(adventure)) {
        alert('Ime sme sadržati samo slova i brojeve.');
        return false;
    }
    if (!checkAddress(adventure)) {
        alert('Država i grad smeju sadržati samo slova.\nUlica sme sadžati samo slova i brojeve.');
        return false;
    }

    return true;
}
function checkPrice(adventure) {
    let regExp = /[1-9][0-9]{0,5}/
    let price = adventure.price;
    if (!regExp.test(price)) {
        return false; // nesto sem cifara u ceni ili previsoka cena
    } else if (price === "") {
        return false; // no phone input
    }
    return true;
}


function checkName(adventure) {
    let regExp = /[!@#$%^&*()]]/

    let name = adventure.name;
    if (regExp.test(name)) {
        return false; // found numbers in the name
    } else if (name === "") {
        return false;
    }
    return true;
}

function checkAddress(adventure) {
    let street = /[!@#$%^&*()]]/
    let statecity = /[!@#$%^&*()]]/
    let address = adventure.address
    if (street.test(address.street)) {
        return false; // found numbers in the name
    } else if (statecity.test(address.city) || statecity.test(address.state)) {
        return false;
    } else if (address === "") {
        return false;
    }
    return true;
}

</script>


<style scoped>
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

body {
    font-family: 'montserrat', sans-serif;
}


.button {
    appearance: none;
    outline: none;
    border: none;
    background: none;
    cursor: pointer;

    display: inline-block;
    padding: 15px 25px;
    background-image: linear-gradient(to right, #CC2E5D, #FF5858);
    border-radius: 8px;

    color: #FFF;
    font-size: 18px;
    font-weight: 700;

    box-shadow: 3px 3px rgba(0, 0, 0, 0.4);
    transition: 0.4s ease-out;

}



/* body {
    font-family: "Roboto Condensed", sans-serif;
    background-color: #f5f5f5;
} */

.main-section {
    position: absolute;
    left: 50%;
    right: 50%;
    transform: translate(-50%, 5%);
}

.left-side-product-box img {
    width: 100%;
}

/* .left-side-product-box{
    margin-top: 5px;
    width: 83px;
    height: 100px;
} */

.right-side-pro-detail span {
    font-size: 15px;
}

.right-side-pro-detail p {
    font-size: 25px;
    color: #a1a1a1;
}

.right-side-pro-detail .price-pro {
    color: #e45641;
}

.right-side-pro-detail {
    font-size: 18px;
    color: #5d4c46;
}
</style>
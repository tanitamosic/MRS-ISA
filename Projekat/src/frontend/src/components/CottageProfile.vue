<template>
    <div class="container mt-5 mb-5">
        <div class="d-flex justify-content-center row">
            <div class="col-lg-8 border p-3 main-section bg-white">
                <form id="profileForm">
                    <div class="row m-0 pt-3">
                        <!-- <div class="col-lg-5 col-sm-12 left-side-product-box pb-3">
                        <img class="border p-3 img-thumbnail" :src="this.cottage.primaryPhoto.assetPath" v-if="this.primaryPhotoExists">
                    </div> -->

                        <div class="col-lg-5 col-sm-12 left-side-product-box pb-3" v-if="this.photosExists">
                            <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel"
                                data-bs-interval="false">
                                <div class="carousel-indicators">
                                    <button v-for="(photo, i) in this.cottage.photos" :key="i" type="button"
                                        data-bs-target="#carouselExampleIndicators" :data-bs-slide-to="i" class="active"
                                        aria-current="true" :aria-label="createAriaLabel(i)"></button>
                                    <!-- <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"
                                    aria-label="Slide 2"></button>
                                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"
                                    aria-label="Slide 3"></button> -->
                                </div>
                                <div class="carousel-inner">
                                    <div class="carousel-item active">
                                        <img :src="this.cottage.photos[0].assetPath"
                                            class="d-block w-100 img-fluid img-responsive" alt="image" />
                                    </div>
                                    <div v-for="(photo, i) in this.cottage.photos.slice(1)" :key="i"
                                        class="carousel-item">
                                        <img :src="photo.assetPath" class="d-block w-100 img-fluid img-responsive"
                                            alt="image" />
                                    </div>
                                    <!-- <div class="carousel-item active">
                                    <img src="img/cottages/vikendica11.jpg" class="d-block w-100 img-fluid img-responsive"
                                        alt="image">
                                </div>
                                <div class="carousel-item">
                                    <img src="img/cottages/vikendica12.jpg" class="d-block w-100 img-fluid img-responsive"
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
                                            placeholder="Unesite ime vikendice" v-model="cottage.name" />
                                    </div>
                                    <div class="col-md-6">
                                        <h5 class="mb-1 price-pro" for="Name">Cena</h5>
                                        <input class="form-control" id="Price" name="Price" type="text"
                                            placeholder="Unesite cenu vikendice" v-model="cottage.price" />
                                    </div>
                                    <div class="col-lg-12 col-sm-12 pt-2">
                                        <h5>Opis</h5>
                                        <div class="col-lg-12">
                                            <textarea class="form-control" id="Description" name="Description"
                                                type="text" placeholder="Unesite opis vikendice"
                                                v-model="cottage.description" />
                                        </div>
                                    </div>
                                    <div class="row gx-3 mb-3">
                                        <h5 class="mt-3">Adresa </h5>
                                        <!-- <span>Marka Kraljevića 18, Novi Sad, Srbija</span> -->
                                        <div class="col-md-4">
                                            <input v-if="this.addressExists" class="form-control" id="State"
                                                name="State" type="text" placeholder="Unesite državu vikendice"
                                                v-model="this.cottage.address.state" />
                                        </div>
                                        <div class="col-md-4">
                                            <input v-if="this.addressExists" class="form-control" id="City" name="City"
                                                type="text" placeholder="Unesite grad vikendice"
                                                v-model="this.cottage.address.city" />
                                        </div>
                                        <div class="col-md-4">
                                            <input v-if="this.addressExists" class="form-control" id="Street"
                                                name="Street" type="text" placeholder="Unesite ulicu i broj vikendice"
                                                v-model="this.cottage.address.street" />
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6 col-sm-6 pb-2">
                                            
                                        </div>
                                        <div class="col-lg-6 col-sm-6"></div>
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
                            <div class="col-lg-6 text-left pt-3">
                                <span>Pravila: </span>
                                <div class="col-lg-6">
                                    <textarea class="form-control" id="Rules" name="Rules" type="text"
                                        placeholder="Unesite pravila vikendice" v-model="cottage.rules" />
                                </div>
                            </div>
                            <div class="col-lg-6 text-left pt-1">
                                <span>Uslovi otkazivanja: </span>
                                <div class="col-lg-6">
                                    <textarea class="form-control" id="Rules" name="Rules" type="text"
                                        placeholder="Unesite uslove otkazivanja rezervacije vikendice"
                                        v-model="cottage.cancellationTerms" />
                                </div>
                            </div>
                        </div>
                        <div class="row mt-5">
                            <div class="col-sm-2">
                                <span>Broj soba: </span>

                            </div>
                            <div class="col-sm-2">
                                <input class="form-control" id="NumOfRooms" name="NumOfRooms" type="text"
                                    placeholder="Unesite broj soba vikendice" v-model="cottage.numberOfRooms" />
                            </div>
                            <div class="col-sm-2">
                                <span>Broj kreveta: </span>

                            </div>
                            <div class="col-sm-2">
                                <input class="form-control" id="NumOfBeds" name="NumOfBeds" type="text"
                                    placeholder="Unesite broj kreveta u vikendici" v-model="cottage.numberOfBeds" />
                            </div>
                        </div>
                    </div>
                    <div class="row" v-if="this.additionalServicesExists">
                        <!-- Dodatne usluge deo -->
                        <div class="col-lg-12 col-sm-12 text-center pt-2">
                            <h4>Dodatne usluge</h4>
                        </div>
                        <div class="col-lg-12 col-sm-12 text-left pt-3">
                            <div v-for="service in this.cottage.additionalServices" :key="service.id">
                                <div class="col-lg-6">
                                    <input class="form-control" id="AdditionalServices" name="AdditionalServices"
                                        type="text" placeholder="Unesite dodatne usluge"
                                        v-model="this.additionalServicesText" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr />
                    <button class="btn btn-primary mx-1" type="button" v-on:click="update_cottage">
                        Sačuvaj izmene
                    </button>
                    <button id="account-deletion" class="btn btn-danger mx-1" type="button" v-on:click="delete_cottage">
                        Obriši vikendicu
                    </button>
                </form>
            </div>
        </div>
    </div>
</template>


<script>
import axios from "axios";

export default {
    name: "CottageProfile",
    data() {
        return {
            id: null,
            cottage: {},
            additionalServicesExists: false,
            primaryPhotoExists: false,
            addressExists: false,
            photosExists: false,
        };
    },
    mounted() {
        this.id = this.$route.params.id;
        axios
            .get("/api/cottages/getCottage/" + this.id)
            .then(
                (response) => (
                    (this.cottage = response.data),
                    (this.additionalServicesExists =
                        this.cottage.additionalServices.length === 0 ? false : true),
                    (this.primaryPhotoExists =
                        this.cottage.primaryPhoto === undefined ? false : true),
                    (this.photosExists = this.cottage.photos.length === 0 ? false : true),
                    (this.addressExists =
                        this.cottage.address === undefined ? false : true),
                    (this.address = this.transformAddress(this.cottage.address)),
                    (this.additionalServicesText = this.transformServices(
                        this.cottage.additionalServices
                    )),
                    console.log(this.cottage)
                )
            );
    },
    methods: {
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
        update_cottage: function () {

            if (this.cottage.name === '' ||
                this.cottage.price === '' ||
                this.cottage.description === '' ||
                this.rules === '' ||
                this.cancellationTerms === '' ||
                this.address.state === '' ||
                this.address.city === '' ||
                this.address.street === '' ||
                this.cottage.numberOfRooms === '' ||
                this.cottage.numberOfBeds === '') {
                alert("Polja sa osnovnim podacima vikendice ne smeju biti prazna.")
                return;
            }

            
            if(checkInput(this.cottage)) {
                // let self = this;
                axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
                axios.defaults.headers.common['Authorization'] = 'Bearer ' + this.$store.accessToken;

                axios.post('/api/cottages/update-cottage-profile',
                    this.cottage).then(function (requestResponse) {
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
        delete_profile: function () {
            let usr = {
                'id': this.$store.User.id
            };
            axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + this.$store.accessToken;
            axios.post('/api/profile/make-delete-profile-request', usr)
                .then((response) => {
                    if (response.status === 200) {
                        alert(response.data);
                    }
                }).catch((err) => {
                    alert(err.data);
                });
        }
    },
};



function checkInput(cottage) {
    if (!checkPrice(cottage)) {
        alert('Cena mora biti broj između 1 i 999999');
        return false;
    }
    if (!checkRooms(cottage)) {
        alert('Broj soba i kreveta sme sadržati samo cifre');
        return false;
    }
    if (!checkName(cottage)) {
        alert('Ime sme sadržati samo slova i brojeve.');
        return false;
    }
    if (!checkAddress(cottage)){
        alert('Država i grad smeju sadržati samo slova.\nUlica sme sadžati samo slova i brojeve.');
        return false;
    }

    return true;
}
function checkPrice(cottage) {
    let regExp = /[1-9][0-9]{0,5}/
    let price = cottage.price;
    if (!regExp.test(price)) {
        return false; // nesto sem cifara u ceni ili previsoka cena
    } else if (price === "") {
        return false; // no phone input
    }
    return true;
}

function checkRooms(cottage) {
    let regExp = /[1-9][0-9]{0,5}/
    let rooms = cottage.numberOfRooms;
    let beds = cottage.numberOfBeds;
    if (!(regExp.test(rooms) && regExp.test(beds))) {
        return false; // nesto sem cifara u ceni ili previsoka cena
    } else if (rooms === "" || beds === "") {
        return false; // no phone input
    }
    return true;
}
function checkName(cottage) {
    let regExp = /[!@#$%^&*()]]/

    let name = cottage.name;
    if (regExp.test(name)) {
        return false; // found numbers in the name
    } else if (name === "") {
        return false;
    }
    return true;
}

function checkAddress(cottage){
    let street = /[!@#$%^&*()]]/
    let statecity=/[!@#$%^&*()]]/
    let address = cottage.address
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
body {
    font-family: "Roboto Condensed", sans-serif;
    background-color: #f5f5f5;
}

.hedding {
    font-size: 20px;
    color: #ab8181;
}

.main-section {
    position: absolute;
    left: 50%;
    right: 50%;
    transform: translate(-50%, 5%);
}

.left-side-product-box img {
    width: 100%;
}

.left-side-product-box .sub-img img {
    margin-top: 5px;
    width: 83px;
    height: 100px;
}

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

.right-side-pro-detail .tag-section {
    font-size: 18px;
    color: #5d4c46;
}

.pro-box-section .pro-box img {
    width: 100%;
    height: 200px;
}
</style>
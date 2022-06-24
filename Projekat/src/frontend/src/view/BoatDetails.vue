<template>
    <div class="container mt-5 mb-5">
        <div class="d-flex justify-content-center row">
            <div class="col-lg-8 border p-3 main-section bg-white">
                <div class="row m-0 pt-3">

                    <div class="col-lg-5 col-sm-12 left-side-product-box pb-3" v-if="this.photosExists">
                        <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel" data-bs-interval="false">
                            <div class="carousel-indicators">
                                <button v-for="(photo, i) in this.boat.photos" :key="i" type="button" 
                                    data-bs-target="#carouselExampleIndicators" :data-bs-slide-to="i"
                                    class="active" aria-current="true" :aria-label="createAriaLabel(i)"></button>
                            </div>
                            <div class="carousel-inner">
                                <div class="carousel-item active">
                                    <img :src="this.boat.photos[0].assetPath" class="d-block w-100 img-fluid img-responsive"
                                        alt="image">
                                </div>
                                <div v-for="(photo, i) in this.boat.photos.slice(1)" :key="i" class="carousel-item">
                                    <img :src="photo.assetPath" class="d-block w-100 img-fluid img-responsive"
                                        alt="image">
                                </div>
                            </div>
                            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators"
                                data-bs-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Previous</span>
                            </button>
                            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators"
                                data-bs-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Next</span>
                            </button>
                        </div>
                    </div>

                    <div class="col-lg-7 col-sm-12">
                        <div class="right-side-pro-detail border p-3 m-0">
                            <div class="row">
                                <div class="col-lg-12 col-sm-12">
                                    <p class="m-0 p-0">{{this.boat.name}}</p>
                                </div>
                                <div class="col-lg-12 col-sm-12 mt-2 mb-2">
                                    <star-rating v-model:rating="this.boat.rating" :increment="0.01" :show-rating="false" read-only></star-rating>
                                </div>
                                <div class="col-lg-12 col-sm-12">
                                    <p class="m-0 p-0 price-pro">${{this.boat.price}}</p>
                                    <hr class="p-0 m-0 mt-2">
                                </div>
                                <div class="col-lg-12 col-sm-12 pt-2">
                                    <h5>Opis</h5>
                                    <div>{{this.boat.description}}</div>
                                    <hr class="m-0 p-0 pt-0 mt-3">
                                </div>
                                <div class="col-lg-12 col-sm-12 pt-2">
                                    <span>Adresa: </span>
                                    <!-- <span>Marka Kraljevića 18, Novi Sad, Srbija</span> -->
                                    <span v-if="this.addressExists">{{ this.transformAddress(this.boat.address) }}</span>
                                    <hr class="m-0 p-0 pt-0 mt-3">
                                </div>
                                <div class="col-lg-12 col-sm-12 mt-5">
                                    <div class="row">
                                        <div class="col-lg-6 col-sm-6"></div>
                                        <div class="col-lg-6 col-sm-12">
                                            <!-- <a href="#" class="btn btn-success w-100">Rezerviši</a> -->
                                            <!-- <router-link class="btn btn-primary btn-sm" :to="/">
                                                Rezerviši
                                            </router-link> -->
                                            <button type="button" class="btn btn-success w-100"
                                                data-bs-toggle="modal" data-bs-target="#exampleModal" v-if="!(this.$store.accessToken==null)">
                                                Rezerviši
                                            </button>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-lg-12 col-sm-12 mt-1">
                                    <div class="row">
                                        <div class="col-lg-6 col-sm-6"></div>
                                        <div class="col-lg-6 col-sm-12">
                                            <button type="button" class="btn btn-success w-100"
                                                data-bs-toggle="modal" data-bs-target="#exampleModalAction" v-if="!(this.$store.accessToken==null)">
                                                Pretplati se na akcije
                                            </button>
                                        </div>                                        
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                
                </div>
                <div class="row">
                    <!-- Detalji deo -->
                    <div class="col-lg-12 col-sm-12 text-center pt-5">
                        <h4>Detalji</h4>
                    </div>
                    <div class="col-lg-12 col-sm-12 text-left pt-3">
                        <span>Pravila: </span>
                        <span>{{this.boat.rules}}</span>
                    </div>
                    <div class="col-lg-12 col-sm-12 text-left pt-1">
                        <span>Uslovi otkazivanja: </span>
                        <span>{{this.boat.cancellationTerms}}</span>
                    </div>
                    <div class="col-lg-12 col-sm-12 text-left pt-1">
                        <span>Tip broda: </span>
                        <span>{{this.boat.type}}</span>
                    </div>
                    <div class="col-lg-12 col-sm-12 text-left pt-1">
                        <span>Kapacitet: </span>
                        <span>{{this.boat.capacity}}</span>
                    </div>
                    <div class="col-lg-12 col-sm-12 text-left pt-1">
                        <span>Dužina broda: </span>
                        <span>{{this.boat.length}}</span>
                    </div>
                    <div class="col-lg-12 col-sm-12 text-left pt-1">
                        <span>Broj motora: </span>
                        <span>{{this.boat.numberOfEngines}}</span>
                    </div>
                    <div class="col-lg-12 col-sm-12 text-left pt-1">
                        <span>Snaga motora: </span>
                        <span>{{this.boat.enginePower}}</span>
                        <span> KS</span>
                    </div>
                    <div class="col-lg-12 col-sm-12 text-left pt-1">
                        <span>Maksimalna brzina: </span>
                        <span>{{this.boat.maxSpeed}}</span>
                        <span> KM/H</span>
                    </div>
                    <div class="col-lg-12 col-sm-12 text-left pt-1">
                        <span>Navigaciona oprema: </span>
                        <span>{{this.boat.navigationalEquipment}}</span>
                    </div>
                    <div class="col-lg-12 col-sm-12 text-left pt-1">
                        <span>Oprema za pecanje: </span>
                        <span>{{this.boat.fishingEquipment}}</span>
                    </div>

                </div>
                <div class="row" v-if="this.additionalServicesExists">
                    <!-- Dodatne usluge deo -->
                    <div class="col-lg-12 col-sm-12 text-center pt-2">
                        <h4>Dodatne usluge</h4>
                    </div>
                    <div class="col-lg-12 col-sm-12 text-left pt-3">
                        <div v-for="service in this.boat.additionalServices" :key="service.id">{{service.name}}</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <Modal :serviceProp="this.boat" :options="this.options" :price-prop="this.boat.price"></Modal>
    <ActionsModal :servicePropID="this.boat.id"></ActionsModal>
</template>

<script>
import axios from 'axios';
import BoatReservationModalVue from '@/components/BoatReservationModal.vue';
import StarRating from 'vue-star-rating';
import ActionsModal from '@/components/ActionsModal.vue';

export default {
    name: 'BoatDetails',
    data() {
        return {
            id: null,
            boat: {},
            additionalServicesExists: false,
            primaryPhotoExists: false,
            addressExists: false,
            photosExists: false,

            options: []
        };
    },
    async mounted() {
        this.id=this.$route.params.id;
        await axios
            .get('/api/boats/getBoat/' + this.id)
            .then(response => (
                this.boat = response.data,
                this.additionalServicesExists = this.boat.additionalServices.length === 0 ? false : true,
                this.primaryPhotoExists = this.boat.primaryPhoto === undefined ? false : true,
                this.photosExists = this.boat.photos.length === 0 ? false : true,
                this.addressExists = this.boat.address === undefined ? false : true
                ));
        this.fillOptions();
    },
    methods: {
        transformAddress(address) {
            if (!(address === null))
                return address.street + ', ' + address.city + ', ' + address.state;
            else
                return '';
        },
        createAriaLabel(index) {
            return 'Slide ' + index;
        },
        async fillOptions() {
            // if(null == this.serviceProp.additionalServices)
            //     return;
            for (let i = 0; i < this.boat.additionalServices?.length; i++) {
                let newD = {text: this.boat.additionalServices[i].name + '    ' +  '$' + this.boat.additionalServices[i].price, 
                            value: this.boat.additionalServices[i], 
                            disabled: false};
                this.options.push(newD);
            }
         }
    },
    components: {
        'Modal': BoatReservationModalVue,
        StarRating: StarRating,
        ActionsModal: ActionsModal
     }

}
</script>


<style scoped>
    body {
      font-family: 'Roboto Condensed', sans-serif;
      background-color: #f5f5f5
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
      color: #E45641;
    }

    .right-side-pro-detail .tag-section {
      font-size: 18px;
      color: #5D4C46;
    }

    .pro-box-section .pro-box img {
      width: 100%;
      height: 200px;
    }
</style>
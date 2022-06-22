<template>
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal fade" id="exampleModalAction" tabindex="-1" aria-labelledby="exampleModalLabel"
            aria-hidden="true">
            <div class="modal-dialog modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Pretplata na akcije</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"
                            v-on:click="modalClosing"></button>
                    </div>
                    <div class="modal-body">
                        <form id="searchForm">

                            <div class="row gx-3 mb-3">
                                <div class="col-md-12">
                                    <div>Da li ste sigurni da želite da se prijavite na akcije?</div>
                                </div>
                            </div>

                            <div class="row gx-3 mb-1 mt-4">
                                <div class="col-md-12">
                                    <div v-if="!(this.returnMessage === undefined || this.returnMessage === '')"
                                        :class="this.klasa">{{ this.returnMessage }}</div>
                                </div>
                            </div>

                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"
                            v-on:click="modalClosing">Zatvori</button>
                        <button type="button" class="btn btn-primary" v-on:click="subscribe">Pretplati se</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    name: 'ActionsModal',
    props: ['servicePropID'],
    async mounted() {
        this.serviceID = this.servicePropID;
    },
    data() {
        return {
            service: null,
            returnMessage: '',
            klasa: 'text-primary'
        }
    },
    methods: {
        async subscribe() {
            axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + this.$store.accessToken;

            
            await axios
                .get('api/subscription/add-subscription/' + this.servicePropID)
                .then(response => (
                    this.returnMessage = response.data,
                    this.klasa = 'text-success'
                ))
                .catch(err => {
                    if (err.response.status === 400) {
                        // alert(err.response.data);
                        this.returnMessage = err.response.data,
                            this.klasa = 'text-danger'
                    }
                    else {
                        // alert(err);
                        this.returnMessage = err.response.data,
                            this.klasa = 'text-danger'
                    }
                    console.log(err);
                }
                );

        },
        modalClosing() {
            this.returnMessage = '';
            this.klasa = 'text-primary';
        }
    }
}

</script>

<style scoped>
.center-screen {
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    min-height: 100vh;
}
</style>

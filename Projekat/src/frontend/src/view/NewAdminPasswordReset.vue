<template>
    <section class="vh-90 gradient-custom">
        <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                    <div class="card bg-dark text-white" style="border-radius: 1rem;">
                        <div class="card-body p-5 text-center">
                            <div class="mb-md-5 mt-md-4 pb-5">
                                <h2 class="fw-bold mb-2 text-uppercase">Nova lozinka</h2>

                                <div class="form-outline form-white mb-4">
                                    <input
                                        type="email"
                                        id="Password1"
                                        class="form-control form-control-lg"
                                        minlength="6"
                                        maxlength="18"
                                        v-on:keyup="checkPassword"
                                        v-model="password1"
                                    />
                                    <label class="form-label" for="Password1">Lozinka</label>
                                </div>

                                <div class="form-outline form-white mb-4">
                                    <input
                                        type="password"
                                        id="Password2"
                                        class="form-control form-control-lg"
                                        minlength="6"
                                        maxlength="18"
                                        v-on:keyup="checkPassword"
                                        v-model="password2"
                                    />
                                    <label class="form-label" for="Password2">Potvrdite lozinku</label>
                                </div>

                                <button
                                    class="btn btn-outline-light btn-lg px-5"
                                    v-on:click.prevent="changePass"
                                >Promenite lozinku</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</template>

<style scoped>
</style>

<script>
import axios from 'axios'
export default {
    name: 'NewAdminPasswordReset',
    data() {
        return {
            password1: '',
            password2: ''
        }

    },

    methods: {
        checkPassword() {
            let pass1 = document.getElementById("Password1").value;
            let pass2 = document.getElementById("Password2").value;
            if (pass1 !== pass2) {
                document.getElementById("Password2").style.border = "1px solid red";
            } else {
                document.getElementById("Password2").style.border = "1px solid #64ff33";
                document.getElementById("Password1").style.border = "1px solid #64ff33";
            }
        },
        changePass() {
            if (this.password1 === this.password2 && 18 >= this.password1.length && this.password1.length >= 6) {
                let self = this;
                let data = {
                    'usr_id': this.$store.User.id,
                    'newPassword': this.password1
                };
                axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
                axios.defaults.headers.common['Authorization'] = 'Bearer ' + this.$store.accessToken;
                axios.post('/api/profile/make-first-password-reset', data).then((response) => {
                    if (response.status === 200) {
                        alert(response.data);
                        self.$router.push('/admin/profile');
                    }
                }).catch((err) => {
                    alert("Došlo je do greške");
                    console.log(err);
                });

            } else {
                alert("Unos lozinke nije adekvatan");
            }
        }

    }

}

</script>

<template>
    <div class="card mx-4 mx-md-5 shadow-lg">
        <div class="card-body py-5 px-md-5">
            <div class="row d-flex justify-content-center">
                <div class="col-lg-8">
                    <h2 class="fw-bold mb-5">Registracija administratora</h2>
                    <form id="registrationForm" name="registrationForm">
                        <!-- 2 column grid layout with text inputs for the first and last names -->
                        <div class="row">
                            <div class="col-md-4 mb-4">
                                <div class="form-outline">
                                    <input
                                        type="text"
                                        id="Name"
                                        name="Name"
                                        class="form-control"
                                        maxlength="25"
                                        v-model="Name"
                                        required
                                    />
                                    <label class="form-label" for="Name">Ime</label>
                                </div>
                            </div>
                            <div class="col-md-4 mb-4">
                                <div class="form-outline">
                                    <input
                                        type="text"
                                        name="Surname"
                                        id="Surname"
                                        class="form-control"
                                        maxlength="25"
                                        v-model="Surname"
                                        required
                                    />
                                    <label class="form-label" for="Surname">Prezime</label>
                                </div>
                            </div>
                            <div class="col-md-4 mb-4">
                                <div class="form-outline">
                                    <input
                                        type="text"
                                        name="Phone"
                                        class="form-control"
                                        id="Phone"
                                        v-model="Phone"
                                        required
                                    />
                                    <label for="Phone">Telefon</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4 mb-4">
                                <div class="form-outline">
                                    <input
                                        type="text"
                                        name="Address"
                                        class="form-control"
                                        id="addressInputState"
                                        v-model="State"
                                        required
                                    />
                                    <label for="addressInput">Država</label>
                                </div>
                            </div>
                            <div class="col-md-4 mb-4">
                                <div class="form-outline">
                                    <input
                                        type="text"
                                        name="Address"
                                        class="form-control"
                                        id="addressInputCity"
                                        v-model="City"
                                        required
                                    />
                                    <label for="addressInput">Grad</label>
                                </div>
                            </div>
                            <div class="col-md-4 mb-4">
                                <div class="form-outline">
                                    <input
                                        type="text"
                                        name="Address"
                                        class="form-control"
                                        id="addressInputStreet"
                                        v-model="Street"
                                        required
                                    />
                                    <label for="addressInput">Ulica i broj</label>
                                </div>
                            </div>
                        </div>
                        <!-- Email input -->
                        <div class="form-outline">
                            <input
                                type="email"
                                id="Email"
                                class="form-control"
                                name="Email"
                                v-model="Username"
                                required
                            />
                            <label class="form-label" for="email">Email adresa</label>
                        </div>

                        <!-- Password 1 input -->
                        <div class="form-outline mb-4">
                            <input
                                title="Lozinka mora imati najmanje 6 ili najviše 18 karaktera."
                                type="password"
                                id="Password1"
                                class="form-control"
                                placeholder="Lozinka"
                                maxlength="18"
                                name="Password1"
                                v-model="Password1"
                                minlength="6"
                                required
                            />
                        </div>
                        <!-- Password 2 input -->
                        <div class="form-outline mb-4">
                            <input
                                title="Morate ponoviti unos lozinke"
                                v-on:keyup="checkPassword"
                                type="password"
                                id="Password2"
                                class="form-control"
                                placeholder="Ponovite lozinku"
                                maxlength="18"
                                name="Password2"
                                v-model="Password2"
                                minlength="6"
                                required
                            />
                        </div>

                        <!-- Submit button -->
                        <button
                            v-on:click.prevent="register"
                            class="btn btn-primary btn-block mb-4"
                        >
                            Registrujte
                            administratora
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
.card {
    margin-top: 100px!important;
    background-color: #fbeec1!important;
}
</style>

<script>

import axios from 'axios'

export default {
    name: 'AdminRegistration',
    data() {
        return {
            Name: '',
            Surname: '',
            State: '',
            Street: '',
            City: '',
            Phone: '',
            Username: '',
            Password1: '',
            Password2: ''
        }
    },
    methods: {
        reset_input_fields: function(self) {
            self.Name = '';
            self.Surname = '';
            self.State = '';
            self.Street = '';
            self.City = '';
            self.Phone = '';
            self.Username = '';
            self.Password1 = '';
            self.Password2 = '';
        },
        register: function () {
            if (!checkInput()) return;

            let newAdmin = {
                'Name': this.Name,
                'Surname': this.Surname,
                'City': this.City,
                'Street': this.Street,
                'State': this.State,
                'Phone': this.Phone,
                'Username': this.Username,
                'DateOfBirth': this.date,
                'Password1': this.Password1,
                'Password2': this.Password2
            };
            let self = this;
            axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + this.$store.accessToken;
            axios.
                post('/auth/admin/register-new-admin',
                    newAdmin)
                .then((response) => {
                    if (response.status === 200) {
                        alert(response.data);
                    }
                    self.reset_input_fields(self);
                }
                ).catch(function (err){
                    if (err.response.status === 400) {
                        alert(err.response.data);
                        
                    } else {
                        alert(err);
                    }
                    self.reset_input_fields(self);
                    console.log(err);
                });
        },



        checkPassword() {
            let pass1 = document.getElementById("Password1").value;
            let pass2 = document.getElementById("Password2").value;
            if (pass1 !== pass2) {
                document.getElementById("Password2").style.border = "1px solid red";
            } else {
                document.getElementById("Password2").style.border = "1px solid #64ff33";
                document.getElementById("Password1").style.border = "1px solid #64ff33";
            }
        }
    }

};

function checkInput() {
    if (!checkPhone()) {
        alert('Nista uneli ispravan broj telefona (ne može sadržati slova)');
        return false;
    }
    if (!checkFullName()) {
        alert('Morate uneti ispravno ime');
        return false;
    }
    return true;
}
function checkPhone() {
    let regExp = /[a-zA-Z]/
    let phone = document.getElementById('Phone').value;
    if (regExp.test(phone)) {
        return false; // found letters in phone number
    } else if (phone === "") {
        return false; // no phone input
    }
    return true;
}
function checkFullName() {
    let regExp = /[0-9]/

    let name = document.getElementById('Name').value;
    if (regExp.test(name)) {
        return false; // found numbers in the name
    } else if (name === "") {
        return false;
    }

    let surname = document.getElementById('Surname').value;
    if (regExp.test(surname)) {
        return false; // found numbers in the surname
    } else if (surname === "") {
        return false;
    }

    return true;
}


</script>

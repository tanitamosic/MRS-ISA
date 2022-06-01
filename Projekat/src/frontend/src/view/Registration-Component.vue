<template>
    <!-- Section: Design Block -->
    <section class="text-center">
        <!-- Background image -->
        <div class="p-5 bg-image" style="
        background-image: url('https://mdbootstrap.com/img/new/textures/full/171.jpg');
        height: 300px;
        "></div>
        <!-- Background image -->

        <div class="card mx-4 mx-md-5 shadow-5-strong" style="
        margin-top: -100px;
        background: hsla(0, 0%, 100%, 0.8);
        backdrop-filter: blur(30px);
        ">
            <div class="card-body py-5 px-md-5">

                <div class="row d-flex justify-content-center">
                    <div class="col-lg-8">
                        <h2 class="fw-bold mb-5">Registracija</h2>
                        <form id="registrationForm" name="registrationForm">
                            <!-- 2 column grid layout with text inputs for the first and last names -->
                            <div class="row">
                                <div class="col-md-4 mb-4">
                                    <div class="form-outline">
                                        <input type="text" id="Name" name="Name" class="form-control" maxlength="25" v-model="Name" required />
                                        <label class="form-label" for="Name">Ime</label>
                                    </div>
                                </div>
                                <div class="col-md-4 mb-4">
                                    <div class="form-outline">
                                        <input type="text" name="Surname" id="Surname" class="form-control" maxlength="25" v-model="Surname" required />
                                        <label class="form-label" for="Surname">Prezime</label>
                                    </div>
                                </div>
                                <div class="col-md-4 mb-4">
                                    <div class="form-outline">
                                        <input type="text" name="Phone" class="form-control" id="Phone" v-model="Phone" required />
                                        <label for="Phone">Telefon</label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-4 mb-4">
                                    <div class="form-outline">
                                        <input type="text" name="Address" class="form-control" id="addressInputState" v-model="State" required />
                                        <label for="addressInput">Država</label>
                                    </div>
                                </div>
                                <div class="col-md-4 mb-4">
                                    <div class="form-outline">
                                        <input type="text" name="Address" class="form-control" id="addressInputCity" v-model="City" required />
                                        <label for="addressInput">Grad</label>
                                    </div>
                                </div>
                                <div class="col-md-4 mb-4">
                                    <div class="form-outline">
                                        <input type="text" name="Address" class="form-control" id="addressInputStreet" v-model="Street" required />
                                        <label for="addressInput">Ulica i broj</label>
                                    </div>
                                </div>
                                
                            </div>
                            <fieldset class="form-group">
                                <div class="row">
                                    <div class="col-sm-2 align-items-right">
                                        <label class="form-label" id="datelbl" for="date">Datum rođenja</label>
                                    </div>
                                    <div class="col-sm-2 align-items-left">
                                        <input class="form-control mb-2" type="date" name="date" id="date" v-model="date"/>
                                    </div>
                                    <label class="col-sm-1">Uloga:</label>
                                    <div class="col-sm-7">
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="gridRadios"
                                                id="gridRadios1" value="Potrosac" checked />
                                            <label class="form-check-label" for="gridRadios1">Klijent</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="gridRadios"
                                                id="gridRadios2" value="Vlasnik broda" />
                                            <label class="form-check-label" for="gridRadios2">Vlasnik broda</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="gridRadios"
                                                id="gridRadios3" value="Instruktor pecanja" />
                                            <label class="form-check-label" for="gridRadios3">Instruktor pecanja</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="gridRadios"
                                                id="gridRadios4" value="Vlasnik vikendice" />
                                            <label class="form-check-label" for="gridRadios4">Vlasnik vikendice</label>
                                        </div>
                                    </div>
                                </div>
                            </fieldset>

                            <!-- Email input -->
                            <div class="form-outline mt-4">
                                <input type="email" id="Email" class="form-control" name="Email" v-model="Email" required />
                                <label class="form-label" for="email">Email adresa</label>
                            </div>

                            <!-- Password 1 input -->
                            <div class="form-outline mb-4">
                                <input title="Lozinka mora imati najmanje 6 ili najviše 18 karaktera." type="password"
                                    id="Password1" class="form-control" placeholder="Lozinka" maxlength="18"
                                    name="Password1"
                                    v-model="Password1"
                                    minlength="6" required />
                            </div>
                            <!-- Password 2 input -->
                            <div class="form-outline mb-4">
                                <input title="Morate ponoviti unos lozinke" v-on:keyup="checkPassword" type="password"
                                    id="Password2" class="form-control" placeholder="Ponovite lozinku" maxlength="18"
                                    name="Password2"
                                    v-model="Password2"
                                    minlength="6" required />
                            </div>

                            <!-- Submit button -->
                            <button v-on:click.prevent="register" class="btn btn-primary btn-block mb-4">Registrujte
                                se</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Section: Design Block -->
</template>

<style scoped>
#password1:invalid {
    border: 1px solid red;
}

#datelbl {
    display: inline-block;
    text-align: right;
    margin-right: 5px;
}
</style>


<script>
import axios from 'axios'
export default {
    name: "Registration-component",
    data() {
        return {
            Name: '',
            Surname: '',
            State: '',
            Street: '',
            City: '',
            Phone: '',
            Email: '',
            date: '',
            Password1: '',
            Password2: ''
        }
    },
    methods: {
        register: function () {
            let role = ''
            role = getRole();

            if (role === '') return;
            if (!checkInput()) return;

            let jsonData = JSON.stringify({
                'Name': this.Name,
                'Surname': this.Surname,
                'City': this.City,
                'Street': this.Street,
                'State': this.State,
                'Phone': this.Phone,
                'Email': this.Email,
                'DateOfBirth': this.date,
                'Password1': this.Password1,
                'Password2': this.Password2
            });
            axios.
                post('api/register/' + role,
                    jsonData,
                    { headers: { 'Content-Type': 'application/json' } })
                .then(function response(requestResponse) {
                    if (requestResponse.status === 200) {
                        alert(requestResponse.data);
                    }
                }
                ).catch(function (err){
                    if (err.response.status === 400) {
                        alert(err.response.data);
                        location.reload();
                    } else {
                        alert(err);
                    }
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

function getRole() {
    switch (document.querySelector('input[name="gridRadios"]:checked').value) {
        case "Potrosac": {
            return 'client'
        }
        case "Vlasnik broda": {
            return 'boatowner'
        }
        case "Instruktor pecanja": {
            return 'instructor'
        }
        case "Vlasnik vikendice": {
            return 'cottageowner'
        }
        default: { alert("Odaberite ulogu!"); return ''; }
    }
}

function checkInput() {
    if (!checkDate()) {
        alert('Morate odabrati ispravan datum');
        return false;
    }
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
function checkDate() {
    let date = new Date(document.getElementById('date').value);
    let today = new Date();
    if (date == null) {
        return false; // birthdate isnt chosen 
    }
    if (today < date) {
        return false; // birthdate isnt in the past
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


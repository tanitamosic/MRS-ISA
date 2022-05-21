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
                        <form id="registrationForm">
                            <!-- 2 column grid layout with text inputs for the first and last names -->
                            <div class="row">
                                <div class="col-md-6 mb-4">
                                    <div class="form-outline">
                                        <input type="text" id="Name" class="form-control" maxlength="25" required />
                                        <label class="form-label" for="Name">Ime</label>
                                    </div>
                                </div>
                                <div class="col-md-6 mb-4">
                                    <div class="form-outline">
                                        <input type="text" id="Surname" class="form-control" maxlength="25" required />
                                        <label class="form-label" for="Surname">Prezime</label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6 mb-4">
                                    <div class="form-outline">
                                        <input type="text" class="form-control" id="addressInput" required />
                                        <label for="addressInput">Adresa</label>
                                    </div>
                                </div>
                                <div class="col-md-6 mb-4">
                                    <div class="form-outline">
                                        <input type="text" class="form-control" id="phoneInput" required />
                                        <label for="phoneInput">Telefon</label>
                                    </div>
                                </div>
                            </div>
                            <fieldset class="form-group">
                                <div class="row">
                                    <div class="col-sm-2 align-items-right">
                                        <label class="form-label" id="datelbl" for="date">Datum rođenja</label>
                                    </div>
                                    <div class="col-sm-2 align-items-left">
                                        <input class="form-control mb-2" type="date" name="date" id="date" />
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
                                <input type="email" id="email" class="form-control" required />
                                <label class="form-label" for="email">Email adresa</label>
                            </div>

                            <!-- Password 1 input -->
                            <div class="form-outline mb-4">
                                <input title="Lozinka mora imati najmanje 6 ili najviše 18 karaktera." type="password"
                                    id="password1" class="form-control" placeholder="Lozinka" maxlength="18"
                                    minlength="6" required />
                            </div>
                            <!-- Password 2 input -->
                            <div class="form-outline mb-4">
                                <input title="Morate ponoviti unos lozinke" v-on:keyup="checkPassword" type="password"
                                    id="password2" class="form-control" placeholder="Ponovite lozinku" maxlength="18"
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
    methods: {
        register: function () {
            let role = ''
            role = getRole();

            if (role === '') return;
            if (!checkInput()) return;

            let formData = new FormData(document.getElementById("registrationForm"));
            let jsonData = JSON.stringify(formData);
            axios.post('api/register/' + role, jsonData, { headers: { 'Content-Type': 'application/json' } })
            //alert('Uspesno ste se registrovali');
            location.reload();
        },

        checkPassword() {
            let pass1 = document.getElementById("password1").value;
            let pass2 = document.getElementById("password2").value;
            if (pass1 !== pass2) {
                document.getElementById("password2").style.border = "1px solid red";
            } else {
                document.getElementById("password2").style.border = "1px solid #64ff33";
                document.getElementById("password1").style.border = "1px solid #64ff33";
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
    let phone = document.getElementById('phoneInput').value;
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


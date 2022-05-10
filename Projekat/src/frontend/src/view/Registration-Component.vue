<template>
    <div class="container align-items-center">
        <div class="mt-5"><h1>Registracija</h1></div>
        <form class="w-25" id="registrationForm">
            <div class="form-group mt-2">
                <label for="name">Ime</label>
                <input type="text" class="form-control" id="name" placeholder="Ime" required/>
            </div>
            <div class="form-group">
                <label for="surname">Prezime</label>
                <input type="text" class="form-control" id="surname" placeholder="Prezime" required/>
            </div>
            <div class="form-group">
                <label for="addressInput">Adresa</label>
                <input type="text" class="form-control" id="addressInput" placeholder="Adresa" required/>
            </div>
            <div class="form-group">
                <label for="phone">Broj telefona</label>
                <input type="text" class="form-control" id="phone" placeholder="Broj telefona" required/>
            </div>
            <div class="form-group">
                <label for="inputEmail1">Email adresa</label>
                <input
                    type="email"
                    class="form-control"
                    id="inputEmail1"
                    aria-describedby="emailHelp"
                    placeholder="Unesite email"
                required/>
            </div>
            <div class="form-group">
                <label for="inputPassword1">Password</label>
                <input
                    type="password"
                    class="form-control"
                    id="inputPassword1"
                    placeholder="Lozinka"
                required/>
            </div>
            <div class="form-group mt-2">
                <input
                    type="password"
                    class="form-control"
                    id="inputPassword2"
                    placeholder="Potvrdite lozinku"
                required/>
            </div>
            <fieldset class="form-group">
                <div class="row">
                    <legend class="col-form-label col-sm-2 pt-0">Uloga:</legend>
                    <div class="col-sm-10">
                        <div class="form-check">
                            <input
                                class="form-check-input"
                                type="radio"
                                name="gridRadios"
                                id="gridRadios1"
                                value="Potrosac"
                                checked
                            />
                            <label class="form-check-label" for="gridRadios1">Potrosac</label>
                        </div>
                        <div class="form-check">
                            <input
                                class="form-check-input"
                                type="radio"
                                name="gridRadios"
                                id="gridRadios2"
                                value="Vlasnik broda"
                            />
                            <label class="form-check-label" for="gridRadios2">Vlasnik broda</label>
                        </div>
                        <div class="form-check">
                            <input
                                class="form-check-input"
                                type="radio"
                                name="gridRadios"
                                id="gridRadios3"
                                value="Instruktor pecanja"
                            />
                            <label class="form-check-label" for="gridRadios3">Instruktor pecanja</label>
                        </div>
                        <div class="form-check">
                            <input
                                class="form-check-input"
                                type="radio"
                                name="gridRadios"
                                id="gridRadios4"
                                value="Vlasnik vikendice"
                            />
                            <label class="form-check-label" for="gridRadios4">Vlasnik vikendice</label>
                        </div>
                    </div>
                </div>
                <div class="text-center">
                    <button
                        v-on:click.prevent="post"
                        class="btn btn-primary mt-5 align-self-center"
                    >Registrujte se</button>
                </div>
            </fieldset>
        </form>
    </div>
</template>


<script>
import axios from 'axios'
export default {
    name: "Registration-component",
    methods: {
        post: function () {
            let formData = new FormData(document.getElementById("registrationForm"));
            let jsonData = JSON.stringify(formData);
            let role = ''
            switch (document.querySelector('input[name="gridRadios"]:checked').value) {
                case "Potrosac": {
                    role = 'client'
                    break;
                }
                case "Vlasnik broda": {
                    role = 'boatowner'
                    break;
                }
                case "Instruktor pecanja": {
                    role = 'instructor'
                    break;
                }
                case "Vlasnik vikendice": {
                    role = 'cottageowner'
                    break;
                }
                default: alert("Odaberite ulogu!"); return;
            }
            axios.post('api/register/' + role, jsonData, { headers: { 'Content-Type': 'application/json' }})
            alert('Uspesno ste se registrovali');
        }
    }
};

</script>


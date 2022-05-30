<template>
  <section class="vh-90 gradient-custom">
    <div class="container py-5 h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-12 col-md-8 col-lg-6 col-xl-5">
          <div class="card bg-dark text-white" style="border-radius: 1rem;">
            <div class="card-body p-5 text-center">
              <div class="mb-md-5 mt-md-4 pb-5">
                <h2 class="fw-bold mb-2 text-uppercase">Prijava</h2>
                <p class="text-white-50 mb-5">Unesite email i lozinku</p>

                <div class="form-outline form-white mb-4">
                  <input type="email" id="emailInput" class="form-control form-control-lg" />
                  <label class="form-label" for="emailInput">Email</label>
                </div>

                <div class="form-outline form-white mb-4">
                  <input type="password" id="passwordInput" class="form-control form-control-lg" />
                  <label class="form-label" for="passwordInput">Lozinka</label>
                </div>

                <p class="small mb-5 pb-lg-2">
                  <a class="text-white-50" href="#!">Zaboravili ste lozinku?</a>
                </p>

                <button class="btn btn-outline-light btn-lg px-5" v-on:click.prevent="login">Prijava</button>

                <div class="d-flex justify-content-center text-center mt-4 pt-1">
                  <a href="#!" class="text-white">
                    <i class="fab fa-facebook-f fa-lg"></i>
                  </a>
                  <a href="#!" class="text-white">
                    <i class="fab fa-twitter fa-lg mx-4 px-2"></i>
                  </a>
                  <a href="#!" class="text-white">
                    <i class="fab fa-google fa-lg"></i>
                  </a>
                </div>
              </div>

              <div>
                <p class="mb-0">
                  Nemate nalog?
                  <a href="/register" class="text-white-50 fw-bold">Registrujte se!</a>
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped>
.card {
  background-color: #659dbd!important;
  top:50px;
}
</style>

<script>
import axios from 'axios'

export default {
  name: "LoginView",
  methods: {
    login: function () {
      let email = document.getElementById('emailInput').value;
      let password = document.getElementById('passwordInput').value;
      if (email === '' || password === '') {
        alert('Email i sifra ne mogu biti prazni');
        return;
      }
      let loginData = {'Email': email, 'Password': password};
      let jsonData = JSON.stringify(loginData);
      axios.get('api/login', jsonData, { headers: { 'Content-Type': 'application/json' } });
    }
  }
}
</script>

<template>
  <div class="container-fluid px-4 mt-4 content-right">
    <div class="row">
      <div class="col-xl-4">
        <!-- Profile picture card-->
        <div class="card mb-4 mb-xl-0">
          <div class="card-header">Profilna slika</div>
          <div class="card-body text-center">
            <!-- Profile picture image-->
            <img
              class="img-account-profile rounded-circle mb-2"
              src="http://bootdey.com/img/Content/avatar/avatar1.png"
              alt
            />
            <!-- Profile picture help block-->
            <div class="small font-italic text-muted mb-4">JPG ili PNG slika ne veća od 5 MB</div>
            <!-- Profile picture upload button-->
            <button class="btn btn-primary" type="button">Zameni sliku</button>
          </div>
        </div>
      </div>
      <div class="col-xl-8">
        <!-- Account details card-->
        <div class="card mb-4">
          <div class="card-header">Detalji profila</div>
          <div class="card-body">
            <form id="profileForm">
              <!-- Form Row-->
              <div class="row gx-3 mb-3">
                <!-- Form Group (first name)-->
                <div class="col-md-6">
                  <label class="small mb-1" for="Name">Ime</label>
                  <input
                    class="form-control"
                    id="Name"
                    name="Name"
                    type="text"
                    placeholder="Enter your first name"
                    value="Valerie"
                  />
                </div>
                <!-- Form Group (last name)-->
                <div class="col-md-6">
                  <label class="small mb-1" for="Surname">Prezime</label>
                  <input
                    class="form-control"
                    id="Surname"
                    name="Surname"
                    type="text"
                    placeholder="Enter your last name"
                    value="Luna"
                  />
                </div>
              </div>
              <!-- Form Row        -->
              <div class="mb-3">
                <!-- Form Group (location)-->
                <label class="small mb-1" for="Address">Adresa</label>
                <input
                  class="form-control"
                  id="Address"
                  name="Address"
                  type="text"
                  placeholder="Enter your location"
                  value="San Francisco, CA"
                />
              </div>
              <!-- Form Group (email address)-->
              <div class="mb-3">
                <label class="small mb-1" for="Email">Email adresa</label>
                <input
                  class="form-control"
                  id="Email"
                  name="Email"
                  type="email"
                  placeholder="Enter your email address"
                  value="name@example.com"
                />
              </div>
              <!-- Form Row-->
              <div class="row gx-3 mb-3">
                <!-- Form Group (phone number)-->
                <div class="col-md-6">
                  <label class="small mb-1" for="Phone">Broj telefona</label>
                  <input
                    class="form-control"
                    name="Phone"
                    id="Phone"
                    type="tel"
                    placeholder="Enter your phone number"
                    value="555-123-4567"
                  />
                </div>
                <!-- Form Group (birthday)-->
                <div class="col-md-6">
                  <label class="small mb-1" for="inputBirthday">Datum rođenja</label>
                  <input
                    class="form-control"
                    id="inputBirthday"
                    type="text"
                    placeholder="Enter your birthday"
                    value="06/10/1988"
                    title="Datum rođenja ne možete izmeniti"
                    readonly
                  />
                </div>
              </div>
              <hr />
              <!-- change password -->
              <div class="bg-secondary-soft px-4 rounded">
                <div class="row g-3">
                  <h5>Promena lozinke</h5>
                  <!-- Old password -->
                  <div class="col-md-6">
                    <label for="OldPassword" class="form-label">Stara lozinka</label>
                    <input type="password" class="form-control" id="OldPassword" name="OldPassword"/>
                  </div>
                  <!-- New password -->
                  <div class="col-md-6">
                    <label for="NewPassword1" class="form-label">Nova lozinka</label>
                    <input type="password" class="form-control" id="NewPassword1" name="NewPassword1" />
                  </div>
                  <!-- Confirm password -->
                  <div class="col-md-12">
                    <label for="NewPassword2" class="form-label">Potvrdite novu lozinku</label>
                    <input type="password" class="form-control" id="NewPassword2" name="NewPassword2" />
                  </div>
                </div>
              </div>
              <hr />
              <!-- Save changes button-->
              <button
                class="btn btn-primary"
                type="button"
                v-on:click="update_profile"
              >Sačuvaj izmene</button>
              <button
                id="account-deletion"
                class="btn btn-danger"
                type="button"
                v-on:click="delete_profile"
              >Izbriši profil</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.content-right {
  top: 50px;
  z-index: -1;
  float: right;
  width: 85%;
}
body {
  /* margin-top:50px; */
  background-color: #f2f6fc;
  color: #69707a;
}
.img-account-profile {
  height: 10rem;
}
.rounded-circle {
  border-radius: 50% !important;
}
.card {
  margin-top: 50px;
  box-shadow: 0 0.15rem 1.75rem 0 rgb(33 40 50 / 15%);
}
.card .card-header {
  font-weight: 500;
}
.card-header:first-child {
  border-radius: 0.35rem 0.35rem 0 0;
}
.card-header {
  padding: 1rem 1.35rem;
  margin-bottom: 0;
  background-color: rgba(33, 40, 50, 0.03);
  border-bottom: 1px solid rgba(33, 40, 50, 0.125);
}
.form-control {
  display: block;
  width: 100%;
  padding: 0.875rem 1.125rem;
  font-size: 0.875rem;
  font-weight: 400;
  line-height: 1;
  color: #69707a;
  background-color: #fff;
  background-clip: padding-box;
  border: 1px solid #c5ccd6;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  border-radius: 0.35rem;
  transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}
input:read-only {
  background-color: white;
}

#account-deletion {
  margin-left: 15px;
}
</style>

<script>

import axios from 'axios'

export default {
  name: 'ProfileView',
  data() {
    return {
      Name: '',
      Surname: '',
      Address: '',
      Phone: '',
      Email: '',
      OldPassword: '',
      NewPassword1: '',
      NewPassword2: ''
    }
  },
  

  methods: {
    update_profile: function () {
      let jsonData = JSON.stringify({
        'Name': this.Name,
        'Surname': this.Surname,
        'Address': this.Address,
        'Phone': this.Phone,
        'Email': this.Email,
        'OldPassword': this.OldPassword,
        'Password1': this.Password1,
        'Password2': this.Password2
      });

      axios.post('api/profile/update-profile', jsonData, { headers: { 'Content-Type': 'application/json' } });
    },
    delete_profile: function () {

    }
  }
}
</script>

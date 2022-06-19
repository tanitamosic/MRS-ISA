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
                    v-model="Name"
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
                    v-model="Surname"
                  />
                </div>
              </div>
              <!-- Form Row        -->
              <div class="row gx-3 mb-3">
                <!-- Form Group (location)-->
                <div class="col-md-4">
                  <label class="small mb-1" for="State">Drzava</label>
                  <input
                    class="form-control"
                    id="State"
                    name="State"
                    type="text"
                    placeholder="Enter your location"
                    v-model="State"
                  />
                </div>
                <div class="col-md-4">
                  <label class="small mb-1" for="City">Grad</label>
                  <input
                    class="form-control"
                    id="City"
                    name="City"
                    type="text"
                    placeholder="Enter your location"
                    v-model="City"
                  />
                </div>
                <div class="col-md-4">
                  <label class="small mb-1" for="Street">Ulica i broj</label>
                  <input
                    class="form-control"
                    id="Street"
                    name="Street"
                    type="text"
                    placeholder="Enter your location"
                    v-model="Street"
                  />
                </div>
                
                
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
                  v-model="Username"
                />
              </div>
              <!-- Form Row-->
              <div class="row gx-3 mb-3">
                <!-- Form Group (phone number)-->
                <div class="col-md-12">
                  <label class="small mb-1" for="Phone">Broj telefona</label>
                  <input
                    class="form-control"
                    name="Phone"
                    id="Phone"
                    type="tel"
                    placeholder="Enter your phone number"
                    v-model="Phone"
                  />
                </div>
              </div>
              <div class="row gx-3 mb-3" v-if="show_biography">
                <!-- Form Group Biography -->
                <div class="col-md-12">
                  <label class="small mb-1" for="Biography">Biografija</label>
                  <textarea
                    class="form-control"
                    rows="5"
                    name="Biography"
                    id="Biography"
                    type="tel"
                    placeholder="Unesite Vašu biografiju"
                    v-model="Biography"
                  ></textarea>
                </div>
              </div>
              <hr />
              <!-- change password -->
              <div class="bg-secondary-soft px-4 rounded">
                <div class="row g-3">
                  <h5>Promena lozinke</h5>
                  <!-- Old password -->
                  <div class="col-md-6">
                    <label for="OldPasswordInput" class="form-label">Stara lozinka</label>
                    <input type="password" class="form-control" id="OldPasswordInput" name="OldPasswordInput" v-model="OldPasswordInput" />
                  </div>
                  <!-- New password -->
                  <div class="col-md-6">
                    <label for="NewPassword1" class="form-label">Nova lozinka</label>
                    <input type="password" class="form-control" id="NewPassword1" name="NewPassword1" v-model="NewPassword1" />
                  </div>
                  <!-- Confirm password -->
                  <div class="col-md-12">
                    <label for="NewPassword2" class="form-label">Potvrdite novu lozinku</label>
                    <input type="password" class="form-control" id="NewPassword2" name="NewPassword2" v-model="NewPassword2" />
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
                v-if="show_delete_btn"
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
      State: '',
      City: '',
      Street: '',
      Phone: '',
      Username: '',
      OldPasswordInput: '',
      OldPassword: '',
      NewPassword1: '',
      NewPassword2: '',

      Biography: '',

      show_biography: false,
      show_delete_btn: true
    }
  },
  mounted() {
    this.Name = this.$store.User.name;
    this.Surname = this.$store.User.surname;
    this.Phone = this.$store.User.phone;
    this.Username = this.$store.username;
    this.State = this.$store.User.address.state;
    this.City = this.$store.User.address.city;
    this.Street = this.$store.User.address.street;
    this.OldPassword = this.$store.currentPassword;

    this.Biography = this.$store.User.biography ? this.$store.User.biography : '';
    if (this.$store.role === "ROLE_INSTRUCTOR") {
      this.show_biography = true;
    }
    if (this.$store.role === "ROLE_ADMIN") {
      this.show_delete_btn = false;
    }
    //console.log(this.$store.User);
  },

  methods: {
    update_profile: function () {
      
      let OldPasswordInput = document.getElementById("OldPasswordInput").value;
      if ((this.OldPassword != OldPasswordInput) && OldPasswordInput != '') {
        alert("Uneta stara lozinka se ne podudara sa trenutnom lozinkom.");
        return;
      } else if (OldPasswordInput != '' && ((this.NewPassword1 != this.NewPassword2) || (this.NewPassword1 === ''))) {
        alert("Unos nove lozinke je neispravan.");
        return;
      }
      
      if (this.Name === '' ||
          this.Surname === '' ||
          this.Phone === '' ||
          this.Username === '' ||
          this.State === '' ||
          this.City === '' ||
          this.Street === '') {
          alert("Polja sa Vašim ličnim podacima ne smeju biti prazna.")
          return;
      }


      let jsonData = {
        'Id': this.$store.User.id,

        'Name': this.Name,
        'Surname': this.Surname,
        // 'Address_id': this.$store.User.address.id,
        'State': this.State,
        'City': this.City,
        'Street': this.Street,
        'Phone': this.Phone,
        
        'NewPassword': this.Password1 ? this.Password1 : '',

        'NewUsername': this.Username,
        'OldUsername': this.$store.username,

        'Biography': this.Biography ? this.Biography : ''
      };
      if(this.checkInput()) {
      // let self = this;
      axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
      axios.defaults.headers.common['Authorization'] = 'Bearer ' + this.$store.accessToken;

      axios.post('/api/profile/update-profile',
        jsonData). then(function (requestResponse) {
          if (requestResponse.status === 200) {
            alert("Promena podataka je uspesna.")
          }
        }).catch(function (err) {
          if (err)
            alert("Serverska greska");
        });
      } else{
        alert("")
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
    },

  }
}
</script>

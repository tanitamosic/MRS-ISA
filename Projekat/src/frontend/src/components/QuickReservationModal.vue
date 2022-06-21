<template>
  <Transition name="modal">
    <div v-if="show" class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container" style="background-color: lightgray;">
          <div class="modal-header" style="font-size: 22px; color:lime; background-color: lightslategray; padding-left: 60px;">
            Nova Akcija
          </div>

          <div class="modal-body">
            <slot name="body">
              <span>Datum kada pocinje akcija</span>
              <input class="form-control mb-3" type="date" v-model="actionDateFrom"/>
              
              <span>Datum kada se zavrsava akcija</span>
              <input type="date" class="form-control mb-3" v-model="actionDateTo"/>
              <hr>
              <span>Popust na cenu (%)</span>
              <input type="number" min="0" max="100" class="form-control mb-3" v-model="discount"/>
            </slot>
          </div>

          <div class="modal-footer">
            <slot name="footer">
              <button
                class="form-control btn btn-primary"
                @click="createAction"
              >Kreiraj</button>
              <button
                class="form-control btn btn-danger"
                @click="$emit('close')"
              >Odustani</button>
            </slot>
          </div>
        </div>
      </div>
    </div>
  </Transition>
</template>

<script>

import axios from 'axios'

export default {
  props: {
    show: Boolean,
    dateFrom: Object,
    dateTo: Object,
    adventureId: Object

  },
  data() {
    return {
      actionDateFrom: null,
      actionDateTo: null,
      discount: null,
    }
  },
  methods: {
    createAction: function() {
      if (!this.discount) {
        alert('Koja je poenta akcije ako nije akcija (popust)?');
        return;
      }

      let today = new Date();
      let actionFrom = Date.parse(this.actionDateFrom);
      let actionTo = Date.parse(this.actionDateTo);

      if (actionFrom > actionTo) { alert('Prvi datum mora biti raniji od drugog'); return; }
      if (today > actionFrom) { alert('akcija ne moze poceti u proslosti'); return;}
      if (Date.parse(this.dateFrom) <= actionFrom && Date.parse(this.dateTo) >= actionTo) {
        // let self = this;
        axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
        axios.defaults.headers.common['Authorization'] = 'Bearer ' + this.$store.accessToken;
        
        let newAction = {
          'serviceId': this.adventureId,
          'actionDateFrom': this.actionDateFrom + "T00:00",
          'actionDateTo': this.actionDateTo + "T00:00",
          'discount': this.discount
        }
        axios.post('/api/adventure/create-action/', newAction).then((response) => {
          alert(response.data);
          this.$emit('close');
        }).catch((err) => {
          alert("Doslo je do greske");
          console.log(err);
        })
      } else {
        alert('Akcija moze biti dostupna samo u opsegu datuma termina');
        return;
      }
    }
  }
}
</script>

<style scoped>
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: table;
  transition: opacity 0.3s ease;
}

.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
}

.modal-container {
  width: 300px;
  margin: 0px auto;
  padding: 20px 30px;
  background-color: #fff;
  border-radius: 2px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
  transition: all 0.3s ease;
}

.modal-header h3 {
  margin-top: 0;
  color: #42b983;
}

.modal-body {
  margin: 20px 0;
}

.modal-default-button {
  float: right;
}

/*
 * The following styles are auto-applied to elements with
 * transition="modal" when their visibility is toggled
 * by Vue.js.
 *
 * You can easily play with the modal transition by editing
 * these styles.
 */

.modal-enter-from {
  opacity: 0;
}

.modal-leave-to {
  opacity: 0;
}

.modal-enter-from .modal-container,
.modal-leave-to .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}
</style>
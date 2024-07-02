<template>
  <q-form @submit.prevent="onSubmit">
    <div class="row reverse-wrap q-gutter-lg">
      <div class="summary-box">
        <q-linear-progress
          stripe
          size="10px"
          :value="progress"
          class="q-mt-md"
        />
        <div>
          <div>
            <h2>Reservando plazas en excursión</h2>
            <p v-if="seats" class="row justify-center items-center">
              Estas reservando {{ seats }} plaza{{ seats > 1 ? "s" : "" }} en la
              excursión:
            </p>
            <ExcursionSummary
              v-if="excursion"
              :excursion="excursion"
              :showSeats="false"
            />
          </div>
          <div class="row no-wrap justify-between q-gutter-lg q-pt-md">
            <div class="q-pl-xl">
              <q-btn
                @click="goBack"
                icon="fa-solid fa-arrow-left"
                label="Volver"
              />
            </div>
            <div class="q-pr-xl">
              <q-btn
                type="Submit"
                icon-right="fa-solid fa-arrow-right"
                label="Continuar"
              />
            </div>
          </div>
        </div>
      </div>
      <div class="summary-box">
        <h2>Datos del comprador</h2>

        <div>
          <q-input
            outlined
            v-model.trim="firstName"
            label="Nombre"
            :rules="[(val) => !!val || 'Este campo es necesario']"
          />
        </div>
        <div>
          <q-input
            outlined
            v-model.trim="lastName"
            label="Apellidos"
            :rules="[(val) => !!val || 'Este campo es necesario']"
          />
        </div>
        <div>
          <q-input
            outlined
            v-model.trim="email"
            label="Correo electrónico"
            :rules="[(val) => !!val || 'Este campo es necesario']"
            type="email"
          >
            <template v-slot:prepend>
              <q-icon name="mail" />
            </template>
          </q-input>
        </div>
      </div>
    </div>
  </q-form>
</template>

<script>
import { useReservationFormStore } from "src/stores/reservationform";
import { useExcursionQueryStore } from "src/stores/excursionquery";
import ExcursionSummary from "./view/ExcursionSummary.vue";

const reservationStore = useReservationFormStore();
const queryStore = useExcursionQueryStore();

export default {
  data() {
    return {
      firstName: null,
      lastName: null,
      email: null,
      seats: null,
      progress: 0.5,
      excursion: null,
      querydata: null,
    };
  },
  components: {
    ExcursionSummary,
  },
  created() {
    this.firstName = reservationStore.firstName;
    this.lastName = reservationStore.lastName;
    this.email = reservationStore.email;
    this.seats = queryStore.querydata.seats;
    this.excursion = reservationStore.selectedExcursion;
  },
  methods: {
    onSubmit() {
      if (this.firstName && this.lastName && this.email) {
        reservationStore.setUserData({
          firstName: this.firstName,
          lastName: this.lastName,
          email: this.email,
        });
        this.goToNext();
      } else {
        this.$q.notify({
          message: "Por favor rellena los campos necesarios.",
          color: "negative",
        });
      }
    },
    goToNext() {
      this.$emit("next");
    },
    goBack() {
      this.$emit("previous");
    },
  },
};
</script>

<style lang="scss" scoped>
@import "src/css/ReservationPage/box.scss";
</style>

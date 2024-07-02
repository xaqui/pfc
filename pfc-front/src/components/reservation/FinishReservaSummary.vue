<template>
  <div class="summary-box">
    <div class="column justify-center content-center">
      <div>
        <div class="sum-head">
          <h2>Reserva Creada</h2>
          <h3>¡Muchas Gracias por tu reserva!</h3>
          <p>Te presentamos un resumen de tu operación</p>
        </div>
      </div>
      <div>
        <ReservaView v-if="reserva" :summary="reserva" />
      </div>
      <div class="column justify-center content-center q-pt-md">
        <q-btn
          @click="goToHome"
          color="primary"
          padding="md"
          icon="fa-solid fa-home"
          label="Volver al Inicio"
        ></q-btn>
      </div>
    </div>
  </div>
</template>

<script>
import ReservaView from "src/components/reservation/view/ReservaView.vue";
import { useReservationFormStore } from "src/stores/reservationform";
import axios from "axios";

const reservationStore = useReservationFormStore();

export default {
  data() {
    return {
      reserva: null,
    };
  },
  created() {
    this.createReservaRequest();
  },
  components: { ReservaView },
  methods: {
    goToHome() {
      this.$router.push("/");
    },
    createReservaRequest() {
      let excursionID = reservationStore.excursionID;
      let email = reservationStore.email;
      let seats = reservationStore.seats;

      if (!excursionID || !email || !seats) {
        Notify.create({
          message: "Error creando la reserva. Por favor, inténtalo de nuevo.",
          color: "negative",
        });
        this.$router.push({
          name: "ErrorPage",
          query: { code: 500 },
        });
        return;
      }

      // Create Reserva
      axios
        .post(`${process.env.API_URL}/reservas`, {
          excursionId: excursionID,
          email: email,
          numPlazas: seats,
        })
        .then((response) => {
          this.fetchReservaSummary(response.data.reservaId);
        })
        .catch((error) => {
          this.$router.push({
            name: "ErrorPage",
            query: { code: error.response.status },
          });
          return;
        });
    },
    fetchReservaSummary(reservaID) {
      axios
        .get(`${process.env.API_URL}/reservas/${reservaID}/summary`)
        .then((response) => {
          this.reserva = response.data;
        })
        .catch((error) => {});
    },
  },
};
</script>

<style lang="scss" scoped>
h2 {
  font-size: 3em;
  margin-bottom: 0.5em;
  text-align: center;
}

.sum-head {
  text-align: center;
  padding-bottom: 1rem;
}

.sum-head h3 {
  padding: 1rem 0;
  margin: 0;
}

.sum-head p {
  padding: 0;
  font-size: 1.5em;
}

.summary-box {
  padding: 2rem;
  height: fit-content;
  border: 1px solid #ccc;
  border-radius: 10px;
  background-color: $highlight;
}
</style>

<template>
  <div class="column items-center justify-center q-gutter-lg">
    <div class="q-pb-md">
      <q-btn
        @click="goBack"
        icon="fa-solid fa-arrow-left"
        label="Volver Atrás"
      />
    </div>
    <div v-if="reservations.length > 0">
      <div v-for="reservation in reservations" :key="reservation.reservationId">
        <ReservationListElement
          @cancel-reservation="handleReservationCancellation"
          :reservation="reservation"
        />
      </div>
    </div>
    <div v-else>
      <h3 style="color: lightgrey">No se encontraron reservas</h3>
    </div>
  </div>
</template>

<script>
import ReservationListElement from "./view/ReservationListElement.vue";
import { useReservationQueryStore } from "src/stores/reservationquery";
import axios from "axios";

const store = useReservationQueryStore();

export default {
  components: {
    ReservationListElement,
  },
  data() {
    return {
      reservations: [],
    };
  },
  created() {
    this.fetchReservations();
  },
  methods: {
    goBack() {
      this.$emit("previous");
    },
    fetchReservations() {
      if (!store.email) {
        return;
      }
      axios
        .post(`${process.env.API_URL}/reservas/user-reservations`, {
          email: store.email,
        })
        .then((response) => {
          this.reservations = response.data;
        })
        .catch((error) => {});
    },
    handleReservationCancellation(reservationId) {
      axios
        .put(`${process.env.API_URL}/reservas/cancel/${reservationId}`)
        .then((response) => {
          this.fetchReservations();
        })
        .catch((error) => {});
    },
  },
};
</script>

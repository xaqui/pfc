<template>
  <div>
    <div class="summary-box">
      <q-linear-progress stripe size="10px" :value="progress" class="q-mt-md" />
      <h2>Confirmación de Operación</h2>
      <SubtotalSummary v-if="subtotalsummary" :summary="subtotalsummary" />
      <div class="row justify-end q-pt-md">
        <p class="q-ma-sm">¿Ves algo incorrecto?</p>
        <q-btn
          @click="goBack"
          icon="fa-solid fa-arrow-left"
          label="Volver Atrás"
        />
      </div>
      <div class="column content-center q-mt-lg">
        <div>
          <PaymentButton @resolution="onPaymentResolution" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { useReservationFormStore } from "src/stores/reservationform";
import SubtotalSummary from "src/components/reservation/view/SubtotalSummary.vue";
import PaymentButton from "src/components/reservation/PaymentButton.vue";
import axios from "axios";

const reservationStore = useReservationFormStore();

export default {
  components: {
    SubtotalSummary,
    PaymentButton,
  },

  data() {
    return {
      subtotalsummary: null,
      progress: 0.75,
    };
  },
  created() {
    let excursionID = reservationStore.excursionID;
    let seats = reservationStore.seats;
    if (seats && excursionID) this.fetchReservaSummary(excursionID, seats);
  },
  methods: {
    fetchReservaSummary(excursionID, seats) {
      axios
        .get(
          `${process.env.API_URL}/excursions/${excursionID}/subtotal/${seats}`
        )
        .then((response) => {
          this.subtotalsummary = response.data;
        })
        .catch((error) => {});
    },
    onPaymentResolution() {
      this.$emit("next");
    },
    goBack() {
      this.$emit("previous");
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

.summary-box {
  padding: 2rem;
  height: fit-content;
  border: 1px solid #ccc;
  border-radius: 10px;
  background-color: $highlight;
}
</style>

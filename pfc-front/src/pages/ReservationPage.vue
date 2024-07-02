<template>
  <SiteHeader :extended="false" />
  <q-page class="row items-start justify-center page-with-header-wrapper page">
    <div>
      <div>
        <component
          :is="currentStepComponent"
          @next="nextStep"
          @previous="previousStep"
        />
      </div>
    </div>
  </q-page>
  <SiteFooter />
</template>

<script>
import SiteHeader from "src/components/SiteHeader.vue";
import ExcursionListing from "../components/reservation/ExcursionListing.vue";
import UserDataForm from "../components/reservation/UserDataForm.vue";
import PaymentForm from "../components/reservation/PaymentForm.vue";
import FinishReservaSummary from "../components/reservation/FinishReservaSummary.vue";
import { useExcursionQueryStore } from "src/stores/excursionquery";
import { useReservationFormStore } from "src/stores/reservationform";
import { Notify } from "quasar";
import SiteFooter from "src/components/SiteFooter.vue";

const queryStore = useExcursionQueryStore();
const reservationStore = useReservationFormStore();

export default {
  components: {
    SiteHeader,
    ExcursionListing,
    UserDataForm,
    PaymentForm,
    FinishReservaSummary,
    SiteFooter,
  },
  data() {
    return {
      currentStep: 1,
      query: null,
      formData: null,
    };
  },
  created() {
    this.query = queryStore.getQuery();
    if (this.query == null) {
      this.$router.push({ path: "/" });
      return;
    }

    reservationStore.clearData();

    this.formData = {
      excursionID: reservationStore.excursionID,
      email: reservationStore.email,
      seats: this.query.seats,
      firstName: reservationStore.firstName,
      lastName: reservationStore.lastName,
    };

    reservationStore.setUserData({
      firstName: this.formData.firstName,
      lastName: this.formData.lastName,
      email: this.formData.email,
    });
    reservationStore.setSeats(this.formData.seats);
    reservationStore.setExcursionID(this.formData.excursionID);
  },
  computed: {
    currentStepComponent() {
      return {
        1: "ExcursionListing",
        2: "UserDataForm",
        3: "PaymentForm",
        4: "FinishReservaSummary",
      }[this.currentStep];
    },
  },
  methods: {
    nextStep() {
      this.formData = {
        excursionID: reservationStore.excursionID,
        email: reservationStore.email,
        seats: reservationStore.seats,
        firstName: reservationStore.firstName,
        lastName: reservationStore.lastName,
      };

      if (this.currentStep == 1 && this.formData.excursionID == null) {
        Notify.create({
          message: "Por favor selecciona una excursión.",
          type: "negative",
        });
        return;
      }

      if (this.currentStep < 4) {
        this.currentStep++;
      }
    },
    previousStep() {
      if (this.currentStep > 1) {
        this.currentStep--;
      }
    },
  },
};
</script>

<script setup>
defineOptions({
  name: "ReservationPage",
});
</script>

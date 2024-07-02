<template>
  <SiteHeader :extended="false" />
  <q-page class="row items-start justify-center page-with-header-wrapper page">
    <div class="summary-box column items-center">
      <div>
        <h2>Mis Reservas</h2>
      </div>
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
import SiteFooter from "src/components/SiteFooter.vue";
import SiteHeader from "src/components/SiteHeader.vue";
import ReservationListing from "src/components/user/ReservationListing.vue";
import ReservationSearchForm from "src/components/user/ReservationSearchForm.vue";
import { useReservationQueryStore } from "src/stores/reservationquery";

const store = useReservationQueryStore();

export default {
  data() {
    return {
      currentStep: 1,
    };
  },
  components: {
    SiteHeader,
    ReservationSearchForm,
    ReservationListing,
    SiteFooter,
  },
  computed: {
    currentStepComponent() {
      return {
        1: "ReservationSearchForm",
        2: "ReservationListing",
      }[this.currentStep];
    },
  },
  methods: {
    nextStep() {
      if (this.currentStep < 2) {
        this.currentStep++;
      }
    },
    previousStep() {
      if (this.currentStep > 1) {
        this.currentStep--;
      }
    },
  },
  created() {
    store.email = null;
  },
};
</script>
<style lang="scss" scoped>
@import "src/css/ReservationPage/box.scss";
</style>

<template>
  <div class="row wrap justify-center q-gutter-lg">
    <div class="summary-box">
      <q-linear-progress stripe size="10px" :value="progress" class="q-mt-md" />
      <h2>Buscando excursiones</h2>
      <div v-if="this.query" class="column justify-start q-pb-sm q-gutter-sm">
        <ul>
          <li v-if="this.query.startDate">
            Desde {{ formatDate(this.query.startDate) }}
          </li>
          <li v-if="this.query.endDate">
            Hasta {{ formatDate(this.query.endDate) }}
          </li>
          <li v-if="this.query.seats">{{ this.query.seats }} plazas</li>
          <li v-if="this.query.location">En {{ this.query.location }}</li>
        </ul>
      </div>
      <div class="column justify-center items-center">
        <q-btn
          @click="goToNext"
          icon-right="fa-solid fa-arrow-right"
          label="Continuar"
        />
      </div>
    </div>
    <div class="listing">
      <h2>Selecciona una excursión</h2>
      <div v-if="excursions.length > 0">
        <div v-for="excursion in excursions" :key="excursion.excursionId">
          <ExcursionListElement
            @select-excursion="handleSelectExcursion"
            :excursion="excursion"
            :isSelected="excursion.excursionId === selectedExcursionID"
          />
        </div>
      </div>
      <div v-else>
        <h3>No se encontraron excursiones</h3>
      </div>
    </div>
  </div>
</template>

<script>
import ExcursionListElement from "src/components/reservation/view/ExcursionListElement.vue";
import axios from "axios";
import { useReservationFormStore } from "src/stores/reservationform";
import { useExcursionQueryStore } from "src/stores/excursionquery";

const queryStore = useExcursionQueryStore();
const store = useReservationFormStore();

export default {
  components: {
    ExcursionListElement,
  },
  data() {
    return {
      excursions: [],
      query: null,
      selectedExcursionID: null,
      progress: 0.25,
    };
  },
  created() {
    this.fetchExcursions();
    this.selectedExcursionID = store.excursionID;
  },
  methods: {
    formatDate(dateString) {
      const options = { year: "numeric", month: "long", day: "numeric" };
      return new Date(dateString).toLocaleDateString(undefined, options);
    },
    goToNext() {
      this.$emit("next");
    },
    fetchExcursions() {
      this.query = queryStore.getQuery();
      if (this.query == null) {
        return;
      }
      axios
        .get(`${process.env.API_URL}/excursions`, {
          params: {
            startDate: this.query.startDate,
            endDate: this.query.endDate,
            city: this.query.location,
          },
        })
        .then((response) => {
          this.excursions = response.data;
        })
        .catch((error) => {
          if (error.message === "Network Error") {
            this.$router.push({
              name: "ErrorPage",
              query: { code: 500 },
            });
          }
        });
    },
    handleSelectExcursion(excursionID) {
      this.selectedExcursionID = excursionID;

      axios
        .get(`${process.env.API_URL}/excursions/${excursionID}`)
        .then((response) => {
          if (response.status === 404) {
            this.$router.push({
              name: "ErrorPage",
              query: { code: 404 },
            });
            return;
          }
          store.setExcursion(response.data);
        })
        .catch((error) => {
          if (error.message === "Network Error") {
            this.$router.push({
              name: "ErrorPage",
              query: { code: 500 },
            });
          }
        });

      if (excursionID !== null) {
        store.setExcursionID(excursionID);
      }
    },
  },
};
</script>

<style lang="scss" scoped>
@import "src/css/ReservationPage/box.scss";
</style>

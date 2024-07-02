<template>
  <div class="form-wrapper">
    <div class="box">
      <div class="form-container wrap column justify-center items-center">
        <form
          @submit.prevent="submitForm"
          class="row wrap justify-center items-center q-gutter-md"
        >
          <div class="form-element">
            <q-input
              v-model.number="seats"
              type="number"
              filled
              label="Plazas"
              style="max-width: 200px"
              @change="clampSeats"
              :min="1"
            />
          </div>
          <div class="form-element">
            <q-input filled v-model="location" label="Ciudad">
              <template v-slot:prepend>
                <q-icon name="place" />
              </template>
              <template v-slot:append>
                <q-icon
                  name="close"
                  @click="location = null"
                  class="cursor-pointer"
                />
              </template>
            </q-input>
          </div>
          <div class="form-element">
            <q-input
              v-model="startDate"
              filled
              type="date"
              label="Fecha Inicial"
            />
          </div>
          <div class="form-element">
            <q-input v-model="endDate" filled type="date" label="Fecha Final" />
          </div>

          <div class="form-element q-pl-md">
            <q-btn
              label="Buscar"
              type="submit"
              color="primary"
              padding="15px 50px"
            />
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import { useExcursionQueryStore } from "src/stores/excursionquery";

const store = useExcursionQueryStore();
export default {
  data() {
    let today = new Date().toISOString().substr(0, 10);
    return {
      startDate: today,
      endDate: null,
      seats: 1,
      location: null,
    };
  },
  methods: {
    clampSeats() {
      this.seats = this.seats < 1 ? Math.abs(this.seats) || 1 : this.seats;
    },
    async submitForm() {
      store.setQuery({
        startDate: this.startDate,
        endDate: this.endDate,
        seats: Math.max(1, this.seats),
        location: this.location,
      });
      this.$router.push({ name: "ReservationPage" });
    },
  },
};
</script>

<style scoped>
.form-wrapper {
  position: relative;
  width: 100%;
  margin: 0 auto;
  padding: 0 16px;
  box-sizing: border-box;
  min-width: 280px;
  max-width: 1168px;
}
.box {
  border-radius: 20px;
  background-color: white;
  box-shadow: rgba(0, 0, 0, 0.15) 0px 5px 15px;
}

.form-container {
  display: flex;
  flex-direction: row;
  padding: 2rem 1rem;
}

.form-element {
  display: flex;
  align-items: center;
}
</style>

<template>
  <div class="excursion">
    <h3>{{ excursion.ciudad }}</h3>
    <p class="description">{{ excursion.descripcion }}</p>
    <div class="row wrap justify-between">
      <div class="dates">
        <p>Fecha de Inicio: {{ formatDate(excursion.fechaInicio) }}</p>
        <p v-if="showSeats">Plazas Libres: {{ excursion.numPlazas }}</p>
      </div>
      <div class="price self-end">
        <p>{{ formatPrice(excursion.precio) }} €</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ExcursionSummary",
  methods: {
    formatDate(dateString) {
      const options = { year: "numeric", month: "long", day: "numeric" };
      return new Date(dateString).toLocaleDateString(undefined, options);
    },
    formatPrice(price) {
      return new Intl.NumberFormat("es-ES", {
        style: "decimal",
        minimumFractionDigits: 2,
      }).format(price);
    },
  },
  props: {
    excursion: {
      type: Object,
      required: true,
    },
    showSeats: {
      type: Boolean,
      default: false,
    },
  },
};
</script>

<style lang="scss" scoped>
@import "src/css/ReservationPage/excursion.scss";
</style>

<template>
  <div class="reserva">
    <h3>{{ summary.ciudad }}</h3>
    <div class="row wrap justify-between q-pt-lg q-pb-sm">
      <div class="dates">
        <p>Fecha de la excursión: {{ formatDate(summary.fechaInicio) }}</p>
        <p>Plazas reservadas: {{ summary.reserva.numPlazas }}</p>
        <p>Total: {{ formatPrice(summary.reserva.precio) }}€</p>
      </div>
      <div class="price self-end">
        <p>Reservado a: {{ summary.reserva.email }}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ReservaView",
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
    summary: {
      type: Object,
      required: true,
    },
  },
};
</script>

<style scoped>
@import "src/css/ReservationPage/reserva.scss";
</style>

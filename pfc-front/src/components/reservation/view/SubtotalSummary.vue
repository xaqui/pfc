<template>
  <div class="excursion selected bg-white">
    <h3>{{ summary.excursion.ciudad }}</h3>
    <p class="description">{{ summary.excursion.descripcion }}</p>
    <div class="row wrap justify-between">
      <div class="dates">
        <p>Fecha: {{ formatDate(summary.excursion.fechaInicio) }}</p>
        <p>
          Estas reservando: {{ summary.seats }} plaza{{
            summary.seats > 1 ? "s" : ""
          }}
        </p>
      </div>
      <div class="price self-end">
        <p>Subtotal: {{ formatPrice(summary.subtotal) }}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "SubtotalSummary",
  props: {
    summary: {
      type: Object,
      required: true,
    },
  },
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
};
</script>

<style scoped>
@import "src/css/ReservationPage/excursion.scss";
</style>

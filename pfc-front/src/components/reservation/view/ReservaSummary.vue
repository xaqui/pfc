<template>
  <div class="subtotal-summary">
    <h3>{{ summary.excursion.ciudad }}</h3>
    <p>{{ summary.excursion.descripcion }}</p>
    <p>Start Date: {{ summary.excursion.fechaInicio }}</p>
    <p>You are reserving: {{ summary.seats }} Seats</p>
    <p>Subtotal: {{ summary.subtotal }}</p>
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
};
</script>

<style scoped>
.subtotal-summary {
  border: 1px solid #ccc;
  padding: 10px;
  margin-bottom: 10px;
}
</style>

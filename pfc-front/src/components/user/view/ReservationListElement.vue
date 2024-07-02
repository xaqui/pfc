<template>
  <div class="reserva size">
    <h3>{{ reservation.excursion.ciudad }}</h3>
    <div class="row wrap justify-between q-pt-lg q-pb-sm">
      <div class="dates">
        <p>
          Fecha de la excursión:
          {{ formatDate(reservation.excursion.fechaInicio) }}
        </p>
        <p>Fecha de Reserva: {{ formatDate(reservation.fechaReserva) }}</p>
        <p>Plazas reservadas: {{ reservation.numPlazas }}</p>
        <p>Total: {{ formatPrice(reservation.precio) }}€</p>
      </div>
      <div class="self-end">
        <div v-if="!reservation.fechaCancelacion" class="">
          <q-btn @click="cancelarReserva" label="CANCELAR" />
          <p v-if="showCancel">
            ¿Cancelar Reserva?
            <q-btn @click="confirmarCancelacion" label="SI" />
          </p>
        </div>
        <div v-else>
          <p>Estado: <span class="text-negative">Cancelada</span></p>
          <p>
            Fecha Cancelacion: {{ formatDate(reservation.fechaCancelacion) }}
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ReservationListElement",
  data() {
    return {
      showCancel: false,
    };
  },
  props: {
    reservation: {
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
    cancelarReserva() {
      this.showCancel = !this.showCancel;
    },
    confirmarCancelacion() {
      this.$emit("cancel-reservation", this.reservation.reservaId);
    },
  },
};
</script>

<style scoped>
@import "src/css/ReservationPage/reserva.scss";
</style>

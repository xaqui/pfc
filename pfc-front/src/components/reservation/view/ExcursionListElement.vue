<template>
  <div class="excursion size" :class="{ selected: isSelected }">
    <h3>{{ excursion.ciudad }}</h3>
    <p class="description">{{ excursion.descripcion }}</p>
    <div class="row wrap justify-between">
      <div class="dates">
        <p>{{ formatDate(excursion.fechaInicio) }}</p>
        <p>Plazas Libres: {{ excursion.numPlazas }}</p>
      </div>
      <div class="price self-end">
        <p>{{ formatPrice(excursion.precio) }} €</p>
      </div>
    </div>
    <div class="row wrap justify-end">
      <q-btn
        @click="selectExcursion"
        padding="5px 30px"
        label="Seleccionar"
        :class="{ 'bg-primary': !isSelected, 'text-white': !isSelected }"
      />
    </div>
  </div>
</template>

<script>
export default {
  name: "ExcursionListElement",
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
    selectExcursion() {
      this.$emit("select-excursion", this.excursion.excursionId);
    },
  },
  props: {
    excursion: {
      type: Object,
      required: true,
    },
    isSelected: {
      type: Boolean,
      default: false,
    },
  },
};
</script>

<style lang="scss" scoped>
@import "src/css/ReservationPage/excursion.scss";
</style>

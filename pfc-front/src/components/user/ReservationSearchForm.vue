<template>
  <div class="text-center">
    <p>Introduce tu dirección de correo electrónico para ver tus reservas.</p>
  </div>
  <q-form
    @submit.prevent="goToNext"
    class="row items-center justify-center q-gutter-md"
  >
    <div>
      <q-input
        outlined=""
        v-model.trim="queryEmail"
        label="Correo electrónico"
        :rules="[(val) => !!val || 'Este campo es necesario']"
        type="email"
      >
        <template v-slot:prepend>
          <q-icon name="mail" />
        </template>
      </q-input>
    </div>
    <div class="self-start q-pt-sm">
      <q-btn type="Submit" icon="fa-solid fa-search" label="Buscar" />
    </div>
  </q-form>
</template>

<script>
import { useReservationQueryStore } from "src/stores/reservationquery";

const store = useReservationQueryStore();

export default {
  emits: ["next", "previous"],
  data() {
    return {
      queryEmail: null,
    };
  },
  methods: {
    goToNext() {
      store.email = this.queryEmail;
      this.$emit("next");
    },
  },
  created() {
    store.email = null;
  },
};
</script>

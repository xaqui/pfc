import { defineStore } from "pinia";

export const useReservationQueryStore = defineStore({
  id: "reservationquery",
  state: () => ({
    email: null,
  }),
  actions: {
    setEmail(email) {
      this.email = email;
    },
  },
});

import { defineStore } from "pinia";

export const useReservationFormStore = defineStore({
  id: "reservationform",
  state: () => ({
    excursionID: null,
    seats: null,
    email: null,
    firstName: null,
    lastName: null,
    selectedExcursion: null,
  }),
  actions: {
    setExcursionID(excursionID) {
      this.excursionID = excursionID;
    },
    setExcursion(excursion) {
      this.selectedExcursion = excursion;
    },
    setSeats(seats) {
      this.seats = seats;
    },
    setUserData({ email, firstName, lastName }) {
      this.email = email;
      this.firstName = firstName;
      this.lastName = lastName;
    },
    clearData() {
      this.excursionID = null;
      this.selectedExcursion = null;
      this.email = null;
      this.seats = null;
      this.firstName = null;
      this.lastName = null;
    },
  },
});

import { defineStore } from "pinia";

export const useExcursionQueryStore = defineStore({
  id: "excursionquery",
  state: () => ({
    querydata: null,
  }),
  actions: {
    setQuery({ startDate, endDate, seats, location }) {
      this.querydata = {
        startDate: startDate,
        endDate: endDate,
        seats: seats,
        location: location,
      };
    },
    getQuery() {
      return this.querydata;
    },
  },
});

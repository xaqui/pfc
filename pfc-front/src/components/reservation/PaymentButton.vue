<template>
  <q-btn
    :loading="progress[0].loading"
    :percentage="progress[0].percentage"
    dark-percentage
    icon="fa-regular fa-credit-card"
    unelevated
    color="orange"
    text-color="grey-9"
    @click="startComputing(0)"
    style="width: 300px"
    label="Procesar Pago"
    padding="md"
  >
  </q-btn>
</template>

<script>
export default {
  data() {
    return {
      progress: [{ loading: false, percentage: 0 }],
      intervals: [null],
    };
  },
  methods: {
    startComputing(id) {
      this.progress[id].loading = true;
      this.progress[id].percentage = 0;

      this.intervals[id] = setInterval(() => {
        this.progress[id].percentage += Math.floor(Math.random() * 8 + 10);
        if (this.progress[id].percentage >= 100) {
          clearInterval(this.intervals[id]);
          this.progress[id].loading = false;
          this.$emit("resolution");
        }
      }, 300);
    },
  },
  beforeUnmount() {
    this.intervals.forEach((val) => {
      clearInterval(val);
    });
  },
};
</script>

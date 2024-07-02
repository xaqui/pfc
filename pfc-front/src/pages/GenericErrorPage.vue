<template>
  <div
    class="fullscreen text-white text-center q-pa-md flex flex-center bg-primary"
  >
    <ErrorInfo :error-code="this.errorCode" />
  </div>
</template>

<script>
import ErrorInfo from "src/components/error/ErrorInfo.vue";

export default {
  data() {
    return {
      errorCode: null,
    };
  },
  components: {
    ErrorInfo,
  },
  created() {
    this.errorCode = this.$route.query.code;
    if (this.errorCode == null) {
      this.$router.push({ path: "/" });
      return;
    }
  },
};
</script>

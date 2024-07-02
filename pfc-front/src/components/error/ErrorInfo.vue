<template>
  <div>
    <div v-if="errorCode">
      <img
        v-if="images[errorCode]"
        :src="images[errorCode]"
        :alt="errorCode"
        class="error-img"
      />

      <h1 class="error-header">Error {{ errorCode }}</h1>
      <div class="error-subheader" style="opacity: 0.4">
        {{ codeMessages[errorCode] }}
      </div>
    </div>
    <div v-else class="error-subheader" style="opacity: 0.4">
      Something went wrong.
    </div>

    <q-btn
      class="q-mt-xl"
      color="white"
      text-color="primary"
      unelevated
      to="/"
      label="Volver al Inicio"
      no-caps
    />
  </div>
</template>

<script>
import image403 from "src/assets/response-codes/403 Forbidden.png";
import image404 from "src/assets/response-codes/404 NotFound.png";
import image503 from "src/assets/response-codes/503 ServiceUnavailable.png";
import image500 from "src/assets/response-codes/500 InternalServerError.png";

export default {
  props: {
    errorCode: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      images: {
        403: image403,
        404: image404,
        503: image503,
        500: image500,
      },
      codeMessages: {
        403: "Forbidden",
        404: "Not Found",
        409: "Conflict",
        503: "Service Unavailable",
        500: "Internal Server Error",
      },
    };
  },
};
</script>

<style scoped>
.error-header {
  font-size: 5em;
  margin: 0;
  padding: 0;
}

.error-subheader {
  font-size: 2em;
  margin: 0;
  padding: 0;
}

.error-img {
  width: 60vh;
  min-width: 20rem;
  height: auto;
  margin: 0 auto;
  display: block;
}
</style>

<template>
  <SiteHeader :extended="true" />
  <q-page class="column justify-center page-with-header-wrapper page q-pb-xl">
    <div class="row justify-center">
      <IndexForm />
    </div>
    <div>
      <div class="flex content-main">
        <section>
          <h2>Reserva de plazas en excursiones</h2>

          <p>
            Descubre el mundo con Excursiones DAM, tu compañía de confianza para
            reservar plazas en emocionantes excursiones por todo el mundo.
            Gracias a nuestra plataforma fácil de usar, puedes explorar y
            comparar diferentes opciones de viaje para encontrar la aventura
            perfecta.
          </p>
          <div class="q-py-lg">
            <q-carousel
              animated
              v-model="slide"
              navigation
              infinite
              :autoplay="autoplay"
              arrows
              transition-prev="slide-right"
              transition-next="slide-left"
              @mouseenter="autoplay = false"
              @mouseleave="autoplay = true"
              class="border-radius"
            >
              <q-carousel-slide
                :name="1"
                img-src="~assets/img/carousel-1.jpg"
              />
              <q-carousel-slide
                :name="2"
                img-src="~assets/img/carousel-2.jpg"
              />
              <q-carousel-slide
                :name="3"
                img-src="~assets/img/carousel-3.jpg"
              />
              <q-carousel-slide
                :name="4"
                img-src="~assets/img/carousel-4.jpg"
              />
              <q-carousel-slide
                :name="5"
                img-src="~assets/img/carousel-5.jpg"
              />
            </q-carousel>
          </div>
        </section>

        <section>
          <h3>Explora el mundo con Excursiones DAM</h3>

          <p>
            Con Excursiones DAM, no solo disfrutarás de viajes inolvidables,
            sino que también contribuirás al medio ambiente al optar por una
            forma de viajar más sostenible y cómoda. Evita las complicaciones de
            los aeropuertos y disfruta de un viaje directo desde el centro de
            Vigo a tus destinos favoritos.
          </p>

          <div class="buttons row justify-center items-center q-gutter-md">
            <q-btn color="primary" label="Reserva tus excursiones" to="/" />
            <q-btn color="primary" label="Destinos Populares" to="/" />
            <q-btn
              color="primary"
              label="Servicios de Excursiones DAM"
              to="/"
            />
            <q-btn color="primary" label="Contáctanos" to="/" />
          </div>
        </section>

        <section>
          <h2>Reserva tus excursiones con Excursiones DAM</h2>

          <p>
            Organiza tus aventuras fácilmente con Excursiones DAM. Explora una
            amplia gama de destinos y actividades, desde excursiones cortas
            hasta viajes épicos alrededor del mundo. Nuestro sistema de reservas
            te permite comparar opciones y asegurar tu lugar con unos pocos
            clics.
          </p>

          <p>
            <b>COMPARA...</b> <br />Encuentra las mejores ofertas y destinos
            para tu próximo viaje con Excursiones DAM. Compara precios,
            itinerarios y actividades para personalizar tu experiencia según tus
            preferencias.
          </p>

          <p>
            <b>...RESERVA...</b> <br />Una vez seleccionada tu excursión ideal,
            reserva fácilmente con Excursiones DAM. Aceptamos diversos métodos
            de pago seguros para tu comodidad.
          </p>

          <p>
            <b>...¡VIAJA!</b> <br />Prepárate para una aventura única con
            Excursiones DAM. Desde la salida hasta el regreso, garantizamos una
            experiencia sin preocupaciones con notificaciones en tiempo real y
            asistencia durante todo el viaje.
          </p>
        </section>

        <section>
          <h3>Descubre las mejores excursiones</h3>
          <p>
            Excursiones DAM te ofrece acceso a experiencias inolvidables tanto
            en España como en destinos internacionales. Descubre nuevos
            horizontes con nuestra amplia selección de excursiones por Europa y
            más allá.
          </p>
        </section>

        <section>
          <h3>Excursiones por todo el mundo</h3>

          <p>
            Explora Europa y más allá con Excursiones DAM. Desde las icónicas
            capitales europeas hasta destinos exóticos, nuestras excursiones te
            llevan a lugares impresionantes y culturas diversas.
          </p>

          <p>
            Disfruta de vistas panorámicas, experiencias únicas y momentos
            memorables en cada excursión. Con Excursiones DAM, cada viaje es una
            oportunidad para descubrir y conectar con el mundo que te rodea.
          </p>
        </section>
      </div>
    </div>
  </q-page>
  <footer>
    <SiteFooter />
  </footer>
</template>

<script>
import { defineComponent, ref } from "vue";
import SiteHeader from "src/components/SiteHeader.vue";
import SiteFooter from "src/components/SiteFooter.vue";
import IndexForm from "src/components/index/IndexForm.vue";

export default defineComponent({
  name: "IndexPage",
  components: {
    SiteHeader,
    SiteFooter,
    IndexForm,
  },
  setup() {
    return {
      slide: ref(1),
      autoplay: ref(true),
    };
  },
});
</script>

<style lang="scss">
@import "src/css/fonts.scss";

.content-main {
  max-width: 1000px;
  margin: 5rem auto;
  padding: 1rem 2rem;
  box-shadow: rgba(50, 50, 93, 0.01) 0px 30px 60px -12px,
    rgba(0, 0, 0, 0.1) 0px 18px 36px -18px;
  border-radius: 10px;
  background-color: $highlight;
}

.content-main h1,
h2,
h3,
h4,
h5,
h6 {
  font-family: "Rubik", sans-serif;
  text-align: center;
}

.content-main h2 {
  font-size: 3rem;
  font-weight: 300;
}

.content-main h3 {
  font-size: 2.5rem;
  line-height: 3.5rem;
  font-weight: 300;
}

.content-main p {
  font-size: 0.9rem;
}

.content-main > section:not(:last-child) {
  border-bottom: 1px solid lightgray;
  padding-bottom: 3rem;
}

.border-radius {
  border-radius: 20px;
}
</style>

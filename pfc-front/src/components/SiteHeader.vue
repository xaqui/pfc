<template>
  <div class="nav-wrapper">
    <div class="nav-bar">
      <div class="nav-content no-wrap row justify-between q-pt-lg">
        <div class="site-logo">
          <img
            src="~assets/img/logo.png"
            width="250rm"
            height="auto"
            alt="Site Logo"
          />
        </div>
        <div>
          <div v-if="$q.screen.gt.xs" class="row no-wrap q-gutter-md">
            <div class="nav-link">
              <router-link to="/"
                ><q-icon name="fa-solid fa-house" /><span>Home</span>
              </router-link>
            </div>
            <div class="nav-link">
              <router-link to="/my-reservations"
                ><q-icon name="fa-solid fa-user" /><span
                  >Mis Reservas</span
                ></router-link
              >
            </div>
          </div>
          <div v-else>
            <q-btn color="cyan-8" icon="fa-solid fa-bars" padding="5px 10px">
              <q-menu class="hamburger-menu">
                <q-list style="min-width: 100px">
                  <q-item clickable v-close-popup class="menu-item">
                    <q-item-section>
                      <router-link to="/">Home</router-link></q-item-section
                    >
                  </q-item>
                  <q-item clickable v-close-popup class="menu-item">
                    <q-item-section
                      ><router-link to="/my-reservations"
                        >Mis Reservas</router-link
                      ></q-item-section
                    >
                  </q-item>
                </q-list>
              </q-menu>
            </q-btn>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="fancy-header">
    <div v-if="extended" class="content">
      <h1>Reserva Excursiones</h1>
      <p>Reserva plaza en excursiones por todo el mundo</p>
    </div>
  </div>
  <div class="filler">&nbsp;</div>
</template>

<script>
export default {
  name: "SiteHeader",
  props: {
    extended: {
      type: Boolean,
      required: true,
    },
  },
};
</script>

<style lang="scss">
@import "src/css/fonts.scss";

.fancy-header {
  min-height: 200px;
  height: 36vh;

  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  background-color: $primary;
  z-index: -1;
  display: flex;
  align-items: center;
}

.fancy-header .content {
  width: 100%;
  padding: 0 32px;
  min-width: 280px;
  max-width: 1168px;
  margin: 0 auto;
  font-family: "Rubik", sans-serif;
}

.fancy-header .content h1 {
  font-size: 40px;
  line-height: 48px;
  color: #fff;
  margin: 0;
  font-size: 3rem;
  font-weight: 700;
}
.fancy-header .content p {
  padding-top: 1rem;
  font-size: 1.4rem;
  line-height: 48px;
  color: #fff;
  margin: 0;
  font-weight: 400;
  line-height: 28px;
}

@media (max-width: 600px) {
  .fancy-header .content h1 {
    font-size: 2rem;
  }
  .fancy-header .content p {
    font-size: 1rem;
  }
}

.nav-bar {
  position: relative;
  width: 100%;
  margin: 0 auto;
  padding: 0 16px;
  box-sizing: border-box;
  min-width: 280px;
  padding: 0 16px;
  max-width: 1168px;
}
.nav-wrapper {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  z-index: 13;
}
.nav-content {
  color: #fff;
  height: 72px;
  align-items: center;
}

.filler {
  height: 25vh;
  min-height: 200px;
  color: #000;
}

.nav-link {
  font-size: 1rem;
  font-weight: 500;
}

.nav-link a {
  color: #fff;
  text-decoration: none;
}

.nav-link a:hover {
  color: #f5f5f5;
  text-decoration: underline;
}

.nav-link span {
  margin-left: 0.5rem;
}

.hamburger-menu a {
  text-decoration: none;
  color: #000;
}

.hamburger-menu a:hover {
  color: #333;
  text-decoration: underline;
}
</style>

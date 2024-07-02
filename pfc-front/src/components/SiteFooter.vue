<template>
  <div class="footer">
    <div class="wrapper">
      <div class="bar">
        <div class="row wrap justify-start items-center">
          <div class="footer-item">
            <img
              src="~assets/img/logo-empresa.png"
              width="auto"
              height="30rm"
              alt="Site Logo"
            />
          </div>
          <div class="footer-item">
            <img
              src="~assets/img/logo.png"
              width="auto"
              height="40rm"
              alt="Site Logo"
            />
          </div>
          <div class="row wrap justify-start items-center">
            <div class="footer-item">
              <a href="/">Información Legal</a>
            </div>
            <div class="footer-item">
              <a href="/">Privacidad y cookies</a>
            </div>
            <div class="footer-item">
              <p>© Excursiones DAM - Imágenes cedidas por Pixabay</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "SiteFooter",
};
</script>

<style lang="scss">
.d-flex {
  display: flex;
}
.footer-item {
  margin-right: 20px;
}
.wrapper {
  width: 100%;
  padding: 20px;
  background-color: #333;
}

.bar {
  position: relative;
  width: 100%;
  margin: 0 auto;
  padding: 0 16px;
  box-sizing: border-box;
  min-width: 280px;
  padding: 0 16px;
  max-width: 1168px;
}

.footer {
  padding: 0;
  margin: 0;
  text-align: center;

  bottom: 0;
  width: 100%;
  color: white;
}

.footer a {
  color: white;
  text-decoration: none;
}

.footer a:hover {
  color: #f5f5f5;
  text-decoration: underline;
}

.footer-item p {
  padding: 0;
  margin: 0;
}
</style>

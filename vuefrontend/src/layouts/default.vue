<template>
  <v-app>
    <div class="screen" v-if="loginStatus == 'true'">
      <Sidebar />
      <v-main class="main">
        <v-container fluid>
          <router-view></router-view>
        </v-container>
      </v-main>
    </div>
    <div class="screen d-flex justify-center align-center" v-else>
      <img src="../assets/undraw_secure_login_pdn4.svg" alt="could not load image please check your internet connection" height="300">
      <div>
        <h1 class="ml-3">You haven't log-in yet </h1>
        <v-btn to="/" text>Click here to Login</v-btn>
      </div>
    </div>
  </v-app>
</template>

<script>
import Sidebar from "../components/Sidebar";
import VueCookies from "vue-cookies";
import Vue from "vue";

Vue.use(VueCookies);
export default {
  name: "App",
  data: () => ({
    loginStatus: Boolean,
  }),
  components: {
    Sidebar,
  },
  mounted() {
    this.loginStatus = Vue.$cookies.get("ISLOGIN");
    console.log(this.loginStatus);
  },
};
</script>
<style scoped>
.screen {
  height: 100%;
}
.image {
  width: 25%;
  background-image: url(../assets/undraw_secure_login_pdn4.svg);
  background-size: contain;
  height: 0;
  margin-top: 170px;
  padding: 0; /* reset */
  padding-bottom: calc(50% * 9 / 16);
}
</style>
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
    <div v-else>
      <v-btn block plain x-large>PLEASE LOGIN</v-btn>
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
</style>
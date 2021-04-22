<template>
  <div id="app">
    <vue-extend-layouts />
  </div>
</template>

<script>
import axios from "@/axios/axios";
import VueCookies from "vue-cookies";
import VueExtendLayouts from "vue-extend-layout";
import Vue from "vue";

Vue.use(VueCookies);
export default {
  name: "App",
  components: { VueExtendLayouts },

  mounted() {
    Vue.$cookies.config("1d");
    const JWT = Vue.$cookies.get("JWT");
    if (JWT) {
      (axios.defaults.headers.common["Authorization"] = "Bearer " + JWT);
    }
  },
};
</script>
<style>
.dash-topic {
  font-size: 1.5em;
  font-weight: bold;
}
</style>
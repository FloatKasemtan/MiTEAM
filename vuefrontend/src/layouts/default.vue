<template>
  <v-app>
    <div class="screen">
      <Sidebar />
      <v-main class="main">
        <v-container fluid>
          <router-view></router-view>
        </v-container>
      </v-main>
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
    
  }),
  components: {
    Sidebar,
  },
  async mounted() {
  },
};
</script>
<style scoped>
.screen {
  height: 100%;
}
</style>
<template>
  <v-lazy
    v-model="isActive"
    :options="{
      threshold: 0.1,
    }"
    transition="fade-transition"
  >
    <div class="mx-8">
      <Header :pageName="pageName" />
      <v-row>
        <v-col cols="12" md="4">
          <Schedule />
        </v-col>
        <v-col cols="12" md="8">
          <Graph />
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12" md="6">
          <LateEmployee />
        </v-col>
        <v-col cols="12" md="6">
          <Overtimer />
        </v-col>
      </v-row>
    </div>
  </v-lazy>
</template>

<script>
import VueCookies from "vue-cookies";
import Vue from "vue";

Vue.use(VueCookies);
export default {
  data: () => ({
    pageName: "Dashboard",
    loginStatus: ""
  }),
  components: {
    Header:() => import("../components/Header"),
    Schedule:() => import("../components/Dashboard/Schedule"),
    Graph:() => import("../components/Dashboard/Graph"),
    LateEmployee:() => import("../components/Dashboard/LateEmployee"),
    Overtimer:() => import("../components/Dashboard/Overtimer"),
  },
  async mounted() {
    this.$store.dispatch("loadEmployeesData");
    this.$store.dispatch('loadTeamData');
    this.$store.dispatch('loadEventData');
  },
};
</script>

<style scoped>
</style>
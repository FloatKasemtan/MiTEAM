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
import Header from "../components/Header";
import Schedule from "../components/Dashboard/Schedule";
import Graph from "../components/Dashboard/Graph";
import LateEmployee from "../components/Dashboard/LateEmployee";
import Overtimer from "../components/Dashboard/Overtimer";
import axios from "../axios/axios";
export default {
  data: () => ({
    pageName: "Dashboard",
  }),
  components: {
    Header,
    Schedule,
    Graph,
    LateEmployee,
    Overtimer,
  },
  async mounted(){
    const response = await axios.get('/employee/listAll',);
    console.log(response.data)
  }
};
</script>

<style scoped>
</style>
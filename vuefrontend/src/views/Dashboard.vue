<template>
  <div class="mx-8">
    <Header :pageName="pageName" />
    
    <div class="d-flex">
      <Schedule/>
      <Graph/>
    </div>
    <div class="d-flex">
      <LateEmployee/>
      <Overtimer/>
    </div>
  </div>
</template>

<script>
import Header from "../components/Header";
import Schedule from "../components/Dashboard/Schedule"
import Graph from "../components/Dashboard/Graph"
import LateEmployee from "../components/Dashboard/LateEmployee"
import Overtimer from "../components/Dashboard/Overtimer"
export default {
  data:() => ({
    pageName: "Dashboard"
  }),
  components: {
    Header,
    Schedule,
    Graph,
    LateEmployee,
    Overtimer
  },

};
</script>

<style scoped>

</style>
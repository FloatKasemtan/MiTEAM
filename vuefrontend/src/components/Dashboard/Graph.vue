<template>
  <div>
    <v-card class="rounded-xl" color="#426ca6">
      <v-card-title class="ml-8 dash-topic graphHead"
        >Overall Work Progress of each team</v-card-title
      >
        <v-sparkline
          :gradient="gradient"
          :labels="this.$store.state.teams.map((team) => team.progress + '%')"
          label-size="4"
          line-width="1px"
          smooth
          padding="10"
          color="white"
          :value="this.$store.state.teams.map((team) => team.progress)"
          auto-draw
          ></v-sparkline
        >
    </v-card>
  </div>
</template>

<script>
export default {
  data: () => ({}),
};
</script>

<style scoped>
.graphHead{
    color: white;
}
.valueNum{
    font-size: 0.5em;
}
</style>
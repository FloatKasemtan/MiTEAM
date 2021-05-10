<template>
  <div>
    <v-card class="rounded-xl" color="#f6f6f6">
      <v-card-title class="ml-8 dash-topic"
        >Overall Work Progress of each team</v-card-title
      >
      <v-sparkline
        :labels="this.$store.state.teams.map((team) => team.progress + '%')"
        label-size="4"
        line-width="1px"
        smooth
        padding="10"
        color="#31517D"
        :value="this.$store.state.teams.map((team) => team.progress)"
        auto-draw
        auto-draw-duration="1000"
      ></v-sparkline>
    </v-card>
  </div>
</template>

<script>
export default {
  data: () => ({}),
};
</script>

<style scoped>
.graphHead {
  color: white;
}
.valueNum {
  font-size: 0.5em;
}
</style>
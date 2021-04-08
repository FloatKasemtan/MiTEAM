<template>
<v-lazy
        v-model="isActive"
        :options="{
          threshold: 0.1,
        }"
        transition="fade-transition"
      >
  <v-card class="mr-8 mb-5 rounded-xl" height="50vh" width="30vw">
    <v-card-title class="ml-8 dash-topic">Up coming Schedule</v-card-title>
    <v-timeline class="ml-10" dense>
      <v-timeline-item
        v-for="message in sortedItems"
        :key="message.name"
        :color="message.finish ? 'red' : 'green'"
      >
        <div class="ml-10">
          <h2 class="day">
            <strong class="dayHead">{{ message.name }}</strong>
          </h2>

          <h5>Team : {{ message.Team }} <br> <div :class="getDateLeft(message.deadLine)=='Late' ? 'late':'' ">Time : {{ getDateLeft(message.deadLine) }}</div></h5>
        </div>
      </v-timeline-item>
    </v-timeline>
  </v-card>
</v-lazy>
</template>

<script>
export default {
  computed: {
    sortedItems: function () {
      this.$store.state.events.sort((a, b) => {
        return new Date(a.deadLine) - new Date(b.deadLine);
      });
      return this.$store.state.events.slice(0, 3);
    },
  },
  methods: {
    getDateLeft(v) {
      var day1 = new Date();
      var day2 = new Date(v);

      var difference = day2 - day1;
      var days = difference / (1000 * 3600 * 24);
      if(days<0){
          return "Late"
      }
      return Math.round(days) + ' day remaining';
    },
  },
};
</script>

<style scoped>
.late {
    color: red;
}
.dayHead {
  font-weight: bold;
}
.day {
  font-weight: lighter;
}
</style>
<template>
  <div class="mx-8">
    <Header :pageName="pageName" />
    <div class="d-flex">
      <v-card class="mr-8 mb-5" height="50vh" width="45vw">
        <div class="ml-8 mb-2 topic">Up coming Schedule</div>
        <v-timeline class="ml-10" dense clipped>
          <v-timeline-item
            v-for="message in sortedItems"
            :key="message.name"
            :color="message.finish ? 'red' : 'green'"
          >
            <div class="ml-10">
              <h1 class="day">
                <strong class="dayHead">{{ message.name }}</strong>
                Team : {{ message.Team }}
              </h1>

              <h4>Time : {{ getDateLeft(message.deadLine) }} day remaining</h4>
            </div>
          </v-timeline-item>
        </v-timeline> </v-card
      ><v-card class="mb-5" height="50vh" width="45vw">
        <div class="ml-8 mb-2 topic">Up coming Schedule</div>
        <v-timeline class="ml-10" dense clipped>
          <v-timeline-item
            v-for="message in sortedItems"
            :key="message.name"
            :color="message.finish ? 'red' : 'green'"
          >
            <div class="ml-10">
              <h1 class="day">
                <strong class="dayHead">{{ message.name }}</strong>
                Team : {{ message.Team }}
              </h1>

              <h4>Time : {{ getDateLeft(message.deadLine) }} day remaining</h4>
            </div>
          </v-timeline-item>
        </v-timeline>
      </v-card>
    </div>
    <div class="d-flex">
      <v-card class="mr-8" height="35vh" width="45vw">
        <div class="ml-8 mb-2 topic">Up coming Schedule</div> </v-card
      ><v-card height="35vh" width="45vw">
        <div class="ml-8 mb-2 topic">Up coming Schedule</div>
      </v-card>
    </div>
  </div>
</template>

<script>
import Header from "../components/Header";
export default {
  data:() => ({
    pageName: "Dashboard"
  }),
  components: {
    Header,
  },
  computed: {
    sortedItems: function () {
      this.$store.state.events.sort((a, b) => {
        return new Date(a.deadLine) - new Date(b.deadLine);
      });
      return this.$store.state.events.slice(0, 4);
    },
  },
  methods: {
    getDateLeft(v) {
      var day1 = new Date();
      var day2 = new Date(v);

      var difference = Math.abs(day2 - day1);
      var days = difference / (1000 * 3600 * 24);
      return Math.round(days);
    },
  },
};
</script>

<style scoped>
.topic {
  font-size: 2em;
  font-weight: bold;
}
.dayHead {
  font-weight: bold;
}
.day {
  font-weight: lighter;
}
</style>
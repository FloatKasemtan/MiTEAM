<template>
  <v-card class="rounded-xl" color="#f6f6f6">
    <v-card-title class="ml-8 dash-topic">Up coming Schedule</v-card-title>
    <v-timeline  class="ml-10" dense style="">
      <v-timeline-item
        v-for="message in sortedItems"
        :key="message.name"
        :color="message.is_finish ? 'green' : 'red'"
      >
        <div class="ml-10">
          <h2 class="day">
            <strong class="dayHead">{{ message.name }}</strong>
          </h2>

          <h5>Team : {{ message.team_name }} <br> <div>{{getDateLeft(message.deadline)}}</div></h5>
        </div>
      </v-timeline-item>
    </v-timeline>
  </v-card>
</template>

<script>
import moment from 'moment'
export default {
  computed: {
    sortedItems: function () {
      this.$store.state.events.sort((a, b) => {
        return new Date(a.deadline) - new Date(b.deadline);
      });
      return this.$store.state.events.slice(0, 3);
    },
  },
  methods: {
    getDateLeft(deadline) {
      return moment(deadline).fromNow();
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
<template>
  <div>
    <v-card height="50vh" width="50vw">
      <v-card-text>
        <div class="ml-8 mb-2 header">Up coming Schedule</div>

        <v-timeline align-top dense>
          <v-timeline-item
            v-for="message in sortedItems.slice(0, 4)"
            :key="message.name"
          >
            <div>
              <h1>
                <strong>{{ message.name }}</strong>
              </h1>
              <h4>{{ message.Team }}</h4>
            </div>
          </v-timeline-item>
        </v-timeline>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
export default {
  data() {},
  computed: {
    sortedItems: function () {
      this.$store.state.events.sort((a, b) => {
        return new Date(a.deadLine) - new Date(b.deadLine);
      });
      return this.$store.state.events;
    },
  },
};
</script>

<style scoped>
.header {
  font-size: 2em;
  font-weight: bold;
}
</style>
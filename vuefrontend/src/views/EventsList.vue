<template>
  <div transition="scale-transition" class="mx-16">
    <Header :pageName="pageName" />
    <v-row>
      <v-col cols="12" md="3">
        <v-text-field
          required
          color="black"
          v-model="event"
          label="Event Name"
          class="ml-5"
        ></v-text-field>
      </v-col>
      <v-col cols="12" md="2">
        <v-menu
          ref="menu1"
          v-model="menu1"
          :close-on-content-click="false"
          transition="scale-transition"
          offset-y
          max-width="290px"
          min-width="auto"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-text-field
              v-model="dateFormatted"
              label="Deadline"
              hint="MM/DD/YYYY"
              persistent-hint
              prepend-icon="mdi-calendar"
              v-bind="attrs"
              @blur="date = parseDate(dateFormatted)"
              v-on="on"
            ></v-text-field>
          </template>
          <v-date-picker
            v-model="date"
            no-title
            @input="menu1 = false"
          ></v-date-picker>
        </v-menu>
      </v-col>
      <v-col cols="12" md="3" class="d-flex"
        ><div class="text-center">
          <v-menu offset-y>
            <template v-slot:activator="{ on, attrs }">
              <v-btn color="#5C6BC0" dark v-bind="attrs" v-on="on" class="mt-3">
                Select Team
              </v-btn>
            </template>
            <v-list>
              <v-list-item
                v-for="(item, index) in this.$store.state.teams.map(
                  (team) => team.name
                )"
                :key="index"
                link
                @click="assignTeam = item"
              >
                <v-list-item-title>{{ item }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </div>
        <v-text-field
          required
          color="black"
          v-model="assignTeam"
          label="Assigned Team"
          class="ml-5"
        ></v-text-field>
      </v-col>
      <v-btn
        :disabled="event == '' || assignTeam == ''"
        class="mt-5 ml-5"
        @click="addEvent"
        >ADD Event</v-btn
      >
    </v-row>
    <div>
      <v-row v-for="event in sortedItems" :key="event.name">
        <v-lazy
          :options="{
            threshold: 0.1,
          }"
          transition="fade-transition"
        >
          <v-card
            @click="changeStatus(event.event_id)"
            width="80vw"
            class="ma-5 eventItem"
            :class="[event.is_finish ? 'finish' : 'unfinish']"
          >
            <v-row>
              <v-col>
                <v-card-title>{{ event.name }}</v-card-title
                ><v-card-subtitle
                  >Start from
                  {{ getFormattedDate(event.start_time) }}</v-card-subtitle
                >
              </v-col>
              <v-col
                ><v-card-title>Deadline</v-card-title
                ><v-card-subtitle>{{
                  getFormattedDate(event.deadline)
                }}</v-card-subtitle></v-col
              >
              <v-col
                ><v-card-title>Team</v-card-title>
                <v-card-subtitle>{{ event.team_name }}</v-card-subtitle>
              </v-col>
              <v-col>
                <v-card-title>Status</v-card-title
                ><v-card-subtitle v-if="event.is_finish"
                  >Finish</v-card-subtitle
                >
                <v-card-subtitle v-else>Unfinish</v-card-subtitle>
              </v-col>
              <v-card-actions class="mb-4">
                <v-col>
                  <v-btn
                    text
                    depressed
                    @click="deleteEvent(event.event_id)"
                    color="error"
                  >
                    Delete
                  </v-btn>
                </v-col></v-card-actions
              >
            </v-row>
          </v-card>
        </v-lazy>
      </v-row>
    </div>
  </div>
</template>

<script>
import axios from "@/axios/axios";
import moment from "moment";
export default {
  components: {
    Header: () => import("../components/Header"),
  },
  data: (vm) => ({
    pageName: "Events",
    items: [],
    valid: false,
    date: new Date().toISOString().substr(0, 10),
    dateFormatted: vm.formatDate(new Date().toISOString().substr(0, 10)),
    menu1: false,
    event: "",
    assignTeam: "",
  }),
  computed: {
    computedDateFormatted() {
      return this.formatDate(this.date);
    },
    sortedItems: function () {
      this.$store.state.events.sort((a, b) => {
        return new Date(a.deadline) - new Date(b.deadline);
      });
      return this.$store.state.events;
    },
  },

  watch: {
    date(val) {
      this.dateFormatted = this.formatDate(this.date);
    },
  },

  methods: {
    getFormattedDate(time) {
      return moment(time).format("LL");
    },

    formatDate(date) {
      if (!date) return null;

      const [year, month, day] = date.split("-");
      return `${month}/${day}/${year}`;
    },
    parseDate(date) {
      if (!date) return null;

      const [month, day, year] = date.split("/");
      return `${year}-${month.padStart(2, "0")}-${day.padStart(2, "0")}`;
    },
    async addEvent() {
      await axios.post(
        "/event/insert?name=" +
          this.event +
          "&start=" +
          Date.parse(new Date().toISOString().substr(0, 10)) +
          "&deadline=" +
          Date.parse(this.dateFormatted) +
          "&team_name=" +
          this.assignTeam
      );
      location.reload();
    },
    async changeStatus(id) {
      window.location.reload();
      await axios.put("/event/update?id=" + id);
    },
    async deleteEvent(id) {
      await axios.delete("/event/delete?id=" + id);
      location.reload();
    },
  },
  mounted() {
    this.$store.dispatch("loadEventData");
    this.$store.dispatch("loadTeamData");
  },
};
</script>

<style scoped>
.finish {
  border-left: 10px solid green;
}
.unfinish {
  border-left: 10px solid red;
}
.eventItem {
  transition: 0.3s ease-out;
}
.eventItem:hover {
  transform: scale(1.02);
}
</style>
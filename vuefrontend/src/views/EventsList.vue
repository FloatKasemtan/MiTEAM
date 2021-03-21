<template>
  <div transition="scale-transition">
    <h1>Events</h1>
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
      <v-col cols="12" md="2" class="d-flex"
        ><div class="text-center">
          <v-menu offset-y>
            <template v-slot:activator="{ on, attrs }">
              <v-btn color="#5C6BC0" dark v-bind="attrs" v-on="on" class="mt-3">
                Select Team
              </v-btn>
            </template>
            <v-list>
              <v-list-item
                v-for="(item, index) in items"
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
        <v-card
          width="100vw"
          class="ma-5"
          :class="[event.finish ? 'finish' : 'unfinish']"
        >
          <v-row>
            <v-col>
              <v-card-title>{{ event.name }}</v-card-title
              ><v-card-subtitle
                >Start from {{ event.startDate }}</v-card-subtitle
              >
            </v-col>
            <v-col
              ><v-card-title>Deadline</v-card-title
              ><v-card-subtitle>{{ event.deadLine }}</v-card-subtitle></v-col
            >
            <v-col
              ><v-card-title>Team</v-card-title>
              <v-card-subtitle>{{ event.Team }}</v-card-subtitle>
            </v-col>
            <v-col>
              <v-card-title>Status</v-card-title
              ><v-card-subtitle v-if="event.finish">Finish</v-card-subtitle>
              <v-card-subtitle v-else>Unfinish</v-card-subtitle>
            </v-col>
            <v-card-actions class="mb-4">
              <v-col>
                <v-btn outlined fab text @click="event.finish = !event.finish">
                  <v-icon>mdi-check</v-icon>
                </v-btn>
                <v-btn
                  text
                  depressed
                  @click="deleteEvent(event.name)"
                  color="error"
                >
                  Delete
                </v-btn>
              </v-col></v-card-actions
            >
          </v-row>
        </v-card>
      </v-row>
    </div>
  </div>
</template>

<script>
import data from "../store/teamInfo";
export default {
  data: (vm) => ({
    items: data.teams.map(team=>team.name),
    valid: false,
    date: new Date().toISOString().substr(0, 10),
    dateFormatted: vm.formatDate(new Date().toISOString().substr(0, 10)),
    menu1: false,
    event: "",
    assignTeam: "",
    events: [
      {
        name: "event1",
        startDate: "03/18/2021",
        deadLine: "03/20/2021",
        Team: "Apple",
        finish: true,
      },
      {
        name: "event2",
        startDate: "03/18/2021",
        deadLine: "04/20/2021",
        Team: "Apple",
        finish: false,
      },
      {
        name: "event3",
        startDate: "03/18/2021",
        deadLine: "03/20/2021",
        Team: "Apple",
        finish: true,
      },
      {
        name: "event4",
        startDate: "03/18/2021",
        deadLine: "03/20/2021",
        Team: "Apple",
        finish: true,
      },
      {
        name: "event5",
        startDate: "03/18/2021",
        deadLine: "03/20/2021",
        Team: "Apple",
        finish: false,
      },
    ],
  }),
  computed: {
    computedDateFormatted() {
      return this.formatDate(this.date);
    },
    sortedItems: function () {
      this.events.sort((a, b) => {
        return new Date(a.deadLine) - new Date(b.deadLine);
      });
      return this.events;
    },
  },

  watch: {
    date(val) {
      this.dateFormatted = this.formatDate(this.date);
    },
  },

  methods: {
    deleteEvent(name) {
      this.checked = name;
      this.events = this.events.filter((el) => el.name != this.checked);
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
    addEvent() {
      if (this.event == "") {
      } else {
        this.events.push({
          name: this.event,
          startDate: new Date().toISOString().substr(0, 10),
          deadLine: this.dateFormatted,
          Team: this.assignTeam,
          finish: false,
        });
      }
    },
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
</style>
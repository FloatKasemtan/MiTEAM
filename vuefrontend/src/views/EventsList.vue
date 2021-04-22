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
        <v-lazy
          :options="{
            threshold: 0.1,
          }"
          transition="fade-transition"
        >
          <v-card
            @click="event.finish = !event.finish"
            width="80vw"
            class="ma-5 eventItem"
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
        </v-lazy>
      </v-row>
    </div>
  </div>
</template>

<script>
import Header from "../components/Header";
import data from "@/store/index";
export default {
  components: {
    Header,
  },
  data: (vm) => ({
    pageName: "Events",
    items: data.state.teams.map((team) => team.name),
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
        return new Date(a.deadLine) - new Date(b.deadLine);
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
    deleteEvent(name) {
      this.checked = name;
      this.$store.state.events = this.$store.state.events.filter(
        (el) => el.name != this.checked
      );
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
        this.$store.state.events.push({
          name: this.event,
          startDate: new Date().toISOString().substr(0, 10),
          deadLine: this.dateFormatted,
          Team: this.assignTeam,
          finish: false,
        });
      }
    },
  },
  mounted(){
    this.$store.dispatch('loadEventData');
  }
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
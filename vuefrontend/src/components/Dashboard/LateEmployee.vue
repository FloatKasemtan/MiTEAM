<template>
  <v-card class="rounded-xl" color="#f6f6f6">
    <v-card-title class="ml-8 mb-2 dash-topic"
      >Check-in Late Worker</v-card-title
    >
    <v-data-table
      :headers="headers"
      :items="this.$store.state.employees"
      :search="search"
      :sort-by="['check_in']"
      :sort-desc="[true]"
      :items-per-page="5"
      ><template #item.check_in="{ item }">
        <v-chip :color="getColorIn(item.check_in)" dark>
          {{ getFormattedDate(item.check_in) }}
        </v-chip>
      </template>
      <template #item.check_out="{ item }">
        <v-chip :color="getColorOut(item.check_out)" dark>
          {{ getFormattedDate(item.check_out) }}
        </v-chip>
      </template>
    </v-data-table>
  </v-card>
</template>

<script>
import moment from "moment";
export default {
  data: () => ({
    search: "",
    headers: [
      {
        text: "Team",
        align: "start",
        sortable: true,
        value: "team_name",
      },
      { text: "Firstname", value: "firstname" },
      { text: "Lastname", value: "lastname" },
      { text: "Contact", value: "email" },
      { text: "Check-in time", value: "check_in" },
      { text: "Check-out time", value: "check_out" },
    ],
  }),
  methods: {
    getFormattedDate(time) {
      return moment(time).format("LT");
    },
    getColorIn(time) {
      if (moment(time).hour() < 9) return "green";
      else if (moment(time).hour() < 11) return "orange";
      else return "gray";
    },
    getColorOut(time) {
      if (moment(time).hour() < 17) return "orange";
      else if (moment(time).hour() < 19) return "green";
      else return "blue";
    },
  },
};
</script>

<style scoped>
.table {
  min-height: 36vh;
}
</style>
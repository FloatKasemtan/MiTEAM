<template>
  <v-data-table
    height="80vh"
    :headers="headers"
    :items="this.$store.state.employees"
    :search="search"
    :items-per-page="15"
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
</template>

<script>
import moment from "moment";
export default {
  props: {
    headers: String,
    employee: Object,
    search: String,
  },
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

<style>
</style>
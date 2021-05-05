<template>
  <div class="mx-16">
    <v-card>
      <v-card-title>
        <Header :pageName="pageName" />
        <v-spacer></v-spacer>
        <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
          color="black"
        ></v-text-field>
      </v-card-title>
      <v-lazy
        :options="{
          threshold: 0.1,
        }"
        min-height="200"
        transition="fade-transition"
      >
        <DataTable :headers="headers" :employee="employee" :search="search" />
      </v-lazy>
    </v-card>
  </div>
</template>

<script>
import Header from "../components/Header";
import DataTable from "../components/OtWorker/DataTable";
export default {
  components: {
    Header,
    DataTable,
  },
  data() {
    return {
      pageName: "Overtime Worker",
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
      { text: "OT done (hour)", value: "ot" },
    ],

      employee: this.$store.state.OTEmployee,
    };
  },
  mounted() {
    this.$store.dispatch("loadOTimerData");
  },
};
</script>

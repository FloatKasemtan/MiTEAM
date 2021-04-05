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
        transition="fade-transition"
      >
        <DataTable :headers="headers" :employee="employee" :search="search" />
      </v-lazy>
    </v-card>
  </div>
</template>

<style scoped>
</style>

<script>
import Header from "../components/Header";
import DataTable from "../components/CheckInTable/DataTable";
export default {
  components: {
    Header,
    DataTable,
  },
  data() {
    return {
      pageName: "Check-in Table",
      search: "",
      headers: [
        {
          text: "Team",
          align: "start",
          sortable: true,
          value: "team",
        },
        { text: "Name", value: "name" },
        { text: "Contact", value: "contact" },
        { text: "Check-in time", value: "in" },
        { text: "Check-out time", value: "out" },
      ],
      //MockData for Table
      employee: [
        {
          team: "Frozen Yogurt",
          name: "John",
          contact: "@gmail.com",
          in: 9.0,
          out: 17,
        },
        {
          team: "Gingerbread",
          name: "Mary",
          contact: "@gmail.com",
          in: 9.0,
          out: 16.55,
        },
        {
          team: "Frozen Yogurt",
          name: "Sea",
          contact: "@gmail.com",
          in: 9.0,
          out: 18.09,
        },
        {
          team: "Gingerbread",
          name: "Pete",
          contact: "@gmail.com",
          in: 9.0,
          out: 17.59,
        },
        {
          team: "Gingerbread",
          name: "Evan",
          contact: "@gmail.com",
          in: 9.0,
          out: 20,
        },
        {
          team: "Frozen Yogurt",
          name: "Isac",
          contact: "@gmail.com",
          in: 9.0,
          out: 18,
        },
        {
          team: "Frozen Yogurt",
          name: "Jess",
          contact: "@gmail.com",
          in: 9.0,
          out: 16,
        },
        {
          team: "KitKat",
          name: "Oven",
          contact: "@gmail.com",
          in: 9.0,
          out: 17,
        },
        {
          team: "Donut",
          name: "Tibb",
          contact: "@gmail.com",
          in: 9.0,
          out: 17,
        },
        {
          team: "KitKat",
          name: "Pop",
          contact: "@gmail.com",
          in: 9.0,
          out: 17,
        },
        {
          team: "Frozen Yogurt",
          name: "Isac",
          contact: "@gmail.com",
          in: 9.0,
          out: 17,
        },
        {
          team: "Frozen Yogurt",
          name: "Jess",
          contact: "@gmail.com",
          in: 9.0,
          out: 17,
        },
        {
          team: "KitKat",
          name: "Oven",
          contact: "@gmail.com",
          in: 9.0,
          out: 17,
        },
        {
          team: "Donut",
          name: "Tibb",
          contact: "@gmail.com",
          in: 9.0,
          out: 17,
        },
        {
          team: "KitKat",
          name: "Pop",
          contact: "@gmail.com",
          in: 9.0,
          out: 17,
        },
      ],
    };
  },
};
</script>
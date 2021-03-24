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
      <DataTable :headers="headers" :employee="employee" :search="search" />
    </v-card>
  </div>
</template>

<style scoped>
</style>

<script>
import Header from "../components/Header";
import DataTable from "../components/CheckInTable/DataTable";
export default {
  components:{
    Header,
    DataTable
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
        { text: "Check-in Status", value: "status" },
      ],
      //MockData for Table
      employee: [
        {
          team: "Frozen Yogurt",
          name: "John",
          contact: "@gmail.com",
          status: 9.00,
        },
        {
          team: "Gingerbread",
          name: "Mary",
          contact: "@gmail.com",
          status: 10.00,
        },
        {
          team: "Frozen Yogurt",
          name: "Sea",
          contact: "@gmail.com",
          status: 11,
        },
        {
          team: "Gingerbread",
          name: "Pete",
          contact: "@gmail.com",
          status: 12,
        },
        {
          team: "Gingerbread",
          name: "Evan",
          contact: "@gmail.com",
          status: 15,
        },
        {
          team: "Frozen Yogurt",
          name: "Isac",
          contact: "@gmail.com",
          status: 9,
        },
        {
          team: "Frozen Yogurt",
          name: "Jess",
          contact: "@gmail.com",
          status: 8.45,
        },
        {
          team: "KitKat",
          name: "Oven",
          contact: "@gmail.com",
          status: 8,
        },
        {
          team: "Donut",
          name: "Tibb",
          contact: "@gmail.com",
          status: 8,
        },
        {
          team: "KitKat",
          name: "Pop",
          contact: "@gmail.com",
          status: 8 ,
        },
        {
          team: "Frozen Yogurt",
          name: "Isac",
          contact: "@gmail.com",
          status: 8,
        },
        {
          team: "Frozen Yogurt",
          name: "Jess",
          contact: "@gmail.com",
          status: 8,
        },
        {
          team: "KitKat",
          name: "Oven",
          contact: "@gmail.com",
          status: 10,
        },
        {
          team: "Donut",
          name: "Tibb",
          contact: "@gmail.com",
          status: 9.30,
        },
        {
          team: "KitKat",
          name: "Pop",
          contact: "@gmail.com",
          status: 10.50,
        },
      ],
    };
  },
};
</script>
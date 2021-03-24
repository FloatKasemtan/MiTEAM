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

<script>
import Header from "../components/Header"
import DataTable from "../components/OtWorker/DataTable"
export default {
  components:{
    Header,
    DataTable
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
          value: "team",
        },
        { text: "Name", value: "name" },
        { text: "Contact", value: "contact" },
        { text: "OT done (hour)", value: "time" },
      ],
      //MockData for DataTable
      employee: [
        {
          team: "Frozen Yogurt",
          name: "John",
          contact: "@gmail.com",
          time: 10,
        },
        {
          team: "Gingerbread",
          name: "Mary",
          contact: "@gmail.com",
          time: 0,
        },

        {
          team: "Frozen Yogurt",
          name: "John",
          contact: "@gmail.com",
          time: 15,
        },
        {
          team: "Gingerbread",
          name: "Mary",
          contact: "@gmail.com",
          time: 3,
        },
        {
          team: "Frozen Yogurt",
          name: "Sea",
          contact: "@gmail.com",
          time: 5,
        },
        {
          team: "Gingerbread",
          name: "Pete",
          contact: "@gmail.com",
          time: 6,
        },
        {
          team: "Gingerbread",
          name: "Evan",
          contact: "@gmail.com",
          time: 8,
        },
        {
          team: "Frozen Yogurt",
          name: "Isac",
          contact: "@gmail.com",
          time: 1,
        },
        {
          team: "Frozen Yogurt",
          name: "Jess",
          contact: "@gmail.com",
          time: 9,
        },
        {
          team: "KitKat",
          name: "Oven",
          contact: "@gmail.com",
          time: 20,
        },
        {
          team: "Donut",
          name: "Tibb",
          contact: "@gmail.com",
          time: 10,
        },
        {
          team: "KitKat",
          name: "Pop",
          contact: "@gmail.com",
          time: 19,
        },
      ],
    };
  },
};
</script>

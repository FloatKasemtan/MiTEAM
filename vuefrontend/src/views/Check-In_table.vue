<template>
  <div class="mx-16">
    <v-card>
      <v-card-title>
        <h1>Check-in Table</h1>
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
      <v-data-table
        :headers="headers"
        :items="employee"
        :search="search"
        :items-per-page="15"
        ><template #item.status="{ item }">
          <v-chip v-if="item.status%1==0" :color="getColor(item.status)" dark>
            {{ item.status }}.00 <div v-if="item.status>12.00">PM</div><div v-else>AM</div>
          </v-chip>
          <v-chip v-else-if="item.status*10%1==0" :color="getColor(item.status)" dark>
            {{ item.status }}0 <div v-if="item.status>12.00">PM</div><div v-else>AM</div>
          </v-chip>
          <v-chip v-else :color="getColor(item.status)" dark>
            {{ item.status }} <div v-if="item.status>12.00">PM</div><div v-else>AM</div>
          </v-chip>
        </template>
      </v-data-table>
    </v-card>
  </div>
</template>

<style scoped>
</style>

<script>
export default {
  data() {
    return {
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
  methods: {
    getColor(status) {
      if (status > 9) return "orange";
      else return "green";
    },

  },
};
</script>
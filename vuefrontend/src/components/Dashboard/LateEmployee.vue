<template>
  <v-card class="rounded-xl" color="#f6f6f6">
    <v-card-title class="ml-8 mb-2 dash-topic"
      >Check-in Late Worker</v-card-title
    >
    <v-data-table
      :headers="headers"
      :items="this.$store.state.employees"
      :search="search"
      :items-per-page="5"
      ><template #item.check_in="{ item }">
        <v-chip :color="getColor(item.check_in)" dark>
          {{ new Date(item.check_in).getHour() }}
        </v-chip>
      </template>
      <template #item.check_out="{ item }">
        <v-chip
          :color="getColorOut(item.out)"
          dark
        >
          {{ item.check_out }}0
          <div v-if="item.out > 12.0">PM</div>
          <div v-else>AM</div>
        </v-chip>
      </template>
    </v-data-table>
  </v-card>
</template>

<script>
export default {
  data: () => ({
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
  }),
  methods: {
    getColorOut(date) {
      time = new Date(date)
      if (time < 17.0) return "orange";
      else if (time < 19) return "gray";
      else return "green";
    },
  },
  mounted(){
    console.log(new Date(this.$store.state.employees[0].check_in))
    console.log(new Date(1619121493000))
  }
};
</script>

<style>
</style>
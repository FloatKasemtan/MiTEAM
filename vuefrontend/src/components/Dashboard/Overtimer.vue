<template>
  <v-card class="rounded-xl" color="#f6f6f6">
        <v-card-title class="ml-8 mb-2 dash-topic">Worker with highest Overtime</v-card-title>
    <v-data-table
      :headers="headers"
      :items="this.$store.state.OTEmployee"
      :search="search"
      :sort-by="['time']"
      :sort-desc="[true]"
      :items-per-page="5"
    >
    </v-data-table>
  </v-card>
</template>

<script>
export default {
  data: () => ({
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
  }),
};
</script>

<style>
</style>
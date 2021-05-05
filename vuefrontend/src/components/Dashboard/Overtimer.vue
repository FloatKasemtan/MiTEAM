<template>
  <v-card class="rounded-xl" color="#f6f6f6">
    <v-card-title class="ml-8 mb-2 dash-topic"
      >Worker with highest Overtime</v-card-title
    >
    <v-data-table
      :headers="headers"
      :items="this.$store.state.OTEmployee"
      :search="search"
      :sort-by="['ot']"
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
        value: "team_name",
      },
      { text: "Firstname", value: "firstname" },
      { text: "Lastname", value: "lastname" },
      { text: "Contact", value: "email" },
      { text: "OT done (hour)", value: "ot" },
    ],
  }),
};
</script>

<style scoped>
</style>
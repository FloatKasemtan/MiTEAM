<template>
  <v-data-table
    :headers="headers"
    :items="employee"
    :search="search"
    :items-per-page="15"
    ><template #item.status="{ item }">
      <v-chip v-if="item.status % 1 == 0" :color="getColor(item.status)" dark>
        {{ item.status }}.00
        <div v-if="item.status > 12.0">PM</div>
        <div v-else>AM</div>
      </v-chip>
      <v-chip
        v-else-if="(item.status * 10) % 1 == 0"
        :color="getColor(item.status)"
        dark
      >
        {{ item.status }}0
        <div v-if="item.status > 12.0">PM</div>
        <div v-else>AM</div>
      </v-chip>
      <v-chip v-else :color="getColor(item.status)" dark>
        {{ item.status }}
        <div v-if="item.status > 12.0">PM</div>
        <div v-else>AM</div>
      </v-chip>
    </template>
  </v-data-table>
</template>

<script>
export default {
  props: {
    headers: String,
    employee: Object,
    search: String,
  },
  methods: {
    getColor(status) {
      if (status > 9) return "orange";
      else return "green";
    },
  },
};
</script>

<style>
</style>
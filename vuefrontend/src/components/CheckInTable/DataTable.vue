<template>
  <v-data-table
    :headers="headers"
    :items="employee"
    :search="search"
    :items-per-page="15"
    ><template #item.in="{ item }">
      <v-chip v-if="item.in % 1 == 0" :color="getColor(item.in)" dark>
        {{ item.in }}.00
        <div v-if="item.in > 12.0">PM</div>
        <div v-else>AM</div>
      </v-chip>
      <v-chip
        v-else-if="(item.in * 10) % 1 == 0"
        :color="getColor(item.in)"
        dark
      >
        {{ item.in }}0
        <div v-if="item.in > 12.0">PM</div>
        <div v-else>AM</div>
      </v-chip>
      <v-chip v-else :color="getColor(item.in)" dark>
        {{ item.in }}
        <div v-if="item.in > 12.0">PM</div>
        <div v-else>AM</div>
      </v-chip>
    </template>
    <template #item.out="{ item }">
      <v-chip v-if="item.out % 1 == 0" :color="getColorOut(item.out)" dark>
        {{ item.out }}.00
        <div v-if="item.out < 12.0">PM</div>
        <div v-else>AM</div>
      </v-chip>
      <v-chip
        v-else-if="(item.out * 10) % 1 == 0"
        :color="getColorOut(item.out)"
        dark
      >
        {{ item.out }}0
        <div v-if="item.out > 12.0">PM</div>
        <div v-else>AM</div>
      </v-chip>
      <v-chip v-else :color="getColorOut(item.out)" dark>
        {{ item.out }}
        <div v-if="item.out > 12.0">PM</div>
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
    getColor(time) {
      if (time > 9) return "orange";
      else return "green";
    },
    getColorOut(time) {
      if (time < 17.0) return "orange";
      else if (time < 19) return "gray";
      else return "green";
    },
  },
};
</script>

<style>
</style>
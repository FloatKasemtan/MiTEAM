<template>
  <v-card class="rounded-xl">
    <div class="ml-8 mb-2 dash-topic">Check-in Late Worker</div>
    <v-data-table
    :headers="headers"
    :items="this.$store.state.lateEmployee"
    :search="search"
    :items-per-page="5"
    ><template #item.in="{ item }">
      <v-chip v-if="item.in % 1 == 0" color="red" dark>
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
  </v-card>
</template>

<script>
export default {
  data:() => ({
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
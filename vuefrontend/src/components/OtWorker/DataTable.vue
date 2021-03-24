<template>
  <v-data-table
        :headers="headers"
        :items="employee"
        :search="search"
        :items-per-page="15"
      >
      </v-data-table>
</template>

<script>
export default {
    props:{
        headers: String,
        employee: Object,
        search: String
    }
}
</script>

<style>

</style>
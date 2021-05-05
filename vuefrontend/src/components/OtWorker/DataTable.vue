<template>
  <v-data-table
  height="80vh"
      :headers="headers"
      :items="this.$store.state.OTEmployee"
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
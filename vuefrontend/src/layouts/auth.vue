<template>
  <v-app>
    <div class="screen">
      <v-main>
        <v-container>
          <router-view></router-view>
        </v-container>
      </v-main>
    </div>
  </v-app>
</template>

<script>
export default {
  name: 'auth',
};
</script>

<style scoped>
</style>
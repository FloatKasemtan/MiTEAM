<template>
  <v-app>
    <div class="screen">
      <Sidebar />
      <v-main class="main"  v-if="this.$store.state.isLogin">
        <v-container fluid>
          <router-view></router-view>
        </v-container>
      </v-main>
    </div>
  </v-app>
</template>

<script>
import Sidebar from "../components/Sidebar";
export default {
  name: "App",
  components: {
    Sidebar,
  },
};
</script>
<style scoped>
.screen {
  height: 100%;
}
</style>
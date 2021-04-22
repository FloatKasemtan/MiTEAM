<template>
  <v-navigation-drawer
    permanent
    expand-on-hover
    app
    class="navigation .rounded-tr-xl"
    color="#31517D"
    dark
  >
    <template v-slot:prepend>
      <v-list class="d-flex justify-center">
        <v-list-item-icon>
          <v-img
            v-if="image"
            max-width="56"
            max-height="56"
            :src="image"
          ></v-img>
          <v-img
            v-else
            max-width="56"
            max-height="56"
            src="../images/logo/logo2nd512.png"
          ></v-img>
          <v-list-item-title>
            <v-list-item-title><h1 class="logo">MiTEAM</h1></v-list-item-title>
          </v-list-item-title>
        </v-list-item-icon>
      </v-list>
      <v-list-item two-line class="px-2">
        <v-list-item-avatar>
          <img
            src="https://ramcotubular.com/wp-content/uploads/default-avatar.jpg"
          />
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title>{{ user }}</v-list-item-title>
          <v-list-item-subtitle>Logged In</v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>
    </template>
    <v-list>
      <v-list-item v-for="item in items" :key="item.title" :to="item.to">
        <v-list-item-icon>
          <v-icon>{{ item.icon }}</v-icon>
        </v-list-item-icon>

        <v-list-item-content>
          <v-list-item-title>{{ item.title }}</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-list>

    <template #append>
      <div>
        <v-btn href="/" @click="logout" block color="red">
          <v-icon>mdi-logout</v-icon>
        </v-btn>
      </div>
    </template>
  </v-navigation-drawer>
</template>

<script>
import axios from "@/axios/axios";
import VueCookies from "vue-cookies";
import Vue from "vue";

Vue.use(VueCookies);
export default {
  data() {
    return {
      user: this.$store.state.userData.firstname+" "+this.$store.state.userData.lastname,
      image: this.$store.state.userData.image,
      items: [
        {
          title: "Dashboard",
          icon: "mdi-desktop-mac-dashboard",
          to: "/dashboard",
        },
        { title: "Teams", icon: "mdi-view-dashboard", to: "/team" },
        {
          title: "Events",
          icon: "mdi-calendar ",
          to: "/events",
        },
        { title: "Check-in Table", icon: "mdi-account-box", to: "/check-in" },
        {
          title: "OT-Timer Table",
          icon: "mdi-clock-time-eleven",
          to: "/ot-timer",
        },
      ],
    };
  },
  methods: {
    logout() {
      Vue.$cookies.set("JWT", null);
      axios.defaults.headers.common["Authorization"] = null;
    },
  },
  mounted(){
    
  }
};
</script>

<style scoped>
.logo {
  font-size: 2em;
  color: transparent;
  -webkit-text-stroke: 0.7px white;
}
</style>
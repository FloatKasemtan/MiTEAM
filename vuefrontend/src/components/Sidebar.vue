<template>
  <div>
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
              max-width="56"
              max-height="56"
              src="../images/logo/logo2nd512.png"
            ></v-img>
            <v-list-item-title>
              <v-list-item-title
                ><h1 class="logo">MiTEAM</h1></v-list-item-title
              >
            </v-list-item-title>
          </v-list-item-icon>
        </v-list>
        <v-list-item two-line class="px-2">
          <v-list-item-avatar>
            <v-img v-if="user.image" :src="user.image"></v-img>
            <img
              v-else
              src="https://ramcotubular.com/wp-content/uploads/default-avatar.jpg"
            />
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title
              >{{ user.firstname }} {{ user.lastname }}</v-list-item-title
            >

            <span style="color: #00e676"
              >Logged In<v-btn
                @click="dialog = !dialog"
                color="white"
                height="23px"
                plain
                x-small
              >
                <v-icon left small class="ml-1 mb-1"> mdi-pencil </v-icon>
              </v-btn></span
            >
          </v-list-item-content>
        </v-list-item>
      </template>
      <v-list>
        <v-list-item v-for="item in items" :key="item.title" :to="item.to" @click="location.reload();">
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
    <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      v-model="dialog"
    >
      <v-card class="pa-5">
        <h1 class="pt-5">Edit your detail</h1>
        <div class="d-flex">
          <v-text-field
            class="mr-3"
            v-model="firstname"
            :rules="firstRules"
            label="Firstname"
            required
            color="#31517d"
          ></v-text-field
          ><v-btn icon color="green" class="mt-3" @click="updateFname">
            <v-icon>mdi-cached</v-icon>
          </v-btn>
        </div>
        <div class="d-flex">
          <v-text-field
            v-model="lastname"
            :rules="lastRules"
            label="Lastname"
            required
            color="#31517d"
          ></v-text-field>
          <v-btn icon color="green" class="mt-3" @click="updateLname">
            <v-icon>mdi-cached</v-icon>
          </v-btn>
        </div>

        <div class="d-flex">
          <v-text-field
            v-model="image"
            prepend-icon="mdi-camera"
            label="Image URL"
            required
            color="#31517d"
          ></v-text-field>
          <v-btn icon color="green" class="mt-3" @click="updateImage">
            <v-icon>mdi-cached</v-icon>
          </v-btn>
        </div>

        <div class="d-flex">
          <v-text-field
            class="mx-2"
            v-model="password"
            prepend-icon="mdi-key"
            label="new password"
            required
            color="#31517d"
          ></v-text-field>
          <v-text-field
            class="mx-2"
            v-model="repassword"
            label="re enter new password"
            required
            color="#31517d"
            :append-icon="showPass ? 'mdi-eye' : 'mdi-eye-off'"
            :type="showPass ? 'text' : 'password'"
            @click:append="showPass = !showPass"
          ></v-text-field>
          <v-btn icon color="green" class="mt-3" @click="updatePass">
            <v-icon>mdi-cached</v-icon>
          </v-btn>
        </div>
      </v-card>
    </v-dialog>
    <v-dialog v-model="dialogSuccess" max-width="300px">
      <v-alert border="bottom" elevation="10" type="success" class="mb-0"
        >Your infomation have changed</v-alert
      >
    </v-dialog>
    <v-dialog v-model="dialogNoPass" max-width="300px">
      <v-alert border="bottom" elevation="10" type="warning" class="mb-0"
        >Enter your password</v-alert
      >
    </v-dialog>
    <v-dialog v-model="dialogNotMatch" max-width="300px">
      <v-alert border="bottom" elevation="10" type="warning" class="mb-0"
        >Password not match</v-alert
      >
    </v-dialog>
  </div>
</template>

<script>
import axios from "@/axios/axios";
import Vue from "vue";

export default {
  data() {
    return {
      user: {},
      loginStatus: {},
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
      firstname: "",
      firstRules: [
        (v) => (v && v.length <= 45) || "Name must be less than 10 characters",
      ],
      lastname: "",
      lastRules: [
        (v) => (v && v.length <= 45) || "Name must be less than 10 characters",
      ],
      image: "",
      password: "",
      repassword: "",
      dialog: false,
      dialogSuccess: false,
      dialogNotMatch: false,
      dialogNoPass: false,
    };
  },
  methods: {
    logout() {
      Vue.$cookies.set("JWT", null);
      Vue.$cookies.set("USERDATA", null);
      Vue.$cookies.set("ISLOGIN", false);
      axios.defaults.headers.common["Authorization"] = null;
    },
    async updateFname() {
      await axios.put("/account/updateFname?newDetail=" + this.firstname);
      this.dialogSuccess = true;
      const response = await axios.get("/account/list");
      Vue.$cookies.set("USERDATA", response.data.userDetail);
      location.reload();
    },
    async updateLname() {
      await axios.put("/account/updateLname?newDetail=" + this.lastname);
      this.dialogSuccess = true;
      const response = await axios.get("/account/list");
      Vue.$cookies.set("USERDATA", response.data.userDetail);
      location.reload();
    },
    async updateImage() {
      await axios.put("/account/updateImage?newDetail=" + this.image);
      this.dialogSuccess = true;
      const response = await axios.get("/account/list");
      Vue.$cookies.set("USERDATA", response.data.userDetail);
      location.reload();
    },
    async updatePass() {
      if (this.password == "") {
        this.dialogNoPass = true;
      } else if (this.password !== this.repassword) {
        this.dialogNotMatch = true;
      } else {
        await axios.put("/account/updatePass?newDetail=" + this.password);
        this.dialogSuccess = true;
      }
    },
  },
  mounted() {
    this.user = Vue.$cookies.get("USERDATA");
  },
};
</script>

<style scoped>
.logo {
  font-size: 2em;
  color: transparent;
  -webkit-text-stroke: 0.7px white;
}
</style>
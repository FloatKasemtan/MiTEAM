<template>
  <div>
    <v-form ref="form" v-model="valid" lazy-validation>
      <v-text-field
        v-model="username"
        :rules="nameRules"
        prepend-icon="mdi-account"
        label="username"
        required
        color="#31517d"
        class="my-3"
        clearable
        clear-icon="mdi-close-circle"
        @keyup.enter="validate"
      ></v-text-field>

      <v-text-field
        v-model="password"
        :rules="passRules"
        prepend-icon="mdi-key"
        label="password"
        required
        color="#31517d"
        class="my-3"
        :append-icon="showPass ? 'mdi-eye' : 'mdi-eye-off'"
        :type="showPass ? 'text' : 'password'"
        @click:append="showPass = !showPass"
        @keyup.enter="validate"
      ></v-text-field>
    </v-form>
    <div>
      <v-btn
        block
        :disabled="!valid"
        color="#31517D"
        class="mr-4 rounded-xl white--text"
        @click="validate"
      >
        SIGN IN
      </v-btn>
      <a class="forgetLink" href="/forget">forget password?</a>
    </div>
    <v-dialog v-model="wrongUser" width="300px">
      <v-alert type="error" elevation="10" class="mb-0" border="bottom"
        >Your username or password are incorrect!</v-alert
      >
    </v-dialog>
  </div>
</template>

<script>
import axios from "@/axios/axios";
import VueCookies from "vue-cookies";
import Vue from "vue";
Vue.use(VueCookies);
export default {
  props: {
    nameRules: Object,
    passRules: Object,
  },
  data: () => ({
    valid: true,
    username: "",
    showPass: false,
    password: "",
    wrongUser:false,
  }),
  methods: {
    async validate() {
      if (this.$refs.form.validate()) {
        const response = await axios.post("/account/login", {
          username: this.username,
          pass: this.password,
        });
        Vue.$cookies.set("JWT", response.data.token);

        if (response.data.loginStatus) {
          location.href = "/dashboard";
          Vue.$cookies.set("USERDATA", response.data.userDetail);
          Vue.$cookies.set("ISLOGIN", response.data.loginStatus);
          this.$store.dispatch("loadUser");
        } else {
          this.password = this.username = "";
          this.wrongUser = true;
        }
      }
    },
  },
};
</script>

<style scoped>
  .forgetLink{
    float: right;
    margin-top: 10px;
  }
</style>
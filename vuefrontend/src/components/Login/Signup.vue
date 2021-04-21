<template>
  <div>
    <v-form ref="form" v-model="valid" lazy-validation>
      <v-text-field
        v-model="usernameRegis"
        :rules="nameRules"
        prepend-icon="mdi-account"
        label="username"
        required
        color="#31517d"
        class="my-3"
        clearable
        clear-icon="mdi-close-circle"
      ></v-text-field>
      <v-text-field
        v-model="email"
        :rules="emailRules"
        prepend-icon="mdi-mail"
        label="E-mail"
        required
        color="#31517d"
        class="my-3"
        clearable
        clear-icon="mdi-close-circle"
      ></v-text-field>

      <v-text-field
        v-model="passwordRegis"
        :rules="passRules"
        prepend-icon="mdi-key"
        label="password"
        required
        color="#31517d"
        class="my-3"
        :append-icon="showPass ? 'mdi-eye' : 'mdi-eye-off'"
        :type="showPass ? 'text' : 'password'"
        @click:append="showPass = !showPass"
      ></v-text-field>
      <v-text-field
        v-model="repassword"
        :rules="passRules"
        prepend-icon="mdi-key"
        label="re enter password"
        required
        color="#31517d"
        class="my-3"
        :type="'password'"
      ></v-text-field>
      <v-text-field
        v-model="firstname"
        :rules="firstRules"
        prepend-icon="mdi-key"
        label="Firstname"
        required
        color="#31517d"
      ></v-text-field>
      <v-text-field
        v-model="lastname"
        :rules="lastRules"
        prepend-icon="mdi-key"
        label="Lastname"
        required
        color="#31517d"
      ></v-text-field>
      <v-text-field
        v-model="image"
        prepend-icon="mdi-key"
        label="Image URL"
        required
        color="#31517d"
      ></v-text-field>
    </v-form>
    <v-btn
      block
      :disabled="!valid"
      color="#31517D"
      class="mr-4 rounded-xl white--text"
      @click="register"
    >
      SIGN UP
    </v-btn>
  </div>
</template>

<script>
import axios from "@/axios/axios";
export default {
  props: {
    nameRules: Object,
    passRules: Object,
    emailRules: Object,
  },
  data: () => ({
    valid: true,
    showPass: false,
    usernameRegis: "",
    email: "",
    passwordRegis: "",
    repassword: "",
    firstname: "",
    firstRules: [
      (v) => !!v || "firstname is required",
      (v) => (v && v.length <= 45) || "Name must be less than 10 characters",
    ],
    lastname: "",
    lastRules: [
      (v) => !!v || "lastname is required",
      (v) => (v && v.length <= 45) || "Name must be less than 10 characters",
    ],
    image: "",
  }),
  methods: {
    async register() {
      if (this.$refs.form.validate()) {
        if (this.repassword == this.passwordRegis) {
          const response = await axios.post("/account/register", {
            username: this.usernameRegis,
            password: this.passwordRegis,
            image: this.image,
            fname: this.firstname,
            lname: this.lastname,
            email: this.email,
          });
          this.$emit("regisSuccess");
          console.log(response.data);
        } else {
          this.$emit("passMatch");
          this.passwordRegis = "";
          this.repassword = "";
        }
      }
    },
  },
};
</script>

<style>
</style>
<template>
  <div style="height: 100vh" class="d-flex align-center">
    <v-card width="40%" elevation="0" class="ma-16">
      <v-card-title style="color: #31517d" class="mb-2"
        ><h1 class="logo">MiTEAM</h1></v-card-title
      >
      <v-card-subtitle>Manage Your Team better than before</v-card-subtitle>
      <v-tabs v-model="tab" color="#31517d">
        <v-tab> login </v-tab>
        <v-tab> sign-up </v-tab>
      </v-tabs>
      <v-tabs-items v-model="tab">
        <v-tab-item>
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
          <v-btn
            block
            :disabled="!valid"
            color="#31517D"
            class="mr-4 rounded-xl white--text"
            @click="validate"
          >
            SIGN IN
          </v-btn>
        </v-tab-item>
        <v-tab-item>
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
        </v-tab-item>
      </v-tabs-items>
    </v-card>
    <v-card
      width="60%"
      height="100%"
      elevation="0"
      class="d-flex align-center justify-center"
    >
      <v-img src="../assets/animation_640.gif"></v-img>
    </v-card>
    <v-dialog v-model="wrongUser" width="300px">
      <v-alert type="error" elevation="10" class="mb-0" border="bottom"
        >Your {{ wrongData }} is incorrect!</v-alert
      >
    </v-dialog>
    <v-dialog v-model="passMatch" width="300px">
      <v-alert type="error" elevation="10" class="mb-0" border="bottom"
        >Your password not match!</v-alert
      >
    </v-dialog>
  </div>
</template>

<script>
export default {
  name: "login",
  data: () => ({
    valid: true,
    wrongUser: false,
    passMatch: false,
    wrongData: "",
    username: "",
    showPass: false,
    tab: null,
    nameRules: [
      (v) => !!v || "username is required",
      (v) => (v && v.length <= 10) || "Name must be less than 10 characters",
    ],
    password: "",
    passRules: [(v) => !!v || "password is required"],
    usernameRegis: "",
    email: "",
    emailRules: [(v) => !!v || "E-mail is required"],
    passwordRegis: "",
    repassword: "",
  }),
  methods: {
    validate() {
      this.$refs.form.validate();
      if (this.username == "" && this.password == "") {
      } else if (this.username == "admin") {
        if (this.password == "111") {
          this.$store.state.isLogin = true;
          this.$router.push("/dashboard");
        } else {
          this.password = this.username = "";
          this.wrongData = "password";
          this.wrongUser = true;
        }
      } else {
        this.password = this.username = "";
        this.wrongData = "username";
        this.wrongUser = true;
      }
    },
    register() {
      if (this.repassword == this.passwordRegis) {
      } else {
        this.passMatch = true;
        this.passwordRegis = "";
        this.repassword = "";
        this.usernameRegis = "";
        this.email = "";
      }
    },
  },
};
</script>

<style scoped>
.logo {
  font-family: Train One;
}
</style>
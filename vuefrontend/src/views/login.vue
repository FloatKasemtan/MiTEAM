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
          <Signin
            :nameRules="nameRules"
            :passRules="passRules"
            @wrongUser="wrongUser = true"
          />
        </v-tab-item>
        <v-tab-item>
          <Signup
            :nameRules="nameRules"
            :passRules="passRules"
            :emailRules="emailRules"
            @passMatch="passMatch = true"
            @regisSuccess="regisSuccess = true"
            @emailUsed="emailUsed = true"
            @fatalError="fatalError = true"
          />
        </v-tab-item>
      </v-tabs-items>
    </v-card>
    <v-card
      width="60%"
      height="100%"
      elevation="0"
      class="d-flex align-center justify-center"
    >
      <v-img src="@/images/animation_640.gif"></v-img>
    </v-card>
    <v-dialog v-model="wrongUser" width="300px">
      <v-alert type="error" elevation="10" class="mb-0" border="bottom"
        >Your username or password are incorrect!</v-alert
      >
    </v-dialog>
    <v-dialog v-model="passMatch" width="300px">
      <v-alert type="error" elevation="10" class="mb-0" border="bottom" 
        >Your password not match!</v-alert
      >
    </v-dialog>
    <v-dialog v-model="regisSuccess" width="300px">
      <v-alert type="success" elevation="10" class="mb-0" border="bottom"
        >Register Successful!</v-alert
      >
    </v-dialog>
    <v-dialog v-model="emailUsed" width="300px">
      <v-alert type="warning" elevation="10" class="mb-0" border="bottom"
        >This email already been used!</v-alert
      >
    </v-dialog>
    <v-dialog v-model="fatalError" width="300px">
      <v-alert type="error" elevation="10" class="mb-0" border="bottom"
        >Register error!</v-alert
      >
    </v-dialog>
  </div>
</template>

<script>
import Signup from "../components/Login/Signup";
import Signin from "../components/Login/Signin";
export default {
  components: {
    Signin,
    Signup,
  },
  name: "login",
  data: () => ({
    valid: true,
    wrongUser: false,
    passMatch: false,
    showPass: false,
    regisSuccess: false,
    fatalError: false,
    emailUsed: false,
    tab: null,
    nameRules: [
      (v) => !!v || "username is required",
      (v) => (v && v.length <= 10) || "Name must be less than 10 characters",
    ],
    passRules: [(v) => !!v || "password is required"],
    emailRules: [
        (v) => !!v || "E-mail is required",
        (v) => /.+@.+\..+/.test(v) || "E-mail must be valid",
      ],
  }),
  mounted(){
    
  }
};

</script>

<style scoped>
.logo {
  font-family: Train One;
}
</style>
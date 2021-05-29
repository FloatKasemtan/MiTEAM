<template>
  <div>
    <div class="stepper">
      <Header class="mb-10" :pageName="pageName" />
      <v-stepper v-model="step" vertical>
        <v-stepper-step :complete="step > 1" step="1">
          Enter your email
        </v-stepper-step>

        <v-stepper-content step="1">
          <v-card elevation="0" class="mb-12" width="50vw">
            <v-form ref="form" v-model="valid" lazy-validation>
              <v-text-field
                :rules="emailRules"
                placeholder="email"
                text
                v-model="email"
              ></v-text-field>
            </v-form>
          </v-card>
          <v-btn color="primary" @click="enterEmail" :loading="loading">
            Continue
          </v-btn>
          <v-btn to="/" class="ml-5"> return to login </v-btn>
        </v-stepper-content>

        <v-stepper-step :complete="step > 2" step="2">
          <v-card
            elevation="0"
            class="d-flex justify-space-between"
            width="50vw"
            ><div>Verification code</div>
          </v-card>
        </v-stepper-step>

        <v-stepper-content step="2">
          <v-card elevation="0" class="mb-12" width="50vw">
            <v-text-field
              placeholder="verify code"
              text
              :rules="tokenRules"
              v-model="token"
            ></v-text-field>
            <div>
              <v-text-field
                placeholder="Enter your new password"
                text
                :rules="passRule"
                v-model="new_pass"
                :type="showPass ? 'text' : 'password'"
              ></v-text-field
              ><v-text-field
                placeholder="Comfirm your password"
                text
                :rules="passRule"
                v-model="com_pass"
                :type="showPass ? 'text' : 'password'"
              ></v-text-field>
              <div class="float-right">
                <div v-if="!resend">Time left : {{ min }}:{{ sec }}</div>
                <div v-else>
                  <a @click="enterEmail"
                    >resend<v-icon class="ml-1">mdi-restart</v-icon></a
                  >
                </div>
              </div>
            </div>
          </v-card>
          <v-btn color="primary" @click="comfirmCode" :loading="loading">
            Continue
          </v-btn>
          <v-btn text @click="backward" class="ml-5"> Cancel </v-btn>
        </v-stepper-content>
      </v-stepper>
    </div>
    <v-dialog v-model="noEmailDialog" width="300px">
      <v-alert type="error" elevation="10" class="mb-0" border="bottom"
        >We can't found this email in our database</v-alert
      >
    </v-dialog>
    <v-dialog v-model="warningDialog" width="300px">
      <v-alert type="warning" elevation="10" class="mb-0" border="bottom"
        >error occur!</v-alert
      >
    </v-dialog>
    <v-dialog v-model="successDialog" width="300px">
      <v-alert type="success" elevation="10" class="mb-0" border="bottom"
        >Password have successfully changed!</v-alert
      >
    </v-dialog>
    <v-dialog v-model="tokenError" width="300px">
      <v-alert type="warning" elevation="10" class="mb-0" border="bottom"
        >Verification code is not correct!</v-alert
      >
    </v-dialog>
  </div>
</template>

<script>
import axios from "@/axios/axios";
export default {
  components: {
    Header: () => import("../components/Header"),
  },
  data() {
    return {
      pageName: "Forget password",
      valid: true,
      successDialog: false,
      noEmailDialog: false,
      tokenError: false,
      warningDialog: false,
      email: "",
      token: "",
      id_reset: "",
      new_pass: "",
      com_pass: "",
      min: "05",
      sec: "00",
      interval: null,
      resend: false,
      tokenRules: [
        (v) => !!v || "Code is required",
        (v) => (v && v.length == 6) || "Code must contain 6 digits",
      ],
      emailRules: [
        (v) => !!v || "E-mail is required",
        (v) => /.+@.+\..+/.test(v) || "E-mail must be valid",
      ],
      passRules: [(v) => !!v || "password is required"],
      step: 1,
      loading: false,
    };
  },
  methods: {
    async enterEmail() {
      this.loading = true;
      const response = await axios.post("/account/forgot?email=" + this.email);
      this.loading = false;
      if (response.data.success) {
        this.step = 2;
        this.timeCount();
        this.id_reset = response.data.id_reset;
      } else if (
        !response.data.success &&
        response.data.text === "Creating user failed, no email obtained"
      ) {
        this.noEmailDialog = true;
      } else {
        this.warningDiaglog = true;
      }
    },
    async comfirmCode() {
      this.loading = true;
      if (this.new_pass === this.com_pass) {
        const response = await axios.patch("/account/reset", {
          verify: this.token,
          id_reset: this.id_reset,
          new_pass: this.new_pass,
        });
        this.loading = false;
        if (response.data.success) {
          this.successDialog = true;
          await new Promise((resolve) => setTimeout(resolve, 1000));
        location.href = "/";
        }else{
          this.tokenError = true;
        }
        
      }
    },
    backward() {
      this.step = 1;
      clearInterval(this.interval);
    },
    timeCount() {
      this.resend = false;
      let totalTime = 300; // 5min
      if (totalTime > 0) {
        this.interval = setInterval(() => {
          this.min = "0" + Math.floor(totalTime / 60).toFixed(0);
          totalTime -= 1;
          this.min = "0" + Math.floor(totalTime / 60).toFixed(0);
          this.sec = ("0" + (totalTime % 60)).slice(-2);
          if (totalTime <= 0) {
            clearInterval(this.interval);
            this.min = "05";
            this.sec = "00";
            this.resend = true;
          }
        }, 1000);
      }
    },
  },
};
</script>

<style scoped>
.stepper {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 80vh;
  flex-direction: column;
}
</style>
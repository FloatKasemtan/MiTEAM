<template>
  <div class="main">
    <div>
      <v-menu offset-y>
        <template v-slot:activator="{ on, attrs }">
          <v-btn color="#31517d" dark v-bind="attrs" v-on="on" class="mt-3">
            Select Employee
          </v-btn>
        </template>
        <v-list>
          <v-list-item
            v-for="(item, index) in employees.map((employee) => employee)"
            :key="index"
            link
            @click="
              (selectedEmployee = item.firstname),
                (employee_id = item.employee_id)
            "
          >
            <v-list-item-title>{{ item.firstname }}</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
      <v-text-field
        disabled
        label="Name"
        color="black"
        v-model="selectedEmployee"
      ></v-text-field>
    </div>

    <v-card elevation="0" class="ma-16">
      <v-card-title style="color: #31517d" class="mb-2"
        ><h1 class="logo">Check-in</h1></v-card-title
      >
      <v-time-picker format="24hr" landscape v-model="time_in"></v-time-picker>
      <v-card-actions
        ><v-spacer></v-spacer
        ><v-btn @click="check_in(time_in)"
          ><v-icon>mdi-account-check</v-icon>check-in</v-btn
        ></v-card-actions
      >
    </v-card>
    <v-card elevation="0" class="ma-16">
      <v-card-title style="color: #31517d" class="mb-2"
        ><h1 class="logo">Check-out</h1></v-card-title
      >
      <v-time-picker format="24hr" landscape v-model="time_out"></v-time-picker>
      <v-card-actions
        ><v-spacer></v-spacer
        ><v-btn @click="check_out(time_out)"
          ><v-icon>mdi-exit-to-app</v-icon> check-out</v-btn
        ></v-card-actions
      >
    </v-card>
    <v-dialog v-model="dialogWarning" width="300px">
      <v-alert type="warning" elevation="10" class="mb-0" border="bottom"
        >Please choose employee first</v-alert
      >
    </v-dialog>
    <v-dialog v-model="regisSuccess" width="300px">
      <v-alert type="success" elevation="10" class="mb-0" border="bottom"
        >Check Successful!</v-alert
      >
    </v-dialog>
    <v-dialog v-model="dialogError" width="420px">
      <v-alert type="error" elevation="10" class="mb-0" border="bottom"
        >This member have already check-in or check-out!</v-alert
      >
    </v-dialog>
  </div>
</template>

<script>
import axios from "@/axios/axios";
export default {
  data: () => ({
    selectedEmployee: null,
    employees: [],
    time_in: "00:00",
    time_out: "00:00",
    employee_id: "",
    dialogWarning: false,
    regisSuccess: false,
    dialogError: false,
  }),
  methods: {
    async check_in(time) {
      if (this.selectedEmployee !== null) {
        var thisDay = new Date();
        thisDay.setHours(time.substring(0, 2));
        thisDay.setMinutes(time.substring(3, 5));
        const response = await axios.post(
          "/check/in?employee_id=" +
            this.employee_id +
            "&check_in_time=" +
            Date.parse(thisDay)
        );
        if (response.data.SUCCESS) {
          this.success = true;
        } else {
          this.dialogError = true;
        }
      } else {
        this.dialogWarning = true;
      }
    },
    async check_out(time) {
      if (this.selectedEmployee !== null) {
        var thisDay = new Date();
        thisDay.setHours(time.substring(0, 2));
        thisDay.setMinutes(time.substring(3, 5));
        const response = await axios.post(
          "/check/out?employee_id=" +
            this.employee_id +
            "&check_out_time=" +
            Date.parse(thisDay) +
            "&thisDate=" +
            Date.parse(new Date().toISOString().substr(0, 10))
        );
        if (response.data.SUCCESS) {
          this.success = true;
        } else {
          this.dialogError = true;
        }
      } else {
        this.dialogWarning = true;
      }
    },
  },
  async mounted() {
    const response = await axios.get("/employee/listForOwner");
    this.employees = await response.data.employees;
  },
};
</script>

<style scoped>
.main {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 80vh;
}
</style>
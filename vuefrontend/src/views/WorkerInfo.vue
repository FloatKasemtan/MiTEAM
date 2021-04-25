<template>
  <section class="destination">
    <h1 class="d-flex ml-7">
      <v-btn icon to="/team" class="mt-5 pa-5"
        ><v-icon elevation="2" color="black" large
          >mdi-chevron-left
        </v-icon></v-btn
      >
      <div class="headerMember ml-5">
        {{ this.team.name }}
      </div>
      <v-btn
        @click="editTeam"
        class="ml-5 mt-5"
        elevation="2"
        rounded
        color="white"
        ><v-icon>mdi-clipboard-edit</v-icon></v-btn
      >
    </h1>
    <div class="mx-16">
      <div>
        <v-row class="justify-space-between">
          <v-col cols="12" md="4">
            <v-text-field
              color="black"
              v-model="seach"
              label="Seach"
              class="ml-8"
            ></v-text-field>
          </v-col>
          <v-btn
            fixed
            right
            elevation="2"
            color="white"
            class="mr-2"
            fab
            @click="dialog = !dialog"
          >
            <v-icon color="#3949AB">mdi-plus</v-icon>
          </v-btn>
        </v-row>
        <v-row>
          <v-col cols="12" md="4"
            ><v-slider
              class="mt-5 ml-7"
              v-model="this.team.progress"
              label="Work Progress"
              track-color="green lighten-1"
              thumb-label="always"
            ></v-slider
          ></v-col>
          <v-col
            ><v-btn icon color="green">
              <v-icon>mdi-cached</v-icon>
            </v-btn></v-col
          >
        </v-row>
      </div>
      <EmployeeList
        :statusItems="statusItems"
        :managers="managers"
        :seachMember="seachMember"
      />

      <v-dialog v-model="editTeamName" max-width="500px">
        <v-card>
          <v-card-text class="pa-5">
            <h1>Edit Team</h1>
            <v-text-field
              label="Team Name"
              v-model="editedTeam"
              color="#31517d"
            >
            </v-text-field>
          </v-card-text>
          <v-card-actions>
            <v-btn text color="#3949AB" @click="confirmEditTeam">
              Comfirm Edit
            </v-btn>
            <v-spacer></v-spacer>
            <v-btn text depressed color="error"> Delete </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      <v-dialog v-model="dialog" max-width="500px">
        <v-card>
          <v-card-text>
            <h1 class="pt-5">Add new member</h1>
            <div class="d-flex">
              <v-text-field
                class="pa-2"
                label="Firstname"
                color="black"
                v-model="addMember.fname"
                prepend-icon="mdi-account-group"
                @keyup.enter="add"
              ></v-text-field
              ><v-text-field
                class="pa-2"
                label="Lastname"
                color="black"
                v-model="addMember.lname"
                prepend-icon="mdi-account-group"
                @keyup.enter="add"
              ></v-text-field>
            </div>

            <v-text-field
              type="number"
              class="pa-2"
              label="Salary"
              color="black"
              v-model="addMember.salary"
              prepend-icon="mdi-currency-usd"
              @keyup.enter="add"
            ></v-text-field>
            <v-select
              :items="statusItems"
              dense
              class="pa-2"
              label="Status"
              color="black"
              v-model="addMember.status"
              prepend-icon="mdi-list-status"
              @keyup.enter="add"
            ></v-select>
            <v-text-field
              class="pa-2"
              label="Email"
              color="black"
              v-model="addMember.email"
              prepend-icon="mdi-email"
              @keyup.enter="add"
            ></v-text-field>
            <v-text-field
              class="pa-2"
              label="Insert Url"
              color="black"
              v-model="addMember.image"
              prepend-icon="mdi-camera"
              @keyup.enter="add"
            ></v-text-field>
            <v-checkbox
              class="ml-2"
              v-model="addMember.is_manager"
              :label="`Manager`"
            ></v-checkbox>
          </v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>

            <v-btn text color="#3949AB" @click="add"> Add Member </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>

      <v-dialog v-model="warning" max-width="300px">
        <v-alert border="bottom" elevation="10" type="warning" class="mb-0"
          >insert all required info first!</v-alert
        >
      </v-dialog>
    </div>
  </section>
</template>

<script>
import axios from "@/axios/axios";
import qs from "qs";
export default {
  components: {
    EmployeeList: () => import("../components/WorkerInfo/EmployeeList"),
  },
  name: "TeamsName",
  data: () => ({
    team: null,
    dialog: false,
    managers: [],
    employees: [],
    editTeamName: false,
    warning: false,
    seach: "",
    statusItems: ["Worker", "Intern Student", "Part-Time"],
    editedTeam: "",
    addMember: {
      fname: "",
      lname: "",
      image: "",
      salary: "",
      status: "",
      email: "",
      is_manager: false,
    },
  }),
  computed: {
    seachMember: function () {
      return this.employees.filter((member) => {
        return (member.firstname + " " + member.lastname)
          .toLowerCase()
          .includes(this.seach.toLowerCase());
      });
    },
  },
  methods: {
    async add() {
      if (
        this.addMember.name === "" ||
        this.addMember.salary === "" ||
        this.addMember.status === ""
      ) {
        this.dialog = false;
        this.warning = true;
      } else {
        this.dialog = false;
        await axios.post(
          "/employee/insert",
          qs.stringify({
            fname: this.addMember.fname,
            lname: this.addMember.lname,
            image: this.addMember.image,
            salary: this.addMember.salary,
            status: this.addMember.status,
            email: this.addMember.email,
            is_manager: this.addMember.is_manager,
            team_id: this.$route.params.id,
          })
        );
        location.reload();
      }
    },

    editTeam() {
      this.editTeamName = true;
      this.editedTeam = this.team.name;
    },
    confirmEditTeam() {
      this.editTeamName = false;
      this.team.name = this.editedTeam;
    },
  },
  async mounted() {
    await this.$store.dispatch("loadTeamData");
    this.team = this.$store.state.teams.find(
      (team) => team.team_id == this.$route.params.id
    );
    const response = await axios.get(
      "/employee/list?id=" + this.$route.params.id
    );
    this.employees = response.data.employees;
    const manager = await axios.get(
      "/employee/listManager?id=" + this.$route.params.id
    );
    this.managers = manager.data.employees;
    console.log(manager.data.employees);
  },
};
</script>
<style scoped>
.headerMember {
  font-size: 50px;
  color: #31517d;
}
</style>
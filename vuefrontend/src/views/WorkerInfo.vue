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
              v-model="progress"
              label="Work Progress"
              :color="color"
              thumb-label="always"
            ></v-slider
          ></v-col>
          <v-col
            ><v-btn icon color="green" @click="updateProgress()">
              <v-icon>mdi-cached</v-icon>
            </v-btn></v-col
          >
        </v-row>
      </div>
      <EmployeeList
        :statusItems="statusItems"
        :managers="managers"
        :seachMember="seachMember"
        @warning="warning = true"
      />

      <v-dialog v-model="editTeamDialog" max-width="500px">
        <v-card>
          <v-card-text class="pa-5">
            <h1>Edit Team</h1>
            <v-text-field
              label="Team Name"
              v-model="edit_teamName"
              color="#31517d"
            >
            </v-text-field>
            <v-text-field
              label="Image Url"
              v-model="edit_image"
              color="#31517d"
            >
            </v-text-field>
          </v-card-text>
          <v-card-actions>
            <v-btn text color="#3949AB" @click="confirmEditTeam">
              Comfirm Edit
            </v-btn>
            <v-spacer></v-spacer>
            <v-btn
              @click="deleteTeamDialog = !deleteTeamDialog"
              text
              depressed
              color="error"
            >
              Delete
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>

      <v-dialog v-model="deleteTeamDialog" max-width="550px">
        <v-card>
          <v-card-title class="headline"
            >Are you sure you want to delete this team?</v-card-title
          >
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text @click="deleteTeamDialog = false"
              >Cancel</v-btn
            >
            <v-btn color="red darken-1" text @click="comfirmDelete()">OK</v-btn>
            <v-spacer></v-spacer>
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
export default {
  components: {
    EmployeeList: () => import("../components/WorkerInfo/EmployeeList"),
  },
  name: "TeamsName",
  data: () => ({
    team: null,
    progress: null,
    dialog: false,
    managers: [],
    employees: [],
    editTeamDialog: false,
    deleteTeamDialog: false,
    warning: false,
    seach: "",
    statusItems: ["Worker", "Intern Student", "Part-Time"],
    edit_teamName: null,
    edit_image: null,
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
    color() {
      if (this.progress < 35) return "orange";
      if (this.progress < 80) return "teal";
      return "green";
    },
  },
  methods: {
    async add() {
      if (
        this.addMember.fname === "" ||
        this.addMember.lname === "" ||
        this.addMember.email === "" ||
        this.addMember.salary === "" ||
        this.addMember.status === ""
      ) {
        this.dialog = false;
        this.warning = true;
      } else {
        this.dialog = false;
        await axios.post("/employee/insert", {
          fname: this.addMember.fname,
          lname: this.addMember.lname,
          image: this.addMember.image,
          salary: this.addMember.salary,
          status: this.addMember.status,
          email: this.addMember.email,
          is_manager: this.addMember.is_manager,
          team_id: this.$route.params.id,
        });
        location.reload();
      }
    },
    async updateProgress() {
      const response = await axios.put(
        "/team/updateProgress?progress=" +
          this.progress +
          "&id=" +
          this.$route.params.id
      );
      console.log(response.data, this.$route.params.id, this.progress);
    },
    editTeam() {
      this.editTeamDialog = true;
      this.edit_teamName = this.team.name;
      this.edit_image = this.team.image;
    },
    async confirmEditTeam() {
      this.editTeamDialog = false;
      const response = await axios.put(
        "/team/update?name=" +
          this.edit_teamName +
          "&id=" +
          this.$route.params.id +
          "&image=" +
          this.edit_image
      );
      location.reload();
    },

    async comfirmDelete() {
      this.editTeamDialog = false;
      const response = await axios.delete(
        "/team/delete?id=" + this.$route.params.id
      );
      location.href = "/team";
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
    this.employees = await response.data.employees;
    const manager = await axios.get(
      "/employee/listManager?id=" + this.$route.params.id
    );
    this.managers = await manager.data.employees;
    this.progress = await this.team.progress;
  },
};
</script>
<style scoped>
.headerMember {
  font-size: 50px;
  color: #31517d;
}
</style>
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
            <v-slider
              class="mt-5 ml-7"
              v-model="this.team.progress"
              label="Work Progress"
              track-color="green lighten-1"
              thumb-label="always"
              @change="updateProgress"
            ></v-slider>
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
      </div>
      <v-row class="">
        <v-col cols="12" md="3">
          <v-card
            min-width="190"
            max-width="500"
            outlined
            style="border-radius: 15px"
            elevation="2"
            color="#426ca6"
            dark
          >
            <v-list-item three-line>
              <v-list-item-avatar
                tile
                size="100"
                color="grey"
                style="border-radius: 15px"
                ><v-img
                  v-if="team.manager.pic != ''"
                  :src="team.manager.pic"
                  alt="can't load pic"
                  aspect-ratio="2"
                ></v-img
              ></v-list-item-avatar>

              <v-list-item-content>
                <div class="overline mb-4">Manager</div>
                <v-list-item-title class="headline mb-1">
                  {{ team.manager.name }}
                </v-list-item-title>
                <v-list-item-subtitle
                  >Salary : {{ team.manager.salary }} Baht</v-list-item-subtitle
                >
                <v-list-item-subtitle
                  >Status : {{ team.manager.status }}</v-list-item-subtitle
                >
                <v-list-item-subtitle
                  >Work Period :
                  {{ team.manager.period }} Year</v-list-item-subtitle
                >
                <v-list-item-subtitle
                  >Contact : {{ team.manager.contact }}</v-list-item-subtitle
                >
              </v-list-item-content>
            </v-list-item>

            <v-card-actions>
              <v-btn
                outlined
                rounded
                text
                @click="editInfo(team.manager)"
                style="border: 1.5px white solid"
              >
                edit
              </v-btn>
              <v-spacer></v-spacer>
              <v-btn
                outlined
                rounded
                color="white"
                @click="deleteItem(team.manager.id)"
              >
                <v-icon>mdi-trash-can</v-icon>
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-col>
        <v-col cols="12" md="3" v-for="member in seachMember" :key="member.id">
          <v-card
            min-width="190"
            max-width="500"
            outlined
            style="border-radius: 15px"
            elevation="2"
          >
            <v-list-item three-line>
              <v-list-item-avatar
                tile
                size="100"
                color="grey"
                style="border-radius: 15px"
                ><v-img
                  v-if="member.pic != ''"
                  :src="member.pic"
                  alt="can't load pic"
                  aspect-ratio="2"
                ></v-img
              ></v-list-item-avatar>

              <v-list-item-content>
                <div class="overline mb-4">Name</div>
                <v-list-item-title class="headline mb-1">
                  {{ member.name }}
                </v-list-item-title>
                <v-list-item-subtitle
                  >Salary : {{ member.salary }} Baht</v-list-item-subtitle
                >
                <v-list-item-subtitle
                  >Status : {{ member.status }}</v-list-item-subtitle
                >
                <v-list-item-subtitle
                  >Work Period : {{ member.period }} Year</v-list-item-subtitle
                >
                <v-list-item-subtitle
                  >Contact : {{ member.contact }}</v-list-item-subtitle
                >
              </v-list-item-content>
            </v-list-item>

            <v-card-actions>
              <v-btn outlined rounded text @click="editInfo(member)">
                edit
              </v-btn>
              <v-spacer></v-spacer>
              <v-btn
                outlined
                rounded
                color="red"
                @click="deleteItem(member.id)"
              >
                <v-icon>mdi-trash-can</v-icon>
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>

      <v-dialog v-model="editTeamName" max-width="500px">
        <v-card>
          <v-card-text class="pa-5">
            <h1>Edit Team</h1>
            <v-text-field
              label="Team Name"
              v-model="this.team.name"
              color="#31517d"
            >
            </v-text-field>
          </v-card-text>
          <v-card-actions>
            <v-btn text color="#3949AB"> Comfirm Edit </v-btn>
            <v-spacer></v-spacer>
            <v-btn text depressed color="error"> Delete </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      <v-dialog v-model="dialog" max-width="500px">
        <v-card>
          <v-card-text>
            <h1 class="pt-5">Add new member</h1>
            <v-text-field
              class="pa-2"
              label="Member Name"
              color="black"
              v-model="addMember.name"
              prepend-icon="mdi-account-group"
              @keyup.enter="add"
            ></v-text-field>
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
              v-model="addMember.contact"
              prepend-icon="mdi-email"
              @keyup.enter="add"
            ></v-text-field>
            <v-text-field
              class="pa-2"
              label="Insert Url"
              color="black"
              v-model="addMember.pic"
              prepend-icon="mdi-camera"
              @keyup.enter="add"
            ></v-text-field>
          </v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>

            <v-btn text color="#3949AB" @click="add"> Add Member </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>

      <v-dialog v-model="dialogEdit" max-width="500px">
        <v-card>
          <v-card-text>
            <h1 class="pt-5">Edit member</h1>
            <v-text-field
              class="pa-2"
              label="Member Name"
              color="black"
              v-model="editMember.name"
              prepend-icon="mdi-account-group"
              @keyup.enter="editConfirm(editMember.id)"
            ></v-text-field>
            <v-text-field
              class="pa-2"
              type="number"
              label="Salary"
              color="black"
              v-model="editMember.salary"
              prepend-icon="mdi-currency-usd"
              @keyup.enter="editConfirm(editMember.id)"
            ></v-text-field>
            <v-select
              :items="statusItems"
              dense
              class="pa-2"
              label="Status"
              color="black"
              v-model="editMember.status"
              prepend-icon="mdi-list-status"
              @keyup.enter="editConfirm(editMember.id)"
            ></v-select>
            <v-text-field
              class="pa-2"
              label="Email"
              color="black"
              v-model="editMember.contact"
              prepend-icon="mdi-email"
              @keyup.enter="editConfirm(editMember.id)"
            ></v-text-field>
            <v-text-field
              class="pa-2"
              label="Insert Url"
              color="black"
              v-model="editMember.pic"
              prepend-icon="mdi-camera"
              @keyup.enter="editConfirm(editMember.id)"
            ></v-text-field>
          </v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>

            <v-btn text color="#31517d" @click="editConfirm(editMember.id)">
              Comfirm Edit
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>

      <v-dialog v-model="warning" max-width="300px">
        <v-alert border="bottom" elevation="10" type="warning" class="mb-0"
          >insert all required info first!</v-alert
        >
      </v-dialog>
      <v-dialog v-model="dialogDelete" max-width="550px">
        <v-card>
          <v-card-title class="headline"
            >Are you sure you want to delete this member?</v-card-title
          >
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text @click="dialogDelete = false"
              >Cancel</v-btn
            >
            <v-btn color="red darken-1" text @click="comfirmDelete()">OK</v-btn>
            <v-spacer></v-spacer>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>
  </section>
</template>

<script>
import info from "@/store/index";
export default {
  name: "TeamsName",
  data: () => ({
    team: null,
    dialog: false,
    editTeamName: false,
    dialogEdit: false,
    dialogDelete: false,
    warning: false,
    seach: "",
    statusItems: ["Worker", "Intern Student", "Part-Time"],

    addMember: {
      name: "",
      salary: "",
      status: "",
      contact: "",
      pic: "",
    },
    currentId: 9,
    editMember: {
      id: "",
      name: "",
      salary: "",
      status: "",
      contact: "",
      pic: "",
    },
  }),
  computed: {
    nameTeam: function () {
      const id = this.$route.params.id;
      const result = info.state.teams.find((team) => team.id == id);
      if (!result) {
        return "";
      }
      return result.name;
    },
    seachMember: function () {
      return this.team.members.filter((member) => {
        return member.name.toLowerCase().includes(this.seach.toLowerCase());
      });
    },
  },
  methods: {
    add() {
      if (
        this.addMember.name === "" ||
        this.addMember.salary === "" ||
        this.addMember.status === ""
      ) {
        this.dialog = false;
        this.warning = true;
      } else {
        this.dialog = false;
        this.team.members.push({
          id: ++this.currentId,
          name: this.addMember.name,
          salary: this.addMember.salary,
          status: this.addMember.status,
          pic: this.addMember.pic,
        });
        this.addMember.name = "";
        this.addMember.salary = "";
        this.addMember.status = "";
        this.addMember.pic = "";
      }
    },
    deleteItem(id) {
      this.dialogDelete = true;
      this.deleteIndex = id;
    },
    comfirmDelete() {
      this.members = this.members.filter((el) => el.id != this.deleteIndex);
      this.dialogDelete = false;
    },
    editTeam() {
      this.editTeamName = true;
    },
    editInfo(member) {
      this.dialogEdit = true;
      this.editMember = { ...member };
    },
    editConfirm(id) {
      this.dialogEdit = false;
      const indexMember = this.members.findIndex((el) => el.id == id);
      this.members[indexMember] = this.editMember;
      this.members = [...this.members];
    },
  },
  mounted() {
    this.team = info.state.teams.find((team) => team.id == this.$route.params.id);
  },
};
</script>
<style scoped>
.headerMember {
  font-size: 50px;
  color: #31517d;
}
</style>
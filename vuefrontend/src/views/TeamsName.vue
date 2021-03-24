<template>
  <div class="mx-16">
    <div class="ml-8">
      <h1 class="header">Teams List</h1>
      <div>
        <v-row>
          <v-col cols="12" md="4">
            <v-text-field
              color="black"
              v-model="seach"
              label="Seach"
            ></v-text-field>
          </v-col>
          <v-btn right fixed class="mr-2" fab @click="dialog = !dialog">
            <v-icon color="#3949AB">mdi-plus</v-icon>
          </v-btn>
        </v-row>
      </div>
    </div>
    <Teamslist :seachTeam="seachTeam" />
    <v-dialog v-model="dialog" max-width="500px">
      <v-card>
        <v-card-text>
          <h1 class="pt-5">Create new Team</h1>
          <v-text-field
            class="pa-5"
            label="Team Name"
            color="black"
            v-model="addInput"
            prepend-icon="mdi-account-group"
            @keyup.enter="addTeam"
          ></v-text-field>
          <v-text-field
            class="pa-5"
            label="Insert image url"
            color="black"
            v-model="addImg"
            prepend-icon="mdi-camera"
            @keyup.enter="addTeam"
          ></v-text-field>
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn text color="#3949AB" @click="addTeam()"> Add Team </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-dialog v-model="warning" max-width="300px">
      <v-alert border="bottom" elevation="10" type="warning" class="mb-0"
        >insert Team name first!</v-alert
      >
    </v-dialog>
  </div>
</template>

<script>
import info from "../store/teamInfo";
import Teamslist from "../components/TeamsList";
export default {
  name: "TeamsName",
  components:{
    Teamslist,
  },
  data: () => ({
    seach: "",
    addInput: "",
    addImg: "",
    dialog: false,
    warning: false,
    isActive: false,
  }),
  computed: {
    seachTeam: function () {
      return info.teams.filter((team) => {
        return team.name.toLowerCase().includes(this.seach.toLowerCase());
      });
    },
  },
  methods: {
    addTeam: function () {
      if (this.addInput === "") {
        this.dialog = false;
        this.warning = true;
      } else {
        info.teams.push({
          id: ++this.currentId,
          name: this.addInput,
          members: [],
          progress: null,
          img: this.addImg,
        });
        this.addInput = "";
        this.dialog = false;
      }
    },
  },
};
</script>

<style scoped>

.v-card {
  transition: opacity 0.4s ease-in-out;
}
.team:not(.on-hover) {
  opacity: 0.8;
}
.show-btns {
  color: rgba(255, 255, 255, 1) !important;
}
</style>
<template>
  <div class="mx-16">
    <div class="ml-8">
      <Header :pageName="pageName" />
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
    <v-dialog transition="dialog-bottom-transition" v-model="dialog" max-width="500px">
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
import axios from '@/axios/axios'
export default {
  name: "TeamsName",
  components: {
    Header: () => import("../components/Header"),
    Teamslist: () => import("../components/TeamsName/TeamsList"),
  },

  data: () => ({
    pageName: "Teams List",
    seach: "",
    addInput: "",
    addImg: "",
    isActive: false,
    dialog: false,
    warning: false,
  }),
  computed: {
    seachTeam: function () {
      return this.$store.state.teams.filter((team) => {
        return team.name.toLowerCase().includes(this.seach.toLowerCase());
      });
    },
  },
  methods: {
    async addTeam() {
      if (this.addInput === "") {
        this.dialog = false;
        this.warning = true;
      } else {
        await axios.post(
          "/team/insert?name=" + this.addInput + "&image=" + this.addImg
        );
        this.addInput = "";
        this.addImg = "";
        this.dialog = false;
        location.reload();
      }
    },
  },
  mounted() {
    this.$store.dispatch("loadTeamData");
  },
};
</script>

<style scoped>
</style>
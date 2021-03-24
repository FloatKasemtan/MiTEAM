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
    <Dialog :dialog="dialog" :warning="warning" />
  </div>
</template>

<script>
import info from "../store/teamInfo";
import Teamslist from "../components/TeamsName/TeamsList";
import Dialog from "../components/TeamsName/Dialog";
export default {
  name: "TeamsName",
  components: {
    Teamslist,
    Dialog,
  },
  data: () => ({
    seach: "",
    isActive: false,
    dialog: false,
    warning: false,
  }),
  computed: {
    seachTeam: function () {
      return info.teams.filter((team) => {
        return team.name.toLowerCase().includes(this.seach.toLowerCase());
      });
    },
  },
};
</script>

<style scoped>
</style>
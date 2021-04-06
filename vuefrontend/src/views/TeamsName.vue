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
    <Dialog
      :dialog="dialog"
      :warning="warning"
      @closeDialog="dialog = false"
      @closeWarning="warning = true"
    />
  </div>
</template>

<script>
import Header from "../components/Header";
import Teamslist from "../components/TeamsName/TeamsList";
import Dialog from "../components/TeamsName/Dialog";
export default {
  name: "TeamsName",
  components: {
    Header,
    Teamslist,
    Dialog,
  },

  data: () => ({
    pageName: "Teams List",
    seach: "",
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
};
</script>

<style scoped>
</style>
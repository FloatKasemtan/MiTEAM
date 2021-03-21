<template>
  <div class="mx-16">
    <div class="ml-8">
      <h1 style="color: #31517d">Teams List</h1>
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

    <v-row class="pa-5">
      <v-col cols="12" md="3" v-for="team in seachTeam" :key="team.id">
        <v-card class="mx-auto" min-width="230" max-width="400" :elevation="10">
          <v-img
            v-if="team.img != ''"
            class="white--text justify-space-between"
            height="200px"
            :src="team.img"
          >
          </v-img>
          <v-card v-else color="grey" height="200px"> </v-card>
          <v-card-title>{{ team.name }}</v-card-title>

          <v-card-subtitle v-if="team.progress == null" class="pb-0">
            Time to do some work?
          </v-card-subtitle>
          <v-card-subtitle v-else-if="team.progress != 100" class="pb-0">
            Work Progress {{ team.progress }}%
          </v-card-subtitle>
          <v-card-subtitle v-else class="pb-0"> Work Done! </v-card-subtitle>

          <v-progress-linear
            v-if="team.progress != null"
            color="#31517D"
            buffer-value="0"
            :value="team.progress"
            stream
          ></v-progress-linear>
          <v-card-actions>
            <v-btn outlined rounded text :to="{name:'WorkerInfo', params:{id: team.id,name: team.name}}"> more info </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>

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
import info from "../store/teamInfo"
export default {
  name: "TeamsName",
  data: () => ({
    seach: "",
    addInput: "",
    addImg: "",
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

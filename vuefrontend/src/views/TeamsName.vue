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
          <v-card-subtitle class="pb-0">
            {{ team.members }} Members
          </v-card-subtitle>

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
            <v-btn outlined rounded text to="/userInfo"> more info </v-btn>
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
export default {
  name: "TeamsName",
  data: () => ({
    seach: "",
    addInput: "",
    addImg: "",
    dialog: false,
    warning: false,
    teams: [
      {
        id: 1,
        name: "Frozen Yogurt",
        members: 159,
        progress: 20,
        img:
          "https://storage.googleapis.com/gen-atmedia/3/2015/08/2bb3585735eedc2040f79a4609514e0e4af2ab3d.jpeg",
      },
      {
        id: 2,
        name: "Ice cream sandwich",
        members: 237,
        progress: 90,
        img:
          "https://hips.hearstapps.com/vidthumb/images/delish-giant-ice-cream-sandwich-cake-still002-1529699322.jpg?crop=0.716xw%3A0.718xh%3B0.151xw%2C0.134xh&resize=480%3A270",
      },
      {
        id: 3,
        name: "Eclair",
        members: 262,
        progress: 30,
        img: "http://www.ndtv.com/cooks/images/chocolate.eclairs.jpg",
      },
      {
        id: 4,
        name: "Cupcake",
        members: 305,
        progress: 100,
        img:
          "https://hips.hearstapps.com/del.h-cdn.co/assets/18/07/1518475314-vanilla-cupcake-horizontal-.jpg",
      },
      {
        id: 5,
        name: "Gingerbread",
        members: 356,
        progress: 81,
        img:
          "https://www.superhealthykids.com/wp-content/uploads/2020/11/Gingerbread-Cookies-4sml.jpg",
      },
      {
        id: 6,
        name: "Jelly bean",
        members: 375,
        progress: 40,
        img:
          "https://www.atrafloor.com/app/uploads/2017/01/jelly-bean-vinyl-flooring-multicolour.jpg",
      },
      {
        id: 7,
        name: "Lollipop",
        members: 392,
        progress: 99,
        img: "http://sc01.alicdn.com/kf/H012b3ea0f9f74449b3f33e3b6ba7a2e77.jpg",
      },
      {
        id: 8,
        name: "Honeycomb",
        members: 408,
        progress: 12,
        img:
          "https://www.grandturkishbazaar.com/wp-content/uploads/2019/01/turkish-honeycomb.jpg",
      },
      {
        id: 9,
        name: "Donut",
        members: 452,
        progress: 60,
        img:
          "https://www.starbucks.co.th/stb-media/2020/08/7.Glaze-Donut1080-1.png",
      },
      {
        id: 10,
        name: "KitKat",
        members: 518,
        progress: 50,
        img:
          "https://i.insider.com/5b5856a9c171cb21008b4639?width=1136&format=jpeg",
      },
    ],
    currentId: 10,
  }),
  computed: {
    seachTeam: function () {
      return this.teams.filter((team) => {
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
        this.teams.push({
          id: ++this.currentId,
          name: this.addInput,
          members: 0,
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

<template>
  <div>
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
import axios from "@/axios/axios";
export default {
  data: () => ({
    addInput: "",
    addImg: "",
  }),
  props: {
    dialog: Boolean,
    warning: Boolean,
  },
  methods: {
    async addTeam() {
      if (this.addInput === "") {
        this.$emit("closeDialog");
        this.$emit("cloaseWarning");
      } else {
        await axios.post("/team/insert?name=" + this.addInput + "&image=" + this.addImg);
        this.addInput = "";
        this.addImg = "";
        this.$emit("closeDialog");
        location.reload();
      }
    },
  },
};
</script>

<style>
</style>
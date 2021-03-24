<template>
  <v-row class="pa-5">
      <v-col cols="12" md="3" v-for="team in seachTeam" :key="team.id">
        <v-lazy
        v-model="isActive"
        :options="{
          threshold: .5
        }"
        min-height="200"
        transition="fade-transition"
      >
        <v-hover v-slot="{ hover }">
          <v-card
            class="mx-auto team"
            :class="{ 'on-hover': hover }"
            min-width="230"
            max-width="400"
            :elevation="hover ? 12 : 2"
          >
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
              <v-btn
                outlined
                rounded
                text
                :to="{ name: 'WorkerInfo', params: { id: team.id } }"
              >
                more info
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-hover>
        </v-lazy>
      </v-col>
    </v-row>
</template>

<script>
export default {
props:{
    seachTeam: Object
},

}
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
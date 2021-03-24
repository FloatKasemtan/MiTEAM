<template>
        <v-row class="">
        <v-col cols="12" md="3" v-for="member in seachMember" :key="member.id">
          <v-card
            min-width="190"
            max-width="500"
            outlined
            class="rounded-xl"
            :elevation="10"
          >
            <v-list-item three-line>
              <v-list-item-avatar
                tile
                size="100"
                color="grey"
                class="rounded-xl"
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
</template>

<script>
export default {
props:{
    seachMember: Object
}
}
</script>

<style>

</style>
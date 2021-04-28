<template>
  <div>
    <v-row class="">
      <v-col
        cols="12"
        md="3"
        v-for="manager in managers"
        :key="manager.employee_id"
      >
        <v-lazy
          v-model="isActive"
          :options="{
            threshold: 0.1,
          }"
          transition="fade-transition"
        >
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
                  v-if="manager.image"
                  :src="manager.image"
                  alt="can't load pic"
                  aspect-ratio="2"
                ></v-img
              ></v-list-item-avatar>

              <v-list-item-content>
                <div class="overline mb-4">Manager</div>
                <v-list-item-title class="headline mb-1">
                  {{ manager.firstname }} {{ manager.lastname }}
                </v-list-item-title>
                <v-list-item-subtitle
                  >Salary : {{ manager.salary }} Baht</v-list-item-subtitle
                >
                <v-list-item-subtitle
                  >Status : {{ manager.status }}</v-list-item-subtitle
                >
                <v-list-item-subtitle
                  >Contact : {{ manager.email }}</v-list-item-subtitle
                >
              </v-list-item-content>
            </v-list-item>

            <v-card-actions>
              <v-btn
                outlined
                rounded
                text
                @click="editInfo(manager)"
                style="border: 1.5px white solid"
              >
                edit
              </v-btn>
              <v-spacer></v-spacer>
              <v-btn
                outlined
                rounded
                color="white"
                @click="deleteItem(editMember.employee_id)"
              >
                <v-icon>mdi-trash-can</v-icon>
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-lazy>
      </v-col>
      <v-col
        cols="12"
        md="3"
        v-for="member in seachMember"
        :key="member.employee_id"
      >
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
                v-if="member.image"
                :src="member.image"
                alt="can't load image"
                aspect-ratio="2"
              ></v-img
            ></v-list-item-avatar>

            <v-list-item-content>
              <div class="overline mb-4">Name</div>
              <v-list-item-title class="headline mb-1">
                {{ member.firstname }}
                {{ member.lastname }}
              </v-list-item-title>
              <v-list-item-subtitle
                >Salary : {{ member.salary }} Baht</v-list-item-subtitle
              >
              <v-list-item-subtitle
                >Status : {{ member.status }}</v-list-item-subtitle
              >
              <v-list-item-subtitle
                >Contact : {{ member.email }}</v-list-item-subtitle
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
              @click="deleteItem(member.employee_id)"
            >
              <v-icon>mdi-trash-can</v-icon>
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
    <v-dialog v-model="dialogEdit" max-width="500px">
      <v-card>
        <v-card-text>
          <h1 class="pt-5">Edit member info</h1>
          <div class="d-flex">
            <v-text-field
              class="pa-2"
              label="Firstname"
              color="black"
              v-model="editMember.firstname"
              prepend-icon="mdi-account-group"
              @keyup.enter="editConfirm(editMember.id)"
            ></v-text-field
            ><v-text-field
              class="pa-2"
              label="Lastname"
              color="black"
              v-model="editMember.lastname"
              prepend-icon="mdi-account-group"
              @keyup.enter="editConfirm(editMember.id)"
            ></v-text-field>
          </div>

          <v-text-field
            type="number"
            class="pa-2"
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
            v-model="editMember.email"
            prepend-icon="mdi-email"
            @keyup.enter="editConfirm(editMember.id)"
          ></v-text-field>
          <v-text-field
            class="pa-2"
            label="Insert Url"
            color="black"
            v-model="editMember.image"
            prepend-icon="mdi-camera"
            @keyup.enter="editConfirm(editMember.id)"
          ></v-text-field>
          <v-checkbox
            class="ml-2"
            v-model="editMember.is_manager"
            :label="`Manager`"
          ></v-checkbox>
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn text color="#31517d" @click="editConfirm(editMember.id)">
            Comfirm Edit
          </v-btn>
        </v-card-actions>
      </v-card>
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
</template>

<script>
import axios from "@/axios/axios";
export default {
  props: {
    seachMember: Object,
    managers: Array,
    statusItems: Array,
  },
  data: () => ({
    dialogEdit: false,
    dialogDelete: false,
    editMember: {
      employee_id: "",
      firstname: "",
      lastname: "",
      image: "",
      salary: "",
      status: "",
      email: "",
      is_manager: Boolean,
    },
    deleteIndex: '',
  }),
  methods: {
    deleteItem(id) {
      this.dialogDelete = true;
      this.deleteIndex = id;
    },
    async comfirmDelete() {
      const response = await axios.delete("/employee/delete?id="+this.deleteIndex);
      this.dialogDelete = false;
      location.reload();
    },
    editInfo(member) {
      this.dialogEdit = true;
      this.editMember = { ...member };
    },
    async editConfirm(id) {
      if (
        this.editMember.firstname === "" ||
        this.editMember.lastname === "" ||
        this.editMember.email === "" ||
        this.editMember.salary === "" ||
        this.editMember.status === ""
      ) {
        this.dialogEdit = false;
        this.$emit("warning");
      } else {
        this.dialogEdit = false;
        const response = await axios.put("/employee/update", {
          fname: this.editMember.firstname,
          lname: this.editMember.lastname,
          image: this.editMember.image,
          salary: this.editMember.salary,
          status: this.editMember.status,
          email: this.editMember.email,
          is_manager: this.editMember.is_manager,
          employee_id: this.editMember.employee_id,
        });
        console.log(response);
        location.reload();
      }
    },
  },
};
</script>

<style>
</style>
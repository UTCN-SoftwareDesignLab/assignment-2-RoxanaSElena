<template>
  <v-card>
    <v-card-title>
      Store<v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search Book"
        single-line
        hide-details
      >
      </v-text-field>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="items"
      :search="search"
      @click:row="sellItem"
    >
    </v-data-table>
    <SellDialog
      :opened="dialogVisible"
      :item="selectedItem"
      @refresh="refreshList"
    ></SellDialog>
  </v-card>
</template>

<script>
import api from "../api";
import SellDialog from "../components/SellDialog";
export default {
  name: "BookList",
  components: { SellDialog },
  data() {
    return {
      items: [],
      search: "",
      headers: [
        {
          text: "author",
          align: "start",
          sortable: false,
          value: "author",
        },

        { text: "title", value: "title" },
        { text: "genre", value: "genre" },
        { text: "price", value: "price" },
        { text: "quantity", value: "quantity" },
      ],
      dialogVisible: false,
      selectedItem: {},
    };
  },
  methods: {
    async refreshList() {
      console.log("refresh");
      this.dialogVisible = false;
      this.selectedItem = {};
      this.items = await api.sell.allItems();
    },
    sellItem(item) {
      this.selectedItem = item;
      this.dialogVisible = true;
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

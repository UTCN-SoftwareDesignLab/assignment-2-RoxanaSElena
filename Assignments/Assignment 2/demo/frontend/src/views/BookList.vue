<template>
  <v-card>
    <v-card-title>
      Books<v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search Book"
        single-line
        hide-details
      >
      </v-text-field>
      <v-btn @click="addItem"> New Book </v-btn>
      <v-spacer></v-spacer>
      <v-btn @click="OutOfStockCsv"> Download CSV</v-btn>
      <v-spacer></v-spacer>
      <v-btn @click="OutOfStockPdf">Download PDF </v-btn>
      <v-spacer></v-spacer>
      <v-btn @click="staff"> Staff </v-btn>
      <v-spacer></v-spacer>
      <v-btn @click="newBooks"> Find Books </v-btn>
      <v-spacer></v-spacer>

    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="items"
      :search="search"
      @click:row="editItem"
    >
    </v-data-table>
    <ItemDialog
      :opened="dialogVisible"
      :item="selectedItem"
      @refresh="refreshList"
    ></ItemDialog>
  </v-card>
</template>

<script>
import api from "../api";
import ItemDialog from "../components/ItemDialog";
import router from "../router";
export default {
  name: "BookList",
  components: { ItemDialog },
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
      this.items = await api.books.allItems();
    },

    OutOfStockCsv() {
      api.report.getCSV();
    },

    OutOfStockPdf() {
      api.report.getPDF();
    },

    editItem(item) {
      this.selectedItem = item;
      this.dialogVisible = true;
    },

    addItem() {
      this.dialogVisible = true;
    },

    staff() {
      router.push("/Users");
    },

    newBooks() {
      router.push("/googleapi");
    },
  },

  created() {
    this.refreshList();
  },
};
</script>

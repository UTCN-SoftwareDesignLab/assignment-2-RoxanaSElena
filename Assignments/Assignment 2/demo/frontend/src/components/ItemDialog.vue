<template>
  <v-dialog
    transition="dialog-bottom-transition"
    max-width="600"
    :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Create item" : "Edit item" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="item.author" label="Author" />
          <v-text-field v-model="item.title" label="Title" />
          <v-text-field v-model="item.genre" label="Genre" />
          <v-text-field v-model="item.quantity" label="Quantity" />
          <v-text-field v-model="item.price" label="Price" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
          <v-btn v-if="!isNew" @click="deleteItem"> Delete </v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "ItemDialog",
  props: {
    item: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        console.log("merge");
        api.books
          .create({
            author: this.item.author,
            title: this.item.title,
            genre: this.item.genre,
            quantity: this.item.quantity,
            price: this.item.price,
          })
          .then(() => this.$emit("refresh"));
      } else {
        api.books
          .edit({
            id: this.item.id,
            author: this.item.author,
            title: this.item.title,
            genre: this.item.genre,
            quantity: this.item.quantity,
            price: this.item.price,
          })
          .then(() => this.$emit("refresh"));
      }
    },
    deleteItem() {
      api.books.delete(this.item.id).then((response) => {
        if (response == true) this.$emit("refresh");
      });
    },
  },
  computed: {
    isNew: function () {
      return !this.item.id;
    },
  },
};
</script>
<style scoped></style>

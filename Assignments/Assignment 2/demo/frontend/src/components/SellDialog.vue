<template>
  <v-dialog
    transition="dialog-bottom-transition"
    max-width="600"
    :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ "Sell book" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="item.author" label="Author" />
          <v-text-field v-model="item.title" label="Title" />
          <v-text-field v-model="item.genre" label="Genre" />
          <v-text-field v-model="item.quantity" label="Quantity" />
          <v-text-field v-model="item.price" label="Price" />
          <v-text-field v-model="amount" label="Amount" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ "Sell" }}
          </v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "SellDialog",
  props: {
    item: Object,
    amount: Number,
    opened: Boolean,
  },
  methods: {
    persist() {
      api.sell
        .sell(
          {
            title: this.item.title,
            author: this.item.author,
            genre: this.item.genre,
            price: this.item.price,
            quantity: this.item.quantity,
          },
          this.amount
        )
        .then(() => this.$emit("refresh"));
    },
  },
};
</script>
<style scoped></style>

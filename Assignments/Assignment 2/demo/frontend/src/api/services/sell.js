import authHeader, { BASE_URL, HTTP } from "../http";

export default {
  allItems() {
    return HTTP.get(BASE_URL + "/sell", { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  sell(book, amount) {
    return HTTP.patch(BASE_URL + "/sell/" + amount, book, {
      headers: authHeader(),
    }).then((response) => {
      return response.data;
    });
  },
};

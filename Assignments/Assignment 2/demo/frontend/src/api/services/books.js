/* eslint-disable prettier/prettier */
import authHeader, { BASE_URL, HTTP } from "../http";

export default {
  allItems() {
    return HTTP.get(BASE_URL + "/fo", { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  create(item) {
    return HTTP.post(BASE_URL + "/fo", item, { headers: authHeader() }).then(
      (response) => {
        console.log(response.data)
        return response.data;
      }
    );
  },
  edit(item) {
    return HTTP.put(BASE_URL + "/fo", item, { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },

  delete(id)
  {
    console.log("id:" + id);
    return HTTP.delete(BASE_URL + "/fo/" + id, {headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },

};

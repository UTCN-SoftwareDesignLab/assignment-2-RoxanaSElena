import authHeader, { BASE_URL, HTTP } from "../http";
export default {
  getPDF() {
    console.log("ciao");
    return HTTP.get(BASE_URL + "/fo/export/PDF", {
      headers: authHeader(),
    }).then((response) => {
      return response.data;
    });
  },

  getCSV() {
    return HTTP.get(BASE_URL + "/fo/export/CSV", {
      headers: authHeader(),
    }).then((response) => {
      return response.data;
    });
  },
};

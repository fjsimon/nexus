import { api } from "./AuthenticationService";

class BookDataService {

    retrieveBooks() {
        return api.get(`/books/scan`);
    }

    retrieveBook(path) {

        return api.get(`/books/resource?path=` + path);
    }

    getBookInfo(isbn) {

        return api.get(`/books/info?isbn=` + isbn);
    }
}

export default new BookDataService()
import axios from 'axios'

class BookDataService {

    retrieveBooks() {
        return axios.get(`${process.env.API_URL}/books/scan`);
    }

    retrieveBook(path) {

        return axios.get(`${process.env.API_URL}/books/resource?path=` + path);
    }

    getBookInfo(isbn) {

        return axios.get(`${process.env.API_URL}/books/info?isbn=` + isbn);
    }
}

export default new BookDataService()
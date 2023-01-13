import axios from 'axios'

class BookDataService {

    retrieveBooks() {
        return axios.get(`${process.env.API_URL}/books/scan`);
    }

    retrieveBook(path) {

        return axios.get(`${process.env.API_URL}/books/resource?path=` + path);
    }
}

export default new BookDataService()
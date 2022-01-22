import axios from 'axios'

class BookDataService {

    retrieveBooks() {
        return axios.get(`${process.env.API_URL}/books/scan`);
    }
}

export default new BookDataService()
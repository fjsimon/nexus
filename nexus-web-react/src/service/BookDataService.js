import axios from 'axios'

const API_URL = 'http://localhost:8080'

class BookDataService {

    retrieveBooks() {
        return axios.get(`${API_URL}/books`);
    }
}

export default new BookDataService()
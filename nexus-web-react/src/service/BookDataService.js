import axios from 'axios'

class BookDataService {

    retrieveBooks() {
        return axios.get(`${process.env.API_URL}/books`);
    }
}

export default new BookDataService()
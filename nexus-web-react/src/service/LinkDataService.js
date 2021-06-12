import axios from 'axios'

const API_URL = 'http://localhost:8080'

class LinkDataService {

    saveLink(link) {

        console.log(link)

        const payload = {
            'link': link
        };

        return axios.post(`${API_URL}/links`, payload);
    }

    retrieveLinks(page) {

        return axios.get(`${API_URL}/links?page=${page}&size=10`);
    }
}

export default new LinkDataService()
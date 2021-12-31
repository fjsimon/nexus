import axios from 'axios'


class LinkDataService {

    saveLink(link) {

        console.log(link)

        const payload = {
            'link': link
        };

        return axios.post(`${process.env.API_URL}/links`, payload);
    }

    retrieveLinks(page) {

        return axios.get(`${process.env.API_URL}/links?page=${page}&size=10`);
    }

    deleteLinks(linkIds) {

        return axios.delete(`${process.env.API_URL}/links?nodeLinkIds=${linkIds}`);
    }
}

export default new LinkDataService()
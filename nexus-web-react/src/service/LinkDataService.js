import { api } from "./AuthenticationService";

class LinkDataService {

    saveLink(link) {

        console.log(link)

        const payload = {
            'link': link
        };

        return api.post(`/links`, payload);
    }

    retrieveLinks(page, size) {

        return api.get(`/links?page=${page}&size=${size}`);
    }

    deleteLinks(linkIds) {

        return api.delete(`/links?nodeLinkIds=${linkIds}`);
    }

    downloadLinks() {

        return api.get(`/links/resource`);
    }
}

export default new LinkDataService()
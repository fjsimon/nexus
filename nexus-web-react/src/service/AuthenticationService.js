import axios from 'axios'

export const USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'

let currentAccessToken = null;

class AuthenticationService {

    executeBasicAuthenticationService(username, password) {
        return axios.get(`${process.env.API_URL}/basicauth`,
            { headers: { authorization: this.createBasicAuthToken(username, password) } })
    }

    executeJwtAuthenticationService(username, password) {

        const payload = {
            username: username,
            password: password
        };

        return axios.post(`${process.env.API_URL}/users/signin`, payload);
    }

    createBasicAuthToken(username, password) {
        return 'Basic ' + window.btoa(username + ":" + password)
    }

    registerSuccessfulLogin(username, password) {
        //let basicAuthHeader = 'Basic ' +  window.btoa(username + ":" + password)
        //console.log('registerSuccessfulLogin')
        sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_NAME, username);
        currentAccessToken = this.createBasicAuthToken(username, password);
    }

    registerSuccessfulLoginForJwt(username, token) {
        sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_NAME, username);
        currentAccessToken = this.createJWTToken(token);
    }

    createJWTToken(token) {
        return 'Bearer ' + token
    }

    logout() {
        sessionStorage.removeItem(USER_NAME_SESSION_ATTRIBUTE_NAME);
        currentAccessToken = null;
    }

    isUserLoggedIn() {
        return sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME) !== null;
    }

    getLoggedInUserName() {
        let user = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME)
        if (user === null) return ''
        return user
    }

    setupAxiosInterceptors() {
        // Request interceptor
        axios.interceptors.request.use(
            (config) => {
                if (this.isUserLoggedIn() && currentAccessToken) {
                    config.headers.authorization = currentAccessToken;
                }
                return config;
            }
        );

        // Response interceptor (auto-refresh if expired)
        axios.interceptors.response.use(
            response => response,
            async error => {
                const originalRequest = error.config;

                if (error.response && error.response.status === 401 && !originalRequest._retry) {
                    originalRequest._retry = true;

                    try {
                        const res = await axios.get(`${process.env.API_URL}/users/refresh`, {
                            withCredentials: true
                        });

                        const newToken = res.data.accessToken;

                        currentAccessToken = this.createJWTToken(newToken);

                        originalRequest.headers.authorization = currentAccessToken;

                        return axios(originalRequest);

                    } catch (refreshError) {
                        this.logout();
                        window.location = "/login";
                    }
                }

                return Promise.reject(error);
            }
        );
    }
}

export default new AuthenticationService()
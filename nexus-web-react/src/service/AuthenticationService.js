import axios from 'axios';

export const USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser';

const api = axios.create({
  baseURL: process.env.API_URL,
  withCredentials: true,
});

let currentAccessToken = null;
let isRefreshing = false;
let isLoggedOut = false;
let failedQueue = [];

const processQueue = (error, token = null) => {
  failedQueue.forEach(p =>
    error ? p.reject(error) : p.resolve(token)
  );
  failedQueue = [];
};

class AuthenticationService {

  executeBasicAuthenticationService(username, password) {
    return api.get(
      `/basicauth`,
      { headers: { authorization: this.createBasicAuthToken(username, password) } }
    );
  }

  executeJwtAuthenticationService(username, password) {
    return api.post(`/users/login`, { username, password });
  }

  createBasicAuthToken(username, password) {
    return 'Basic ' + window.btoa(username + ':' + password);
  }

  registerSuccessfulLogin(username, password) {
    sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_NAME, username);
    currentAccessToken = this.createBasicAuthToken(username, password);
  }

  registerSuccessfulLoginForJwt(username, token) {
    sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_NAME, username);
    currentAccessToken = this.createJWTToken(token);
  }

  createJWTToken(token) {
    return 'Bearer ' + token;
  }

  logout() {
    isLoggedOut = true;
    sessionStorage.removeItem(USER_NAME_SESSION_ATTRIBUTE_NAME);
    currentAccessToken = null;
    delete api.defaults.headers.common.authorization;
    processQueue(new Error("User logged out"));
    api.post("/users/logout").catch(() => {});
  }

  isUserLoggedIn() {
    return sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME) !== null;
  }

  setupAxiosInterceptors() {
    /* ---------- Request interceptor ---------- */
    api.interceptors.request.use(config => {

      console.log("API request:", config.url);

      if (this.isUserLoggedIn() && currentAccessToken) {
        config.headers.authorization = currentAccessToken;
      }
      return config;
    });

    /* ---------- Response interceptor ---------- */
    api.interceptors.response.use(
      response => response,
      async error => {
        const originalRequest = error.config;

        if (!originalRequest) {
          return Promise.reject(error);
        }

        if (originalRequest.url === '/users/refresh') {
          return Promise.reject(error);
        }

        if (error.response?.status === 403 && !originalRequest._retry) {
          originalRequest._retry = true;

          if (isRefreshing) {
            return new Promise((resolve, reject) => {
              failedQueue.push({ resolve, reject });
            }).then(token => {
              originalRequest.headers.authorization = token;
              return api(originalRequest);
            });
          }

          isRefreshing = true;

          try {
            const res = await api.post('/users/refresh');

            const newToken = this.createJWTToken(res.data);
            currentAccessToken = newToken;

            api.defaults.headers.common.authorization = newToken;
            processQueue(null, newToken);

            originalRequest.headers.authorization = newToken;
            return api(originalRequest);

          } catch (refreshError) {
            processQueue(refreshError);
            this.logout();
            window.location.href = '/login';
            return Promise.reject(refreshError);

          } finally {
            isRefreshing = false;
          }
        }

        return Promise.reject(error);
      }
    );
  }
}

export default new AuthenticationService();
export { api };
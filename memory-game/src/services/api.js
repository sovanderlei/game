import axios from 'axios';
//npm install cors

const api = axios.create({
    baseURL: 'http://localhost:8000',  
    timeout: 10000,                     
});
 
api.interceptors.request.use((config) => {
    const token = localStorage.getItem('token');
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});

export default api;

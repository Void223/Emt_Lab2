import axios from "axios";

const instance = axios.create({
    baseURL: 'https://emtlab173148.herokuapp.com/api',
    headers: {
        'Access-Control-Allow-Origin' : '*'
    }
})

export default instance;
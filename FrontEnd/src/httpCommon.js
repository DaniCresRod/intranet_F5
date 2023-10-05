import axios from "axios";
import { getToken } from "./services/TokenService";

export default axios.create({    

    baseURL:"http://localhost:8080",
    headers: {
        "Content-type": "application/json",
        'Authorization': getToken()
    }
})
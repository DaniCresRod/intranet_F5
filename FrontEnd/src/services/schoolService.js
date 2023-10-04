import http from "../httpCommon";
import axios from "axios";

const httpClient = axios.create({
    http,
    headers: {},
    });

const schoolService = {
    getSchools: async () => {
        try {
            const response = await http.get("/schools");
                return await response.data;
        } catch (error) {
            throw error;
        }
    },
};

export default schoolService; 

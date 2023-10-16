import http from "../httpCommon";

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

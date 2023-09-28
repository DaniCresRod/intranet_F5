import http from '../httpCommon';

export default({


    getById(Id) {
        return http.get(`/users/${Id}`);
    },
    
    post(Data) {
        return http.post(`/request`, Data);
    },
    
});
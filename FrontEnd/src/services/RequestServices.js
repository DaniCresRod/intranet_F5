import http from '../httpCommon';

export default({


    getById(id) {
        return http.get(`/users/${id}`);
    },
    
    post(Data) {
        return http.post(`/request`, Data);
    },
    
    getAll(){
        return http.get(`/request`);
    },
});
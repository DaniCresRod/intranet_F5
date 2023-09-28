import http from '../httpCommon';

export default({

    create(Data){
        return http.post(`/request`, Data);
    },

    getAll() {
        return http.get(`/request`);
    },
      
    getById(Id) {
        return http.get(`/users/${Id}`);
    },
    
    post(Id, Data) {
        return http.post(`/request/post/${Id}`, Data);
    },
    
 
});
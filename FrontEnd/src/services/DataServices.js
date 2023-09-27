import http from '../httpCommon';

export default({

    create(Data){
        return http.post(`/#`, Data);
    },

    getAll(params) {
        return http.get(`/#`, { params });
    },
      
    getById(Id) {
        return http.get(`/#/${Id}`);
    },
    
    update(Id, Data) {
        return http.put(`/#/update/${Id}`, Data);
    },
    
    delete(Id) {
        return http.delete(`/#/delete/${Id}`);
    }
});
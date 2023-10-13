import http from '../httpCommon';

export default({

    
    getAll(){
        return http.get(`/logs`);
    },
    
});
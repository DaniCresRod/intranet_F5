import http from '../httpCommon';

export default({

    post(Data) {
        return http.post(`/users`, Data);
    },
    
});
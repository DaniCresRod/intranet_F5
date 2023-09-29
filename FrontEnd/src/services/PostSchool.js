import http from '../httpCommon';

export default({

    
    post(FormData) {
        return http.post(`/schools`,FormData);
    },
    
});
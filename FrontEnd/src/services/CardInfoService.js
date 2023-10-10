import http from '../httpCommon';

export default {
  
  getWorkerData(id) {
    return http.get(`/users/${id}`);
  },
};
  
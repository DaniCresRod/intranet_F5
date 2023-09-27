import http from '../httpCommon';

export default {
  
  getWorkerData(id) {
    return http.get(`/workers/${id}`);
  },
};

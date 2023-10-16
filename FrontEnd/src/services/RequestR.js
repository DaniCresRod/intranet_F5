import http from '../httpCommon';

export default {
  
  getHolidaysData(id) {
    return http.get(`/users/${id}`);
  },

};

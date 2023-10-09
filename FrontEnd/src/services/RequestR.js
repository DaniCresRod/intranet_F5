import http from '../httpCommon';

export default {
  
  getHolidaysData(id) {
    return http.get(`/users/${id}`);
  },

  updateUserRequestStatus(id, b) {
    return http.put(`/request/updateStatus/${id}`, b);
  },

};

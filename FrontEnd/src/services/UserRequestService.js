import http from '../httpCommon';

export default {
  
  getHolidaysData(id) {
    return http.get(`/userRequest/${id}`);
  },

  updateUserRequestStatus(id, b) {
    return http.put(`/request/updateStatus/${id}`, b);
  },

};
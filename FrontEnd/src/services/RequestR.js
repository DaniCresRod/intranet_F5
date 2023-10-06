import http from '../httpCommon';

export default {
  
  getHolidaysData(id) {
    return http.get(`/users/${id}`);
  },

  updateUserRequestStatus(id) {
    return http.put(`/request/update/${id}`, { status: b });
  },

};

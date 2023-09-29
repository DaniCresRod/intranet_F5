import http from '../httpCommon';

export default {
  
  getHolidaysData(id) {
    return http.get(`/users/2`);
  },

  updateUserRequestStatus(id, b) {
    return http.put(`/request/update/2`, { status: b });
  },

};

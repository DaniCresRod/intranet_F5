import axiosConnection from '../httpCommon';


export const getById = (Id) => {
    return axiosConnection.get(`/users/${Id}`);
};

export const updateById = (Id, Data) => {
    return axiosConnection.put(`/users/upgrade/${Id}`, Data);
};

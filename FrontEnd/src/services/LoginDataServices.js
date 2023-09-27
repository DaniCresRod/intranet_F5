import axiosConnection from '../httpCommon';

export default({

    create(Data){
        return axiosConnection.post(`/login/logo`, Data);
    },

    SignUp(Data){
        return axiosConnection.post('/login/add', Data);
    },

    saveFavs(Data){
        return axiosConnection.post('/login/favs', Data);
    }

    
})
import http from '../httpCommon';

export default {
  
  async doLogIn(payload) {
    try{
      console.log(JSON.stringify(payload));
        
        let myToken=await http.post("/auth/login", JSON.stringify(payload));
        console.log(myToken.data);

        window.localStorage.setItem("myToken_Key", myToken.data.token.toString());
        window.localStorage.setItem("myUser_Key", myToken.data.userId.toString());
        return "El token se ha traido y guardado en el LocalStorage"
    }
    catch(error){
        console.log(error);
        return "No se ha obtenido Token"
    }
  },
};
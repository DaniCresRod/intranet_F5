import http from '../httpCommon';
//import router from '../router/index'

export default {
  
  async doLogIn(payload) {
    try{
      console.log(JSON.stringify(payload));
        
        let myToken=await http.post("/auth/login", JSON.stringify(payload));
        console.log(myToken.data);

        window.localStorage.setItem("myToken_Key", myToken.data.token.toString());
        
        window.localStorage.setItem("myUser_Key", myToken.data.userId.toString());
        //return "El token se ha traido y guardado en el LocalStorage"
        //router.push("/Employee");
        return myToken.data.token;
    }
    catch(error){
        console.log(error);
        //return "No se ha obtenido Token"
        return null;
    }
  },

  async whereToGo(){
    let userId=window.localStorage.getItem("myUser_Key");
    console.log(userId);
    let myUser=await http.get(`/users/${userId}`);
    console.log(myUser.data);

    return myUser.data.userType;

  }
};
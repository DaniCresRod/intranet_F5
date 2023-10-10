import http from '../httpCommon';

export default {
  
  async doLogIn(payload) {
    try{        
        let myToken=await http.post("/auth/login", JSON.stringify(payload));

        window.localStorage.setItem("myToken_Key", myToken.data.token.toString());        
        window.localStorage.setItem("myUser_Key", myToken.data.userId.toString());

        return myToken.data.token;
    }
    catch(error){
        console.log(error);
        return null;
    }
  },

  async whereToGo(){
    let userId=window.localStorage.getItem("myUser_Key");
    let myUser=await http.get(`/users/${userId}`);
    
    return myUser.data.userType;
  }
};
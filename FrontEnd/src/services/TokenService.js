function getToken() {

    const token = localStorage.getItem("myToken_Key");
  
    if (token) {
      return "Bearer " + token;
    } else {
      return ""; 
    }
  }
  
  export { getToken };
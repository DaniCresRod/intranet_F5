function getToken() {

    const token = localStorage.getItem("myToken_Key");
    console.log(token);
    if (token) {
      return "Bearer " + token;
    } else {
      return ""; 
    }
  }
  
  export { getToken };
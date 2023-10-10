import axios from "axios";
//import { getToken } from "./services/TokenService";

// export default axios.create({    

//     baseURL:"http://localhost:8080",
//     headers: {
//         "Content-type": "application/json",
//         'Authorization': `Bearer ${localStorage.getItem("myToken_Key")}`
//     }
// })

// axios.interceptors.request.use(
//     (config) => {
//       //const token = localStorage.getItem("myToken_Key");
//       const token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJFbCBDaGF2byIsImlhdCI6MTY5Njk2MzQwMywiZXhwIjoxNjk3MDQ5ODAzfQ.s0LiL-HISpzYUq28N7wLz255wZ8TZeiVk1xhX0KlGAA";
//       console.log(token);
//       if (token) {
//         config.headers["Authorization"] = `Bearer ${token}`;
//         config.headers["Content-type"] = 'application/json';
//         config.headers["MiHeader"] = 'Ejemplo';
//       }
//       return config;
//     },
//     (error) => {
//       return Promise.reject(error);
//     }
//   );

    const instance=axios.create({
        baseURL:"http://localhost:8080",
        headers:{
            "Content-type": "application/json",
            
        }
    });

    instance.interceptors.request.use(
            (config) => {
              const token = localStorage.getItem("myToken_Key");
              //const token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJFbCBDaGF2byIsImlhdCI6MTY5Njk2MzQwMywiZXhwIjoxNjk3MDQ5ODAzfQ.s0LiL-HISpzYUq28N7wLz255wZ8TZeiVk1xhX0KlGAA";
              console.log(token);
              if (token) {
                config.headers["Authorization"] = `Bearer ${token}`;
                config.headers["Content-type"] = 'application/json';
                
              }
              return config;
            },
            (error) => {
              return Promise.reject(error);
            }
          );

  
  export default instance;
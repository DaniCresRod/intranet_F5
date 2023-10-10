import axios from "axios";
import { getToken } from "./services/TokenService";

export default axios.create({    

    baseURL:"http://localhost:8080",
    headers: {
        "Content-type": "application/json",
        'Authorization': `Bearer ${localStorage.getItem("myToken_Key")}`
    }
})

// axios.interceptors.request.use(
//     (config) => {
//       const token = localStorage.getItem("myToken_Key");
//       console.log(token);
//       if (token) {
//         config.headers["Authorization"] = `Bearer ${token}`;
//       }
//       return config;
//     },
//     (error) => {
//       return Promise.reject(error);
//     }
//   );

//     const instance=axios.create({
//         baseURL:"http://localhost:8080",
//         headers:{
//             "Content-type": "application/json",
            
//         }
//     });

  
//   export default instance;
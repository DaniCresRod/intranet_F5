import axios from "axios";

    const instance=axios.create({
        baseURL:"http://localhost:8080",
        headers:{
            "Content-type": "application/json",            
        }
    });

    //Los interceptor actuan sobre una request (o response)
    //En este caso añade el token para todas las peticiones salientes
    //Evita problemas de autenticacion relacionados con CORS
    instance.interceptors.request.use(
            (config) => {
              const token = localStorage.getItem("myToken_Key");
              
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
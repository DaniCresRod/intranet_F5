package com.intranet_F5.Config;


import com.intranet_F5.Model.UserModel;
import com.intranet_F5.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig{

    @Autowired
    UserRepository userRepository;

    /**
     * https://medium.com/@davidbernalgonzalez/la-biblia-de-spring-security-635f35bb1a26
     * @HttpSecurity: es equivalente a trabajar con un fichero XML en los que
     *                definir la seguridad de las peticiones. Por tanto, esta clase
     *                sería la alternativa mediante a la cual vamos a realizar la
     *                configuración.
     * @authorizeHttpRequests: nos permite autorizar a la petición HTTP, es decir, a
     *                         la HTTP request
     * @anyRequest: mediante el método anterior ya hemos autorizado la request, en
     *              este caso en concrecto, vamos a autorizar a todas las request,
     *              ya que con este metodo especificamos any request (cualquier
     *              request).
     * @authentificated: el usuario debe de estar autentificado.
     * @httpBasic: nos permite agregar una instancia a la cadena de filtros en la
     *             que se añadirá el filtro de BasicAutheticationFilter, que como ya
     *             hemos explicado, es el encargado de realizar una autentificación
     *             básica de la manera más típica, con una ventana de login, user y
     *             password. Cuando añadimos este método, la autentificación
     *             intentará realizar el logeo y cuando este sea satisfactorio
     *             guardará el objeto de autentificación resultante en el
     *             SegurityContextHolder para que posteriormente podamos consultar
     *             dicha autentificación en el futuro.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) //Para que funcionen los POST
                .authorizeHttpRequests((authz) -> authz
                        .anyRequest().permitAll()//.authenticated()
                )
                .httpBasic(withDefaults())
                ;

        return http.build();
    }

    /**
     * Define los roles.
     * Coge de la base de datos todos los usuarios y prepara la lista de roles
     */
//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
//        List<UserDetails> myUserDetailsList=new ArrayList<>();
//        //myUserDetailsList.add(User.withUsername("Employee").password("1234").roles("Employee").build());
//
//        List<UserModel>listOfUsers = userRepository.findAll();
//
//        for(UserModel eachUser : listOfUsers){
//            myUserDetailsList.add
//                    (User
//                    .withUsername(eachUser.getUserName())
//                    .password(eachUser.getUserPass())
//                    .roles(String.valueOf(eachUser.getUserType())
//                    ).build());
//        }
//        return new InMemoryUserDetailsManager(myUserDetailsList);
//    }

}
package com.intranet_F5.Config;

/**
 * WebSecurityConfig se encarga de crear la estructura de filtros que se encontraran las
 * peticiones antes de llegar a los controladores (Servlet)
 */

import com.intranet_F5.Repository.UserRepository;
import com.intranet_F5.jwt.AuthTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig{

    private final AuthenticationProvider authenticationProvider;
    private final AuthTokenFilter authTokenFilter;

    @Autowired
    UserRepository userRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable()) //Para que funcionen los POST
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/auth/login").permitAll()
                        //.requestMatchers("/schools").hasRole("Supervisor")
                        //.anyRequest().authenticated()
                        .anyRequest().permitAll()
                )
                .httpBasic(withDefaults());
        http
                .logout((mylogOut)->mylogOut.logoutSuccessUrl("/"));

        http
                .sessionManagement(sessionManager->
                        sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

        http
                .formLogin(myLog->myLog
                .loginPage("/")
                .defaultSuccessUrl("/EmployeeView")
                //.failureUrl("/login?error=true")
                .permitAll());

        return http.build();
    }
}

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
 *
 * @requestMatchers: establece la ruta de la API sobre la que se se va a actuar, es decir,
 *                  que queremos que suceda cuando se quiera acceder a determinada ruta:
 *                      .permitAll      -> Que a esa ruta pueda entrar cualquiera
 *                      .hasRole        -> Que a esa ruta solo pueda entrar alguien con determinado
 *                                          rol (Autorizacion).
 *                      .authenticated  ->  Solo usuarios autenticados
 */
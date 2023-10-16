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
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig{

    private final AuthenticationProvider authenticationProvider;
    private final AuthTokenFilter authTokenFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable()) //Para que funcionen los POST
//                .csrf(csrf -> csrf
//                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                        .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler()))
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers(antMatcher(HttpMethod.POST,"/schools/**")).hasRole("HR")
                        .requestMatchers(antMatcher(HttpMethod.PUT,"/schools/**")).hasRole("HR")
                        .requestMatchers(antMatcher(HttpMethod.DELETE,"/schools/**")).hasRole("HR")
                        .requestMatchers(antMatcher(HttpMethod.POST,"/users/**")).hasRole("HR")
                        .requestMatchers(antMatcher(HttpMethod.PUT,"/users/**")).hasRole("HR")
                        .requestMatchers(antMatcher(HttpMethod.DELETE,"/users/**")).hasRole("HR")
                        .requestMatchers(antMatcher(HttpMethod.POST,"/request/**")).hasRole("Formador")
                        .requestMatchers(antMatcher(HttpMethod.PUT,"/request/**")).hasAnyRole("Supervisor", "HR")
                        .requestMatchers(antMatcher(HttpMethod.DELETE,"/request/**")).hasAnyRole("Supervisor", "HR")
                        .requestMatchers(antMatcher(HttpMethod.GET,"/logs/**")).hasRole("HR")
                        .anyRequest().authenticated()
//                        .anyRequest().permitAll()

                )
                .httpBasic(withDefaults());
//        http
//                .logout((mylogOut)->mylogOut.logoutSuccessUrl("/"));

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sessionManager->
                        sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

//        http
//                .formLogin(myLog->myLog
//                //.loginPage("/")
//                .defaultSuccessUrl("/EmployeeView")
//                //.failureUrl("/login?error=true")
//                .permitAll());

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
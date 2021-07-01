package licenta.backend.config;

import licenta.backend.jwt.AuthEntryPointJwt;
import licenta.backend.jwt.AuthTokenFIlter;
import licenta.backend.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    UserService userService;

    @Resource
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFIlter authenticationJwtTokenFilter() {
        return new AuthTokenFIlter();
    }

    @Bean
    public AuthEntryPointJwt authEntryPointJwt() {
        return new AuthEntryPointJwt();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().
                antMatchers(HttpMethod.POST, "/auth/signin").permitAll().
                antMatchers(HttpMethod.POST, "/auth/signup").permitAll()
                .antMatchers(HttpMethod.GET, "/room/{checkin}/{checkout}").permitAll()
                .antMatchers(HttpMethod.GET, "/room/{checkin}/{checkout}/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/room/images").permitAll()
                .antMatchers(HttpMethod.GET, "/room/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/room/details").permitAll()
                .antMatchers(HttpMethod.GET, "/email/{username}").permitAll()
                .antMatchers(HttpMethod.GET, "/reviews/{id}").permitAll().

                antMatchers(HttpMethod.GET, "/room//freerooms/{roomtype}").permitAll().
                antMatchers(HttpMethod.GET, "/user/rooms/{id}").permitAll().
                antMatchers(HttpMethod.GET, "/user").permitAll().
                antMatchers(HttpMethod.POST, "/room/views").permitAll().
                antMatchers(HttpMethod.GET, "/room/views/{id}").permitAll().
                antMatchers(HttpMethod.POST, "/room").permitAll().
                antMatchers(HttpMethod.POST, "/room/images").permitAll().
                antMatchers(HttpMethod.PATCH, "/room/images/{id}").permitAll().
                antMatchers(HttpMethod.DELETE, "/room/images/{id}").permitAll().


                antMatchers(HttpMethod.GET, "/user/{id}").permitAll().
                antMatchers(HttpMethod.GET, "/user/hotelreview").permitAll()

                .antMatchers(HttpMethod.GET, "/reservations").permitAll()
                .antMatchers(HttpMethod.POST, "/reservations").permitAll().

                antMatchers(HttpMethod.GET, "/reviews/reviewed/{id}").permitAll().
                antMatchers(HttpMethod.POST, "/user").permitAll()

                .antMatchers(HttpMethod.POST, "/reservations").permitAll()
                .antMatchers(HttpMethod.PATCH, "/user/{id}").permitAll()
                .antMatchers(HttpMethod.PATCH, "/user/{id}/type").permitAll()
       
                .antMatchers(HttpMethod.GET, "/freeroomsbytype").permitAll().
                antMatchers(HttpMethod.GET, "/roomsbytype").permitAll()

                .antMatchers(HttpMethod.GET, "/user/emails/{email}").permitAll()
                .antMatchers(HttpMethod.PATCH, "/user/usercode/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/user/usercode/{id}").permitAll()
                .antMatchers(HttpMethod.PATCH, "/user/password/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/room/roomsbytype/{roomtype}/{checkin}/{checkout}").permitAll()
                .anyRequest().authenticated();
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}


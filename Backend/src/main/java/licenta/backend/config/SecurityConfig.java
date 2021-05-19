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
                .antMatchers(HttpMethod.GET, "/room/images").permitAll()
                .antMatchers(HttpMethod.GET, "/room/{id}").permitAll()

                .antMatchers(HttpMethod.GET, "room/days/{checkin}/{checkout}").permitAll()
                .antMatchers(HttpMethod.GET, "/reviews/{id}").permitAll()
                .antMatchers(HttpMethod.POST, "/reviews").permitAll()
                .antMatchers(HttpMethod.GET, "/prices/{checkin}/{checkout}/{id}").permitAll().
                antMatchers(HttpMethod.GET,"/prices/one/{id}").permitAll().

                antMatchers(HttpMethod.GET, "/room/all").permitAll().
                antMatchers(HttpMethod.POST, "/room").permitAll().
                antMatchers(HttpMethod.POST, "/room/images").permitAll().
                antMatchers(HttpMethod.PATCH, "/room/{id}").permitAll().
                antMatchers(HttpMethod.DELETE, "/room/{id}").permitAll().
                antMatchers(HttpMethod.GET,"/user/{id}").permitAll().
                antMatchers(HttpMethod.DELETE,"/user/{id}").permitAll().
                antMatchers(HttpMethod.GET,"/user").permitAll().
                antMatchers(HttpMethod.GET, "/user/user1/{username}").permitAll()
                .antMatchers(HttpMethod.GET,"/reservations").permitAll()
                .antMatchers(HttpMethod.GET,"/reservations/all").permitAll()
                .antMatchers(HttpMethod.GET,"/reservations/{id}").permitAll().
        antMatchers(HttpMethod.POST,"/user").permitAll()
                .antMatchers(HttpMethod.PATCH,"/reservations/{id}").permitAll()
                .antMatchers(HttpMethod.PATCH,"/reservations/delete/{id}").permitAll()
                .antMatchers(HttpMethod.POST, "/reservations").permitAll()
                .antMatchers(HttpMethod.PATCH, "/user/{id}").permitAll()
                .antMatchers(HttpMethod.PATCH, "/user/updatehotelreview/{id}").permitAll()
                .anyRequest().authenticated();
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}


package licenta.backend.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRange;
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

import licenta.backend.jwt.AuthEntryPointJwt;
import licenta.backend.jwt.AuthTokenFIlter;
import licenta.backend.service.UserService;

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
				.antMatchers(HttpMethod.GET,"/room/{id}").permitAll()
				.antMatchers(HttpMethod.GET,"room/days/{checkin}/{checkout}").permitAll()
                .antMatchers(HttpMethod.GET,"/reviews/{id}").permitAll()
				.antMatchers(HttpMethod.GET,"/prices/{checkin}/{checkout}/{id}").permitAll().
				antMatchers(HttpMethod.GET,"/room/all").permitAll().
		         antMatchers(HttpMethod.GET,"/user/user1/{username}").permitAll()
				.antMatchers(HttpMethod.POST,"/reservations").permitAll().
				antMatchers(HttpMethod.POST,"/reservations/roomreservations").permitAll()
				.anyRequest().authenticated();
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}

}


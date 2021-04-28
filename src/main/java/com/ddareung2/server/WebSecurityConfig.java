package com.ddareung2.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ddareung2.server.admin.AdminService;
import com.ddareung2.server.handler.AuthFailureHandler;
import com.ddareung2.server.handler.AuthSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
//                .antMatchers("/").permitAll()
                .antMatchers(
                		"/h2-console/**", 
                		"/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**", "/swagger/**",
                		"/login").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
            .cors().and()
            .formLogin()
                .permitAll()
                .successHandler(new AuthSuccessHandler())
				.failureHandler(new AuthFailureHandler())
                .and()
            .logout()
	            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	            .invalidateHttpSession(true)
                .permitAll();
        http.csrf().disable(); 
    	http.headers()
    		.frameOptions().sameOrigin(); 
    }
	
	@Autowired
	private AdminService adminService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(adminService).passwordEncoder(passwordEncoder());
	}
}

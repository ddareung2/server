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

import com.ddareung2.server.user.UserService;

@Configuration
@EnableWebSecurity  //@EnableWebSecurity : Spring Security의 웹 보안 지원을 활성화하고 Spring MVC 통합을 제공
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	//configure(HttpSecurity http) : 보안 처리할 경로와 처리하지 않을 경로 정의 
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
//                .antMatchers("/").permitAll()
                .antMatchers(
                		"/h2-console/**", 
                		"/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**", "/swagger/**",
                		"/login").permitAll()
                .anyRequest().authenticated()
                .and()
            .cors().and()
            .formLogin()
                .permitAll()
                .and()
            .logout()
                .permitAll();
        http.csrf().disable(); 
//		    	.ignoringAntMatchers("/h2-console/**"); //2. csrf 설정으로 h2-console 콘솔에서 접속 시도하면 인증화면으로 변경되는 문제 해결
    	http.headers()
    		.frameOptions().sameOrigin(); //3. h2-console 콘솔 접속 후 화면 표시 이상 해결 
    }
	
	@Autowired
	private UserService userService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}
}

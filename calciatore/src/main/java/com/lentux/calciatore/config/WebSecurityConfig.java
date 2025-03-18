package com.lentux.calciatore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@SuppressWarnings("deprecation")
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(requests ->{
			try {
				requests
				        .requestMatchers("/", "/index").permitAll()
				        .requestMatchers("/images/**").permitAll()
				        .anyRequest().authenticated()
				        .and()
				        .formLogin(login -> login
				                .loginPage("/login")
				                .permitAll())
				        .logout(logout -> logout
				                .permitAll()
				                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				                .logoutSuccessUrl("/login"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
    return http.build();
	}

	 @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) 
	      throws Exception {
	        auth
	          .inMemoryAuthentication()
	          .withUser("user").password(passwordEncoder().encode("password")).roles("USER")
	          .and()
	          .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
	    }
	    
	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
}
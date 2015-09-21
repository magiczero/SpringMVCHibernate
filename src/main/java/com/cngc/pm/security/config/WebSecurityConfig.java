package com.cngc.pm.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

//@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//	@Autowired
    private UserDetailsService userDetailsService;
	
//	@Override
    protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//        .antMatchers("/signup", "/static/**").permitAll()
//        .antMatchers("/code").hasRole("PRE_AUTH_USER")
//        .antMatchers("/home").hasRole("USER")
//        .anyRequest().authenticated();
		
		 // 自定义登录页面  
        http.csrf().disable().formLogin().loginPage("/initLogin")  
                .failureUrl("/login?error=1")  
                .loginProcessingUrl("/login")  
                .usernameParameter("username")  
                .passwordParameter("password").permitAll(); 

//		http.formLogin()
//		        .loginPage("/initLogin")
//		        .permitAll()
//		        // always use the default success url despite if a protected page had been previously visited
//		        .defaultSuccessUrl("/code", true)
//		        .and()
//		    .logout()
//		        .permitAll();
	}
	
//	@Autowired
    public void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}

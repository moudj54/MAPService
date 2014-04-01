package com.neuralnoise.map.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	//@Autowired
	//private SecurityService securityService;
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	//auth.userDetailsService(ss());
    	auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    }
    
	/*
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
		//auth.useDetailsService(new SecurityServiceImpl(new SecurityDAO(), new Assembler())).passwordEncoder(passwordEncoder());	
	}

	@Bean
	public BasePasswordEncoder passwordEncoder() {
		return new Md5PasswordEncoder();
	}
	*/

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/**").hasRole("USER").and()
			//.formLogin().permitAll().and()
			.httpBasic().and().rememberMe().and().csrf().disable();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}

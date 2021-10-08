package com.agenda.AgendaSpring.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsersDetailsServiceImp userDetails;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetails).passwordEncoder(passwordEncoder());
	}
	
    @Override
    protected void configure(HttpSecurity security) throws Exception
    {
    	security.csrf().disable()
    	.authorizeRequests().antMatchers("/js/**", "/css/**", "/login", "/register", "/auth/register").permitAll().anyRequest().hasRole("USER")
    	.and()
    	.formLogin().loginPage("/login").defaultSuccessUrl("/", true)
    	.and()
    	.logout()
    	.logoutUrl("/logout").logoutSuccessUrl("/login");
    }
    
}

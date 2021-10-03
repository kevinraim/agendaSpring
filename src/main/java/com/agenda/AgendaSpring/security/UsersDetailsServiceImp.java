package com.agenda.AgendaSpring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

import com.agenda.AgendaSpring.daoImp.UsuarioDaoImp;
import com.agenda.AgendaSpring.models.Usuario;

@Service
public class UsersDetailsServiceImp implements UserDetailsService{
	
	@Autowired
	@Lazy
	private UsuarioDaoImp usuarioImp;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario user = usuarioImp.getByUsername(username);
		UserBuilder builder = null;
		
		if(user != null) {
			builder = User.withUsername(username);
			builder.disabled(false);
			builder.password(user.getPassword());
			builder.authorities(new SimpleGrantedAuthority("ROLE_USER"));
		}
		else {
			throw new UsernameNotFoundException("Not Found User");
		}
		return builder.build();
	}
	
	
}

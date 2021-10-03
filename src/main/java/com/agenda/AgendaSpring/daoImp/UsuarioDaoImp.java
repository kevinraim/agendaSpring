package com.agenda.AgendaSpring.daoImp;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.agenda.AgendaSpring.dao.UsuarioDao;
import com.agenda.AgendaSpring.models.Usuario;

@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao{
	
	@Autowired
	private EntityManager em;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Usuario getByUsername(String username) {
		return em.createQuery("FROM Usuario WHERE username='"+ username + "'", Usuario.class).getSingleResult();
	}

	@Override
	public void save(Usuario usuario) {
		
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		
		em.merge(usuario);
	}

}

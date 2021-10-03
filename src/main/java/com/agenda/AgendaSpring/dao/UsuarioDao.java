package com.agenda.AgendaSpring.dao;

import com.agenda.AgendaSpring.models.Usuario;

public interface UsuarioDao {

	Usuario getByUsername(String username);
	
	void save(Usuario usuario);
}

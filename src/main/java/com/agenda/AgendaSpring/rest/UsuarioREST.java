package com.agenda.AgendaSpring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.agenda.AgendaSpring.daoImp.UsuarioDaoImp;
import com.agenda.AgendaSpring.models.Usuario;

@RestController
public class UsuarioREST {
	
	@Autowired(required=true)
	@Lazy
	private UsuarioDaoImp usuarioImp;
	
	@PostMapping(value="/auth/register")
	public void registrar(@RequestBody Usuario usuario) {
		usuarioImp.save(usuario);
	}
	
}

package com.agenda.AgendaSpring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<String> registrar(@RequestBody Usuario usuario) {
		
		if(usuarioImp.getByUsername(usuario.getUsername()) != null) {
			return ResponseEntity.badRequest().body("Nombre de usuario ya registrado");
		}
		
		usuarioImp.save(usuario);
		return ResponseEntity.ok("Usuario creado");
	}
	
	
}

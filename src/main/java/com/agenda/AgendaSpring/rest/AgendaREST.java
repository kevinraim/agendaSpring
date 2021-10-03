package com.agenda.AgendaSpring.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agenda.AgendaSpring.daoImp.AgendaDaoImp;
import com.agenda.AgendaSpring.daoImp.UsuarioDaoImp;
import com.agenda.AgendaSpring.models.Agenda;
import com.agenda.AgendaSpring.models.Usuario;

@RestController
public class AgendaREST {
	
	@Autowired
	private AgendaDaoImp agendaImp;
	
	@Autowired
	private UsuarioDaoImp usuarioImp;
	
	private Long getSessionUserId() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return usuarioImp.getByUsername(user.getUsername()).getId();
	}
	
	@GetMapping(value="/api/agenda/all")
	public List<Agenda> getAll() {
		return agendaImp.findAll(getSessionUserId());
	}
	
	@GetMapping(value="/api/agenda/all/fecha")
	public List<Agenda> getAllOrdFecha(){
		return agendaImp.findAllOrderByDate(getSessionUserId());
	}
	
	@GetMapping(value="/api/agenda", params="nombre")
	public List<Agenda> getByNombre(@RequestParam(name="nombre") String nombre){
		return agendaImp.getByNombre(nombre, getSessionUserId());
	}
	
	@RequestMapping(value="/api/agenda", method = RequestMethod.POST)
	public void addAgenda(@RequestBody Agenda agenda) {
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuario = usuarioImp.getByUsername(user.getUsername());	
		agenda.setUsuario_id(usuario.getId());
		
		agendaImp.save(agenda);
	}
	
	@PutMapping(value="/api/agenda/{id}")
	public void cambiarAgenda(@RequestBody Agenda copyAgenda, @PathVariable Long id) {
		agendaImp.update(copyAgenda, id);
	}
	
	@RequestMapping(value="/api/agenda/{id}", method = RequestMethod.DELETE)
	public void eliminarAgenda(@PathVariable Long id) {
		agendaImp.delete(id);
	}
	
	@RequestMapping(value="api/agenda/{id}")
	public Agenda getAgendaId(@PathVariable Long id) {
		return agendaImp.getById(id);
	}
	
}

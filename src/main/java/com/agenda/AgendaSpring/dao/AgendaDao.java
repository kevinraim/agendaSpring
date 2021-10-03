package com.agenda.AgendaSpring.dao;

import java.util.List;

import com.agenda.AgendaSpring.models.Agenda;

public interface AgendaDao {
	
	Agenda getById(Long id);
	
	List<Agenda> findAll(Long userId);
	
	List<Agenda> findAllOrderByDate(Long userId);
	
	List<Agenda> getByNombre(String nombre, Long userId);
	
	void save(Agenda agenda);
	
	void delete(Long id);
	
	void update(Agenda agenda, Long id);
}

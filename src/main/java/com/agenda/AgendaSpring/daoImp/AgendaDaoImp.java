package com.agenda.AgendaSpring.daoImp;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.agenda.AgendaSpring.dao.AgendaDao;
import com.agenda.AgendaSpring.models.Agenda;

@Repository
@Transactional
public class AgendaDaoImp implements AgendaDao{
	
	@Autowired
	private EntityManager em;
	
	@Override
	public Agenda getById(Long id) {
		return em.createQuery("FROM Agenda WHERE id="+id, Agenda.class).getSingleResult();
	}

	@Override
	public List<Agenda> findAll(Long userId) {
		return em.createQuery("FROM Agenda WHERE usuario_id="+ userId, Agenda.class ).getResultList();
	}

	@Override
	public List<Agenda> findAllOrderByDate(Long userId) {
		return em.createQuery("FROM Agenda WHERE usuario_id="+ userId+ " ORDER BY turno ASC", Agenda.class ).getResultList();
	}

	@Override
	public List<Agenda> getByNombre(String nombre, Long userId) {
		return em.createQuery("FROM Agenda WHERE usuario_id="+ userId+ " AND nombre='"+nombre+"'", Agenda.class).getResultList();
	}

	@Override
	public void save(Agenda agenda) {
		em.merge(agenda);
	}

	@Override
	public void delete(Long id) {
		Agenda toDelete = em.find(Agenda.class, id);
		
		em.remove(toDelete);
	}

	@Override
	public void update(Agenda agenda, Long id) {
		Agenda toUpdate = em.find(Agenda.class, id);
		
		if(toUpdate == null)
			return;
		
		if(agenda.getNombre() != null && !agenda.getNombre().isEmpty()) {
			toUpdate.setNombre(agenda.getNombre());
		}
		
		toUpdate.setEmail(agenda.getEmail());
		toUpdate.setTelefono(agenda.getTelefono());
		toUpdate.setPrecio(agenda.getPrecio());
		toUpdate.setTurno(agenda.getTurno());
		
		em.merge(toUpdate);
	}

}

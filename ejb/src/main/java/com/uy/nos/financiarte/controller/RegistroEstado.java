package com.uy.nos.financiarte.controller;

import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import com.uy.nos.financiarte.model.Estado;



@Stateful
@Model
public class RegistroEstado {
	
	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<Estado> estadosEventSrc;

	   private Estado newEstados;
	   
	   @Produces
	   @Named
	   public Estado getNewEstados() {
	      return newEstados;
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newEstados.getNombre());
	      em.persist(newEstados);
	      estadosEventSrc.fire(newEstados);
	      initNewEstados();
	   }
	   
	   public void modificar(Estado estados) throws Exception {
		   log.info("Modifico " + estados);
		   em.merge(estados);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   Estado estados = em.find(Estado.class, id);
		   em.remove(estados);
		   estadosEventSrc.fire(newEstados);
	   }
	   
	   public Estado buscar(Long id) throws Exception {
		   log.info("Buscar " + id);
		   Estado estados = em.find(Estado.class, id);
		   return estados;
	   }
	   
	   @PostConstruct
	   public void initNewEstados() {
		   newEstados = new Estado();
	   }


}

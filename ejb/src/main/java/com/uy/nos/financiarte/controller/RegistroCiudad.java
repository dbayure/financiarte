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

import com.uy.nos.financiarte.model.Ciudad;



@Stateful
@Model
public class RegistroCiudad {

	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<Ciudad> ciudadEventSrc;

	   private Ciudad newCiudad;

	   @Produces
	   @Named
	   public Ciudad getNewCiudad() {
	      return newCiudad;
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newCiudad.getNombre());
	      em.persist(newCiudad);
	      ciudadEventSrc.fire(newCiudad);
	      initNewCiudad();
	   }
	   
	   public void modificar(Ciudad ciudad) throws Exception {
		   log.info("Modifico " + ciudad);
		   em.merge(ciudad);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   Ciudad ciudad = em.find(Ciudad.class, id);
		   em.remove(ciudad);
		   ciudadEventSrc.fire(newCiudad);
	   }

	   public Ciudad buscar(Long id) throws Exception {
		   log.info("Buscar " + id);
		   Ciudad ciudad = em.find(Ciudad.class, id);
		   return ciudad;
	   }
	   
	   @PostConstruct
	   public void initNewCiudad() {
		   newCiudad = new Ciudad();
	   }
}
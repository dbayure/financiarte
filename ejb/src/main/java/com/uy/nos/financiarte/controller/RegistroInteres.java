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

import com.uy.nos.financiarte.model.Interes;



@Stateful
@Model
public class RegistroInteres {
	
	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<Interes> interesesEventSrc;

	   private Interes newInteres;
	   
	   @Produces
	   @Named
	   public Interes getNewInteres() {
	      return newInteres;
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newInteres.getNombre());
	      em.persist(newInteres);
	      interesesEventSrc.fire(newInteres);
	      initNewInteres();
	   }
	   
	   public void modificar(Interes interes) throws Exception {
		   log.info("Modifico " + interes);
		   em.merge(interes);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   Interes interes = em.find(Interes.class, id);
		   em.remove(interes);
		   interesesEventSrc.fire(newInteres);
	   }
	   
	   public Interes buscar(Long id) throws Exception {
		   log.info("Buscar " + id);
		   Interes interes = em.find(Interes.class, id);
		   return interes;
	   }
	   
	   @PostConstruct
	   public void initNewInteres() {
		   newInteres = new Interes();
	   }


}

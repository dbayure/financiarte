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
import com.uy.nos.financiarte.model.Devolucion;



@Stateful
@Model
public class RegistroDevolucion {
	
	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<Devolucion> devolucionEventSrc;

	   private Devolucion newDevolucion;
	   
	   @Produces
	   @Named
	   public Devolucion getNewDevolucion() {
	      return newDevolucion;
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newDevolucion.getDescripcion());
	      em.persist(newDevolucion);
	      devolucionEventSrc.fire(newDevolucion);
	      initNewDevolucion();
	   }
	   
	   public void modificar(Devolucion devolucion) throws Exception {
		   log.info("Modifico " + devolucion);
		   em.merge(devolucion);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   Devolucion devolucion = em.find(Devolucion.class, id);
		   em.remove(devolucion);
		   devolucionEventSrc.fire(newDevolucion);
	   }
	   
	   public Devolucion buscar(Long id) throws Exception {
		   log.info("Buscar " + id);
		   Devolucion devolucion = em.find(Devolucion.class, id);
		   return devolucion;
	   }
	   
	   @PostConstruct
	   public void initNewDevolucion() {
		   newDevolucion = new Devolucion();
	   }


}

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

import com.uy.nos.financiarte.model.NotaCredito;



@Stateful
@Model
public class RegistroNotaCredito {
	
	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<NotaCredito> notaCreditoEventSrc;

	   private NotaCredito newNotaCredito;
	   
	   @Produces
	   @Named
	   public NotaCredito getNewNotaCredito() {
	      return newNotaCredito;
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newNotaCredito.getNumeroSerie());
	      em.persist(newNotaCredito);
	      notaCreditoEventSrc.fire(newNotaCredito);
	      initNewNotaCredito();
	   }
	   
	   public void modificar(NotaCredito notaCredito) throws Exception {
		   log.info("Modifico " + notaCredito);
		   em.merge(notaCredito);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   NotaCredito notaCredito = em.find(NotaCredito.class, id);
		   em.remove(notaCredito);
		   notaCreditoEventSrc.fire(newNotaCredito);
	   }
	   
	   public NotaCredito buscar(Long id) throws Exception {
		   log.info("Buscar " + id);
		   NotaCredito notaCredito = em.find(NotaCredito.class, id);
		   return notaCredito;
	   }
	   
	   @PostConstruct
	   public void initNewNotaCredito() {
		   newNotaCredito = new NotaCredito();
	   }


}

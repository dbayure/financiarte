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
import com.uy.nos.financiarte.model.PagoMedioPago;



@Stateful
@Model
public class RegistroPagoMedioPago {
	
	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<PagoMedioPago> pagoMedioPagoEventSrc;

	   private PagoMedioPago newPagoMedioPago;
	   
	   @Produces
	   @Named
	   public PagoMedioPago getNewPagoMedioPago() {
	      return newPagoMedioPago;
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newPagoMedioPago.getId());
	      em.persist(newPagoMedioPago);
	      pagoMedioPagoEventSrc.fire(newPagoMedioPago);
	      initNewPagoMedioPago();
	   }
	   
	   public void modificar(PagoMedioPago pagoMedioPago) throws Exception {
		   log.info("Modifico " + pagoMedioPago);
		   em.merge(pagoMedioPago);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   PagoMedioPago pagoMedioPago = em.find(PagoMedioPago.class, id);
		   em.remove(pagoMedioPago);
		   pagoMedioPagoEventSrc.fire(newPagoMedioPago);
	   }
	   
	   public PagoMedioPago buscar(Long id) throws Exception {
		   log.info("Buscar " + id);
		   PagoMedioPago pagoMedioPago = em.find(PagoMedioPago.class, id);
		   return pagoMedioPago;
	   }
	   
	   @PostConstruct
	   public void initNewPagoMedioPago() {
		   newPagoMedioPago = new PagoMedioPago();
	   }


	   
}

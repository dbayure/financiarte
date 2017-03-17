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

import com.uy.nos.financiarte.model.MedioPago;



@Stateful
@Model
public class RegistroMedioPago {

	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<MedioPago> medioPagoEventSrc;

	   private MedioPago newMedioPago;

	   @Produces
	   @Named
	   public MedioPago getNewMedioPago() {
	      return newMedioPago;
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newMedioPago.getNombre());
	      em.persist(newMedioPago);
	      medioPagoEventSrc.fire(newMedioPago);
	      initNewMedioPago();
	   }
	   
	   public void modificar(MedioPago medioPago) throws Exception {
		   log.info("Modifico " + medioPago);
		   em.merge(medioPago);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   MedioPago medioPago = em.find(MedioPago.class, id);
		   em.remove(medioPago);
		   medioPagoEventSrc.fire(newMedioPago);
	   }

	   public MedioPago buscar(Long id) throws Exception {
		   log.info("Buscar " + id);
		   MedioPago medioPago = em.find(MedioPago.class, id);
		   return medioPago;
	   }
	   
	   @PostConstruct
	   public void initNewMedioPago() {
		   newMedioPago = new MedioPago();
	   }
}
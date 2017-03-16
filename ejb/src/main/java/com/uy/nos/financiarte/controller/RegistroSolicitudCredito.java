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

import com.uy.nos.financiarte.model.solicitudCredito;



@Stateful
@Model
public class RegistroSolicitudCredito {
	
	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<solicitudCredito> pagoClienteEventSrc;

	   private solicitudCredito newPagoCliente;
	   
	   @Produces
	   @Named
	   public solicitudCredito getNewPagoCliente() {
	      return newPagoCliente;
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newPagoCliente.getMonto());
	      em.persist(newPagoCliente);
	      pagoClienteEventSrc.fire(newPagoCliente);
	      initNewPagoCliente();
	   }
	   
	   public void modificar(solicitudCredito pagoCliente) throws Exception {
		   log.info("Modifico " + pagoCliente);
		   em.merge(pagoCliente);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   solicitudCredito pagoCliente = em.find(solicitudCredito.class, id);
		   em.remove(pagoCliente);
		   pagoClienteEventSrc.fire(newPagoCliente);
	   }
	   
	   public solicitudCredito buscar(Long id) throws Exception {
		   log.info("Buscar " + id);
		   solicitudCredito pagoCliente = em.find(solicitudCredito.class, id);
		   return pagoCliente;
	   }

	   @PostConstruct
	   public void initNewPagoCliente() {
		   newPagoCliente = new solicitudCredito();
	   }


}

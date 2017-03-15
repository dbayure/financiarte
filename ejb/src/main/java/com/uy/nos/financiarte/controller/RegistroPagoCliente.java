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

import com.uy.nos.financiarte.model.PagoCliente;



@Stateful
@Model
public class RegistroPagoCliente {
	
	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<PagoCliente> pagoClienteEventSrc;

	   private PagoCliente newPagoCliente;
	   
	   @Produces
	   @Named
	   public PagoCliente getNewPagoCliente() {
	      return newPagoCliente;
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newPagoCliente.getMonto());
	      em.persist(newPagoCliente);
	      pagoClienteEventSrc.fire(newPagoCliente);
	      initNewPagoCliente();
	   }
	   
	   public void modificar(PagoCliente pagoCliente) throws Exception {
		   log.info("Modifico " + pagoCliente);
		   em.merge(pagoCliente);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   PagoCliente pagoCliente = em.find(PagoCliente.class, id);
		   em.remove(pagoCliente);
		   pagoClienteEventSrc.fire(newPagoCliente);
	   }
	   
	   public PagoCliente buscar(Long id) throws Exception {
		   log.info("Buscar " + id);
		   PagoCliente pagoCliente = em.find(PagoCliente.class, id);
		   return pagoCliente;
	   }

	   @PostConstruct
	   public void initNewPagoCliente() {
		   newPagoCliente = new PagoCliente();
	   }


}

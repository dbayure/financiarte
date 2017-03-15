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

import com.uy.nos.financiarte.model.Factura;



@Stateful
@Model
public class RegistroFactura {
	
	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<Factura> facturaEventSrc;

	   private Factura newFactura;
	   
	   @Produces
	   @Named
	   public Factura getNewFactura() {
	      return newFactura;
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newFactura.getDescripcion());
	      em.persist(newFactura);
	      facturaEventSrc.fire(newFactura);
	      initNewFactura();
	   }
	   
	   public void modificar(Factura factura) throws Exception {
		   log.info("Modifico " + factura);
		   em.merge(factura);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   Factura factura = em.find(Factura.class, id);
		   em.remove(factura);
		   facturaEventSrc.fire(newFactura);
	   }
	   
	   public Factura buscar(Long id) throws Exception {
		   log.info("Buscar " + id);
		   Factura factura = em.find(Factura.class, id);
		   return factura;
	   }

	   @PostConstruct
	   public void initNewFactura() {
		   newFactura = new Factura();
	   }


}

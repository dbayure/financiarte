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

import com.uy.nos.financiarte.model.CuentaCorriente;



@Stateful
@Model
public class RegistroCuentaCorriente {

	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<CuentaCorriente> cuentaCorrienteEventSrc;

	   private CuentaCorriente newCuentaCorriente;

	   @Produces
	   @Named
	   public CuentaCorriente getNewCuentaCorriente() {
	      return newCuentaCorriente;
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newCuentaCorriente.getId());
	      em.persist(newCuentaCorriente);
	      cuentaCorrienteEventSrc.fire(newCuentaCorriente);
	      initNewCuentaCorriente();
	   }
	   
	   public void modificar(CuentaCorriente cuentaCorriente) throws Exception {
		   log.info("Modifico " + cuentaCorriente);
		   em.merge(cuentaCorriente);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   CuentaCorriente cuentaCorriente = em.find(CuentaCorriente.class, id);
		   em.remove(cuentaCorriente);
		   cuentaCorrienteEventSrc.fire(newCuentaCorriente);
	   }

	   public CuentaCorriente buscar(Long id) throws Exception {
		   log.info("Buscar " + id);
		   CuentaCorriente cuentaCorriente = em.find(CuentaCorriente.class, id);
		   return cuentaCorriente;
	   }
	   
	   @PostConstruct
	   public void initNewCuentaCorriente() {
		   newCuentaCorriente = new CuentaCorriente();
	   }
}
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

import com.uy.nos.financiarte.model.Cliente;
import com.uy.nos.financiarte.model.Contrato;
import com.uy.nos.financiarte.model.Proveedor;



@Stateful
@Model
public class RegistroContrato {

	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<Contrato> contratoEventSrc;

	   private Contrato newContrato;

	   @Produces
	   @Named
	   public Contrato getNewContrato() {
	      return newContrato;
	   }

	   public void registro(Cliente clienteSeleccionado, Proveedor proveedorSeleccionado) throws Exception {
	      log.info("Registro " + newContrato.getId());
	      newContrato.setCliente(clienteSeleccionado);
	      newContrato.setProveedor(proveedorSeleccionado);
	      em.persist(newContrato);
	      contratoEventSrc.fire(newContrato);
	      initNewComercio();
	   }
	   
	   public void modificar(Contrato contrato) throws Exception {
		   log.info("Modifico " + contrato);
		   em.merge(contrato);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   Contrato contrato = em.find(Contrato.class, id);
		   em.remove(contrato);
		   contratoEventSrc.fire(newContrato);
	   }

	   public Contrato buscar(Long id) throws Exception {
		   log.info("Buscar " + id);
		   Contrato contrato = em.find(Contrato.class, id);
		   return contrato;
	   }
	   
	   @PostConstruct
	   public void initNewComercio() {
		   newContrato = new Contrato();
	   }
	   
}
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

import com.uy.nos.financiarte.model.Proveedor;
import com.uy.nos.financiarte.model.Rol;



@Stateful
@Model
public class RegistroProveedor {

	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<Proveedor> clienteEventSrc;

	   private Proveedor newProveedor;

	   @Produces
	   @Named
	   public Proveedor getNewProveedor() {
	      return newProveedor;
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newProveedor.getNombre());
	      Long idRol = 2L;
	      Rol rol = em.find(Rol.class, idRol);
	      newProveedor.setRol(rol);
	      em.persist(newProveedor);
	      clienteEventSrc.fire(newProveedor);
	      initNewProveedor();
	   }
	   
	   public void modificar(Proveedor proveedor) throws Exception {
		   log.info("Modifico " + proveedor);
		   em.merge(proveedor);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   Proveedor proveedor = em.find(Proveedor.class, id);
		   em.remove(proveedor);
		   clienteEventSrc.fire(newProveedor);
	   }

	   public Proveedor buscar(Long id) throws Exception {
		   log.info("Buscar " + id);
		   Proveedor proveedor = em.find(Proveedor.class, id);
		   return proveedor;
	   }
	   
	   @PostConstruct
	   public void initNewProveedor() {
		   newProveedor = new Proveedor();
	   }
}
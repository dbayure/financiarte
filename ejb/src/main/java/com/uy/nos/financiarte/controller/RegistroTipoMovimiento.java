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

import com.uy.nos.financiarte.model.TipoMovimiento;



@Stateful
@Model
public class RegistroTipoMovimiento {

	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<TipoMovimiento> tipoMovimientoEventSrc;

	   private TipoMovimiento newTipoMovimiento;

	   @Produces
	   @Named
	   public TipoMovimiento getNewTipoMovimiento() {
	      return newTipoMovimiento;
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newTipoMovimiento.getNombre());
	      em.persist(newTipoMovimiento);
	      tipoMovimientoEventSrc.fire(newTipoMovimiento);
	      initNewTipoMovimiento();
	   }
	   
	   public void modificar(TipoMovimiento tipo) throws Exception {
		   log.info("Modifico " + tipo.getNombre());
		   em.merge(tipo);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   TipoMovimiento tipo = em.find(TipoMovimiento.class, id);
		   em.remove(tipo);
		   tipoMovimientoEventSrc.fire(newTipoMovimiento);
	   }

	   public TipoMovimiento buscar(Long id) throws Exception {
		   log.info("Buscar " + id);
		   TipoMovimiento tipo = em.find(TipoMovimiento.class, id);
		   return tipo;
	   }
	   
	   @PostConstruct
	   public void initNewTipoMovimiento() {
		   newTipoMovimiento = new TipoMovimiento();
	   }
}
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

import com.uy.nos.financiarte.model.TipoContrato;



@Stateful
@Model
public class RegistroTipoContrato {
	
	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<TipoContrato> tiposContratoEventSrc;

	   private TipoContrato newTiposContrato;
	   
	   @Produces
	   @Named
	   public TipoContrato getNewTiposContrato() {
	      return newTiposContrato;
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newTiposContrato.getNombre());
	      em.persist(newTiposContrato);
	      tiposContratoEventSrc.fire(newTiposContrato);
	      initNewTiposContrato();
	   }
	   
	   public void modificar(TipoContrato tiposContratos) throws Exception {
		   log.info("Modifico " + tiposContratos);
		   em.merge(tiposContratos);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   TipoContrato tiposContratos = em.find(TipoContrato.class, id);
		   em.remove(tiposContratos);
		   tiposContratoEventSrc.fire(newTiposContrato);
	   }
	   
	   public TipoContrato buscar(Long id) throws Exception {
		   log.info("Buscar " + id);
		   TipoContrato tiposContratos = em.find(TipoContrato.class, id);
		   return tiposContratos;
	   }
	   
	   @PostConstruct
	   public void initNewTiposContrato() {
		   newTiposContrato = new TipoContrato();
	   }


}

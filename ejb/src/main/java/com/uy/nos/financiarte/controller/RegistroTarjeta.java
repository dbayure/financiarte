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

import com.uy.nos.financiarte.model.Tarjeta;



@Stateful
@Model
public class RegistroTarjeta {

	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<Tarjeta> tarjetaEventSrc;

	   private Tarjeta newTarjeta;

	   @Produces
	   @Named
	   public Tarjeta getNewTarjeta() {
	      return newTarjeta;
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newTarjeta.getNombre());
	      em.persist(newTarjeta);
	      tarjetaEventSrc.fire(newTarjeta);
	      initNewTarjeta();
	   }
	   
	   public void modificar(Tarjeta tarjeta) throws Exception {
		   log.info("Modifico " + tarjeta);
		   em.merge(tarjeta);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   Tarjeta tarjeta = em.find(Tarjeta.class, id);
		   em.remove(tarjeta);
		   tarjetaEventSrc.fire(newTarjeta);
	   }

	   public Tarjeta buscar(Long id) throws Exception {
		   log.info("Buscar " + id);
		   Tarjeta tarjeta = em.find(Tarjeta.class, id);
		   return tarjeta;
	   }
	   
	   @PostConstruct
	   public void initNewTarjeta() {
		   newTarjeta = new Tarjeta();
	   }
}
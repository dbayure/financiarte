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

import com.uy.nos.financiarte.model.Banco;



@Stateful
@Model
public class RegistroBanco {

	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<Banco> bancoEventSrc;

	   private Banco newBanco;

	   @Produces
	   @Named
	   public Banco getNewBanco() {
	      return newBanco;
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newBanco.getNombre());
	      em.persist(newBanco);
	      bancoEventSrc.fire(newBanco);
	      initNewBanco();
	   }
	   
	   public void modificar(Banco banco) throws Exception {
		   log.info("Modifico " + banco);
		   em.merge(banco);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   Banco banco = em.find(Banco.class, id);
		   em.remove(banco);
		   bancoEventSrc.fire(newBanco);
	   }

	   public Banco buscar(Long id) throws Exception {
		   log.info("Buscar " + id);
		   Banco banco = em.find(Banco.class, id);
		   return banco;
	   }
	   
	   @PostConstruct
	   public void initNewBanco() {
		   newBanco = new Banco();
	   }
}
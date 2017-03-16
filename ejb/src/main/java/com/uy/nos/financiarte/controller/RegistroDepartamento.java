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

import com.uy.nos.financiarte.model.Departamento;



@Stateful
@Model
public class RegistroDepartamento {

	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<Departamento> departamentoEventSrc;

	   private Departamento newDepartamento;

	   @Produces
	   @Named
	   public Departamento getNewDepartamento() {
	      return newDepartamento;
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newDepartamento.getNombre());
	      em.persist(newDepartamento);
	      departamentoEventSrc.fire(newDepartamento);
	      initNewDepartamento();
	   }
	   
	   public void modificar(Departamento departamento) throws Exception {
		   log.info("Modifico " + departamento);
		   em.merge(departamento);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   Departamento departamento = em.find(Departamento.class, id);
		   em.remove(departamento);
		   departamentoEventSrc.fire(newDepartamento);
	   }

	   public Departamento buscar(Long id) throws Exception {
		   log.info("Buscar " + id);
		   Departamento departamento = em.find(Departamento.class, id);
		   return departamento;
	   }
	   
	   @PostConstruct
	   public void initNewDepartamento() {
		   newDepartamento = new Departamento();
	   }
}
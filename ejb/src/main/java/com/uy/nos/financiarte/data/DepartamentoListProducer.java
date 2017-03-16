
package com.uy.nos.financiarte.data;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.uy.nos.financiarte.model.Departamento;


@RequestScoped
public class DepartamentoListProducer {
	
   @Inject
   private EntityManager em;

   private List<Departamento> departamentos;


   @Produces
   @Named
   public List<Departamento> getDepartamentos() {
      return departamentos;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Departamento departamento) {
	      retrieveAllOrderedByName();
   }

   @PostConstruct
   public void retrieveAllOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Departamento> criteria = cb.createQuery(Departamento.class);
      Root<Departamento> departamento = criteria.from(Departamento.class);
      criteria.select(departamento).orderBy(cb.asc(departamento.get("id")));
      departamentos = em.createQuery(criteria).getResultList();
   }
   
}

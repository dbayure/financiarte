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

import com.uy.nos.financiarte.model.Interes;



@RequestScoped
public class MedioPagoListProducer {
	
   @Inject
   private EntityManager em;

   private List<Interes> intereses;


   @Produces
   @Named
   public List<Interes> getIntereses() {
      return intereses;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Interes interes) {
	      retrieveAllOrderedByName();
   }

   @PostConstruct
   public void retrieveAllOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Interes> criteria = cb.createQuery(Interes.class);
      Root<Interes> interes = criteria.from(Interes.class);
      criteria.select(interes).orderBy(cb.asc(interes.get("id")));
      intereses = em.createQuery(criteria).getResultList();
   }
}

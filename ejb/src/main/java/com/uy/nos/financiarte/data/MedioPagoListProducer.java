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

import com.uy.nos.financiarte.model.MedioPago;




@RequestScoped
public class MedioPagoListProducer {
	
   @Inject
   private EntityManager em;

   private List<MedioPago> mediosPago;


   @Produces
   @Named
   public List<MedioPago> getMediosPago() {
      return mediosPago;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final MedioPago madiosPago) {
	      retrieveAllOrderedByName();
   }

   @PostConstruct
   public void retrieveAllOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<MedioPago> criteria = cb.createQuery(MedioPago.class);
      Root<MedioPago> madiosPago = criteria.from(MedioPago.class);
      criteria.select(madiosPago).orderBy(cb.asc(madiosPago.get("id")));
      mediosPago = em.createQuery(criteria).getResultList();
   }
}

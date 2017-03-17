
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

import com.uy.nos.financiarte.model.Tarjeta;


@RequestScoped
public class TarjetaListProducer {
	
   @Inject
   private EntityManager em;

   private List<Tarjeta> tarjetas;


   @Produces
   @Named
   public List<Tarjeta> getTarjetas() {
      return tarjetas;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Tarjeta tarjeta) {
	      retrieveAllOrderedByName();
   }

   @PostConstruct
   public void retrieveAllOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Tarjeta> criteria = cb.createQuery(Tarjeta.class);
      Root<Tarjeta> tarjeta = criteria.from(Tarjeta.class);
      criteria.select(tarjeta).orderBy(cb.asc(tarjeta.get("id")));
      tarjetas = em.createQuery(criteria).getResultList();
   }
   
}

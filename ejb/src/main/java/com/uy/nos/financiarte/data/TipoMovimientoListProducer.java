
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

import com.uy.nos.financiarte.model.TipoMovimiento;


@RequestScoped
public class TipoMovimientoListProducer {
	
   @Inject
   private EntityManager em;

   private List<TipoMovimiento> tipos;


   @Produces
   @Named
   public List<TipoMovimiento> getTiposMovimiento() {
      return tipos;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final TipoMovimiento tipo) {
	      retrieveAllOrderedByName();
   }

   @PostConstruct
   public void retrieveAllOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<TipoMovimiento> criteria = cb.createQuery(TipoMovimiento.class);
      Root<TipoMovimiento> tipo = criteria.from(TipoMovimiento.class);
      criteria.select(tipo).orderBy(cb.asc(tipo.get("id")));
      tipos = em.createQuery(criteria).getResultList();
   }
   
}

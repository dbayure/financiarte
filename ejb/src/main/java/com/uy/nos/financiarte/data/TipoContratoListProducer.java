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

import com.uy.nos.financiarte.model.TipoContrato;



@RequestScoped
public class TipoContratoListProducer {
	
   @Inject
   private EntityManager em;

   private List<TipoContrato> tiposContrato;


   @Produces
   @Named
   public List<TipoContrato> getTiposContrato() {
      return tiposContrato;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final TipoContrato tipoContrato) {
	      retrieveAllOrderedByName();
   }

   @PostConstruct
   public void retrieveAllOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<TipoContrato> criteria = cb.createQuery(TipoContrato.class);
      Root<TipoContrato> tipoContrato = criteria.from(TipoContrato.class);
      criteria.select(tipoContrato).orderBy(cb.asc(tipoContrato.get("id")));
      tiposContrato = em.createQuery(criteria).getResultList();
   }
}

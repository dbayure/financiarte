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

import com.uy.nos.financiarte.model.NotaCredito;



@RequestScoped
public class NotaCreditoListProducer {
	
   @Inject
   private EntityManager em;

   private List<NotaCredito> notasCredito;


   @Produces
   @Named
   public List<NotaCredito> getNotasCredito() {
      return notasCredito;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final NotaCredito notaCredito) {
	      retrieveAllOrderedByName();
   }

   @PostConstruct
   public void retrieveAllOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<NotaCredito> criteria = cb.createQuery(NotaCredito.class);
      Root<NotaCredito> notaCredito = criteria.from(NotaCredito.class);
      criteria.select(notaCredito).orderBy(cb.asc(notaCredito.get("notaCredito")));
      notasCredito = em.createQuery(criteria).getResultList();
   }
   
   public List<NotaCredito> getNotaCreditoPorContrato(Long idContrato) {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<NotaCredito> criteria = cb.createQuery(NotaCredito.class);
      Root<NotaCredito> notaCredito = criteria.from(NotaCredito.class);
      criteria.select(notaCredito);
      criteria.where(cb.equal(notaCredito.get("contrato"), idContrato));
      List<NotaCredito> nc = em.createQuery(criteria).getResultList();
      return nc;
   }
}

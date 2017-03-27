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

import com.uy.nos.financiarte.model.PagoMedioPago;



@RequestScoped
public class PagoMedioPagoListProducer {
	
   @Inject
   private EntityManager em;

   private List<PagoMedioPago> pagosMedioPago;


   @Produces
   @Named
   public List<PagoMedioPago> getPagosMedioPago() {
      return pagosMedioPago;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final PagoMedioPago pagoMedioPago) {
	      retrieveAllOrderedByName();
   }

   @PostConstruct
   public void retrieveAllOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<PagoMedioPago> criteria = cb.createQuery(PagoMedioPago.class);
      Root<PagoMedioPago> pagoMedioPago = criteria.from(PagoMedioPago.class);
      criteria.select(pagoMedioPago).orderBy(cb.asc(pagoMedioPago.get("id")));
      pagosMedioPago = em.createQuery(criteria).getResultList();
   }
   
   public List<PagoMedioPago> getPagoMedioPagoPorContrato(Long idContrato) {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<PagoMedioPago> criteria = cb.createQuery(PagoMedioPago.class);
      Root<PagoMedioPago> pagoMedioPago = criteria.from(PagoMedioPago.class);
      criteria.select(pagoMedioPago);
      criteria.where(cb.equal(pagoMedioPago.get("contrato"), idContrato));
      List<PagoMedioPago> pmp = em.createQuery(criteria).getResultList();
      return pmp;
   }
}

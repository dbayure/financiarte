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

import com.uy.nos.financiarte.model.SolicitudCredito;



@RequestScoped
public class SolicitudCreditoListProducer {
	
   @Inject
   private EntityManager em;

   private List<SolicitudCredito> solicitudes;


   @Produces
   @Named
   public List<SolicitudCredito> getSolicitudCredito() {
      return solicitudes;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final SolicitudCredito solicitudCredito) {
	      retrieveAllOrderedByName();
   }

   @PostConstruct
   public void retrieveAllOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<SolicitudCredito> criteria = cb.createQuery(SolicitudCredito.class);
      Root<SolicitudCredito> solicitudCredito = criteria.from(SolicitudCredito.class);
      criteria.select(solicitudCredito).orderBy(cb.asc(solicitudCredito.get("id")));
      solicitudes = em.createQuery(criteria).getResultList();
   }
   
   public List<SolicitudCredito> getSolicitudCreditoContrato(Long idContrato) {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<SolicitudCredito> criteria = cb.createQuery(SolicitudCredito.class);
      Root<SolicitudCredito> solicitudCredito = criteria.from(SolicitudCredito.class);
      criteria.select(solicitudCredito);
      criteria.where(cb.equal(solicitudCredito.get("contrato"), idContrato));
      List<SolicitudCredito> pc = em.createQuery(criteria).getResultList();
      return pc;
   }
}

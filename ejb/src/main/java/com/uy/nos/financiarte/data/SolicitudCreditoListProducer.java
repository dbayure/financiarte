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

import com.uy.nos.financiarte.model.solicitudCredito;



@RequestScoped
public class SolicitudCreditoListProducer {
	
   @Inject
   private EntityManager em;

   private List<solicitudCredito> pagosCliente;


   @Produces
   @Named
   public List<solicitudCredito> getPagosCliente() {
      return pagosCliente;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final solicitudCredito pagoCliente) {
	      retrieveAllOrderedByName();
   }

   @PostConstruct
   public void retrieveAllOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<solicitudCredito> criteria = cb.createQuery(solicitudCredito.class);
      Root<solicitudCredito> pagoCliente = criteria.from(solicitudCredito.class);
      criteria.select(pagoCliente).orderBy(cb.asc(pagoCliente.get("pagoCliente")));
      pagosCliente = em.createQuery(criteria).getResultList();
   }
   
   public List<solicitudCredito> getPagoClientePorContrato(Long idContrato) {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<solicitudCredito> criteria = cb.createQuery(solicitudCredito.class);
      Root<solicitudCredito> pagoCliente = criteria.from(solicitudCredito.class);
      criteria.select(pagoCliente);
      criteria.where(cb.equal(pagoCliente.get("contrato"), idContrato));
      List<solicitudCredito> pc = em.createQuery(criteria).getResultList();
      return pc;
   }
}

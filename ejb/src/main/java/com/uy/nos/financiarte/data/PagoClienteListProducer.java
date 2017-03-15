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

import com.uy.nos.financiarte.model.PagoCliente;



@RequestScoped
public class PagoClienteListProducer {
	
   @Inject
   private EntityManager em;

   private List<PagoCliente> pagosCliente;


   @Produces
   @Named
   public List<PagoCliente> getPagosCliente() {
      return pagosCliente;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final PagoCliente pagoCliente) {
	      retrieveAllOrderedByName();
   }

   @PostConstruct
   public void retrieveAllOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<PagoCliente> criteria = cb.createQuery(PagoCliente.class);
      Root<PagoCliente> pagoCliente = criteria.from(PagoCliente.class);
      criteria.select(pagoCliente).orderBy(cb.asc(pagoCliente.get("pagoCliente")));
      pagosCliente = em.createQuery(criteria).getResultList();
   }
   
   public List<PagoCliente> getPagoClientePorContrato(Long idContrato) {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<PagoCliente> criteria = cb.createQuery(PagoCliente.class);
      Root<PagoCliente> pagoCliente = criteria.from(PagoCliente.class);
      criteria.select(pagoCliente);
      criteria.where(cb.equal(pagoCliente.get("contrato"), idContrato));
      List<PagoCliente> pc = em.createQuery(criteria).getResultList();
      return pc;
   }
}

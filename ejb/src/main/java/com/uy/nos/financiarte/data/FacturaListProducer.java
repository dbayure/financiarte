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

import com.uy.nos.financiarte.model.Factura;



@RequestScoped
public class FacturaListProducer {
	
   @Inject
   private EntityManager em;

   private List<Factura> facturas;


   @Produces
   @Named
   public List<Factura> getFacturas() {
      return facturas;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Factura factura) {
	      retrieveAllOrderedByName();
   }

   @PostConstruct
   public void retrieveAllOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Factura> criteria = cb.createQuery(Factura.class);
      Root<Factura> factura = criteria.from(Factura.class);
      criteria.select(factura).orderBy(cb.asc(factura.get("factura")));
      facturas = em.createQuery(criteria).getResultList();
   }
   
   public List<Factura> getFacturaPorContrato(Long idContrato) {
	   System.out.println("id del contrato a buscar en facturas " + idContrato);
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Factura> criteria = cb.createQuery(Factura.class);
      Root<Factura> factura = criteria.from(Factura.class);
      criteria.select(factura);
      criteria.where(cb.equal(factura.get("contrato"), idContrato));
      List<Factura> fac = em.createQuery(criteria).getResultList();
      return fac;
   }
}

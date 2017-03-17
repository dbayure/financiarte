
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

import com.uy.nos.financiarte.model.Contrato;


@RequestScoped
public class ContratoListProducer {
	
   @Inject
   private EntityManager em;

   private List<Contrato> contratos;


   @Produces
   @Named
   public List<Contrato> getContratos() {
      return contratos;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Contrato contrato) {
	      retrieveAllOrderedByName();
   }

   @PostConstruct
   public void retrieveAllOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Contrato> criteria = cb.createQuery(Contrato.class);
      Root<Contrato> contrato = criteria.from(Contrato.class);
      criteria.select(contrato).orderBy(cb.asc(contrato.get("id")));
      contratos = em.createQuery(criteria).getResultList();
   }
   
   public List<Contrato> getContratosPorCliente(Long idcliente) {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Contrato> criteria = cb.createQuery(Contrato.class);
      Root<Contrato> contrato = criteria.from(Contrato.class);
      criteria.select(contrato);
      criteria.where(cb.equal(contrato.get("cliente"), idcliente));
      List<Contrato> contratos = em.createQuery(criteria).getResultList();
      return contratos;
   }
   
   public List<Contrato> getContratosDuplicados(Long idcliente, Long idproveedor) {
	      CriteriaBuilder cb = em.getCriteriaBuilder();
	      CriteriaQuery<Contrato> criteria = cb.createQuery(Contrato.class);
	      Root<Contrato> contrato = criteria.from(Contrato.class);
	      criteria.select(contrato);
	      criteria.where(cb.and(cb.equal(contrato.get("cliente"),idcliente)),
	    		  				cb.equal(contrato.get("proveedor"), idproveedor));
	      List<Contrato> contratos = em.createQuery(criteria).getResultList();
	      return contratos;
	   }
   
}


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

import com.uy.nos.financiarte.model.CuentaCorriente;


@RequestScoped
public class CuentaCorrienteListProducer {
	
   @Inject
   private EntityManager em;

   private List<CuentaCorriente> cuentasCorriente;


   @Produces
   @Named
   public List<CuentaCorriente> getCuentasCorriente() {
      return cuentasCorriente;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final CuentaCorriente cuentaCorriente) {
	      retrieveAllOrderedByName();
   }

   @PostConstruct
   public void retrieveAllOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<CuentaCorriente> criteria = cb.createQuery(CuentaCorriente.class);
      Root<CuentaCorriente> cuentaCorriente = criteria.from(CuentaCorriente.class);
      criteria.select(cuentaCorriente).orderBy(cb.asc(cuentaCorriente.get("id")));
      cuentasCorriente = em.createQuery(criteria).getResultList();
   }
   
}

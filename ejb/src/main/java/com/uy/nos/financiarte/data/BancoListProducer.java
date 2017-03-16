
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

import com.uy.nos.financiarte.model.Banco;


@RequestScoped
public class BancoListProducer {
	
   @Inject
   private EntityManager em;

   private List<Banco> bancos;


   @Produces
   @Named
   public List<Banco> getBancos() {
      return bancos;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Banco banco) {
	      retrieveAllOrderedByName();
   }

   @PostConstruct
   public void retrieveAllOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Banco> criteria = cb.createQuery(Banco.class);
      Root<Banco> banco = criteria.from(Banco.class);
      criteria.select(banco).orderBy(cb.asc(banco.get("id")));
      bancos = em.createQuery(criteria).getResultList();
   }
   
}

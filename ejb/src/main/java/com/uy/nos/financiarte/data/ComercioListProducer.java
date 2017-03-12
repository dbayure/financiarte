
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

import com.uy.nos.financiarte.model.Comercio;


@RequestScoped
public class ComercioListProducer {
	
   @Inject
   private EntityManager em;

   private List<Comercio> comercios;


   @Produces
   @Named
   public List<Comercio> getComercios() {
      return comercios;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Comercio comercio) {
	      retrieveAllOrderedByName();
   }

   @PostConstruct
   public void retrieveAllOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Comercio> criteria = cb.createQuery(Comercio.class);
      Root<Comercio> comercio = criteria.from(Comercio.class);
      criteria.select(comercio).orderBy(cb.asc(comercio.get("id")));
      comercios = em.createQuery(criteria).getResultList();
   }
   
   
   public List<Comercio> comerciosPorCliente(Long idCliente){
	  CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Comercio> criteria = cb.createQuery(Comercio.class);
      Root<Comercio> comercio = criteria.from(Comercio.class);
      criteria.select(comercio);
      criteria.where(cb.equal(comercio.get("cliente"), idCliente));
      List<Comercio> resultado = em.createQuery(criteria).getResultList();
      return resultado;
   }
}

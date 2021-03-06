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

import com.uy.nos.financiarte.model.Estado;



@RequestScoped
public class EstadoListProducer {
	
   @Inject
   private EntityManager em;

   private List<Estado> estados;


   @Produces
   @Named
   public List<Estado> getEstados() {
      return estados;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Estado estado) {
	      retrieveAllOrderedByName();
   }

   @PostConstruct
   public void retrieveAllOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Estado> criteria = cb.createQuery(Estado.class);
      Root<Estado> estado = criteria.from(Estado.class);
      criteria.select(estado).orderBy(cb.asc(estado.get("id")));
      estados = em.createQuery(criteria).getResultList();
   }
   
   public List<Estado> obtenerEstadoPorNombre(String nombre) {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Estado> criteria = cb.createQuery(Estado.class);
      Root<Estado> estado = criteria.from(Estado.class);
      criteria.select(estado);
      criteria.where(cb.equal(estado.get("nombre"), nombre));
      List<Estado> e = em.createQuery(criteria).getResultList();
      return e;
   }
}


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

import com.uy.nos.financiarte.model.Ciudad;


@RequestScoped
public class CiudadListProducer {
	
   @Inject
   private EntityManager em;

   private List<Ciudad> ciudades;


   @Produces
   @Named
   public List<Ciudad> getCiudades() {
      return ciudades;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Ciudad ciudad) {
	      retrieveAllOrderedByName();
   }

   @PostConstruct
   public void retrieveAllOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Ciudad> criteria = cb.createQuery(Ciudad.class);
      Root<Ciudad> ciudad = criteria.from(Ciudad.class);
      criteria.select(ciudad).orderBy(cb.asc(ciudad.get("id")));
      ciudades = em.createQuery(criteria).getResultList();
   }
   
   public List<Ciudad> obtenerCiudadesPorDepto(Long depto) {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Ciudad> criteria = cb.createQuery(Ciudad.class);
      Root<Ciudad> ciudad = criteria.from(Ciudad.class);
      criteria.select(ciudad);
      criteria.where(cb.equal(ciudad.get("departamento"), depto));
      List<Ciudad> cities = em.createQuery(criteria).getResultList();
      return cities;
   }

}

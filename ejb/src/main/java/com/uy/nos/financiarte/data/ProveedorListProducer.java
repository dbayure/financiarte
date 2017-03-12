
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

import com.uy.nos.financiarte.model.Proveedor;


@RequestScoped
public class ProveedorListProducer {
	
   @Inject
   private EntityManager em;

   private List<Proveedor> proveedores;


   @Produces
   @Named
   public List<Proveedor> getProveedores() {
      return proveedores;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Proveedor proveedor) {
	      retrieveAllOrderedByName();
   }

   @PostConstruct
   public void retrieveAllOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Proveedor> criteria = cb.createQuery(Proveedor.class);
      Root<Proveedor> proveedor = criteria.from(Proveedor.class);
      criteria.select(proveedor).orderBy(cb.asc(proveedor.get("id")));
      proveedores = em.createQuery(criteria).getResultList();
   }
}

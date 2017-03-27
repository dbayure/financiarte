
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

import com.uy.nos.financiarte.model.Transaccion;


@RequestScoped
public class TransaccionListProducer {
	
   @Inject
   private EntityManager em;

   private List<Transaccion> transacciones;


   @Produces
   @Named
   public List<Transaccion> getTransacciones() {
      return transacciones;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Transaccion transaccion) {
	      retrieveAllOrderedByName();
   }

   @PostConstruct
   public void retrieveAllOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Transaccion> criteria = cb.createQuery(Transaccion.class);
      Root<Transaccion> transaccion = criteria.from(Transaccion.class);
      criteria.select(transaccion).orderBy(cb.asc(transaccion.get("id")));
      transacciones = em.createQuery(criteria).getResultList();
   }
   
   
   public  List<Transaccion> obtenerTransaccionesPorContrato(Long idContrato) {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Transaccion> criteria = cb.createQuery(Transaccion.class);
      Root<Transaccion> transaccion = criteria.from(Transaccion.class);
      criteria.select(transaccion);
      criteria.where(cb.equal(transaccion.get("contrato"), idContrato));
      List<Transaccion> resultado = em.createQuery(criteria).getResultList();
      return resultado;
   }
   
}

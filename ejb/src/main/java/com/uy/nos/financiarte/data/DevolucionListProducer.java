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

import com.uy.nos.financiarte.model.Devolucion;



@RequestScoped
public class DevolucionListProducer {
	
   @Inject
   private EntityManager em;

   private List<Devolucion> devoluciones;


   @Produces
   @Named
   public List<Devolucion> getDevoluciones() {
      return devoluciones;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Devolucion devolucion) {
	      retrieveAllOrderedByName();
   }

   @PostConstruct
   public void retrieveAllOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Devolucion> criteria = cb.createQuery(Devolucion.class);
      Root<Devolucion> devolucion = criteria.from(Devolucion.class);
      criteria.select(devolucion).orderBy(cb.asc(devolucion.get("devolucion")));
      devoluciones = em.createQuery(criteria).getResultList();
   }
   
   public List<Devolucion> getDevolucionPorContrato(Long idContrato) {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Devolucion> criteria = cb.createQuery(Devolucion.class);
      Root<Devolucion> devolucion = criteria.from(Devolucion.class);
      criteria.select(devolucion);
      criteria.where(cb.equal(devolucion.get("contrato"), idContrato));
      List<Devolucion> devs = em.createQuery(criteria).getResultList();
      return devs;
   }

}

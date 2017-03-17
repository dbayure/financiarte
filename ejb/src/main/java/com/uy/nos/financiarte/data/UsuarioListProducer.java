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

import com.uy.nos.financiarte.model.Usuario;



@RequestScoped
public class UsuarioListProducer {
	
   @Inject
   private EntityManager em;

   private List<Usuario> usuarios;


   @Produces
   @Named
   public List<Usuario> getUsuarios() {
      return usuarios;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Usuario usuario) {
	      retrieveAllOrderedByName();
   }

   @PostConstruct
   public void retrieveAllOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
      Root<Usuario> usuario = criteria.from(Usuario.class);
      criteria.select(usuario).orderBy(cb.asc(usuario.get("usuario")));
      usuarios = em.createQuery(criteria).getResultList();
   }
   
   public Usuario buscarUsuarioPorNombre(String usuario){
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
      Root<Usuario> usr = criteria.from(Usuario.class);
      criteria.select(usr);
      criteria.where(cb.equal(usr.get("usuario"), usuario));
	  Usuario u = new Usuario();
	  u = em.createQuery(criteria).getSingleResult();
	  return u;
	   
   }
}

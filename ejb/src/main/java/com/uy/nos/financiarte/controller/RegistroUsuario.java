package com.uy.nos.financiarte.controller;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import com.uy.nos.financiarte.data.UsuarioListProducer;
import com.uy.nos.financiarte.model.Usuario;



@Stateful
@Model
public class RegistroUsuario {

	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;
	   
	   @Inject
	   private  UsuarioListProducer ulp;;

	   @Inject
	   private Event<Usuario> usuarioEventSrc;

	   private Usuario newUsuario;

	   @Produces
	   @Named
	   public Usuario getNewUsuario() {
	      return newUsuario;
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newUsuario.getNombre());
	      em.persist(newUsuario);
	      usuarioEventSrc.fire(newUsuario);
	      initNewUsuario();
	   }
	   
	   public void modificar(Usuario usuario) throws Exception {
		   log.info("Modifico " + usuario);
		   em.merge(usuario);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   Usuario usuario = em.find(Usuario.class, id);
		   em.remove(usuario);
		   usuarioEventSrc.fire(newUsuario);
	   }

	   public Usuario buscar(Long id) throws Exception {
		   log.info("Buscar " + id);
		   Usuario usuario = em.find(Usuario.class, id);
		   return usuario;
	   }
	   
	   @PostConstruct
	   public void initNewUsuario() {
		   newUsuario = new Usuario();
	   }
	   
	   public Usuario buscarUsuarioPorNombre(String usuario){
		   log.info("Buscar " + usuario);
		   Usuario u = ulp.buscarUsuarioPorNombre(usuario);
		   return u;
	   }
}
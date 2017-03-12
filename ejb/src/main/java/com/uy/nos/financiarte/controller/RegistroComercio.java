package com.uy.nos.financiarte.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.jboss.security.SecurityContextAssociation;

import com.uy.nos.financiarte.data.ComercioListProducer;
import com.uy.nos.financiarte.model.Cliente;
import com.uy.nos.financiarte.model.Comercio;
import com.uy.nos.financiarte.model.Usuario;



@Stateful
@Model
public class RegistroComercio {

	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;
	   
	   @Inject
	   private RegistroUsuario registroUsuario;

	   @Inject
	   private Event<Comercio> comercioEventSrc;

	   private Comercio newComercio;
	   
	   @Inject
	   private ComercioListProducer clp;

	   @Produces
	   @Named
	   public Comercio getNewComercio() {
	      return newComercio;
	   }

	   public void registro(Cliente clienteSeleccionado) throws Exception {
	      log.info("Registro " + newComercio.getNombre());
	      if(clienteSeleccionado == null){
	    	  String usr = SecurityContextAssociation.getPrincipal().getName();
		      Usuario usuario  = registroUsuario.buscarUsuarioPorNombre(usr);
		      Cliente cliente = em.find(Cliente.class, usuario.getId());
		      newComercio.setCliente(cliente);
	      }
	      else{
	    	  newComercio.setCliente(clienteSeleccionado);
	      }
	      em.persist(newComercio);
	      comercioEventSrc.fire(newComercio);
	      initNewComercio();
	   }
	   
	   public void modificar(Comercio comercio) throws Exception {
		   log.info("Modifico " + comercio);
		   em.merge(comercio);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   Comercio comercio = em.find(Comercio.class, id);
		   em.remove(comercio);
		   comercioEventSrc.fire(newComercio);
	   }

	   public Comercio buscar(Long id) throws Exception {
		   log.info("Buscar " + id);
		   Comercio comercio = em.find(Comercio.class, id);
		   return comercio;
	   }
	   
	   @PostConstruct
	   public void initNewComercio() {
		   newComercio = new Comercio();
	   }
	   
	   
	   public List<Comercio> comerciosPorCliente(Long idCliente){
		   return clp.comerciosPorCliente(idCliente);
	   }
}
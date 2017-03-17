package com.uy.nos.financiarte.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.uy.nos.financiarte.data.DevolucionListProducer;
import com.uy.nos.financiarte.model.Devolucion;



/**
 * JAX-RS Example
 * 
 * This class produces a RESTful service to read the contents of the members table.
 */
@Path("/devoluiciones")
@RequestScoped
public class DevolucionResourceRESTService {
	
   @Inject
   private EntityManager em;
   
   @Inject
   private DevolucionListProducer devolucion;

   @GET
   @Produces("application/json")
   public List<Devolucion> listAll() {
      // Use @SupressWarnings to force IDE to ignore warnings about "genericizing" the results of
      // this query
      @SuppressWarnings("unchecked")
      // We recommend centralizing inline queries such as this one into @NamedQuery annotations on
      // the @Entity class
      // as described in the named query blueprint:
      // https://blueprints.dev.java.net/bpcatalog/ee5/persistence/namedquery.html
      final List<Devolucion> results = devolucion.getDevoluciones();
      return results;
   }

   @GET
   @Path("/{id:[0-9][0-9]*}")
   @Produces("application/json")
   public Devolucion lookupById(@PathParam("id") long id) {
      return em.find(Devolucion.class, id);
   }
}

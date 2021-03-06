package com.uy.nos.financiarte.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.uy.nos.financiarte.data.MedioPagoListProducer;
import com.uy.nos.financiarte.model.MedioPago;



/**
 * JAX-RS Example
 * 
 * This class produces a RESTful service to read the contents of the members table.
 */
@Path("/mediosPago")
@RequestScoped
public class MedioPagoResourceRESTService {
	
   @Inject
   private EntityManager em;
   
   @Inject
   private MedioPagoListProducer medios;

   @GET
   @Produces("application/json")
   public List<MedioPago> listAll() {
      // Use @SupressWarnings to force IDE to ignore warnings about "genericizing" the results of
      // this query
      @SuppressWarnings("unchecked")
      // We recommend centralizing inline queries such as this one into @NamedQuery annotations on
      // the @Entity class
      // as described in the named query blueprint:
      // https://blueprints.dev.java.net/bpcatalog/ee5/persistence/namedquery.html
      final List<MedioPago> results = medios.getMediosPago();
      return results;
   }

   @GET
   @Path("/{id:[0-9][0-9]*}")
   @Produces("application/json")
   public MedioPago lookupById(@PathParam("id") long id) {
      return em.find(MedioPago.class, id);
   }
}

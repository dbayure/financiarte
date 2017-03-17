package com.uy.nos.financiarte.controller;

import java.util.ArrayList;
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

import com.uy.nos.financiarte.data.ContratoListProducer;
import com.uy.nos.financiarte.data.FacturaListProducer;
import com.uy.nos.financiarte.data.UsuarioListProducer;
import com.uy.nos.financiarte.model.Contrato;
import com.uy.nos.financiarte.model.Devolucion;
import com.uy.nos.financiarte.model.Factura;
import com.uy.nos.financiarte.model.Usuario;



@Stateful
@Model
public class RegistroDevolucion {
	
	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;
	   
	   @Inject
	   private ContratoListProducer clp;
	   
	   @Inject
	   private UsuarioListProducer ulp;
	   
	   @Inject
	   private FacturaListProducer flp;

	   @Inject
	   private Event<Devolucion> devolucionEventSrc;

	   private Devolucion newDevolucion;
	   
	   @Produces
	   @Named
	   public Devolucion getNewDevolucion() {
	      return newDevolucion;
	   }

	   public void registro(Contrato contrato, String descripcion, long monto, Factura factura) throws Exception {
	      log.info("Registro " + newDevolucion.getDescripcion());
	      em.persist(newDevolucion);
	      devolucionEventSrc.fire(newDevolucion);
	      initNewDevolucion();
	   }
	   
	   public void modificar(Devolucion devolucion) throws Exception {
		   log.info("Modifico " + devolucion);
		   em.merge(devolucion);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   Devolucion devolucion = em.find(Devolucion.class, id);
		   em.remove(devolucion);
		   devolucionEventSrc.fire(newDevolucion);
	   }
	   
	   public Devolucion buscar(Long id) throws Exception {
		   log.info("Buscar " + id);
		   Devolucion devolucion = em.find(Devolucion.class, id);
		   return devolucion;
	   }
	   
	   @PostConstruct
	   public void initNewDevolucion() {
		   newDevolucion = new Devolucion();
	   }

	   public List<Contrato> contratosPorCliente(String usuario){
		   List<Contrato> contratos = new ArrayList<Contrato>();
		   Usuario usr = new Usuario(); 
		   usr = ulp.buscarUsuarioPorNombre(usuario);
		   contratos = clp.getContratosPorCliente(usr.getId());
		   return contratos;
	   }
	  
	  public List<Factura> facturasPorcontrato(Long idContrato){
		  System.out.println("id del contrato a buscacr la factura" + idContrato);
		  List<Factura> facturas = null;
		  facturas = flp.getFacturaPorContrato(idContrato);
		  return facturas;
	  }
}
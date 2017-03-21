package com.uy.nos.financiarte.controller;

import java.util.Calendar;
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
import com.uy.nos.financiarte.model.Cliente;
import com.uy.nos.financiarte.model.Contrato;
import com.uy.nos.financiarte.model.Interes;
import com.uy.nos.financiarte.model.Proveedor;
import com.uy.nos.financiarte.model.TipoContrato;



@Stateful
@Model
public class RegistroContrato {

	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private ContratoListProducer clp;
	   
	   @Inject
	   private Event<Contrato> contratoEventSrc;

	   private Contrato newContrato;
	   private boolean tieneDatos = false;

	   @Produces
	   @Named
	   public Contrato getNewContrato() {
	      return newContrato;
	   }
	   
	   public boolean registro(Cliente cliente, Proveedor proveedor, long montoPrestamo, int diasInteres, long pagoMinimo, int plazoPago, Interes interes, TipoContrato tipo) throws Exception {
	      log.info("Registro " + newContrato.getId());
	      boolean seAgrega = false;
	      if (clp.getContratoPorClienteProveedor(cliente.getId(), proveedor.getId()) == null){
	    	  Calendar today = Calendar.getInstance();
		      today.set(Calendar.HOUR_OF_DAY, 0);
		      newContrato.setFecha(today);
		      newContrato.setCliente(cliente);
		      newContrato.setProveedor(proveedor);
		      newContrato.setInteres(interes);
		      newContrato.setMontoPrestamo(montoPrestamo);
		      newContrato.setDiasSinInteres(diasInteres);
		      newContrato.setPagoMinimo(pagoMinimo);
		      newContrato.setPlazoPago(plazoPago);
		      newContrato.setTiposContrato(tipo);
		      em.persist(newContrato);
		      contratoEventSrc.fire(newContrato);
		      initNewComercio();
		      seAgrega = true;
	      }
	      return seAgrega;
	   }
	   
	   public void modificar(Contrato contrato) throws Exception {
		   log.info("Modifico " + contrato);
		   em.merge(contrato);
	   }
	   
	  public boolean eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   boolean fueEliminado = false;
		   contratoTieneDatos(id);
		   if (tieneDatos == false){
			   Contrato contrato = em.find(Contrato.class, id);
			   em.remove(contrato);
			   contratoEventSrc.fire(newContrato);
			   fueEliminado = true;
		   }
		   return fueEliminado;
	   }

	   public Contrato buscar(Long id) throws Exception {
		   log.info("Buscar " + id);
		   Contrato contrato = em.find(Contrato.class, id);
		   return contrato;
	   }
	   
	   @PostConstruct
	   public void initNewComercio() {
		   newContrato = new Contrato();
	   }
	   
	   public void contratoTieneDatos(Long idContrato){
		   Contrato contrato = em.find(Contrato.class, idContrato);
		   if (!contrato.getDevoluciones().isEmpty()){
			   tieneDatos = true;
		   }
		   if (!contrato.getSolicitudes().isEmpty()){
			   tieneDatos = true;
		   }
		   if (!contrato.getFacturas().isEmpty()){
			   tieneDatos = true;
		   }
		   if (!contrato.getNotas().isEmpty()){
			   tieneDatos = true;
		   }
		   if (!contrato.getPagos().isEmpty()){
			   tieneDatos = true;
		   }
			   
	   }
	   
	   public boolean buscarContratoDuplicado(Long cliente, Long proveedor){
		   if (clp.getContratoPorClienteProveedor(cliente,proveedor) == null){
			   return false;
		   }
		   else{
			   return true;
		   }
		   
	   }
}
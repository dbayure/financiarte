package com.uy.nos.financiarte.controller;

import java.util.Calendar;
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

import com.uy.nos.financiarte.data.DevolucionListProducer;
import com.uy.nos.financiarte.data.FacturaListProducer;
import com.uy.nos.financiarte.data.NotaCreditoListProducer;
import com.uy.nos.financiarte.data.SolicitudCreditoListProducer;
import com.uy.nos.financiarte.data.PagoMedioPagoListProducer;
import com.uy.nos.financiarte.model.Cliente;
import com.uy.nos.financiarte.model.Contrato;
import com.uy.nos.financiarte.model.Devolucion;
import com.uy.nos.financiarte.model.Interes;
import com.uy.nos.financiarte.model.Proveedor;



@Stateful
@Model
public class RegistroContrato {

	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;
	   
	   @Inject
	   private DevolucionListProducer dlp;
	   
	   @Inject
	   private SolicitudCreditoListProducer pclp;
	   
	   @Inject
	   private FacturaListProducer flp;
	   
	   @Inject
	   private NotaCreditoListProducer nclp;
	   
	   @Inject
	   private PagoMedioPagoListProducer pmplp;

	   @Inject
	   private Event<Contrato> contratoEventSrc;

	   private Contrato newContrato;
	   private boolean tieneDatos = false;

	   @Produces
	   @Named
	   public Contrato getNewContrato() {
	      return newContrato;
	   }
	   
	   public void registro(Cliente cliente, Proveedor proveedor, long montoPrestamo, int diasInteres, long pagoMinimo, int plazoPago, Interes interes) throws Exception {
	      log.info("Registro " + newContrato.getId());
	      Calendar today = Calendar.getInstance();
	      today.set(Calendar.HOUR_OF_DAY, 0);
	      newContrato.setFecha(today);
	      newContrato.setCliente(cliente);
	      newContrato.setProveedor(proveedor);
	      newContrato.setInteres(interes);
	      newContrato.setMontoPrestamo(montoPrestamo);
	      newContrato.setDiasInteres(diasInteres);
	      newContrato.setPagoMinimo(pagoMinimo);
	      newContrato.setPlazoPago(plazoPago);
	      em.persist(newContrato);
	      contratoEventSrc.fire(newContrato);
	      initNewComercio();
	   }
	   
	   public void modificar(Contrato contrato) throws Exception {
		   log.info("Modifico " + contrato);
		   em.merge(contrato);
	   }
	   
	  public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   contratoTieneDatos(id);
		   if (tieneDatos == false){
			   Contrato contrato = em.find(Contrato.class, id);
			   em.remove(contrato);
			   contratoEventSrc.fire(newContrato);
		   }
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
		   List<Devolucion> devoluciones = dlp.getDevolucionPorContrato(idContrato);
		   System.out.println("Cantidad de devoluciones " + devoluciones.size());
//		   if(!dlp.getDevolucionPorContrato(idContrato).isEmpty()){
//			   tieneDatos = true;
//		   }
//		   if(!pclp.getPagoClientePorContrato(idContrato).isEmpty()){
//			   tieneDatos = true;
//		   }
//		   if(!flp.getFacturaPorContrato(idContrato).isEmpty()){
//			   tieneDatos = true;
//		   }
//		   if(!nclp.getNotaCreditoPorContrato(idContrato).isEmpty()){
//			   tieneDatos = true;
//		   }
//		   if(!pmplp.getPagoMedioPagoPorContrato(idContrato).isEmpty()){
//			   tieneDatos = true;
//		   }
	   }
	   
}
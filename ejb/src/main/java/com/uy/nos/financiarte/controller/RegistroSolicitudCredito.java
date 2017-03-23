package com.uy.nos.financiarte.controller;

import java.util.ArrayList;
import java.util.Date;
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

import com.uy.nos.financiarte.data.ClienteListProducer;
import com.uy.nos.financiarte.data.ContratoListProducer;
import com.uy.nos.financiarte.data.EstadoListProducer;
import com.uy.nos.financiarte.data.FacturaListProducer;
import com.uy.nos.financiarte.model.Cliente;
import com.uy.nos.financiarte.model.Contrato;
import com.uy.nos.financiarte.model.Factura;
import com.uy.nos.financiarte.model.Estado;
import com.uy.nos.financiarte.model.SolicitudCredito;



@Stateful
@Model
public class RegistroSolicitudCredito {
	
	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;
	   
	   @Inject
	   private RegistroFactura rf;
	   
	   @Inject
	   private FacturaListProducer flp;
	   
	   @Inject
	   private ContratoListProducer clp;
	   
	   @Inject
	   private ClienteListProducer clilp;
	   
	   @Inject
	   private EstadoListProducer elp;

	   @Inject
	   private Event<SolicitudCredito> solicitudCreditoEventSrc;

	   private SolicitudCredito newSolicitudCredito;
	   
	   @Produces
	   @Named
	   public SolicitudCredito getNewSolicitudCredito() {
	      return newSolicitudCredito;
	   }

	   public void registro(List<Factura> facturasSeleccionada, Contrato contrato) throws Exception {
	      log.info("Registro solicitud de credito" + newSolicitudCredito.getMonto());
	      List<Estado> estados = elp.obtenerEstadoPorNombre("entregada");
	      for (Factura factura : facturasSeleccionada) {
	    	  factura.setEstados(estados.get(0));
	 	      rf.modificar(factura);
		      newSolicitudCredito.setContrato(contrato);
		      Date hoy = new Date();
		      newSolicitudCredito.setFecha(hoy);
		      newSolicitudCredito.setMonto(factura.getMonto());
		      newSolicitudCredito.setTotalParcial(true);
		      em.persist(newSolicitudCredito);
		      solicitudCreditoEventSrc.fire(newSolicitudCredito);
		      initNewSolicitudCredito();
	      }
	   }
	   
	   public void modificar(SolicitudCredito solicitudCredito) throws Exception {
		   log.info("Modifico " + solicitudCredito);
		   em.merge(solicitudCredito);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   SolicitudCredito solicitudCredito = em.find(SolicitudCredito.class, id);
		   em.remove(solicitudCredito);
		   solicitudCreditoEventSrc.fire(newSolicitudCredito);
	   }
	   
	   public SolicitudCredito buscar(Long id) throws Exception {
		   log.info("Buscar " + id);
		   SolicitudCredito solicitudCredito = em.find(SolicitudCredito.class, id);
		   return solicitudCredito;
	   }

	   @PostConstruct
	   public void initNewSolicitudCredito() {
		   newSolicitudCredito = new SolicitudCredito();
	   }

	   public List<Factura> obtenerFacturasPorContrato(Long idCcontrato){
		   List<Factura> facturas = new ArrayList<Factura>();
		   facturas = flp.getFacturaPorContrato(idCcontrato);
		   return facturas;
	   }
	   
	   public Cliente obtenerClientePorUsuario(String usuario){
		   return clilp.obtenerClientePorUsuario(usuario);
	   }
	   
	   public List<Contrato> obtenerContatosPorCliente(Long idCliente){
		   List<Contrato> contratos = clp.getContratosPorCliente(idCliente);
		   return contratos;
	   }
	   
}

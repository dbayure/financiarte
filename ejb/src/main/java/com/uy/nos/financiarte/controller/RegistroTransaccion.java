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

import com.uy.nos.financiarte.data.ContratoListProducer;
import com.uy.nos.financiarte.data.TransaccionListProducer;
import com.uy.nos.financiarte.data.UsuarioListProducer;
import com.uy.nos.financiarte.model.Contrato;
import com.uy.nos.financiarte.model.TipoMovimiento;
import com.uy.nos.financiarte.model.Transaccion;
import com.uy.nos.financiarte.model.Usuario;



@Stateful
@Model
public class RegistroTransaccion {

	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;
	   
	   @Inject
	   private UsuarioListProducer ulp;
	   
	   @Inject
	   private ContratoListProducer clp;
	   
	   @Inject
	   private TransaccionListProducer tlp;

	   @Inject
	   private Event<Transaccion> transaccionEventSrc;

	   private Transaccion newTransaccion;

	   @Produces
	   @Named
	   public Transaccion getNewTransaccion() {
	      return newTransaccion;
	   }

	   public void registro(Contrato contrato, float monto, TipoMovimiento tipo) throws Exception {
	      log.info("Registro " + newTransaccion.getId());
	      newTransaccion.setContrato(contrato);
	      newTransaccion.setMonto(monto);
	      newTransaccion.setTipos(tipo);
	      Date fecha = new Date();
	      newTransaccion.setFecha(fecha);
	      em.persist(newTransaccion);
	      transaccionEventSrc.fire(newTransaccion);
	      initNewTransaccion();
	   }
	   
	   public void modificar(Transaccion transaccion) throws Exception {
		   log.info("Modifico " + transaccion.getId());
		   em.merge(transaccion);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   Transaccion transaccion = em.find(Transaccion.class, id);
		   em.remove(transaccion);
		   transaccionEventSrc.fire(newTransaccion);
	   }

	   public Transaccion buscar(Long id) throws Exception {
		   log.info("Buscar " + id);
		   Transaccion transaccion = em.find(Transaccion.class, id);
		   return transaccion;
	   }
	   
	   @PostConstruct
	   public void initNewTransaccion() {
		   newTransaccion = new Transaccion();
	   }
	   
	   public Usuario buscarUsuarioPorNombre(String usuario){
		   return ulp.buscarUsuarioPorNombre(usuario);
	   }
	   
	   public List<Contrato> obtenerContratosPorcliente(Long idCliente){
		   List<Contrato> contratos = new ArrayList<Contrato>();
		   contratos = clp.getContratosPorCliente(idCliente);
		   return contratos;
	   }
	   
	   public List<Transaccion> obtenerTransaccionesPorContrato(Long idContrato){
		   List<Transaccion> transacciones = new ArrayList<Transaccion>();
		   transacciones = tlp.obtenerTransaccionesPorContrato(idContrato);
		   return transacciones;
	   }	   
	   
	   
}
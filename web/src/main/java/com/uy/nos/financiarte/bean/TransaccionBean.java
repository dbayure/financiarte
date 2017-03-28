package com.uy.nos.financiarte.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.jboss.security.SecurityContextAssociation;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import com.uy.nos.financiarte.controller.RegistroTransaccion;
import com.uy.nos.financiarte.model.Cliente;
import com.uy.nos.financiarte.model.Contrato;
import com.uy.nos.financiarte.model.Factura;
import com.uy.nos.financiarte.model.NotaCredito;
import com.uy.nos.financiarte.model.PagoMedioPago;
import com.uy.nos.financiarte.model.Proveedor;
import com.uy.nos.financiarte.model.SolicitudCredito;
import com.uy.nos.financiarte.model.Transaccion;
import com.uy.nos.financiarte.model.Usuario;



@ManagedBean
@RequestScoped
public class TransaccionBean {

	private List<Transaccion> transaccionesCliente = new ArrayList<Transaccion>();
	private List<Factura> factruasPendientesCliente = new ArrayList<Factura>();
	private List<NotaCredito> notasCreditoCliente = new ArrayList<NotaCredito>();
	private List<SolicitudCredito> solicitudesPendientesCliente = new ArrayList<SolicitudCredito>();
	private List<Contrato> contratos = new ArrayList<Contrato>();
	private List<PagoMedioPago> pagosCliente = new ArrayList<PagoMedioPago>();
	private Transaccion transaccionSeleccionada;
	private Factura facturaSeleccionada;
	private PagoMedioPago pagoSeleccionado;
	private SolicitudCredito solicitudSeleccionada;
	private float totalCreditos;
	private float totalCreditosPendientes;
	private float totalPagos;
	private float saldoActual;
	private float iva =0.22f;
	
	public List<Transaccion> getTransaccionesCliente() {
		return transaccionesCliente;
	}

	public void setTransaccionesCliente(List<Transaccion> transaccionesCliente) {
		this.transaccionesCliente = transaccionesCliente;
	}
	
	public List<Factura> getFactruasPendientesCliente() {
		return factruasPendientesCliente;
	}

	public void setFactruasPendientesCliente(List<Factura> factruasPendientesCliente) {
		this.factruasPendientesCliente = factruasPendientesCliente;
	}

	public List<NotaCredito> getNotasCreditoCliente() {
		return notasCreditoCliente;
	}

	public void setNotasCreditoCliente(List<NotaCredito> notasCreditoCliente) {
		this.notasCreditoCliente = notasCreditoCliente;
	}

	public List<SolicitudCredito> getSolicitudesPendientesCliente() {
		return solicitudesPendientesCliente;
	}

	public void setSolicitudesPendientesCliente(List<SolicitudCredito> solicitudesPendientesCliente) {
		this.solicitudesPendientesCliente = solicitudesPendientesCliente;
	}

	public List<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}

	public List<PagoMedioPago> getPagosCliente() {
		return pagosCliente;
	}

	public void setPagosCliente(List<PagoMedioPago> pagosCliente) {
		this.pagosCliente = pagosCliente;
	}

	public Transaccion getTransaccionSeleccionada() {
		return transaccionSeleccionada;
	}

	public void setTransaccionSeleccionada(Transaccion transaccionSeleccionada) {
		this.transaccionSeleccionada = transaccionSeleccionada;
	}

	public Factura getFacturaSeleccionada() {
		return facturaSeleccionada;
	}

	public void setFacturaSeleccionada(Factura facturaSeleccionada) {
		this.facturaSeleccionada = facturaSeleccionada;
	}

	public PagoMedioPago getPagoSeleccionado() {
		return pagoSeleccionado;
	}

	public void setPagoSeleccionado(PagoMedioPago pagoSeleccionado) {
		this.pagoSeleccionado = pagoSeleccionado;
	}

	public SolicitudCredito getSolicitudSeleccionada() {
		return solicitudSeleccionada;
	}

	public void setSolicitudSeleccionada(SolicitudCredito solicitudSeleccionada) {
		this.solicitudSeleccionada = solicitudSeleccionada;
	}

	public float getTotalCreditos() {
		return totalCreditos;
	}

	public void setTotalCreditos(float totalCreditos) {
		this.totalCreditos = totalCreditos;
	}

	public float getTotalCreditosPendientes() {
		return totalCreditosPendientes;
	}

	public void setTotalCreditosPendientes(float totalCreditosPendientes) {
		this.totalCreditosPendientes = totalCreditosPendientes;
	}

	public float getTotalPagos() {
		return totalPagos;
	}

	public void setTotalPagos(float totalPagos) {
		this.totalPagos = totalPagos;
	}

	public float getSaldoActual() {
		return saldoActual;
	}

	public void setSaldoActual(float saldoActual) {
		this.saldoActual = saldoActual;
	}

	@Inject
	private RegistroTransaccion registroTransaccion;
	
	@PostConstruct
	public void init() {
		generarListaTransaccionesCliente();
	}
	
	public void registrar() {
		try {
			registroTransaccion.registro(null,0,null);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró ", "con éxito!");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch (Exception e) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al registrar ", "");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
		}
	}
	
	public void onEdit(RowEditEvent event) {  
		Transaccion transaccion = ((Transaccion) event.getObject());
           
            try {
            	registroTransaccion.modificar(transaccion);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se modificó ", transaccion.getId().toString());  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al modificar ", transaccion.getId().toString());  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
    }
	
	public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Se canceló modificar ", ((Transaccion) event.getObject()).getId().toString());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
	
	public void eliminar(Long id) {
		try {
			registroTransaccion.eliminar(id);
			FacesMessage msg = new FacesMessage("Se eliminó ", id.toString());  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch(Exception e) {
			FacesMessage msg = new FacesMessage("Error al eliminar", id.toString());  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		  
	}
	
	public void buscar(Long id) {
		try {
			registroTransaccion.buscar(id);
			FacesMessage msg = new FacesMessage("Se encontró ", id.toString());  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch(Exception e) {
			FacesMessage msg = new FacesMessage("Error al buscar", id.toString());  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		  
	}
	
	public void onCellEdit(CellEditEvent event) {  
		Object oldValue = event.getOldValue();
	    Object newValue = event.getNewValue();
            try {
            	if(newValue != null && !newValue.equals(oldValue)) {
            	    DataTable d = (DataTable) event.getSource();
            	    Transaccion transaccion = (Transaccion) d.getRowData();
            		registroTransaccion.modificar(transaccion);
                }
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "La Transaccion fue modificado exitosamente" , "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al modificar la Transaccion", "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
	}
	
	public void onRowSelect(SelectEvent event) {
		setSolicitudSeleccionada((SolicitudCredito) event.getObject());
		Long estado = 0L;
		for (Factura factura : solicitudSeleccionada.getContrato().getFacturas()) {
			estado = factura.getEstados().getId();
			if(estado == 5L){
				factruasPendientesCliente.add(factura);
			}
		}
		for (NotaCredito nota : solicitudSeleccionada.getContrato().getNotas()) {
			estado = nota.getEstados().getId();
			if(estado == 5L){
				notasCreditoCliente.add(nota);
			}
		}
		pagosCliente.addAll(solicitudSeleccionada.getContrato().getPagos());
    }
	
	
	public void generarListaTransaccionesCliente(){
		String cli = SecurityContextAssociation.getPrincipal().getName();
		Usuario usuario = registroTransaccion.buscarUsuarioPorNombre(cli);
		Cliente cliente = new Cliente();
		Proveedor proveedor = new Proveedor();
		List<SolicitudCredito> solicitudes = new ArrayList<SolicitudCredito>();
		Long estado = 0L;
		float interes = 0;
		float impuesto = 0;
		float total = 0;
		int dias;
		Date hoy = new Date();
		if (usuario instanceof Cliente){
			cliente = (Cliente) usuario;
			contratos.addAll(cliente.getContratos());
			for (Contrato contrato : contratos) {
				setTransaccionesCliente(registroTransaccion.obtenerTransaccionesPorContrato(contrato.getId()));
				solicitudes.addAll(contrato.getSolicitudes());
			}
		}
		if (usuario instanceof Proveedor){
			proveedor = (Proveedor) usuario;
			contratos.addAll(proveedor.getContratos());
			for (Contrato contrato : contratos) {
				setTransaccionesCliente(registroTransaccion.obtenerTransaccionesPorContrato(contrato.getId()));
				solicitudes.addAll(contrato.getSolicitudes());
			}
		}
		for (SolicitudCredito solicitudCredito : solicitudes) {
			estado = solicitudCredito.getEstados().getId();
			dias = (int) ((hoy.getTime() - solicitudCredito.getFecha().getTime())/86400000);
			if (estado == 3L){
				interes = solicitudCredito.getContrato().getInteres().getMonto();
				interes = interes/10000;
				interes = interes * solicitudCredito.getMonto();
				interes = interes * dias;
				impuesto = interes * iva;
				total = solicitudCredito.getMonto() + interes + impuesto;
				totalCreditosPendientes  = totalCreditosPendientes + total;
				solicitudCredito.setInteres(interes);
				solicitudCredito.setIva(impuesto);
				solicitudCredito.setTotal(total);
				solicitudesPendientesCliente.add(solicitudCredito);
			}
		}
		calcularSaldos();
	}
		
	
	public void calcularSaldos(){
		float creditos = 0L;
		float pagos = 0L;
		
		for (Transaccion transaccion : transaccionesCliente) {
			long tipo = transaccion.getTipos().getId();
			if( tipo == 1L){
				creditos = creditos + transaccion.getMonto();
			}
			if(tipo == 1L){
				pagos = pagos + transaccion.getMonto();
			}
		}
		setTotalPagos(pagos);
		setTotalCreditos(creditos);
		setSaldoActual(pagos -(creditos + totalCreditosPendientes));
	}
	
	
}

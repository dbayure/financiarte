package com.uy.nos.financiarte.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.jboss.security.SecurityContextAssociation;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import com.uy.nos.financiarte.controller.RegistroSolicitudCredito;
import com.uy.nos.financiarte.model.Cliente;
import com.uy.nos.financiarte.model.Contrato;
import com.uy.nos.financiarte.model.Factura;
import com.uy.nos.financiarte.model.NotaCredito;
import com.uy.nos.financiarte.model.SolicitudCredito;



@ManagedBean
@ViewScoped
public class SolicitudCreditoBean {
	
	private List<Factura> facturas = new ArrayList<Factura>();
	private List<NotaCredito> notas = new ArrayList<NotaCredito>();
	private List<Contrato> contratosDisponibles;
	private Cliente clienteSeleccionado;
	private Contrato contratoSeleccionado;
	private String pin;
	private float monto;
	private float montoFacturas;
	private float montoNotas;

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public Cliente getClienteSeleccionado() {
		return clienteSeleccionado;
	}

	public void setClienteSeleccionado(Cliente clienteSeleccionado) {
		this.clienteSeleccionado = clienteSeleccionado;
	}

	public Contrato getContratoSeleccionado() {
		return contratoSeleccionado;
	}

	public void setContratoSeleccionado(Contrato contratoSeleccionado) {
		this.contratoSeleccionado = contratoSeleccionado;
	}

	public List<Contrato> getContratosDisponibles() {
		return contratosDisponibles;
	}

	public void setContratosDisponibles(List<Contrato> contratosDisponibles) {
		this.contratosDisponibles = contratosDisponibles;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public float getMonto() {
		calcularMontoTotal();
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public List<NotaCredito> getNotas() {
		return notas;
	}

	public void setNotas(List<NotaCredito> notas) {
		this.notas = notas;
	}

	public float getMontoFacturas() {
		return montoFacturas;
	}

	public void setMontoFacturas(float montoFacturas) {
		this.montoFacturas = montoFacturas;
	}

	public float getMontoNotas() {
		return montoNotas;
	}

	public void setMontoNotas(float montoNotas) {
		this.montoNotas = montoNotas;
	}

	@Inject
	private RegistroSolicitudCredito registroSolicitudCredito;
	
	@PostConstruct
	public void init() {
		generarListaContratos();
	}
	
	public void registrar() {
		try {
			registroSolicitudCredito.registro(getFacturas(), getContratoSeleccionado(), getNotas(), getMonto());
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró ", "con éxito!");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        limpiarListas();
	    	calcularMontoTotal();
		}
		catch (Exception e) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al registrar ", "");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
		}
		generarListaFacturasPendientes();
	}
	
	public void onEdit(RowEditEvent event) {  
		SolicitudCredito pagoCliente = ((SolicitudCredito) event.getObject());
		Long pago = ((SolicitudCredito) event.getObject()).getId();
            try {
            	registroSolicitudCredito.modificar(pagoCliente);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se modificó ", Long.toString(pago));  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al modificar ", Long.toString(pago));  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
    }
	
	public void onCancel(RowEditEvent event) {  
		Long pago = ((SolicitudCredito) event.getObject()).getId();
        FacesMessage msg = new FacesMessage("Se canceló modificar ", Long.toString(pago));  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
	
	public void eliminar(Long id) {
		try {
			registroSolicitudCredito.eliminar(id);
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
			registroSolicitudCredito.buscar(id);
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
            	    SolicitudCredito pagoCliente = (SolicitudCredito) d.getRowData();
            	    registroSolicitudCredito.modificar(pagoCliente);
                }
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "La solicitud de crédito fue modificado exitosamente" , "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al modificar la solicitud de crédito", "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("Llamo a los metodos par acargar tablas auxiliares");
    	generarListaFacturasPendientes();
    	generarListaNotasPendientes();
    	calcularMontoTotal();
    }
	
	public void generarListaFacturasPendientes(){
		float montoParcial = 0L;
		long estado = 0L;
		if (contratoSeleccionado.getFacturas().size() > 0){
			for (Factura factura : contratoSeleccionado.getFacturas()) {
				estado = factura.getEstados().getId();
				if(estado == 3L){
					this.facturas.add(factura);
					montoParcial = montoParcial + factura.getMonto();
				}
			}
		}
		setMontoFacturas(montoParcial);
	}
	
	public void generarListaNotasPendientes(){
		float montoParcial = 0L;
		long estado = 0L;
		if (contratoSeleccionado.getNotas().size() > 0){
			for (NotaCredito notaCredito : contratoSeleccionado.getNotas()) {
				estado = notaCredito.getEstados().getId();
				if(estado == 3L){
					this.notas.add(notaCredito);
					montoParcial = montoParcial + notaCredito.getMonto();
				}
			}
			setMontoNotas(montoParcial);
		}
	}
	
	public void generarListaContratos(){
		String cli = SecurityContextAssociation.getPrincipal().getName();
    	setClienteSeleccionado(registroSolicitudCredito.obtenerClientePorUsuario(cli));
    	List<Contrato> contratos = registroSolicitudCredito.obtenerContatosPorCliente(clienteSeleccionado.getId());
    	if(contratos.size() > 0){
    		setContratosDisponibles(contratos);
    	}
	}
	
	public void calcularMontoTotal(){
    	setMonto(getMontoFacturas() - getMontoNotas());
	}

	public void limpiarListas(){
		this.notas.clear();
		this.facturas.clear();
	}
}

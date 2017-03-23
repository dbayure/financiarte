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
import com.uy.nos.financiarte.model.Proveedor;
import com.uy.nos.financiarte.model.SolicitudCredito;



@ManagedBean
@ViewScoped
public class SolicitudCreditoBean {
	
	private List<Factura> facturasPendientes;
	private List<Contrato> contratosDisponibles;
	private List<Factura> facturasSeleccionadas;
	private Proveedor proveedorSeleccionado;
	private Cliente clienteSeleccionado;
	private Contrato contratoSeleccionado;
	private String pin;

	public List<Factura> getFacturasPendientes() {
		return facturasPendientes;
	}

	public void setFacturasPendientes(List<Factura> facturasPendientes) {
		this.facturasPendientes = facturasPendientes;
	}

	public List<Factura> getFacturasSeleccionadas() {
		return facturasSeleccionadas;
	}

	public void setFacturasSeleccionadas(List<Factura> facturasSeleccionadas) {
		System.out.println("factura seleccionada " + facturasSeleccionadas.get(0).getDescripcion());
		this.facturasSeleccionadas = facturasSeleccionadas;
	}

	public Proveedor getProveedorSeleccionado() {
		return proveedorSeleccionado;
	}

	public void setProveedorSeleccionado(Proveedor proveedorSeleccionado) {
		this.proveedorSeleccionado = proveedorSeleccionado;
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


	@Inject
	private RegistroSolicitudCredito registroSolicitudCredito;
	
	@PostConstruct
	public void init() {
		generarListaProveedores();
	}
	
	public void registrar() {
		try {
			registroSolicitudCredito.registro(facturasSeleccionadas, contratoSeleccionado);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró ", "con éxito!");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
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
    	generarListaFacturasPendientes();
    }
	
	public void generarListaFacturasPendientes(){
		List<Factura> facturas = new ArrayList<Factura>();
		facturas = registroSolicitudCredito.obtenerFacturasPorContrato(contratoSeleccionado.getId());
		if (facturas.size() > 0){
			setFacturasPendientes(facturas);
		}
	}
	
	public void generarListaProveedores(){
		String cli = SecurityContextAssociation.getPrincipal().getName();
    	setClienteSeleccionado(registroSolicitudCredito.obtenerClientePorUsuario(cli));
    	List<Contrato> contratos = registroSolicitudCredito.obtenerContatosPorCliente(clienteSeleccionado.getId());
    	if(contratos.size() > 0){
    		setContratosDisponibles(contratos);
    	}
    	
	}

}

package com.uy.nos.financiarte.bean;

import java.util.List;

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

import com.uy.nos.financiarte.controller.RegistroSolicitudCredito;
import com.uy.nos.financiarte.model.Cliente;
import com.uy.nos.financiarte.model.Contrato;
import com.uy.nos.financiarte.model.Factura;
import com.uy.nos.financiarte.model.Proveedor;
import com.uy.nos.financiarte.model.SolicitudCredito;



@ManagedBean
@RequestScoped
public class SolicitudCreditoBean {
	
	private List<Factura> facturasPendientes;
	private Factura facturaSeleccionada;
	private Proveedor proveedorSeleccionado;
	private Cliente clienteSeleccionado;
	private Contrato contratoSeleccionado;

	public List<Factura> getFacturasPendientes() {
		return facturasPendientes;
	}

	public void setFacturasPendientes(List<Factura> facturasPendientes) {
		this.facturasPendientes = facturasPendientes;
	}

	public Factura getFacturaSeleccionada() {
		return facturaSeleccionada;
	}

	public void setFacturaSeleccionada(Factura facturaSeleccionada) {
		this.facturaSeleccionada = facturaSeleccionada;
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

	@Inject
	private RegistroSolicitudCredito registroSolicitudCredito;
	
	public void registrar() {
		try {
			registroSolicitudCredito.registro(facturaSeleccionada, contratoSeleccionado);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró ", "con éxito!");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch (Exception e) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al registrar ", "");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
		}
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
    	String cli = SecurityContextAssociation.getPrincipal().getName();
    	setClienteSeleccionado(registroSolicitudCredito.obtenerClientePorUsuario(cli));
    	generarListaFacturasPendientes();
    	setContratoSeleccionado(registroSolicitudCredito.obtenerContatoAsociado(clienteSeleccionado.getId(), proveedorSeleccionado.getId()));
    }
	
	public void generarListaFacturasPendientes(){
		setFacturasPendientes(registroSolicitudCredito.obtenerFacturasPorContrato(clienteSeleccionado.getId(), proveedorSeleccionado.getId()));
	}

}

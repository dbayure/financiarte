package com.uy.nos.financiarte.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import com.uy.nos.financiarte.controller.RegistroContrato;
import com.uy.nos.financiarte.model.Cliente;
import com.uy.nos.financiarte.model.Comercio;
import com.uy.nos.financiarte.model.Contrato;


@ManagedBean
@ViewScoped
public class ContratoBean {

	@Inject
	private RegistroContrato registroContrato;
	
	private Cliente clienteSeleccionado;
	private List<Cliente> clientesFiltrados;
	private List<Comercio> comerciosCliente;
	private boolean mostrarComerciosCliente = false;
	
	public boolean isMostrarComerciosCliente() {
		return mostrarComerciosCliente;
	}

	public void setMostrarComerciosCliente(boolean mostrarComerciosCliente) {
		this.mostrarComerciosCliente = mostrarComerciosCliente;
	}

	public List<Comercio> getComerciosCliente() {
		return comerciosCliente;
	}

	public void setComerciosCliente(List<Comercio> comerciosCliente) {
		this.comerciosCliente = comerciosCliente;
	}

	public Cliente getClienteSeleccionado() {
		return clienteSeleccionado;
	}

	public void setClienteSeleccionado(Cliente clienteSeleccionado) {
		this.clienteSeleccionado = clienteSeleccionado;
	}

	public List<Cliente> getClientesFiltrados() {
		return clientesFiltrados;
	}

	public void setClientesFiltrados(List<Cliente> clientesFiltrados) {
		this.clientesFiltrados = clientesFiltrados;
	}

	public void registrar() {
		try {
			registroContrato.registro();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró ", "con éxito!");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch (Exception e) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al registrar ", "");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
		}
	}
	
	public void onEdit(RowEditEvent event) {  
		Contrato contrato = ((Contrato) event.getObject());
           
            try {
            	registroContrato.modificar(contrato);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se modificó ", contrato.getId().toString());  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al modificar ", contrato.getId().toString());  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
    }
	
	public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Se canceló modificar ", ((Comercio) event.getObject()).getNombre());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
	
	public void eliminar(Long id) {
		try {
			registroContrato.eliminar(id);
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
			registroContrato.buscar(id);
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
            	    Contrato contrato = (Contrato) d.getRowData();
            	    registroContrato.modificar(contrato);
                }
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El contrato fue modificado exitosamente" , "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al modificar el contrato", "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
	}
	
	
}

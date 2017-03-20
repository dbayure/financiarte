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
import org.primefaces.event.SelectEvent;

import com.uy.nos.financiarte.controller.RegistroComercio;
import com.uy.nos.financiarte.model.Ciudad;
import com.uy.nos.financiarte.model.Cliente;
import com.uy.nos.financiarte.model.Comercio;
import com.uy.nos.financiarte.model.Departamento;


@ManagedBean
@ViewScoped
public class ComercioBean {

	@Inject
	private RegistroComercio registroComercio;
	
	private Cliente clienteSeleccionado;
	private List<Cliente> clientesFiltrados;
	private List<Comercio> comerciosCliente;
	private boolean mostrarComerciosCliente = false;
	private Departamento deptoSeleccionado;
	private Ciudad ciudadSeleccionada;
	private List<Ciudad> ciudadesDisponibles;
	
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

	public Departamento getDeptoSeleccionado() {
		return deptoSeleccionado;
	}

	public void setDeptoSeleccionado(Departamento deptoSeleccionado) {
		this.deptoSeleccionado = deptoSeleccionado;
	}

	public Ciudad getCiudadSeleccionada() {
		return ciudadSeleccionada;
	}

	public void setCiudadSeleccionada(Ciudad ciudadSeleccionada) {
		this.ciudadSeleccionada = ciudadSeleccionada;
	}

	public List<Ciudad> getCiudadesDisponibles() {
		return ciudadesDisponibles;
	}

	public void setCiudadesDisponibles(List<Ciudad> ciudadesDisponibles) {
		this.ciudadesDisponibles = ciudadesDisponibles;
	}

	public void registrar() {
		try {
			registroComercio.registro(clienteSeleccionado, deptoSeleccionado);
			setComerciosCliente(registroComercio.comerciosPorCliente(clienteSeleccionado.getId()));
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró ", "con éxito!");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch (Exception e) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al registrar ", "");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
		}
	}
	
	public void onEdit(RowEditEvent event) {  
		Comercio comercio = ((Comercio) event.getObject());
           
            try {
            	registroComercio.modificar(comercio);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se modificó ", comercio.getNombre());  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al modificar ", comercio.getNombre());  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
    }
	
	public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Se canceló modificar ", ((Comercio) event.getObject()).getNombre());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
	
	public void eliminar(Long id) {
		try {
			registroComercio.eliminar(id);
			setComerciosCliente(registroComercio.comerciosPorCliente(clienteSeleccionado.getId()));
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
			registroComercio.buscar(id);
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
            	    Comercio comercio = (Comercio) d.getRowData();
            	    registroComercio.modificar(comercio);
                }
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El comercio fue modificado exitosamente" , "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al modificar el comercio", "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
	}
	
    public void onRowSelect(SelectEvent event) {
        Long idCliente = ((Cliente) event.getObject()).getId();
        setComerciosCliente(registroComercio.comerciosPorCliente(idCliente));
        setMostrarComerciosCliente(true);
        System.out.println("Cantidad de comercios del cliente " + idCliente + " = " + comerciosCliente.size());
    }
    
    public void obtenerCiudadesPorDepto(){
    	setCiudadesDisponibles(registroComercio.obtenerCiudadesPorDepto(deptoSeleccionado.getId()));
    	System.out.println("Cantidad de ciudades encontradas : " + ciudadesDisponibles.size());
    	
    }
	
    
}

package com.uy.nos.financiarte.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import com.uy.nos.financiarte.controller.RegistroCliente;
import com.uy.nos.financiarte.model.Banco;
import com.uy.nos.financiarte.model.Cliente;
import com.uy.nos.financiarte.model.Comercio;
import com.uy.nos.financiarte.model.Tarjeta;



@ManagedBean
@RequestScoped
public class ClienteBean {
	
	private Cliente clienteSeleccionado;
	private List<Banco> bancosSeleccionados = new ArrayList<Banco>();
	private List<Comercio> comerciosCliente = new ArrayList<Comercio>();
	private List<Tarjeta> tarjetasCliente = new ArrayList<Tarjeta>();
	private boolean mostrarAnalisis = false;
	private String propietario;

	public Cliente getClienteSeleccionado() {
		return clienteSeleccionado;
	}

	public void setClienteSeleccionado(Cliente clienteSeleccionado) {
		this.clienteSeleccionado = clienteSeleccionado;
	}

	public List<Comercio> getComerciosCliente() {
		return comerciosCliente;
	}

	public void setComerciosCliente(List<Comercio> comerciosCliente) {
		this.comerciosCliente = comerciosCliente;
	}

	public boolean isMostrarAnalisis() {
		return mostrarAnalisis;
	}

	public void setMostrarAnalisis(boolean mostrarAnalisis) {
		this.mostrarAnalisis = mostrarAnalisis;
	}

	public List<Banco> getBancosSeleccionados() {
		return bancosSeleccionados;
	}

	public void setBancosSeleccionados(List<Banco> bancosSeleccionados) {
		this.bancosSeleccionados = bancosSeleccionados;
	}

	public List<Tarjeta> getTarjetasCliente() {
		return tarjetasCliente;
	}

	public void setTarjetasCliente(List<Tarjeta> tarjetasCliente) {
		this.tarjetasCliente = tarjetasCliente;
	}

	public String getPropietario() {
		return propietario;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}



	@Inject
	private RegistroCliente registroCliente;
	
	public void registrar() {
		try {
			registroCliente.registro();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró ", "con éxito!");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch (Exception e) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al registrar ", "");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
		}
	}
	
	public void onEdit(RowEditEvent event) {  
		Cliente cliente = ((Cliente) event.getObject());
           
            try {
            	registroCliente.modificar(cliente);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se modificó ", cliente.getNombre());  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al modificar ", cliente.getNombre());  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
    }
	
	public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Se canceló modificar ", ((Cliente) event.getObject()).getNombre());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
	
	public void eliminar(Long id) {
		try {
			registroCliente.eliminar(id);
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
			registroCliente.buscar(id);
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
            	    Cliente cliente = (Cliente) d.getRowData();
            		registroCliente.modificar(cliente);
                }
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El Cliente fue modificado exitosamente" , "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al modificar el Cliente", "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
	}

    public void onRowSelect(SelectEvent event) {
    	Cliente cli = (Cliente) event.getObject();
    	bancosSeleccionados.addAll(cli.getBancos());
    	tarjetasCliente.addAll(cli.getTarjetas());
    	comerciosCliente.addAll(cli.getComercios());
    	if (cli.isPropietario()){
    		setPropietario("Propietario");
    	}
    	else{
    		setPropietario("Inquilino");
    	}
    	if (mostrarAnalisis == false)
    		setMostrarAnalisis(true);
    	else
    		setMostrarAnalisis(false);
    }
	

}

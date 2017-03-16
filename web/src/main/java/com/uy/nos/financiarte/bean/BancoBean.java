package com.uy.nos.financiarte.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import com.uy.nos.financiarte.controller.RegistroBanco;
import com.uy.nos.financiarte.model.Banco;



@ManagedBean
@RequestScoped
public class BancoBean {

	@Inject
	private RegistroBanco registroBanco;
	
	public void registrar() {
		try {
			registroBanco.registro();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró ", "con éxito!");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch (Exception e) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al registrar ", "");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
		}
	}
	
	public void onEdit(RowEditEvent event) {  
		Banco banco = ((Banco) event.getObject());
           
            try {
            	registroBanco.modificar(banco);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se modificó ", banco.getNombre());  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al modificar ", banco.getNombre());  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
    }
	
	public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Se canceló modificar ", ((Banco) event.getObject()).getNombre());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
	
	public void eliminar(Long id) {
		try {
			registroBanco.eliminar(id);
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
			registroBanco.buscar(id);
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
            	    Banco banco = (Banco) d.getRowData();
            		registroBanco.modificar(banco);
                }
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El Banco fue modificado exitosamente" , "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al modificar el Banco", "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
	}
	
}

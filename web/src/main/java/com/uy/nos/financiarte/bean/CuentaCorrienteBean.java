package com.uy.nos.financiarte.bean;

import java.util.ArrayList;
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

import com.uy.nos.financiarte.controller.RegistroCuentaCorriente;
import com.uy.nos.financiarte.model.Contrato;
import com.uy.nos.financiarte.model.CuentaCorriente;
import com.uy.nos.financiarte.model.PagoMedioPago;
import com.uy.nos.financiarte.model.Proveedor;
import com.uy.nos.financiarte.model.SolicitudCredito;
import com.uy.nos.financiarte.model.Usuario;



@ManagedBean
@RequestScoped
public class CuentaCorrienteBean {

	private List<CuentaCorriente> cuentasUsuario = new ArrayList<CuentaCorriente>();
	private List<Contrato> contratos = new ArrayList<Contrato>();
	private List<SolicitudCredito> solicitudesPendientesCliente = new ArrayList<SolicitudCredito>();
	private List<PagoMedioPago> pagosCliente = new ArrayList<PagoMedioPago>();


	
	public List<CuentaCorriente> getCuentasUsuario() {
		return cuentasUsuario;
	}

	public void setCuentasUsuario(List<CuentaCorriente> cuentasUsuario) {
		this.cuentasUsuario = cuentasUsuario;
	}
	
	@PostConstruct
	public void init() {
		generarListaCuentaCorrienteCliente();
	}

	@Inject
	private RegistroCuentaCorriente registroCuentaCorriente;
	
	public void registrar() {
		try {
			registroCuentaCorriente.registro();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró ", "con éxito!");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch (Exception e) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al registrar ", "");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
		}
	}
	
	public void onEdit(RowEditEvent event) {  
		CuentaCorriente cuentaCorriente = ((CuentaCorriente) event.getObject());
           
            try {
            	registroCuentaCorriente.modificar(cuentaCorriente);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se modificó ", cuentaCorriente.getId().toString());  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al modificar ", cuentaCorriente.getId().toString());  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
    }
	
	public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Se canceló modificar ", ((CuentaCorriente) event.getObject()).getId().toString());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
	
	public void eliminar(Long id) {
		try {
			registroCuentaCorriente.eliminar(id);
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
			registroCuentaCorriente.buscar(id);
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
            	    CuentaCorriente cuentaCorriente = (CuentaCorriente) d.getRowData();
            		registroCuentaCorriente.modificar(cuentaCorriente);
                }
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "La CuentaCorriente fue modificado exitosamente" , "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al modificar la CuentaCorriente", "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
	}
	
	public void generarListaCuentaCorrienteCliente(){
		String cli = SecurityContextAssociation.getPrincipal().getName();
		Usuario usuario = registroCuentaCorriente.buscarUsuarioPorNombre(cli);
		Proveedor proveedor = new Proveedor();
		proveedor = (Proveedor) usuario;
		contratos.addAll(proveedor.getContratos());
		for (Contrato contrato : contratos) {
			solicitudesPendientesCliente.addAll(contrato.getSolicitudes());
			pagosCliente.addAll(contrato.getPagos());
		}
	}
	
	
}

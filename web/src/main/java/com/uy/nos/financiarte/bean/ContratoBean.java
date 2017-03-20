package com.uy.nos.financiarte.bean;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;

import com.uy.nos.financiarte.controller.RegistroContrato;
import com.uy.nos.financiarte.model.Cliente;
import com.uy.nos.financiarte.model.Comercio;
import com.uy.nos.financiarte.model.Contrato;
import com.uy.nos.financiarte.model.Interes;
import com.uy.nos.financiarte.model.Proveedor;
import com.uy.nos.financiarte.model.TipoContrato;


@ManagedBean
@ViewScoped
public class ContratoBean {

	@Inject
	private RegistroContrato registroContrato;
	
	private Cliente clienteSeleccionado;
	private Proveedor proveedorSeleccionado;
	private long montoPrestamo;
	private int diasSinInteres;
	private long pagoMinimo;
	private int plazoPago;
	private Interes interes;
	private TipoContrato tipo;
	private boolean skip;
	
	public Cliente getClienteSeleccionado() {
		return clienteSeleccionado;
	}

	public void setClienteSeleccionado(Cliente clienteSeleccionado) {
		this.clienteSeleccionado = clienteSeleccionado;
	}

	public Proveedor getProveedorSeleccionado() {
		return proveedorSeleccionado;
	}

	public void setProveedorSeleccionado(Proveedor proveedorSeleccionado) {
		this.proveedorSeleccionado = proveedorSeleccionado;
	}
	
	public long getMontoPrestamo() {
		return montoPrestamo;
	}

	public void setMontoPrestamo(long montoPrestamo) {
		this.montoPrestamo = montoPrestamo;
	}

	public int getDiasSinInteres() {
		return diasSinInteres;
	}

	public void setDiasSinInteres(int diasSinInteres) {
		this.diasSinInteres = diasSinInteres;
	}

	public long getPagoMinimo() {
		return pagoMinimo;
	}

	public void setPagoMinimo(long pagoMinimo) {
		this.pagoMinimo = pagoMinimo;
	}

	public int getPlazoPago() {
		return plazoPago;
	}

	public void setPlazoPago(int plazoPago) {
		this.plazoPago = plazoPago;
	}

	public Interes getInteres() {
		return interes;
	}

	public void setInteres(Interes interes) {
		this.interes = interes;
	}

	public TipoContrato getTipo() {
		return tipo;
	}

	public void setTipo(TipoContrato tipo) {
		this.tipo = tipo;
	}

	public boolean isSkip() {
        return skip;
    }
 
    public void setSkip(boolean skip) {
        this.skip = skip;
    }

	public void registrar() {
		try {
			if (registroContrato.registro(clienteSeleccionado, proveedorSeleccionado, montoPrestamo, diasSinInteres, pagoMinimo, plazoPago, interes, tipo)){
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El contrato fue registrado con éxito ", "");  
		        FacesContext.getCurrentInstance().addMessage(null, msg);
		        recargarPagina();
			}
			else{
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo registrar el contrato ", "Ya existe un contrato con el cliente y proveedor seleccionados");  
		        FacesContext.getCurrentInstance().addMessage(null, msg);
		        recargarPagina();
			}
		}
		catch (Exception e) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error inesperado al registrar el contrato ", "");  
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
			if (registroContrato.eliminar(id)){
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El contrato fue eliminado correctamente", "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
			else{
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede eliminar el contrato porque tiene datos asociados ","");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}

		}
		catch(Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error inesperado al intentar eliminar el contrato ", "");  
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
	
	public String onFlowProcess(FlowEvent event) {
	    if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
        	if(event.getOldStep().equals("cliente")){
        		System.out.println("Estoy en el paso cliente ");
        		if(clienteSeleccionado == null){
    				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar un cliente para proseguir", "");  
    	            FacesContext.getCurrentInstance().addMessage(null, msg);
    	            return "cliente";
    	    	}
        	}
        	if(event.getOldStep().equals("proveedor")){
        		System.out.println("Estoy en el paso proveedor ");
	        	if(proveedorSeleccionado == null){
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar un proveedor para proseguir", "");  
		            FacesContext.getCurrentInstance().addMessage(null, msg);
		            return "proveedor";
		    	}
	        	else{
	        		if (registroContrato.buscarContratoDuplicado(clienteSeleccionado.getId(), proveedorSeleccionado.getId())){
	        			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error ya existe un contrato para el cliente y proveedor seleccionados ", "");  
	        	        FacesContext.getCurrentInstance().addMessage(null, msg); 
	        	        return "proveedor";
	        		}
	        	}
        	}
        	if(event.getOldStep().equals("contrato")){
        		System.out.println("Estoy en el paso contrato " );
	        	if(montoPrestamo == 0){
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar un monto para el prestamo", "");  
		            FacesContext.getCurrentInstance().addMessage(null, msg);
		            return "contrato";
		    	}
	        	if(pagoMinimo == 0){
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar un monto para el pago mínimo", "");  
		            FacesContext.getCurrentInstance().addMessage(null, msg);
		            return "contrato";
		    	}
	        	if(diasSinInteres == 0){
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar un monto para los días sin interés", "");  
		            FacesContext.getCurrentInstance().addMessage(null, msg);
		            return "contrato";
		    	}
	        	if(plazoPago == 0){
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar un monto para el plazo de pago", "");  
		            FacesContext.getCurrentInstance().addMessage(null, msg);
		            return "contrato";
		    	}
	        	if(interes == null){
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar un monto para el interés", "");  
		            FacesContext.getCurrentInstance().addMessage(null, msg);
		            return "contrato";
		    	}
	        	if(tipo == null){
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar un monto para el tipo de contrato", "");  
		            FacesContext.getCurrentInstance().addMessage(null, msg);
		            return "contrato";
		    	}
        	}
            return event.getNewStep();
            
        }
	}
    
    public void recargarPagina() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    ec.redirect(ec.getRequestContextPath() + "/proveedor/contratos/contratos.jsf");
    }

}

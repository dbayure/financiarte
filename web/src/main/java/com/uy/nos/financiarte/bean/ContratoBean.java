package com.uy.nos.financiarte.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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


@ManagedBean
@ViewScoped
public class ContratoBean {

	@Inject
	private RegistroContrato registroContrato;
	
	private Cliente clienteSeleccionado;
	private Proveedor proveedorSeleccionado;
	private long montoPrestamo;
	private int diasInteres;
	private long pagoMinimo;
	private int plazoPago;
	private Interes interes;
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

	public int getDiasInteres() {
		return diasInteres;
	}

	public void setDiasInteres(int diasInteres) {
		this.diasInteres = diasInteres;
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

	public boolean isSkip() {
        return skip;
    }
 
    public void setSkip(boolean skip) {
        this.skip = skip;
    }

	public void registrar() {
		try {
			registroContrato.registro(clienteSeleccionado, proveedorSeleccionado, montoPrestamo, diasInteres, pagoMinimo, plazoPago, interes);
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
	
    public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }
	
}

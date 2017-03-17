package com.uy.nos.financiarte.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.jboss.security.SecurityContextAssociation;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;

import com.uy.nos.financiarte.controller.RegistroDevolucion;
import com.uy.nos.financiarte.model.Contrato;
import com.uy.nos.financiarte.model.Devolucion;
import com.uy.nos.financiarte.model.Factura;



@ManagedBean
@ViewScoped
public class DevolucionBean {

	@Inject
	private RegistroDevolucion registroDevolucion;
	
	private Contrato contratoSeleccionado;
	private Factura facturaSeleccionada;
	private String descripcion;
	private long monto;
	private boolean mostrarDevoluciones = false;
	private boolean skip;
	private List<Devolucion> devoluciones;
	private List<Factura> facturas;
	private List<Contrato> contratos;
 
	public Contrato getContratoSeleccionado() {
		return contratoSeleccionado;
	}

	public void setContratoSeleccionado(Contrato contratoSeleccionado) {
		this.contratoSeleccionado = contratoSeleccionado;
		System.out.println("contrato seleccionado " + contratoSeleccionado.getId());
	}
	
	public Factura getFacturaSeleccionada() {
		return facturaSeleccionada;
	}

	public void setFacturaSeleccionada(Factura facturaSeleccionada) {
		this.facturaSeleccionada = facturaSeleccionada;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public long getMonto() {
		return monto;
	}

	public void setMonto(long monto) {
		this.monto = monto;
	}

	public boolean isMostrarDevoluciones() {
		return mostrarDevoluciones;
	}

	public void setMostrarDevoluciones(boolean mostrarDevoluciones) {
		this.mostrarDevoluciones = mostrarDevoluciones;
	}

	public boolean isSkip() {
        return skip;
    }
 
    public void setSkip(boolean skip) {
        this.skip = skip;
    }
    
	public List<Devolucion> getDevoluciones() {
		devolucionesPorContrato();
		return devoluciones;
	}

	public void setDevoluciones(List<Devolucion> devoluciones) {
		this.devoluciones = devoluciones;
	}

	public List<Factura> getFacturas() {
		facturasPorContrato();
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public List<Contrato> getContratos() {
		contratosPorCliente();
		return contratos;
	}

	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}

	public void registrar() {
		try {
			registroDevolucion.registro(contratoSeleccionado, descripcion, monto, facturaSeleccionada);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró ", "con éxito!");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch (Exception e) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al registrar ", "");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
		}
	}
	
	public void onEdit(RowEditEvent event) {  
		Devolucion devolucion = ((Devolucion) event.getObject());
           
            try {
            	registroDevolucion.modificar(devolucion);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se modificó ", devolucion.getDescripcion());  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al modificar ", devolucion.getDescripcion());  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
    }
	
	public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Se canceló modificar ", ((Devolucion) event.getObject()).getDescripcion());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
	
	public void eliminar(Long id) {
		try {
			registroDevolucion.eliminar(id);
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
			registroDevolucion.buscar(id);
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
            	    Devolucion devolucion = (Devolucion) d.getRowData();
            		registroDevolucion.modificar(devolucion);
                }
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "La Devolucion fue modificado exitosamente" , "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al modificar la Devolucion", "");  
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
	  
	  public void contratosPorCliente(){
		  String usuario = SecurityContextAssociation.getPrincipal().getName();
		  System.out.println("usuario a buscar contratos " + usuario);
		  setContratos(registroDevolucion.contratosPorCliente(usuario)); 
	  }
	  
	  public void devolucionesPorContrato(){
		  if(contratoSeleccionado != null){
			  //setDevoluciones(registroDevolucion.devolucionesPorcontrato(contratoSeleccionado.getId()));  
		  }
		  setDevoluciones(null);
		  FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Debe existir un contrato seleccionado para poder proseguir", "");  
          FacesContext.getCurrentInstance().addMessage(null, msg); 
	  }
	  
	  public void facturasPorContrato(){
		  if(contratoSeleccionado != null){
			  setFacturas(registroDevolucion.facturasPorcontrato(contratoSeleccionado.getId()));

		  }
		  setFacturas(null);
		  FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Debe existir un contrato seleccionado para poder proseguir", "");  
          FacesContext.getCurrentInstance().addMessage(null, msg); 
	  }
}

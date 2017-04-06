package com.uy.nos.financiarte.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.jboss.as.ee.component.deployers.InterceptorsAnnotationInformationFactory;
import org.jboss.security.SecurityContextAssociation;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import com.uy.nos.financiarte.controller.RegistroTransaccion;
import com.uy.nos.financiarte.model.Cliente;
import com.uy.nos.financiarte.model.Contrato;
import com.uy.nos.financiarte.model.Estado;
import com.uy.nos.financiarte.model.Factura;
import com.uy.nos.financiarte.model.NotaCredito;
import com.uy.nos.financiarte.model.PagoMedioPago;
import com.uy.nos.financiarte.model.Proveedor;
import com.uy.nos.financiarte.model.SolicitudCredito;
import com.uy.nos.financiarte.model.Transaccion;
import com.uy.nos.financiarte.model.Usuario;



@ManagedBean
@RequestScoped
public class TransaccionBean {

	private List<Transaccion> transaccionesCliente = new ArrayList<Transaccion>();
	private List<Transaccion> transaccionesContrato = new ArrayList<Transaccion>();
	private List<Factura> factruasPendientesCliente = new ArrayList<Factura>();
	private List<NotaCredito> notasCreditoCliente = new ArrayList<NotaCredito>();
	private List<SolicitudCredito> solicitudesPendientesCliente = new ArrayList<SolicitudCredito>();
	List<SolicitudCredito> solicitudesAgrupadas = new ArrayList<SolicitudCredito>();
	private List<Contrato> contratos = new ArrayList<Contrato>();
	private List<PagoMedioPago> pagosCliente = new ArrayList<PagoMedioPago>();
	private Transaccion transaccionSeleccionada;
	private Factura facturaSeleccionada;
	private PagoMedioPago pagoSeleccionado;
	private SolicitudCredito solicitudSeleccionada;
	private float totalCreditosPendientesCliente = 0L;
	private float totalImpuestosPendientesCliente = 0L;
	private float totalInteresesPendientesCiente = 0L;
	private float totalPagosRealizadosCliente = 0L;
	private float totalCreditosCerradosCliente = 0L;
	
	private float totalCreditosPendientes = 0L;
	private float totalImpuestosPendientes = 0L;
	private float totalInteresesPendientes = 0L;
	private float totalPagosRealizados = 0L;
	private float totalCreditosCerrados = 0L;

	private float iva =0.22f;
	
	public List<Transaccion> getTransaccionesCliente() {
		return transaccionesCliente;
	}

	public void setTransaccionesCliente(List<Transaccion> transaccionesCliente) {
		this.transaccionesCliente = transaccionesCliente;
	}
	
	public List<Transaccion> getTransaccionesContrato() {
		return transaccionesContrato;
	}

	public void setTransaccionesContrato(List<Transaccion> transaccionesContrato) {
		this.transaccionesContrato = transaccionesContrato;
	}

	public List<Factura> getFactruasPendientesCliente() {
		return factruasPendientesCliente;
	}

	public void setFactruasPendientesCliente(List<Factura> factruasPendientesCliente) {
		this.factruasPendientesCliente = factruasPendientesCliente;
	}

	public List<NotaCredito> getNotasCreditoCliente() {
		return notasCreditoCliente;
	}

	public void setNotasCreditoCliente(List<NotaCredito> notasCreditoCliente) {
		this.notasCreditoCliente = notasCreditoCliente;
	}

	public List<SolicitudCredito> getSolicitudesPendientesCliente() {
		return solicitudesPendientesCliente;
	}

	public void setSolicitudesPendientesCliente(List<SolicitudCredito> solicitudesPendientesCliente) {
		this.solicitudesPendientesCliente = solicitudesPendientesCliente;
	}

	public List<SolicitudCredito> getSolicitudesAgrupadas() {
		return solicitudesAgrupadas;
	}

	public void setSolicitudesAgrupadas(List<SolicitudCredito> solicitudesAgrupadas) {
		this.solicitudesAgrupadas = solicitudesAgrupadas;
	}

	public List<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}

	public List<PagoMedioPago> getPagosCliente() {
		return pagosCliente;
	}

	public void setPagosCliente(List<PagoMedioPago> pagosCliente) {
		this.pagosCliente = pagosCliente;
	}

	public Transaccion getTransaccionSeleccionada() {
		return transaccionSeleccionada;
	}

	public void setTransaccionSeleccionada(Transaccion transaccionSeleccionada) {
		this.transaccionSeleccionada = transaccionSeleccionada;
	}

	public Factura getFacturaSeleccionada() {
		return facturaSeleccionada;
	}

	public void setFacturaSeleccionada(Factura facturaSeleccionada) {
		this.facturaSeleccionada = facturaSeleccionada;
	}

	public PagoMedioPago getPagoSeleccionado() {
		return pagoSeleccionado;
	}

	public void setPagoSeleccionado(PagoMedioPago pagoSeleccionado) {
		this.pagoSeleccionado = pagoSeleccionado;
	}

	public SolicitudCredito getSolicitudSeleccionada() {
		return solicitudSeleccionada;
	}

	public void setSolicitudSeleccionada(SolicitudCredito solicitudSeleccionada) {
		this.solicitudSeleccionada = solicitudSeleccionada;
	}

	public float getTotalCreditosPendientesCliente() {
		return totalCreditosPendientesCliente;
	}

	public void setTotalCreditosPendientesCliente(float totalCreditosPendientesCliente) {
		this.totalCreditosPendientesCliente = totalCreditosPendientesCliente;
	}

	public float getTotalImpuestosPendientesCliente() {
		return totalImpuestosPendientesCliente;
	}

	public void setTotalImpuestosPendientesCliente(float totalImpuestosPendientesCliente) {
		this.totalImpuestosPendientesCliente = totalImpuestosPendientesCliente;
	}

	public float getTotalInteresesPendientesCiente() {
		return totalInteresesPendientesCiente;
	}

	public void setTotalInteresesPendientesCiente(float totalInteresesPendientesCiente) {
		this.totalInteresesPendientesCiente = totalInteresesPendientesCiente;
	}

	public float getTotalPagosRealizadosCliente() {
		return totalPagosRealizadosCliente;
	}

	public void setTotalPagosRealizadosCliente(float totalPagosRealizadosCliente) {
		this.totalPagosRealizadosCliente = totalPagosRealizadosCliente;
	}

	public float getTotalCreditosCerradosCliente() {
		return totalCreditosCerradosCliente;
	}

	public void setTotalCreditosCerradosCliente(float totalCreditosCerradosCliente) {
		this.totalCreditosCerradosCliente = totalCreditosCerradosCliente;
	}

	public float getTotalCreditosPendientes() {
		return totalCreditosPendientes;
	}

	public void setTotalCreditosPendientes(float totalCreditosPendientes) {
		this.totalCreditosPendientes = totalCreditosPendientes;
	}

	public float getTotalImpuestosPendientes() {
		return totalImpuestosPendientes;
	}

	public void setTotalImpuestosPendientes(float totalImpuestosPendientes) {
		this.totalImpuestosPendientes = totalImpuestosPendientes;
	}

	public float getTotalInteresesPendientes() {
		return totalInteresesPendientes;
	}

	public void setTotalInteresesPendientes(float totalInteresesPendientes) {
		this.totalInteresesPendientes = totalInteresesPendientes;
	}

	public float getTotalPagosRealizados() {
		return totalPagosRealizados;
	}

	public void setTotalPagosRealizados(float totalPagosRealizados) {
		this.totalPagosRealizados = totalPagosRealizados;
	}

	public float getTotalCreditosCerrados() {
		return totalCreditosCerrados;
	}

	public void setTotalCreditosCerrados(float totalCreditosCerrados) {
		this.totalCreditosCerrados = totalCreditosCerrados;
	}

	@Inject
	private RegistroTransaccion registroTransaccion;
	
	@PostConstruct
	public void init() {
		String cli = SecurityContextAssociation.getPrincipal().getName();
		Usuario usuario = registroTransaccion.buscarUsuarioPorNombre(cli);
		if (usuario instanceof Cliente){
			generarListaTransaccionesCliente(usuario);
		}
		if (usuario instanceof Proveedor){
			generarListaTransaccionesProveedor(usuario);
		}
	}
	
	public void registrar() {
		try {
			registroTransaccion.registro(null,0,null);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró ", "con éxito!");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch (Exception e) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al registrar ", "");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
		}
	}
	
	public void onEdit(RowEditEvent event) {  
		Transaccion transaccion = ((Transaccion) event.getObject());
           
            try {
            	registroTransaccion.modificar(transaccion);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se modificó ", transaccion.getId().toString());  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al modificar ", transaccion.getId().toString());  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
    }
	
	public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Se canceló modificar ", ((Transaccion) event.getObject()).getId().toString());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
	
	public void eliminar(Long id) {
		try {
			registroTransaccion.eliminar(id);
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
			registroTransaccion.buscar(id);
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
            	    Transaccion transaccion = (Transaccion) d.getRowData();
            		registroTransaccion.modificar(transaccion);
                }
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "La Transaccion fue modificado exitosamente" , "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al modificar la Transaccion", "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
	}
	
	public void onRowSelect(SelectEvent event) {
		setSolicitudSeleccionada((SolicitudCredito) event.getObject());
		List<Factura> facturas = new ArrayList<Factura>();
		facturas.addAll(solicitudSeleccionada.getContrato().getFacturas());
		Long estado = 0L;
		for (Factura factura : facturas) {
			estado = factura.getEstados().getId();
			if(estado == 5L){
				factruasPendientesCliente.add(factura);
			}
		}
		for (NotaCredito nota : solicitudSeleccionada.getContrato().getNotas()) {
			estado = nota.getEstados().getId();
			if(estado == 5L){
				notasCreditoCliente.add(nota);
			}
		}
		pagosCliente.addAll(solicitudSeleccionada.getContrato().getPagos());
		Contrato contrato = solicitudSeleccionada.getContrato();
		List<SolicitudCredito> solicitudes = new ArrayList<SolicitudCredito>();
		solicitudes.addAll(contrato.getSolicitudes());
		calcularSaldosContrato(solicitudes);
    }
	
	
	public void generarListaTransaccionesCliente(Usuario usuario){
		Cliente cliente = new Cliente();
		cliente = (Cliente) usuario;
		List<SolicitudCredito> solicitudes = new ArrayList<SolicitudCredito>();
		Long estado = 0L;
		float interes = 0;
		float impuesto = 0;
		float total = 0;
		int dias;
		Date hoy = new Date();
		cliente = (Cliente) usuario;
		contratos.addAll(cliente.getContratos());
		for (Contrato contrato : contratos) {
			solicitudes.addAll(contrato.getSolicitudes());
		}
		for (SolicitudCredito solicitudCredito : solicitudes) {
			estado = solicitudCredito.getEstados().getId();
			dias = (int) ((hoy.getTime() - solicitudCredito.getFecha().getTime())/86400000);
			if (estado == 3L){
				interes = solicitudCredito.getContrato().getInteres().getMonto();
				interes = interes/10000;
				interes = interes * solicitudCredito.getMonto();
				interes = interes * dias;
				totalInteresesPendientes = totalInteresesPendientes + interes;
				impuesto = interes * iva;
				totalImpuestosPendientes = totalImpuestosPendientes + impuesto;
				total = solicitudCredito.getMonto() + interes + impuesto;
				totalCreditosPendientes  = totalCreditosPendientes + total;
				solicitudCredito.setInteres(interes);
				solicitudCredito.setIva(impuesto);
				solicitudCredito.setTotal(total);
				solicitudesPendientesCliente.add(solicitudCredito);
				calcularSaldosGenerales();
			}
		}
	}
		
	public void generarListaTransaccionesProveedor(Usuario usuario){
		Proveedor proveedor = new Proveedor();
		proveedor = (Proveedor) usuario;
		Long estado = 0L;
		int dias;
		proveedor = (Proveedor) usuario;
		contratos.addAll(proveedor.getContratos());
		for (Contrato contrato : contratos) {
			SolicitudCredito solicitudTmp = new SolicitudCredito();
			System.out.println("comienzo con  el contrato " + contrato.getId());
			for (SolicitudCredito solicitudCredito : contrato.getSolicitudes()) {
				float intContrato = 0;
				float interes = 0;
				float impuesto = 0;
				float capital = 0;
				float interesAm = 0;
				float impuestoAm = 0;
				float capitalAm = 0;
				float p = 0;
				System.out.println("Analizo estado de solicitud " + solicitudCredito.getId());
				estado = solicitudCredito.getEstados().getId();
				if (estado == 3L){
					System.out.println("pido monto de solicitud " + solicitudCredito.getMonto());
					capital = solicitudCredito.getMonto();
					intContrato = solicitudCredito.getContrato().getInteres().getMonto();
					intContrato = intContrato/10000;
					if (solicitudCredito.getPagos().size() > 0){
						System.out.println("cantidad de pagos de la solicitud " + solicitudCredito.getPagos().size());
						
						for (PagoMedioPago pago : solicitudCredito.getPagos()) {
							System.out.println("comienzo a ver el pago " + pago.getId() + " con monto " + pago.getMonto());
							p = pago.getMonto();
							dias = (int) ((pago.getFecha().getTime() - solicitudCredito.getFecha().getTime())/86400000);
							System.out.println("cantidad de dias a calcular " + dias);
							interes = intContrato * capital;
							interes = interes * dias;
							impuesto = interes * iva;
							System.out.println("impuesto e interes antes de pagar" + impuesto + " - " + interes);
							if (p >= impuesto){
								p = p - impuesto;
								impuestoAm = impuestoAm + impuesto;
								impuesto = 0;
								System.out.println("impuesto amortizado y plata " + impuestoAm + " - " + p);
							}else{
								impuestoAm = impuestoAm + p;
								impuesto = impuesto - p;
								p = 0;
								System.out.println("impuesto " + impuesto);
							}
							if (p >= interes){
								p = p - interes;
								interesAm = interesAm + interes;
								interes = 0;
								System.out.println("interes amortizado y plata " + interesAm+ " - " + p);
							}else{
								interesAm = interesAm + p;
								interes = interes - p;
								p = 0;
								System.out.println("interes " + interes);
							}
							if (p >= capital){
								p = p - capital;
								capitalAm = capitalAm + capital;
								capital = 0;
								System.out.println("capital amortizado y plata " + capitalAm+ " - " + p);
							}else{
								capitalAm = capitalAm + p;
								capital = capital - (p - interes - impuesto);
								p = 0;
								System.out.println("capital y capital amortizado" + capital+ " - " + capitalAm);
							}
						}
					}
					else{
						Date hoy = new Date();
						dias = (int) ((hoy.getTime() - solicitudCredito.getFecha().getTime())/86400000);
						System.out.println("cantidad de dias a calcular si pagos" + dias);
						interes = intContrato * capital;
						interes = interes * dias;
						impuesto = interes * iva;
					}
				}
				solicitudTmp.setInteres(solicitudTmp.getInteres() + interes);
				solicitudTmp.setIva(solicitudTmp.getIva() + impuesto);
				solicitudTmp.setIvaAmortizado(solicitudTmp.getIvaAmortizado() + impuestoAm);
				solicitudTmp.setInteresAmortizado(solicitudTmp.getInteresAmortizado() + interesAm);
				solicitudTmp.setMontoAmortizado(solicitudTmp.getMontoAmortizado() + capitalAm);
				solicitudTmp.setMonto(solicitudTmp.getMonto() + solicitudCredito.getMonto());
				solicitudTmp.setTotal(solicitudTmp.getTotal() + solicitudCredito.getMonto() + interes + impuesto);
				System.out.println("monto total parcial " + solicitudTmp.getTotal());
				solicitudTmp.setSaldoActual(solicitudTmp.getSaldoActual() + capital);
				solicitudTmp.setEstados(solicitudCredito.getEstados());
				System.out.println("guarido interes, impusto, imp am, int am, cap am, monto total " + solicitudTmp.getInteres() + " - " +
						solicitudTmp.getIva() + " - " + solicitudTmp.getIvaAmortizado() + " - " + solicitudTmp.getInteresAmortizado()
						+ " - " + solicitudTmp.getMontoAmortizado()+ " - " +solicitudTmp.getMonto() );
				
			}
			solicitudTmp.setContrato(contrato);
			solicitudesAgrupadas.add(solicitudTmp);
		}
	}
	
	public void calcularSaldosContrato(List<SolicitudCredito> solicitudes){
		float creditos = 0L;
		float pagos = 0L;
		Long estado = 0L;
		float interes = 0;
		float impuesto = 0;
		float total = 0;
		int dias;
		Date hoy = new Date();
		Contrato contrato = solicitudSeleccionada.getContrato();
		for (SolicitudCredito solicitud : solicitudes) {
			estado = solicitud.getEstados().getId();
			dias = (int) ((hoy.getTime() - solicitud.getFecha().getTime())/86400000);
			if (estado == 3L){
				interes = solicitud.getContrato().getInteres().getMonto();
				interes = interes/10000;
				interes = interes * solicitud.getMonto();
				interes = interes * dias;
				totalInteresesPendientesCiente = totalInteresesPendientesCiente + interes;
				impuesto = interes * iva;
				totalImpuestosPendientesCliente = totalImpuestosPendientesCliente + impuesto;
				total = solicitud.getMonto() + interes + impuesto;
				totalCreditosPendientesCliente  = totalCreditosPendientesCliente + total;
		}
		setTransaccionesCliente(registroTransaccion.obtenerTransaccionesPorContrato(contrato.getId()));
		for (Transaccion transaccion : transaccionesCliente) {
			long tipo = transaccion.getTipos().getId();
			if( tipo == 1L){
				creditos = creditos + transaccion.getMonto();
			}
			if(tipo == 2L){
				pagos = pagos + transaccion.getMonto();
			}
		}
		setTotalPagosRealizadosCliente(pagos);
		setTotalCreditosCerradosCliente(creditos);
		}
	}
	
	public void calcularSaldosGenerales(){
		float creditos = 0L;
		float pagos = 0L;
		for (Contrato contrato : contratos) {
			for (Transaccion transaccion : registroTransaccion.obtenerTransaccionesPorContrato(contrato.getId())) {
				long tipo = transaccion.getTipos().getId();
				if( tipo == 1L){
					creditos = creditos + transaccion.getMonto();
				}
				if(tipo == 2L){
					pagos = pagos + transaccion.getMonto();
				}
			}
		}
		
		setTotalPagosRealizados(pagos);
		setTotalCreditosCerrados(creditos);
	}

	
}

package com.uy.nos.financiarte.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@Entity
@XmlRootElement
@Table(name = "contratos")
@JsonIgnoreProperties({"solicitudes","devoluciones","facturas","notas","pagos","interes",
	"estados","tiposContrato","cliente","proveedor"})
public class Contrato implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private long montoPrestamo;
	private long pagoMinimo;
	private int diasSinInteres;
	private Calendar fecha;
	private int plazoPago;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="interes")
	private Interes interes;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="estados")
	private Estado estados;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tiposContrato")
	private TipoContrato tiposContrato;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cliente")
	private Cliente cliente;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="proveedor")
	private Proveedor proveedor;
	
    @OneToMany(cascade=CascadeType.ALL, mappedBy="contrato")  
    private Set<SolicitudCredito> solicitudes;  
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="contrato")  
    private Set<Devolucion> devoluciones;  
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="contrato")  
    private Set<Factura> facturas;  
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="contrato")  
    private Set<NotaCredito> notas;  

    @OneToMany(cascade=CascadeType.ALL, mappedBy="contrato")  
    private Set<PagoMedioPago> pagos;

    public Contrato (){
    	solicitudes = new HashSet<SolicitudCredito>();
    	devoluciones = new HashSet<Devolucion>();
    	facturas = new HashSet<Factura>();
    	notas = new HashSet<NotaCredito>();
    	pagos = new HashSet<PagoMedioPago>();
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getMontoPrestamo() {
		return montoPrestamo;
	}

	public void setMontoPrestamo(long montoPrestamo) {
		this.montoPrestamo = montoPrestamo;
	}

	public long getPagoMinimo() {
		return pagoMinimo;
	}

	public void setPagoMinimo(long pagoMinimo) {
		this.pagoMinimo = pagoMinimo;
	}

	public int getDiasSinInteres() {
		return diasSinInteres;
	}

	public void setDiasSinInteres(int diasSinInteres) {
		this.diasSinInteres = diasSinInteres;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
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

	public Estado getEstados() {
		return estados;
	}

	public void setEstados(Estado estados) {
		this.estados = estados;
	}

	public TipoContrato getTiposContrato() {
		return tiposContrato;
	}

	public void setTiposContrato(TipoContrato tiposContrato) {
		this.tiposContrato = tiposContrato;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Set<SolicitudCredito> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(Set<SolicitudCredito> solicitudes) {
		this.solicitudes = solicitudes;
	}

	public Set<Devolucion> getDevoluciones() {
		return devoluciones;
	}

	public void setDevoluciones(Set<Devolucion> devoluciones) {
		this.devoluciones = devoluciones;
	}

	public Set<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(Set<Factura> facturas) {
		this.facturas = facturas;
	}

	public Set<NotaCredito> getNotas() {
		return notas;
	}

	public void setNotas(Set<NotaCredito> notas) {
		this.notas = notas;
	}

	public Set<PagoMedioPago> getPagos() {
		return pagos;
	}

	public void setPagos(Set<PagoMedioPago> pagos) {
		this.pagos = pagos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + diasSinInteres;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (int) (montoPrestamo ^ (montoPrestamo >>> 32));
		result = prime * result + (int) (pagoMinimo ^ (pagoMinimo >>> 32));
		result = prime * result + plazoPago;
<<<<<<< HEAD
=======
		result = prime * result + ((tiposContrato == null) ? 0 : tiposContrato.hashCode());
>>>>>>> 2a4dd0c19ac6bc57b376f9934d8c586251526870
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contrato other = (Contrato) obj;
		if (diasSinInteres != other.diasSinInteres)
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (montoPrestamo != other.montoPrestamo)
			return false;
		if (pagoMinimo != other.pagoMinimo)
			return false;
		if (plazoPago != other.plazoPago)
			return false;
<<<<<<< HEAD
=======
		if (tiposContrato == null) {
			if (other.tiposContrato != null)
				return false;
		} else if (!tiposContrato.equals(other.tiposContrato))
			return false;
>>>>>>> 2a4dd0c19ac6bc57b376f9934d8c586251526870
		return true;
	}

	
}

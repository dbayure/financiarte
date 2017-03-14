package com.uy.nos.financiarte.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@XmlRootElement
@Table(name = "contratos")
public class Contrato implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private long montoPrestamo;
	
	private int diasInteres;
	
	private Calendar fecha;
	
	private long pagoMinimo;
	
	private int plazoPago;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="interes")
	private Interes interes;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cliente")
	private Cliente cliente;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="proveedor")
	private Proveedor proveedor;

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

	public int getDiasInteres() {
		return diasInteres;
	}

	public void setDiasInteres(int diasInteres) {
		this.diasInteres = diasInteres;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + diasInteres;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (int) (montoPrestamo ^ (montoPrestamo >>> 32));
		result = prime * result + (int) (pagoMinimo ^ (pagoMinimo >>> 32));
		result = prime * result + plazoPago;
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
		if (diasInteres != other.diasInteres)
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
		return true;
	}
	
}

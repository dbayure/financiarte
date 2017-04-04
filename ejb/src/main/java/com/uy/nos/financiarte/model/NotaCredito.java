package com.uy.nos.financiarte.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "notasCredito")
public class NotaCredito implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="factura")
	private Factura factura;
	
	private long numeroSerie;
	
	private float monto;
	
	private Date fecha;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="estado")
	private Estado estados;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="contrato")
	private Contrato contrato;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="solicitudCredito")
	private SolicitudCredito solicitud;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public long getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(long numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Estado getEstados() {
		return estados;
	}

	public void setEstados(Estado estados) {
		this.estados = estados;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public SolicitudCredito getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(SolicitudCredito solicitud) {
		this.solicitud = solicitud;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + Float.floatToIntBits(monto);
		result = prime * result + (int) (numeroSerie ^ (numeroSerie >>> 32));
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
		NotaCredito other = (NotaCredito) obj;
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
		if (Float.floatToIntBits(monto) != Float.floatToIntBits(other.monto))
			return false;
		if (numeroSerie != other.numeroSerie)
			return false;
		return true;
	}

	
}

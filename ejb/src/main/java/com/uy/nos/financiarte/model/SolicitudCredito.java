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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "solicitudesCredito")
public class SolicitudCredito implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date fecha;
	
	private Date vencimiento;
	
	private float monto;
	
	@Transient
	private float interes;
	
	@Transient
	private float iva;
	
	@Transient
	private float total;
	
	@Transient
	private float interesAmortizado;
	
	@Transient
	private float ivaAmortizado;
	
	@Transient
	private float montoAmortizado;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="estado")
	private Estado estados;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="contrato")
	private Contrato contrato;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getVencimiento() {
		return vencimiento;
	}

	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public float getInteres() {
		return interes;
	}

	public void setInteres(float interes) {
		this.interes = interes;
	}

	public float getIva() {
		return iva;
	}

	public void setIva(float iva) {
		this.iva = iva;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public float getInteresAmortizado() {
		return interesAmortizado;
	}

	public void setInteresAmortizado(float interesAmortizado) {
		this.interesAmortizado = interesAmortizado;
	}

	public float getIvaAmortizado() {
		return ivaAmortizado;
	}

	public void setIvaAmortizado(float ivaAmortizado) {
		this.ivaAmortizado = ivaAmortizado;
	}

	public float getMontoAmortizado() {
		return montoAmortizado;
	}

	public void setMontoAmortizado(float montoAmortizado) {
		this.montoAmortizado = montoAmortizado;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + Float.floatToIntBits(interes);
		result = prime * result + Float.floatToIntBits(interesAmortizado);
		result = prime * result + Float.floatToIntBits(iva);
		result = prime * result + Float.floatToIntBits(ivaAmortizado);
		result = prime * result + Float.floatToIntBits(monto);
		result = prime * result + Float.floatToIntBits(montoAmortizado);
		result = prime * result + Float.floatToIntBits(total);
		result = prime * result + ((vencimiento == null) ? 0 : vencimiento.hashCode());
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
		SolicitudCredito other = (SolicitudCredito) obj;
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
		if (Float.floatToIntBits(interes) != Float.floatToIntBits(other.interes))
			return false;
		if (Float.floatToIntBits(interesAmortizado) != Float.floatToIntBits(other.interesAmortizado))
			return false;
		if (Float.floatToIntBits(iva) != Float.floatToIntBits(other.iva))
			return false;
		if (Float.floatToIntBits(ivaAmortizado) != Float.floatToIntBits(other.ivaAmortizado))
			return false;
		if (Float.floatToIntBits(monto) != Float.floatToIntBits(other.monto))
			return false;
		if (Float.floatToIntBits(montoAmortizado) != Float.floatToIntBits(other.montoAmortizado))
			return false;
		if (Float.floatToIntBits(total) != Float.floatToIntBits(other.total))
			return false;
		if (vencimiento == null) {
			if (other.vencimiento != null)
				return false;
		} else if (!vencimiento.equals(other.vencimiento))
			return false;
		return true;
	}

	
}

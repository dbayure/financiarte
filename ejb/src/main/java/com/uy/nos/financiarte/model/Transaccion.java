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
@Table(name = "transacciones")
public class Transaccion implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private float monto;
	
	private float interesAmortizado;
	
	private float impuestoAmortizado;
	
	private float saldoAmortizado;
	
	private Date fecha;
	
	@Transient
	private float interes;
	
	@Transient
	private float iva;
	
	@Transient
	private float total;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="tipoMovimiento")
	private TipoMovimiento tipos;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="contrato")
	private Contrato contrato;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public float getInteresAmortizado() {
		return interesAmortizado;
	}

	public void setInteresAmortizado(float interesAmortizado) {
		this.interesAmortizado = interesAmortizado;
	}

	public float getImpuestoAmortizado() {
		return impuestoAmortizado;
	}

	public void setImpuestoAmortizado(float impuestoAmortizado) {
		this.impuestoAmortizado = impuestoAmortizado;
	}

	public float getSaldoAmortizado() {
		return saldoAmortizado;
	}

	public void setSaldoAmortizado(float saldoAmortizado) {
		this.saldoAmortizado = saldoAmortizado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

	public TipoMovimiento getTipos() {
		return tipos;
	}

	public void setTipos(TipoMovimiento tipos) {
		this.tipos = tipos;
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
		result = prime * result + Float.floatToIntBits(impuestoAmortizado);
		result = prime * result + Float.floatToIntBits(interes);
		result = prime * result + Float.floatToIntBits(interesAmortizado);
		result = prime * result + Float.floatToIntBits(iva);
		result = prime * result + Float.floatToIntBits(monto);
		result = prime * result + Float.floatToIntBits(saldoAmortizado);
		result = prime * result + Float.floatToIntBits(total);
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
		Transaccion other = (Transaccion) obj;
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
		if (Float.floatToIntBits(impuestoAmortizado) != Float.floatToIntBits(other.impuestoAmortizado))
			return false;
		if (Float.floatToIntBits(interes) != Float.floatToIntBits(other.interes))
			return false;
		if (Float.floatToIntBits(interesAmortizado) != Float.floatToIntBits(other.interesAmortizado))
			return false;
		if (Float.floatToIntBits(iva) != Float.floatToIntBits(other.iva))
			return false;
		if (Float.floatToIntBits(monto) != Float.floatToIntBits(other.monto))
			return false;
		if (Float.floatToIntBits(saldoAmortizado) != Float.floatToIntBits(other.saldoAmortizado))
			return false;
		if (Float.floatToIntBits(total) != Float.floatToIntBits(other.total))
			return false;
		return true;
	}



}

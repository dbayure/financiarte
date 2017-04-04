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
@Table(name = "cuentasCorrientes")
public class CuentaCorriente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="tipoMovimiento")
	private TipoMovimiento tipos;
	
	private Date fechaMovimiento;
	
	private float monto;
	
	private int interes;
	
	private int impuestos;
	
	private int idClietne;
	
	private String nombreCliente;
	
	private int idProveedor;
	
	private String nombreProveedor;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="contrato")
	private Contrato contrato;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoMovimiento getTipos() {
		return tipos;
	}

	public void setTipos(TipoMovimiento tipos) {
		this.tipos = tipos;
	}

	public Date getFechaMovimiento() {
		return fechaMovimiento;
	}

	public void setFechaMovimiento(Date fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public int getInteres() {
		return interes;
	}

	public void setInteres(int interes) {
		this.interes = interes;
	}

	public int getImpuestos() {
		return impuestos;
	}

	public void setImpuestos(int impuestos) {
		this.impuestos = impuestos;
	}

	public int getIdClietne() {
		return idClietne;
	}

	public void setIdClietne(int idClietne) {
		this.idClietne = idClietne;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getNombreProveedor() {
		return nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
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
		result = prime * result + ((fechaMovimiento == null) ? 0 : fechaMovimiento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + idClietne;
		result = prime * result + idProveedor;
		result = prime * result + impuestos;
		result = prime * result + interes;
		result = prime * result + Float.floatToIntBits(monto);
		result = prime * result + ((nombreCliente == null) ? 0 : nombreCliente.hashCode());
		result = prime * result + ((nombreProveedor == null) ? 0 : nombreProveedor.hashCode());
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
		CuentaCorriente other = (CuentaCorriente) obj;
		if (fechaMovimiento == null) {
			if (other.fechaMovimiento != null)
				return false;
		} else if (!fechaMovimiento.equals(other.fechaMovimiento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idClietne != other.idClietne)
			return false;
		if (idProveedor != other.idProveedor)
			return false;
		if (impuestos != other.impuestos)
			return false;
		if (interes != other.interes)
			return false;
		if (Float.floatToIntBits(monto) != Float.floatToIntBits(other.monto))
			return false;
		if (nombreCliente == null) {
			if (other.nombreCliente != null)
				return false;
		} else if (!nombreCliente.equals(other.nombreCliente))
			return false;
		if (nombreProveedor == null) {
			if (other.nombreProveedor != null)
				return false;
		} else if (!nombreProveedor.equals(other.nombreProveedor))
			return false;
		return true;
	}


}

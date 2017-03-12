package com.uy.nos.financiarte.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "proveedores")
public class Proveedor extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long rut;
	
	@OneToMany(mappedBy="proveedor", cascade={CascadeType.ALL})
	private Set<Contrato> contratos;
	
	public Proveedor (){
		contratos = new HashSet<Contrato>();
	}

	public long getRut() {
		return rut;
	}

	public void setRut(long rut) {
		this.rut = rut;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((contratos == null) ? 0 : contratos.hashCode());
		result = prime * result + (int) (rut ^ (rut >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proveedor other = (Proveedor) obj;
		if (contratos == null) {
			if (other.contratos != null)
				return false;
		} else if (!contratos.equals(other.contratos))
			return false;
		if (rut != other.rut)
			return false;
		return true;
	}

}

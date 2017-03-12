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
@Table(name = "clientes")
public class Cliente extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long ci;
	
	private int pin;
    
	@OneToMany(mappedBy="cliente", cascade={CascadeType.ALL})
	private Set<Comercio> comercios;
	
	@OneToMany(mappedBy="cliente", cascade={CascadeType.ALL})
	private Set<Contrato> contratos;
	
	public Cliente (){
		comercios = new HashSet<Comercio>();
		contratos = new HashSet<Contrato>();
	}

	public long getCi() {
		return ci;
	}

	public void setCi(long ci) {
		this.ci = ci;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (int) (ci ^ (ci >>> 32));
		result = prime * result + ((comercios == null) ? 0 : comercios.hashCode());
		result = prime * result + ((contratos == null) ? 0 : contratos.hashCode());
		result = prime * result + pin;
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
		Cliente other = (Cliente) obj;
		if (ci != other.ci)
			return false;
		if (comercios == null) {
			if (other.comercios != null)
				return false;
		} else if (!comercios.equals(other.comercios))
			return false;
		if (contratos == null) {
			if (other.contratos != null)
				return false;
		} else if (!contratos.equals(other.contratos))
			return false;
		if (pin != other.pin)
			return false;
		return true;
	}

}
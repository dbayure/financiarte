package com.uy.nos.financiarte.model;

import java.io.Serializable;
import java.util.Calendar;
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
	private boolean propietario;
	private Calendar fechaNacimiento;
	private int cel;
	private String vehiculo;
	private int pin;
	private String tipoCuentaBancaria;
    
	@OneToMany(mappedBy="cliente", cascade={CascadeType.ALL})
	private Set<Tarjeta> tarjetas;
	
	@OneToMany(mappedBy="cliente", cascade={CascadeType.ALL})
	private Set<Banco> bancos;
	
	@OneToMany(mappedBy="cliente", cascade={CascadeType.ALL})
	private Set<Comercio> comercios;
	
	@OneToMany(mappedBy="cliente", cascade={CascadeType.ALL})
	private Set<Contrato> contratos;
	
	public Cliente (){
		tarjetas = new HashSet<Tarjeta>();
		bancos = new HashSet<Banco>();
		comercios = new HashSet<Comercio>();
		contratos = new HashSet<Contrato>();
	}

	public long getCi() {
		return ci;
	}

	public void setCi(long ci) {
		this.ci = ci;
	}

	public boolean isPropietario() {
		return propietario;
	}

	public void setPropietario(boolean propietario) {
		this.propietario = propietario;
	}

	public Calendar getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Calendar fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getCel() {
		return cel;
	}

	public void setCel(int cel) {
		this.cel = cel;
	}

	public String getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(String vehiculo) {
		this.vehiculo = vehiculo;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getTipoCuentaBancaria() {
		return tipoCuentaBancaria;
	}

	public void setTipoCuentaBancaria(String tipoCuentaBancaria) {
		this.tipoCuentaBancaria = tipoCuentaBancaria;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bancos == null) ? 0 : bancos.hashCode());
		result = prime * result + cel;
		result = prime * result + (int) (ci ^ (ci >>> 32));
		result = prime * result + ((comercios == null) ? 0 : comercios.hashCode());
		result = prime * result + ((contratos == null) ? 0 : contratos.hashCode());
		result = prime * result + ((fechaNacimiento == null) ? 0 : fechaNacimiento.hashCode());
		result = prime * result + pin;
		result = prime * result + (propietario ? 1231 : 1237);
		result = prime * result + ((tarjetas == null) ? 0 : tarjetas.hashCode());
		result = prime * result + ((tipoCuentaBancaria == null) ? 0 : tipoCuentaBancaria.hashCode());
		result = prime * result + ((vehiculo == null) ? 0 : vehiculo.hashCode());
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
		if (bancos == null) {
			if (other.bancos != null)
				return false;
		} else if (!bancos.equals(other.bancos))
			return false;
		if (cel != other.cel)
			return false;
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
		if (fechaNacimiento == null) {
			if (other.fechaNacimiento != null)
				return false;
		} else if (!fechaNacimiento.equals(other.fechaNacimiento))
			return false;
		if (pin != other.pin)
			return false;
		if (propietario != other.propietario)
			return false;
		if (tarjetas == null) {
			if (other.tarjetas != null)
				return false;
		} else if (!tarjetas.equals(other.tarjetas))
			return false;
		if (tipoCuentaBancaria == null) {
			if (other.tipoCuentaBancaria != null)
				return false;
		} else if (!tipoCuentaBancaria.equals(other.tipoCuentaBancaria))
			return false;
		if (vehiculo == null) {
			if (other.vehiculo != null)
				return false;
		} else if (!vehiculo.equals(other.vehiculo))
			return false;
		return true;
	}

	
}

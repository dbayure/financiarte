package com.uy.nos.financiarte.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	private Date fechaNacimiento;
	private int cel;
	private String vehiculo;
	private int pin;
	private String tipoCuentaBancaria;
    
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "clientes_tarjetas",
			joinColumns = {@JoinColumn(name = "cliente_id", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "tarjeta_id",	nullable = false, updatable = false) })
	private Set<Tarjeta> tarjetas;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "clientes_bancos",
			joinColumns = {@JoinColumn(name = "cliente_id", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "banco_id",	nullable = false, updatable = false) })
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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
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

	public Set<Tarjeta> getTarjetas() {
		return tarjetas;
	}

	public void setTarjetas(Set<Tarjeta> tarjetas) {
		this.tarjetas = tarjetas;
	}

	public Set<Banco> getBancos() {
		return bancos;
	}

	public void setBancos(Set<Banco> bancos) {
		this.bancos = bancos;
	}

	public Set<Comercio> getComercios() {
		return comercios;
	}

	public void setComercios(Set<Comercio> comercios) {
		this.comercios = comercios;
	}

	public Set<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(Set<Contrato> contratos) {
		this.contratos = contratos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + cel;
		result = prime * result + (int) (ci ^ (ci >>> 32));
		result = prime * result + ((fechaNacimiento == null) ? 0 : fechaNacimiento.hashCode());
		result = prime * result + pin;
		result = prime * result + (propietario ? 1231 : 1237);
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
		if (cel != other.cel)
			return false;
		if (ci != other.ci)
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

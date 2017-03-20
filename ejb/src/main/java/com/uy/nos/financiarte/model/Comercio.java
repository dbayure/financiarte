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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "comercios")
public class Comercio implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	private String razonSocial;
	
	private long rut;
	
	private long telefono;
	
	private String direccion;
	
	private String localidaBarrio;
	
	private Date fechaInicio;
	
	private int metrosCuadrados;
	
	private int cantidadCajas;
	
	private boolean carniceria;
	
	private boolean fiambreria;
	
	private boolean roticeria;
	
	private boolean tarjetaMides;
	
	private String tipoCuenta;
	
    @OneToOne(orphanRemoval = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "ciudad", unique = false)
    private Ciudad ciudad;
	
    @OneToOne(orphanRemoval = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "departamento", unique = false)
    private Departamento deparatamento;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cliente_id")
	private Cliente cliente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public long getRut() {
		return rut;
	}

	public void setRut(long rut) {
		this.rut = rut;
	}

	public long getTelefono() {
		return telefono;
	}

	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getLocalidaBarrio() {
		return localidaBarrio;
	}

	public void setLocalidaBarrio(String localidaBarrio) {
		this.localidaBarrio = localidaBarrio;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public int getMetrosCuadrados() {
		return metrosCuadrados;
	}

	public void setMetrosCuadrados(int metrosCuadrados) {
		this.metrosCuadrados = metrosCuadrados;
	}

	public int getCantidadCajas() {
		return cantidadCajas;
	}

	public void setCantidadCajas(int cantidadCajas) {
		this.cantidadCajas = cantidadCajas;
	}

	public boolean isCarniceria() {
		return carniceria;
	}

	public void setCarniceria(boolean carniceria) {
		this.carniceria = carniceria;
	}

	public boolean isFiambreria() {
		return fiambreria;
	}

	public void setFiambreria(boolean fiambreria) {
		this.fiambreria = fiambreria;
	}

	public boolean isRoticeria() {
		return roticeria;
	}

	public void setRoticeria(boolean roticeria) {
		this.roticeria = roticeria;
	}

	public boolean isTarjetaMides() {
		return tarjetaMides;
	}

	public void setTarjetaMides(boolean tarjetaMides) {
		this.tarjetaMides = tarjetaMides;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public Departamento getDeparatamento() {
		return deparatamento;
	}

	public void setDeparatamento(Departamento deparatamento) {
		this.deparatamento = deparatamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cantidadCajas;
		result = prime * result + (carniceria ? 1231 : 1237);
		result = prime * result + ((ciudad == null) ? 0 : ciudad.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((deparatamento == null) ? 0 : deparatamento.hashCode());
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((fechaInicio == null) ? 0 : fechaInicio.hashCode());
		result = prime * result + (fiambreria ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((localidaBarrio == null) ? 0 : localidaBarrio.hashCode());
		result = prime * result + metrosCuadrados;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((razonSocial == null) ? 0 : razonSocial.hashCode());
		result = prime * result + (roticeria ? 1231 : 1237);
		result = prime * result + (int) (rut ^ (rut >>> 32));
		result = prime * result + (tarjetaMides ? 1231 : 1237);
		result = prime * result + (int) (telefono ^ (telefono >>> 32));
		result = prime * result + ((tipoCuenta == null) ? 0 : tipoCuenta.hashCode());
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
		Comercio other = (Comercio) obj;
		if (cantidadCajas != other.cantidadCajas)
			return false;
		if (carniceria != other.carniceria)
			return false;
		if (ciudad == null) {
			if (other.ciudad != null)
				return false;
		} else if (!ciudad.equals(other.ciudad))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (deparatamento == null) {
			if (other.deparatamento != null)
				return false;
		} else if (!deparatamento.equals(other.deparatamento))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (fechaInicio == null) {
			if (other.fechaInicio != null)
				return false;
		} else if (!fechaInicio.equals(other.fechaInicio))
			return false;
		if (fiambreria != other.fiambreria)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (localidaBarrio == null) {
			if (other.localidaBarrio != null)
				return false;
		} else if (!localidaBarrio.equals(other.localidaBarrio))
			return false;
		if (metrosCuadrados != other.metrosCuadrados)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (razonSocial == null) {
			if (other.razonSocial != null)
				return false;
		} else if (!razonSocial.equals(other.razonSocial))
			return false;
		if (roticeria != other.roticeria)
			return false;
		if (rut != other.rut)
			return false;
		if (tarjetaMides != other.tarjetaMides)
			return false;
		if (telefono != other.telefono)
			return false;
		if (tipoCuenta == null) {
			if (other.tipoCuenta != null)
				return false;
		} else if (!tipoCuenta.equals(other.tipoCuenta))
			return false;
		return true;
	}

	
}

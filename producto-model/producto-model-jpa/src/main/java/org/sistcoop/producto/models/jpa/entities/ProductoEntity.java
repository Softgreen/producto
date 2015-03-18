package org.sistcoop.producto.models.jpa.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.sistcoop.producto.models.enums.TipoPersona;

@MappedSuperclass
public abstract class ProductoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String denominacion;
	protected TipoPersona tipoPersona;
	protected List<String> monedas;
	protected boolean estado;

	private Set<ProductoTasa> tasas = new HashSet<ProductoTasa>();

	@NotNull
	@Size(min = 1, max = 100)
	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	public TipoPersona getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(TipoPersona tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<String> getMonedas() {
		return monedas;
	}

	public void setMonedas(List<String> monedas) {
		this.monedas = monedas;
	}

	@NotNull
	@Type(type = "org.hibernate.type.TrueFalseType")
	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "producto")
	public Set<ProductoTasa> getTasas() {
		return tasas;
	}

	public void setTasas(Set<ProductoTasa> tasas) {
		this.tasas = tasas;
	}

}

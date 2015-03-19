package org.sistcoop.producto.models.jpa.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;
import org.sistcoop.producto.models.enums.TipoPersona;

import javax.persistence.InheritanceType;

@Entity
@Table(name = "PRODUCTO")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ProductoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	protected String codigo;
	protected String denominacion;
	protected TipoPersona tipoPersona;
	protected boolean estado;

	protected List<ProductoMoneda> monedas;
	private Set<ProductoTasa> tasas = new HashSet<ProductoTasa>();

	private Timestamp optlk;

	@Id
	@GeneratedValue(generator = "SgGenericGenerator")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@NotNull
	@NotBlank
	@Size(min = 1, max = 100)
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@NotNull
	@NotBlank
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
	public List<ProductoMoneda> getMonedas() {
		return monedas;
	}

	public void setMonedas(List<ProductoMoneda> monedas) {
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

	@Version
	public Timestamp getOptlk() {
		return optlk;
	}

	public void setOptlk(Timestamp optlk) {
		this.optlk = optlk;
	}
}

package org.sistcoop.producto.models.jpa.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PRODUCTO_CREDITO", indexes = { @Index(columnList = "id") })
public class ProductoCreditoEntity extends ProductoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static String base = "org.sistcoop.producto.models.jpa.entities.ProductoCreditoEntity.";
	public final static String findAll = base + "findAll";

	private Integer id;
	private BigDecimal montoMinimo;
	private BigDecimal montoMaximo;

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
	@Min(value = 1)
	@Max(value = 1000000)
	public BigDecimal getMontoMinimo() {
		return montoMinimo;
	}

	public void setMontoMinimo(BigDecimal montoMinimo) {
		this.montoMinimo = montoMinimo;
	}

	@NotNull
	@Min(value = 1)
	@Max(value = 1000000)
	public BigDecimal getMontoMaximo() {
		return montoMaximo;
	}

	public void setMontoMaximo(BigDecimal montoMaximo) {
		this.montoMaximo = montoMaximo;
	}

	@Version
	public Timestamp getOptlk() {
		return optlk;
	}

	public void setOptlk(Timestamp optlk) {
		this.optlk = optlk;
	}

}

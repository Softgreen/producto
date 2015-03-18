package org.sistcoop.producto.models.jpa.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "PRODUCTO_CUENTAPERSONAL", indexes = { @Index(columnList = "id") })
public class ProductoCuentaPersonalEntity extends ProductoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static String base = "org.sistcoop.producto.models.jpa.entities.ProductoCreditoEntity.";
	public final static String findAll = base + "findAll";

	private Integer id;

	private Timestamp optlk;

	@Id
	@GeneratedValue(generator = "SgGenericGenerator")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Version
	public Timestamp getOptlk() {
		return optlk;
	}

	public void setOptlk(Timestamp optlk) {
		this.optlk = optlk;
	}

}

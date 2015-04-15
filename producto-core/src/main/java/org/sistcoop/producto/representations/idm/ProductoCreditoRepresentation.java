package org.sistcoop.producto.representations.idm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "productoCredito")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ProductoCreditoRepresentation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String codigo;
	private String denominacion;
	private String tipoPersona;
	private boolean estado;
	private String moneda;

	//protected List<ProductoMonedaRepresentation> monedas;
	private List<ProductoTasaRepresentation> tasas;

	private BigDecimal montoMinimo;
	private BigDecimal montoMaximo;

	@XmlAttribute
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@XmlAttribute
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@XmlAttribute
	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	@XmlAttribute
	public String getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	@XmlAttribute
	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@XmlAttribute
	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	@XmlAttribute
	public List<ProductoTasaRepresentation> getTasas() {
		return tasas;
	}

	public void setTasas(List<ProductoTasaRepresentation> tasas) {
		this.tasas = tasas;
	}

	@XmlAttribute
	public BigDecimal getMontoMinimo() {
		return montoMinimo;
	}

	public void setMontoMinimo(BigDecimal montoMinimo) {
		this.montoMinimo = montoMinimo;
	}

	@XmlAttribute
	public BigDecimal getMontoMaximo() {
		return montoMaximo;
	}

	public void setMontoMaximo(BigDecimal montoMaximo) {
		this.montoMaximo = montoMaximo;
	}
}

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
	private String denominacion;
	private String persona;
	private Boolean estado;
	private List<String> monedas;
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
	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	@XmlAttribute
	public String getPersona() {
		return persona;
	}

	public void setPersona(String persona) {
		this.persona = persona;
	}

	@XmlAttribute
	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	@XmlAttribute
	public List<String> getMonedas() {
		return monedas;
	}

	public void setMonedas(List<String> monedas) {
		this.monedas = monedas;
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

package org.sistcoop.producto.models.jpa;

import javax.persistence.EntityManager;

import org.sistcoop.producto.models.ProductoCreditoModel;
import org.sistcoop.producto.models.enums.TipoPersona;
import org.sistcoop.producto.models.jpa.entities.ProductoCreditoEntity;

public class ProductoCreditoAdapter implements ProductoCreditoModel {

	private static final long serialVersionUID = 1L;

	protected ProductoCreditoEntity tipoDocumentoEntity;
	protected EntityManager em;

	public ProductoCreditoAdapter(EntityManager em, ProductoCreditoEntity tipoDocumentoEntity) {
		this.em = em;
		this.tipoDocumentoEntity = tipoDocumentoEntity;
	}

	public ProductoCreditoEntity getTipoDocumentEntity() {
		return tipoDocumentoEntity;
	}

	@Override
	public void commit() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getAbreviatura() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAbreviatura(String abreviatura) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getDenominacion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDenominacion(String denominacion) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getCantidadCaracteres() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCantidadCaracteres(int cantidadCaracteres) {
		// TODO Auto-generated method stub

	}

	@Override
	public TipoPersona getTipoPersona() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTipoPersona(TipoPersona tipoPersona) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getEstado() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void desactivar() {
		// TODO Auto-generated method stub

	}

}

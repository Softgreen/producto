package org.sistcoop.producto.models.jpa;

import javax.persistence.EntityManager;

import org.sistcoop.producto.models.ProductoCreditoModel;
import org.sistcoop.producto.models.enums.TipoPersona;
import org.sistcoop.producto.models.jpa.entities.ProductoCreditoEntity;

public class TipoDocumentoAdapter implements ProductoCreditoModel {

	private static final long serialVersionUID = 1L;

	protected ProductoCreditoEntity tipoDocumentoEntity;
	protected EntityManager em;

	public TipoDocumentoAdapter(EntityManager em, ProductoCreditoEntity tipoDocumentoEntity) {
		this.em = em;
		this.tipoDocumentoEntity = tipoDocumentoEntity;
	}

	public ProductoCreditoEntity getTipoDocumentEntity() {
		return tipoDocumentoEntity;
	}

	@Override
	public String getAbreviatura() {
		return tipoDocumentoEntity.getAbreviatura();
	}

	@Override
	public void setAbreviatura(String abreviatura) {
		tipoDocumentoEntity.setAbreviatura(abreviatura);
	}

	@Override
	public String getDenominacion() {
		return tipoDocumentoEntity.getDenominacion();
	}

	@Override
	public void setDenominacion(String denominacion) {
		tipoDocumentoEntity.setDenominacion(denominacion);
	}

	@Override
	public int getCantidadCaracteres() {
		return tipoDocumentoEntity.getCantidadCaracteres();
	}

	@Override
	public void setCantidadCaracteres(int cantidadCaracteres) {
		tipoDocumentoEntity.setCantidadCaracteres(cantidadCaracteres);
	}

	@Override
	public TipoPersona getTipoPersona() {
		return tipoDocumentoEntity.getTipoPersona();
	}

	@Override
	public void setTipoPersona(TipoPersona tipoPersona) {
		tipoDocumentoEntity.setTipoPersona(tipoPersona);
	}

	@Override
	public boolean getEstado() {
		return tipoDocumentoEntity.isEstado();
	}

	@Override
	public void desactivar() {
		tipoDocumentoEntity.setEstado(false);
	}

	public static ProductoCreditoEntity toTipoDocumentoEntity(ProductoCreditoModel model, EntityManager em) {
		if (model instanceof TipoDocumentoAdapter) {
			return ((TipoDocumentoAdapter) model).getTipoDocumentEntity();
		}
		return em.getReference(ProductoCreditoEntity.class, model.getAbreviatura());
	}

	@Override
	public void commit() {
		em.merge(tipoDocumentoEntity);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || !(o instanceof ProductoCreditoModel))
			return false;

		ProductoCreditoModel that = (ProductoCreditoModel) o;
		return that.getAbreviatura().equals(getAbreviatura());
	}

	@Override
	public int hashCode() {
		return getAbreviatura().hashCode();
	}

}

package org.sistcoop.producto.models.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.sistcoop.producto.models.ProductoCreditoModel;
import org.sistcoop.producto.models.ProductoProvider;
import org.sistcoop.producto.models.enums.TipoPersona;
import org.sistcoop.producto.models.jpa.entities.ProductoCreditoEntity;

@Named
@Stateless
@Local(ProductoProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaProductoProvider implements ProductoProvider {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public ProductoCreditoModel addTipoDocumento(String abreviatura, String denominacion, int cantidadCaracteres, TipoPersona tipoPersona) {
		ProductoCreditoEntity tipoDocumentoEntity = new ProductoCreditoEntity();
		tipoDocumentoEntity.setAbreviatura(abreviatura);
		tipoDocumentoEntity.setDenominacion(denominacion);
		tipoDocumentoEntity.setCantidadCaracteres(cantidadCaracteres);
		tipoDocumentoEntity.setTipoPersona(tipoPersona);
		tipoDocumentoEntity.setEstado(true);
		em.persist(tipoDocumentoEntity);
		return new TipoDocumentoAdapter(em, tipoDocumentoEntity);
	}

	@Override
	public ProductoCreditoModel getTipoDocumentoByAbreviatura(String abreviatura) {
		TypedQuery<ProductoCreditoEntity> query = em.createNamedQuery(ProductoCreditoEntity.findByAbreviatura, ProductoCreditoEntity.class);
		query.setParameter("abreviatura", abreviatura);
		List<ProductoCreditoEntity> results = query.getResultList();
		if (results.size() == 0)
			return null;
		return new TipoDocumentoAdapter(em, results.get(0));
	}

	@Override
	public List<ProductoCreditoModel> getTiposDocumento() {
		TypedQuery<ProductoCreditoEntity> query = em.createNamedQuery(ProductoCreditoEntity.findAll, ProductoCreditoEntity.class);
		List<ProductoCreditoEntity> list = query.getResultList();
		List<ProductoCreditoModel> results = new ArrayList<ProductoCreditoModel>();
		for (ProductoCreditoEntity entity : list) {
			results.add(new TipoDocumentoAdapter(em, entity));
		}
		return results;
	}

	@Override
	public List<ProductoCreditoModel> getTiposDocumento(TipoPersona tipoPersona) {
		TypedQuery<ProductoCreditoEntity> query = em.createNamedQuery(ProductoCreditoEntity.findByTipopersona, ProductoCreditoEntity.class);
		query.setParameter("tipoPersona", tipoPersona);
		List<ProductoCreditoEntity> list = query.getResultList();
		List<ProductoCreditoModel> results = new ArrayList<ProductoCreditoModel>();
		for (ProductoCreditoEntity entity : list) {
			results.add(new TipoDocumentoAdapter(em, entity));
		}
		return results;
	}

	@Override
	public List<ProductoCreditoModel> getTiposDocumento(boolean estado) {
		TypedQuery<ProductoCreditoEntity> query = em.createNamedQuery(ProductoCreditoEntity.findAll, ProductoCreditoEntity.class);
		List<ProductoCreditoEntity> list = query.getResultList();
		List<ProductoCreditoModel> results = new ArrayList<ProductoCreditoModel>();
		for (ProductoCreditoEntity entity : list) {
			if(entity.isEstado() == estado)
				results.add(new TipoDocumentoAdapter(em, entity));
		}
		return results;
	}

	@Override
	public List<ProductoCreditoModel> getTiposDocumento(TipoPersona tipoPersona, boolean estado) {
		TypedQuery<ProductoCreditoEntity> query = em.createNamedQuery(ProductoCreditoEntity.findByTipopersona, ProductoCreditoEntity.class);
		query.setParameter("tipoPersona", tipoPersona);
		List<ProductoCreditoEntity> list = query.getResultList();
		List<ProductoCreditoModel> results = new ArrayList<ProductoCreditoModel>();
		for (ProductoCreditoEntity entity : list) {
			if(entity.isEstado() == estado)
				results.add(new TipoDocumentoAdapter(em, entity));
		}
		return results;
	}

	@Override
	public boolean desactivarTipoDocumento(ProductoCreditoModel tipoDocumentoModel) {
		ProductoCreditoEntity tipoDocumentoEntity = TipoDocumentoAdapter.toTipoDocumentoEntity(tipoDocumentoModel, em);
		tipoDocumentoEntity.setEstado(false);
		em.merge(tipoDocumentoEntity);
		return true;
	}
	
	@Override
	public boolean removeTipoDocumento(ProductoCreditoModel tipoDocumentoModel) {
		ProductoCreditoEntity tipoDocumentoEntity = TipoDocumentoAdapter.toTipoDocumentoEntity(tipoDocumentoModel, em);		
		if (em.contains(tipoDocumentoEntity)){
			em.remove(tipoDocumentoEntity);	
		}			
		else {
			em.remove(em.getReference(ProductoCreditoEntity.class, tipoDocumentoEntity.getAbreviatura()));	
		}			
		return true;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

}

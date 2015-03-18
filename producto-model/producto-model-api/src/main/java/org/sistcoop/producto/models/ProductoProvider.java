package org.sistcoop.producto.models;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import org.sistcoop.producto.models.enums.TipoPersona;
import org.sistcoop.producto.provider.Provider;

@Local
public interface ProductoProvider extends Provider {

	ProductoCreditoModel addProducto(
			String denominacion, 
			TipoPersona tipoPersona, 
			List<String> monedas,
			BigDecimal montoMinimo,
			BigDecimal montoMaximo);

	boolean desactivarProducto(ProductoCreditoModel productoCreditoModel);

	ProductoCreditoModel getProductoById(Integer id);

	List<ProductoCreditoModel> getProductos();

	List<ProductoCreditoModel> getProductos(TipoPersona tipoPersona);

	List<ProductoCreditoModel> getTiposDocumento(boolean estado);

	List<ProductoCreditoModel> getTiposDocumento(TipoPersona tipoPersona, boolean estado);

}

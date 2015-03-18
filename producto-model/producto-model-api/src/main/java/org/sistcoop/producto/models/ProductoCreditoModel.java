package org.sistcoop.producto.models;

import org.sistcoop.producto.models.enums.TipoPersona;

public interface ProductoCreditoModel extends Model {

	String getAbreviatura();

	void setAbreviatura(String abreviatura);

	String getDenominacion();

	void setDenominacion(String denominacion);

	int getCantidadCaracteres();

	void setCantidadCaracteres(int cantidadCaracteres);

	TipoPersona getTipoPersona();

	void setTipoPersona(TipoPersona tipoPersona);

	boolean getEstado();

	void desactivar();

}
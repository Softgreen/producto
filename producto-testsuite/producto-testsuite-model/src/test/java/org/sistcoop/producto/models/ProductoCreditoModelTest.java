package org.sistcoop.producto.models;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.File;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sistcoop.producto.models.ProductoCreditoModel;
import org.sistcoop.producto.models.ProductoProvider;
import org.sistcoop.producto.models.enums.TipoPersona;
import org.sistcoop.producto.models.jpa.JpaProductoProvider;
import org.sistcoop.producto.models.jpa.TipoDocumentoAdapter;
import org.sistcoop.producto.provider.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Arquillian.class)
@UsingDataSet("empty.xml")
public class ProductoCreditoModelTest {

	Logger log = LoggerFactory.getLogger(ProductoCreditoModelTest.class);

	@PersistenceContext
	private EntityManager em;

	@Resource           
	private UserTransaction utx; 
	
	@Inject
	private ProductoProvider tipoDocumentoProvider;	
	
	@Deployment
	public static WebArchive createDeployment() {
		File[] dependencies = Maven.resolver()
				.resolve("org.slf4j:slf4j-simple:1.7.10")
				.withoutTransitivity().asFile();

		WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war")
				/**persona-model-api**/
				.addClass(Provider.class)										
				.addClass(ProductoProvider.class)
				
				.addPackage(ProductoCreditoModel.class.getPackage())
				.addPackage(TipoPersona.class.getPackage())
				
				/**persona-model-jpa**/
				.addClass(JpaProductoProvider.class)
				.addClass(TipoDocumentoAdapter.class)
				
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("test-ds.xml");

		war.addAsLibraries(dependencies);

		return war;
	}				
	
	@Test
	public void desactivar() {
		ProductoCreditoModel model = tipoDocumentoProvider.addTipoDocumento("DNI", "Documento nacional de identidad", 8, TipoPersona.NATURAL);				
		model.desactivar();
		
		assertThat(model.getEstado(), is(false));
	}
	
	@Test
	public void commit() {
		ProductoCreditoModel model1 = tipoDocumentoProvider.addTipoDocumento("DNI", "Documento nacional de identidad", 8, TipoPersona.NATURAL);		
		
		String abreviatura = model1.getAbreviatura();
		String denominacionNueva = "Otra denominacion";
		
		model1.setDenominacion(denominacionNueva);
		model1.commit();	
						
		ProductoCreditoModel model2 = tipoDocumentoProvider.getTipoDocumentoByAbreviatura(abreviatura);
		
		assertThat(model2.getDenominacion(), is(equalTo(denominacionNueva)));
	}	
	
	@Test
	public void testAttributes() {
		ProductoCreditoModel model = tipoDocumentoProvider.addTipoDocumento("DNI", "Documento nacional de identidad", 8, TipoPersona.NATURAL);		
		
		assertThat(model.getAbreviatura(), is(notNullValue()));
		assertThat(model.getDenominacion(), is(notNullValue()));
		assertThat(model.getCantidadCaracteres(), is(notNullValue()));
		assertThat(model.getTipoPersona(), is(equalTo(TipoPersona.NATURAL)));
		assertThat(model.getEstado(), is(true));	
	}	
		
}

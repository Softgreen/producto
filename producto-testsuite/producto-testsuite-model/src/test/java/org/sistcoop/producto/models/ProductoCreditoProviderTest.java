package org.sistcoop.producto.models;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

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
public class ProductoCreditoProviderTest {

	Logger log = LoggerFactory.getLogger(ProductoCreditoProviderTest.class);	
	
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
	public void addTipoDocumento() {
		ProductoCreditoModel model = tipoDocumentoProvider.addTipoDocumento("DNI", "Documento nacional de identidad", 8, TipoPersona.NATURAL);		
		assertThat(model, is(notNullValue()));
		assertThat(model.getEstado(), is(true));	
	}	

	@Test
	public void getTipoDocumentoByAbreviatura() {
		ProductoCreditoModel model1 = tipoDocumentoProvider.addTipoDocumento("DNI", "Documento nacional de identidad", 8, TipoPersona.NATURAL);						
		
		String abreviatura = model1.getAbreviatura();		
		ProductoCreditoModel model2 = tipoDocumentoProvider.getTipoDocumentoByAbreviatura(abreviatura);
				
		assertThat(model1, is(equalTo(model2)));
	}
	

	@Test
	public void getTiposDocumento() {	
		tipoDocumentoProvider.addTipoDocumento("DNI", "Documento nacional de identidad", 8, TipoPersona.NATURAL);
		
		List<ProductoCreditoModel> models = tipoDocumentoProvider.getTiposDocumento();
		for (ProductoCreditoModel tipoDocumentoModel : models) {			
			assertThat(tipoDocumentoModel.getEstado(), is(true));
		}
		
		assertThat(models.size(), is(equalTo(1)));
	}
	
	@Test
	public void getTiposDocumentoByTipoPersona() {		
		tipoDocumentoProvider.addTipoDocumento("DNI", "Documento nacional de identidad", 8, TipoPersona.NATURAL);
		tipoDocumentoProvider.addTipoDocumento("RUC", "Registro unico de contribuyente", 11, TipoPersona.JURIDICA);
		
		List<ProductoCreditoModel> modelsNatural = tipoDocumentoProvider.getTiposDocumento(TipoPersona.NATURAL);
		for (ProductoCreditoModel tipoDocumentoModel : modelsNatural) {			
			assertThat(tipoDocumentoModel.getEstado(), is(true));
			assertThat(tipoDocumentoModel.getTipoPersona(), is(equalTo(TipoPersona.NATURAL)));
		}
		
		List<ProductoCreditoModel> modelsJuridico = tipoDocumentoProvider.getTiposDocumento(TipoPersona.JURIDICA);
		for (ProductoCreditoModel tipoDocumentoModel : modelsJuridico) {
			assertThat(tipoDocumentoModel.getEstado(), is(true));
			assertThat(tipoDocumentoModel.getTipoPersona(), is(equalTo(TipoPersona.JURIDICA)));	
		}
		
		assertThat(modelsNatural.size(), is(equalTo(1)));
		assertThat(modelsJuridico.size(), is(equalTo(1)));
	}
	
	@Test
	public void getTiposDocumentoByEstado() {	
		@SuppressWarnings("unused")
		ProductoCreditoModel tipoDocumentoModel1 = tipoDocumentoProvider.addTipoDocumento("DNI", "Documento nacional de identidad", 8, TipoPersona.NATURAL);
		ProductoCreditoModel tipoDocumentoModel2 = tipoDocumentoProvider.addTipoDocumento("RUC", "Registro unico de contribuyente", 11, TipoPersona.JURIDICA);
		
		tipoDocumentoProvider.desactivarTipoDocumento(tipoDocumentoModel2);
		
		List<ProductoCreditoModel> modelsActive = tipoDocumentoProvider.getTiposDocumento(true);
		for (ProductoCreditoModel tipoDocumentoModel : modelsActive) {
			assertThat(tipoDocumentoModel.getEstado(), is(true));								
		}
		
		List<ProductoCreditoModel> modelsInactive = tipoDocumentoProvider.getTiposDocumento(false);
		for (ProductoCreditoModel tipoDocumentoModel : modelsInactive) {
			assertThat(tipoDocumentoModel.getEstado(), is(false));				
		}
		
		assertThat(modelsActive.size(), is(equalTo(1)));
		assertThat(modelsInactive.size(), is(equalTo(1)));
	}
	
	@Test
	public void getTiposDocumentoByTipoPersonaAndEstado()  {	
		@SuppressWarnings("unused")
		ProductoCreditoModel tipoDocumentoModel1 = tipoDocumentoProvider.addTipoDocumento("DNI", "Documento nacional de identidad", 8, TipoPersona.NATURAL);
		ProductoCreditoModel tipoDocumentoModel2 = tipoDocumentoProvider.addTipoDocumento("Partida nacimiento", "Partida de nacimiento", 8, TipoPersona.NATURAL);
		
		@SuppressWarnings("unused")
		ProductoCreditoModel tipoDocumentoModel3 = tipoDocumentoProvider.addTipoDocumento("RUC", "Registro unico de contribuyente", 11, TipoPersona.JURIDICA);
		ProductoCreditoModel tipoDocumentoModel4 = tipoDocumentoProvider.addTipoDocumento("RRR", "Registro registral restricto", 11, TipoPersona.JURIDICA);
		
		tipoDocumentoProvider.desactivarTipoDocumento(tipoDocumentoModel2);
		tipoDocumentoProvider.desactivarTipoDocumento(tipoDocumentoModel4);
		
		List<ProductoCreditoModel> modelsNaturalActivo = tipoDocumentoProvider.getTiposDocumento(TipoPersona.NATURAL, true);
		for (ProductoCreditoModel tipoDocumentoModel : modelsNaturalActivo) {
			assertThat(tipoDocumentoModel.getEstado(), is(true));
			assertThat(tipoDocumentoModel.getTipoPersona(), is(equalTo(TipoPersona.NATURAL)));
		}
		
		List<ProductoCreditoModel> modelsNaturalInactivo = tipoDocumentoProvider.getTiposDocumento(TipoPersona.NATURAL, false);
		for (ProductoCreditoModel tipoDocumentoModel : modelsNaturalInactivo) {
			assertThat(tipoDocumentoModel.getEstado(), is(false));
			assertThat(tipoDocumentoModel.getTipoPersona(), is(equalTo(TipoPersona.NATURAL)));
		}
		
		List<ProductoCreditoModel> modelsJuridicaActivo = tipoDocumentoProvider.getTiposDocumento(TipoPersona.JURIDICA, true);
		for (ProductoCreditoModel tipoDocumentoModel : modelsJuridicaActivo) {
			assertThat(tipoDocumentoModel.getEstado(), is(true));
			assertThat(tipoDocumentoModel.getTipoPersona(), is(equalTo(TipoPersona.JURIDICA)));
		}
		
		List<ProductoCreditoModel> modelsJuridicaInactivo = tipoDocumentoProvider.getTiposDocumento(TipoPersona.JURIDICA, false);
		for (ProductoCreditoModel tipoDocumentoModel : modelsJuridicaInactivo) {
			assertThat(tipoDocumentoModel.getEstado(), is(false));
			assertThat(tipoDocumentoModel.getTipoPersona(), is(equalTo(TipoPersona.JURIDICA)));
		}
		
		assertThat(modelsNaturalActivo.size(), is(equalTo(1)));
		assertThat(modelsNaturalInactivo.size(), is(equalTo(1)));
		assertThat(modelsJuridicaActivo.size(), is(equalTo(1)));
		assertThat(modelsJuridicaInactivo.size(), is(equalTo(1)));
	}
	
	@Test
	public void desactivarTipoDocumento()  {			
		ProductoCreditoModel model1 = tipoDocumentoProvider.addTipoDocumento("DNI", "Documento nacional de identidad", 8, TipoPersona.NATURAL);						
		
		String abreviatura = model1.getAbreviatura();		
		boolean result = tipoDocumentoProvider.desactivarTipoDocumento(model1);
		
		ProductoCreditoModel model2 = tipoDocumentoProvider.getTipoDocumentoByAbreviatura(abreviatura);
		
		assertThat(result, is(true));
		assertThat(model2, is(notNullValue()));
		assertThat(model2.getEstado(), is(false));			
	}
	
	@Test
	public void removeTipoDocumento()  {			
		ProductoCreditoModel model1 = tipoDocumentoProvider.addTipoDocumento("DNI", "Documento nacional de identidad", 8, TipoPersona.NATURAL);				
		
		String abreviatura = model1.getAbreviatura();
		boolean result = tipoDocumentoProvider.removeTipoDocumento(model1);
		
		ProductoCreditoModel model2 = tipoDocumentoProvider.getTipoDocumentoByAbreviatura(abreviatura);			
		
		assertThat(result, is(true));
		assertThat(model2, is(nullValue()));	
	}
	
}

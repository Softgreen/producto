package org.sistcoop.producto.models;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.io.File;

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
import org.sistcoop.producto.models.enums.TipoPersona;
import org.sistcoop.producto.models.jpa.JpaProductoProvider;
import org.sistcoop.producto.models.jpa.ProductoCreditoAdapter;
import org.sistcoop.producto.models.utils.ModelToRepresentation;
import org.sistcoop.producto.models.utils.RepresentationToModel;
import org.sistcoop.producto.representations.idm.ProductoCreditoRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.net.ssl.internal.ssl.Provider;

@RunWith(Arquillian.class)
@UsingDataSet("empty.xml")
public class ProductoCreditoProviderTest {

	Logger log = LoggerFactory.getLogger(ProductoCreditoProviderTest.class);

	@Inject
	private ProductoProvider productoProvider;

	@Deployment
	public static WebArchive createDeployment() {
		File[] dependencies = Maven.resolver().resolve("org.slf4j:slf4j-simple:1.7.10")
				.withoutTransitivity().asFile();

		WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war")
		/** persona-core */
		.addPackage(ProductoCreditoRepresentation.class.getPackage())

		/** persona-core-jaxrs */
		//.addPackage(JaxRsActivator.class.getPackage())

		/** persona-client */
		//.addClasses(TipoDocumentoResource.class, TiposDocumentoResource.class)

		/** persona-services */
		//.addClasses(TipoDocumentoResourceImpl.class, TiposDocumentoResourceImpl.class)

		/** persona-model-api */
		.addClass(Provider.class)
		.addClass(ProductoProvider.class)
		.addPackage(ProductoCreditoModel.class.getPackage())
		.addPackage(TipoPersona.class.getPackage())

		.addClass(RepresentationToModel.class)
		.addClass(ModelToRepresentation.class)

		/** persona-model-jpa */
		.addClass(ProductoCreditoAdapter.class)
		.addClass(JpaProductoProvider.class)

		.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
		.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
		.addAsWebInfResource("test-ds.xml");

		war.addAsLibraries(dependencies);
		
		return war;
	}
	
	@Test
	public void prueba(){
		String a = "car";
		String b = "los";
		String c = "carlos";
		
		assertThat(a+b, is(equalTo(c)));	
	}
}

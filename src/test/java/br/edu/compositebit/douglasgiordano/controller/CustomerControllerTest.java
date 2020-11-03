package br.edu.compositebit.douglasgiordano.controller;

import br.edu.compositebit.douglasgiordano.model.entities.Address;
import br.edu.compositebit.douglasgiordano.model.entities.Customer;
import br.edu.compositebit.douglasgiordano.model.entities.EnumState;
import br.edu.compositebit.douglasgiordano.model.entities.Message;
import com.despegar.http.client.*;
import com.despegar.sparkjava.test.SparkServer;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerControllerTest extends SuperCrudControllerTest {
	private Customer customer;

	@ClassRule
	public static SparkServer<TestContollerTestSparkApplication> testServer = new SparkServer<>(CustomerControllerTest.TestContollerTestSparkApplication.class, 8080);

	@Override
	public Customer getSimpleCostumer(){
		Customer customer = new Customer();
		customer.setName("Douglas Montanha Giordano");
		customer.setBirthDate(LocalDate.now());
		customer.setCpf("999.999.999-99");
		customer.setEmail("douglasmontanhagiordano@teste.com");

		Address address = new Address();
		address.setMain(true);
		address.setCity("Alegrete");
		address.setState(EnumState.RS);
		address.setAdditionalInformation("Not found");
		address.setNumber("4");
		address.setNeighborhood("Nei");
		address.setZipCode("99-999-999");
		customer.setMainAddress(address);
		return customer;
	}

	@Override
	public SparkServer<TestContollerTestSparkApplication> getServer() {
		return CustomerControllerTest.testServer;
	}

	@After
	public void afterDeleteTest() throws IOException, HttpClientException {
		Message message = this.deleteCostumer(this.customer, 200);
		assertEquals("The customer has been deleted!", message.getMessage());
		assertNotNull(testServer.getApplication());
	}

	@Test
	public void test1_createCostumer() throws HttpClientException, IOException {
		this.customer = createCostumer(this.getSimpleCostumer(), 200);
		assertNotNull(customer);
		assertNotNull(customer.getId());
		assertNotNull(testServer.getApplication());
	}

	@Test
	public void test2_updateCostumerTest() throws HttpClientException, IOException {
		this.customer = createCostumer(this.getSimpleCostumer(), 200);
		int idCostumer = customer.getId();
		customer.setName("Douglas Updated");
		customer = this.updateCostumer(customer, 200);
		assertNotNull(customer);
		assertEquals(idCostumer, customer.getId());
		assertEquals("Douglas Updated", customer.getName());
		assertNotNull(testServer.getApplication());
	}

	@Test
	public void test3_searchCostumersTest() throws Exception {
		this.customer = createCostumer(this.getSimpleCostumer(), 200);
		final Integer idCostumer = customer.getId();
		List<Customer> customers = this.searchListCostumer(200);
		customers.stream().filter(c -> c.getId() == idCostumer).forEach(c -> {
			assertNotNull(c);
			assertNotNull(c.getId());
		});
		assertNotNull(testServer.getApplication());
	}

	@Test
	public void test4_searchCostumerByIdTest() throws Exception {
		this.customer = createCostumer(this.getSimpleCostumer(), 200);
		int id = customer.getId();
		Customer customer = super.searchCostumer(id, 200);
		assertNotNull(customer);
		assertEquals(id, customer.getId());
		assertNotNull(testServer.getApplication());
	}
}
package br.edu.compositebit.douglasgiordano.controller;

import br.edu.compositebit.douglasgiordano.model.entities.Address;
import br.edu.compositebit.douglasgiordano.model.entities.Customer;
import br.edu.compositebit.douglasgiordano.model.entities.EnumState;
import br.edu.compositebit.douglasgiordano.model.entities.Message;
import com.despegar.http.client.HttpClientException;
import com.despegar.sparkjava.test.SparkServer;
import org.junit.After;
import org.junit.ClassRule;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AddressControllerTest extends SuperCrudControllerTest {
	private Customer customer;
	private Address address;

	@ClassRule
	public static SparkServer<TestContollerTestSparkApplication> testServer = new SparkServer<>(TestContollerTestSparkApplication.class, 8080);

	@Override
	public Customer getSimpleCostumer(){
		Customer customer = new Customer();
		customer.setName("Douglas Montanha Giordano");
		customer.setBirthDate(LocalDate.now());
		customer.setCpf("300.682.880-18");
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

	public Address getSimpleMainAddress(){
		Address address = new Address();
		address.setMain(true);
		address.setCity("Porto Alegre");
		address.setState(EnumState.RS);
		address.setAdditionalInformation("Found");
		address.setNumber("5");
		address.setNeighborhood("Harry");
		address.setZipCode("99-999-999");
		return address;
	}

	@Override
	public SparkServer<TestContollerTestSparkApplication> getServer() {
		return AddressControllerTest.testServer;
	}

	@After
	public void afterDeleteTest() throws IOException, HttpClientException {
		Message message = this.deleteAddress(customer.getId(), address, 200);
		this.deleteCostumer(customer, 200);
		assertEquals("The address has been deleted!", message.getMessage());
		assertNotNull(testServer.getApplication());
	}

	@Test
	public void test1_createAddressTest() throws HttpClientException, IOException {
		this.customer = super.createCostumer(this.getSimpleCostumer(), 200);
		this.address = this.getSimpleMainAddress();
		this.address = super.createAddress(this.customer.getId(), this.address, 200);
		assertNotNull(this.address);
		assertNotNull(this.address.getId());
		assertEquals(true, this.address.isMain());
		assertNotNull(testServer.getApplication());
	}

	@Test
	public void test2_updateAddressTest() throws HttpClientException, IOException {
		this.customer = createCostumer(this.getSimpleCostumer(), 200);
		this.address = this.customer.getMainAddress();
		this.address.setNumber("5A");
		this.address = super.updateAddress(this.customer.getId(), this.address, 200);
		assertNotNull(this.address);
		assertNotNull(this.address.getId());
		assertEquals("5A", this.address.getNumber());
		assertNotNull(testServer.getApplication());
	}

	@Test
	public void test3_searchAddressesTest() throws Exception {
		this.customer = createCostumer(this.getSimpleCostumer(), 200);
		final Integer idCostumer = customer.getId();
		Address address = this.getSimpleMainAddress();
		address.setCustomer(idCostumer);
		this.address = super.createAddress(idCostumer, address , 200);
		List<Address> addresses = this.searchListAddress(this.customer, 200);
		assertEquals(2, addresses.size());
		addresses.stream().forEach(c -> {
			assertNotNull(c);
			assertNotNull(c.getId());
		});
		assertNotNull(testServer.getApplication());
	}

	@Test
	public void test4_searchAddressByIdTest() throws Exception {
		this.customer = createCostumer(this.getSimpleCostumer(), 200);
		int id = customer.getId();
		this.address = super.searchAddress(id, this.customer.getMainAddress().getId(), 200);
		assertNotNull(address);
		assertNotNull(testServer.getApplication());
	}
}
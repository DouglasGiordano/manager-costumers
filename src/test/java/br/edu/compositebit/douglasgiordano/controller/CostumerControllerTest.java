package br.edu.compositebit.douglasgiordano.controller;

import br.edu.compositebit.douglasgiordano.Application;
import br.edu.compositebit.douglasgiordano.controller.view.CostumerView;
import br.edu.compositebit.douglasgiordano.model.entities.Address;
import br.edu.compositebit.douglasgiordano.model.entities.Costumer;
import br.edu.compositebit.douglasgiordano.model.entities.EnumState;
import br.edu.compositebit.douglasgiordano.model.entities.Message;
import com.despegar.http.client.*;
import com.despegar.sparkjava.test.SparkServer;
import org.junit.ClassRule;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import spark.servlet.SparkApplication;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CostumerControllerTest extends SuperControllerTest{
	public static class TestContollerTestSparkApplication implements SparkApplication {
		@Override
		public void init() {
			Application.main();
		}
	}

	@ClassRule
	public static SparkServer<TestContollerTestSparkApplication> testServer = new SparkServer<>(CostumerControllerTest.TestContollerTestSparkApplication.class, 8080);

	public Costumer getSimpleCostumer(){
		Costumer costumer = new Costumer();
		costumer.setName("Douglas Montanha Giordano");
		costumer.setBirthDate(LocalDate.now());
		costumer.setCpf("999.999.999-99");
		costumer.setEmail("douglasmontanhagiordano@teste.com");

		Address address = new Address();
		address.setMain(true);
		address.setCity("Alegrete");
		address.setState(EnumState.RS);
		address.setAdditionalInformation("Not found");
		address.setNumber("4");
		address.setNeighborhood("Nei");
		address.setZipCode("99-999-999");
		costumer.setMainAddress(address);
		return costumer;
	}

	@Test
	public void test1_createCostumer() throws HttpClientException, IOException {
		Costumer c = this.getSimpleCostumer();
		String body = this.objectMapper.writerWithView(CostumerView.CREATE.class).writeValueAsString(c);
		PostMethod post = testServer.post(Path.COSTUMERS, body,false);
		HttpResponse httpResponse = testServer.execute(post);
		Costumer cResponse = objectMapper.readerWithView(CostumerView.CREATE.class).forType(Costumer.class).readValue(httpResponse.body());
		assertNotNull(cResponse);
		assertNotNull(cResponse.getId());
		assertEquals(200, httpResponse.code());
		assertNotNull(testServer.getApplication());
	}

	@Test
	public void test2_updateCostumerTest() throws HttpClientException, IOException {
		Costumer c = this.getSimpleCostumer();
		c.setId(1);
		c.setName("Douglas Updated");
		String body = this.objectMapper.writerWithView(CostumerView.CREATE.class).writeValueAsString(c);
		PutMethod post = testServer.put(Path.COSTUMERS+"/1", body,false);
		HttpResponse httpResponse = testServer.execute(post);
		Costumer cResponse = objectMapper.readerWithView(CostumerView.CREATE.class).forType(Costumer.class).readValue(httpResponse.body());
		assertNotNull(cResponse);
		assertEquals("1", String.valueOf(cResponse.getId()));
		assertEquals("Douglas Updated", cResponse.getName());
		assertEquals(200, httpResponse.code());
		assertNotNull(testServer.getApplication());
	}

	@Test
	public void test3_searchCostumersTest() throws Exception {
		GetMethod get = testServer.get(Path.COSTUMERS, false);
		HttpResponse httpResponse = testServer.execute(get);
		List<Costumer> cResponse = Arrays.asList(objectMapper.readValue(httpResponse.body(), Costumer[].class));
		assertNotNull(cResponse);
		assertFalse(cResponse.isEmpty());
		assertEquals(200, httpResponse.code());
		assertNotNull(testServer.getApplication());
	}

	@Test
	public void test4_searchCostumerByIdTest() throws Exception {
		GetMethod get = testServer.get(Path.COSTUMERS+"/1", false);
		HttpResponse httpResponse = testServer.execute(get);
		Costumer cResponse = objectMapper.readerWithView(CostumerView.CREATE.class).forType(Costumer.class).readValue(httpResponse.body());
		assertNotNull(cResponse);
		assertEquals(1, cResponse.getId());
		assertEquals(200, httpResponse.code());
		assertNotNull(testServer.getApplication());
	}

	@Test
	public void test5_deleteCostumerTest() throws Exception {
		DeleteMethod get = testServer.delete(Path.COSTUMERS+"/1", false);
		HttpResponse httpResponse = testServer.execute(get);
		Message cResponse = objectMapper.readerFor(Message.class).readValue(httpResponse.body());
		assertNotNull(cResponse);
		assertEquals("The costumer has been deleted!", cResponse.getMessage());
		assertEquals(200, httpResponse.code());
		assertNotNull(testServer.getApplication());
	}
}
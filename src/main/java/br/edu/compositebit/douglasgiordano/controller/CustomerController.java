package br.edu.compositebit.douglasgiordano.controller;

import br.edu.compositebit.douglasgiordano.controller.view.CustomerView;
import br.edu.compositebit.douglasgiordano.dao.filter.CustomerFilter;
import br.edu.compositebit.douglasgiordano.model.entities.Customer;
import br.edu.compositebit.douglasgiordano.model.entities.Message;
import br.edu.compositebit.douglasgiordano.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import lombok.Data;

import java.util.ArrayList;

import static spark.Spark.*;

/**
 * @author Douglas Montanha Giordano
 * Class responsible for defining CRUD Customer routes.
 */
@Data
public class CustomerController extends SuperController {

    @Inject
    private CustomerService customerService;

    @Inject
    private ObjectMapper objectMapper;

    /**
     * Configures the API with the routes to create, delete, search and delete.
     */
    @Override
    public void initApi() {
        get(Path.CUSTOMERS, (req, res) -> {
            CustomerFilter filter = new CustomerFilter(req.queryMap().toMap());
            res.type("application/json");
            ArrayList<Customer> customers = this.customerService.get(filter);
            return objectMapper.writerWithView(CustomerView.DEFAULT.class).writeValueAsString(customers);
        });

        get(Path.CUSTOMERS + "/:id", (req, res) -> {
            res.type("application/json");
            String id = req.params("id");
            Customer customer = this.customerService.get(Integer.parseInt(id));
            return objectMapper.writerWithView(CustomerView.DEFAULT.class).writeValueAsString(customer);
        });

        post(Path.CUSTOMERS, (req, res) -> {
            res.type("application/json");
            Customer c = objectMapper.readerWithView(CustomerView.CREATE.class).readValue(req.body(), Customer.class);
            Customer customer = this.customerService.add(c);
            return objectMapper.writerWithView(CustomerView.DEFAULT.class).writeValueAsString(customer);
        });

        put(Path.CUSTOMERS + "/:id", (req, res) -> {
            res.type("application/json");
            String id = req.params("id");
            Customer c = objectMapper.readerWithView(CustomerView.CREATE.class).readValue(req.body(), Customer.class);
            Customer customer = this.customerService.update(Integer.parseInt(id), c);
            return objectMapper.writerWithView(CustomerView.DEFAULT.class).writeValueAsString(customer);
        });

        delete(Path.CUSTOMERS + "/:id", (req, res) -> {
            res.type("application/json");
            String id = req.params("id");
            this.customerService.delete(Integer.parseInt(id));
            return objectMapper.writeValueAsString(new Message("The customer has been deleted!"));
        });
    }
}

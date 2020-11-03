package br.edu.compositebit.douglasgiordano.controller;

import br.edu.compositebit.douglasgiordano.controller.view.CustomerView;
import br.edu.compositebit.douglasgiordano.controller.view.AddressView;
import br.edu.compositebit.douglasgiordano.model.entities.Address;
import br.edu.compositebit.douglasgiordano.model.entities.Message;
import br.edu.compositebit.douglasgiordano.service.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import lombok.Data;

import java.util.ArrayList;

import static spark.Spark.*;

/**
 * @author Douglas Montanha Giordano
 * Class responsible for defining CRUD Address routes.
 */
@Data
public class AddressController extends SuperController {

    @Inject
    private AddressService addressService;

    @Inject
    private ObjectMapper objectMapper;

    /**
     * Configures the API with the routes to create, delete, search and delete.
     */
    @Override
    public void initApi() {
        get(Path.ADDRESSES, (req, res) -> {
            res.type("application/json");
            Integer id = this.getParamInt(req.params("id"));
            ArrayList<Address> customers = this.addressService.getBycustomer(id);
            return objectMapper.writerWithView(AddressView.DEFAULT.class).writeValueAsString(customers);
        });

        get(Path.ADDRESSES + "/:idAddress", (req, res) -> {
            res.type("application/json");
            Integer customerId = this.getParamInt(req.params("id"));
            Integer addressId = this.getParamInt(req.params("idAddress"));
            Address address = this.addressService.getBycustomer(customerId, addressId);
            return objectMapper.writerWithView(AddressView.DEFAULT.class).writeValueAsString(address);
        });

        post(Path.ADDRESSES, (req, res) -> {
            res.type("application/json");
            Integer customerId = this.getParamInt(req.params("id"));
            Address c = objectMapper.readerWithView(AddressView.CREATE.class).forType(Address.class).readValue(req.body());
            c.setCustomer(customerId);
            Address address = this.addressService.add(c);
            return objectMapper.writerWithView(CustomerView.DEFAULT.class).writeValueAsString(address);
        });

        put(Path.ADDRESSES + "/:idAddress", (req, res) -> {
            res.type("application/json");
            Integer customerId = this.getParamInt(req.params("id"));
            Integer addressId = this.getParamInt(req.params("idAddress"));
            Address address = objectMapper.readerWithView(AddressView.CREATE.class).forType(Address.class).readValue(req.body());
            address.setCustomer(customerId);
            address = this.addressService.update(addressId, address);
            return objectMapper.writerWithView(CustomerView.DEFAULT.class).writeValueAsString(address);
        });

        delete(Path.ADDRESSES + "/:idAddress", (req, res) -> {
            res.type("application/json");
            this.addressService.delete(this.getParamInt(req.params("id")), this.getParamInt(req.params("idAddress")));
            return objectMapper.writeValueAsString(new Message("The address has been deleted!"));
        });
    }
}

package br.edu.compositebit.douglasgiordano.controller;

import br.edu.compositebit.douglasgiordano.controller.view.AddressView;
import br.edu.compositebit.douglasgiordano.controller.view.CostumerView;
import br.edu.compositebit.douglasgiordano.model.entities.Address;
import br.edu.compositebit.douglasgiordano.model.entities.Costumer;
import br.edu.compositebit.douglasgiordano.dao.filter.CostumerFilter;
import br.edu.compositebit.douglasgiordano.model.entities.Message;
import br.edu.compositebit.douglasgiordano.service.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import lombok.Data;

import java.util.ArrayList;

import static spark.Spark.*;

@Data
public class AddressController extends SuperController{

    @Inject
    private AddressService addressService;

    @Inject
    private ObjectMapper objectMapper;

    @Override
    public void initApi() {
        get(Path.ADDRESSES, (req, res) -> {
            res.type("application/json");
            Integer id = this.getParamInt(req.params("id"));
            ArrayList<Address> costumers = this.addressService.getByCostumer(id);
            return objectMapper.writerWithView(AddressView.DEFAULT.class).writeValueAsString(costumers);
        });

        get(Path.ADDRESSES +"/:idAddress", (req, res) -> {
            res.type("application/json");
            Integer costumerId = this.getParamInt(req.params("id"));
            Integer addressId = this.getParamInt(req.params("idAddress"));
            Address address = this.addressService.getByCostumer(costumerId, addressId);
            return objectMapper.writerWithView(AddressView.DEFAULT.class).writeValueAsString(address);
        });

        post(Path.ADDRESSES, (req, res) -> {
            res.type("application/json");
            Integer costumerId = this.getParamInt(req.params("id"));
            Address c = objectMapper.readerWithView(AddressView.CREATE.class).forType(Address.class).readValue(req.body());
            c.setCostumer(costumerId);
            Address address = this.addressService.add(c);
            return objectMapper.writerWithView(CostumerView.CREATE.class).writeValueAsString(address);
        });

        put(Path.ADDRESSES +"/:idAddress", (req, res) -> {
            res.type("application/json");
            Integer costumerId = this.getParamInt(req.params("id"));
            Integer addressId = this.getParamInt(req.params("idAddress"));
            Address address = objectMapper.readerWithView(AddressView.CREATE.class).forType(Address.class).readValue(req.body());
            address.setCostumer(costumerId);
            address = this.addressService.update(addressId, address);
            return objectMapper.writerWithView(CostumerView.CREATE.class).writeValueAsString(address);
        });

        delete(Path.ADDRESSES+"/:idAddress", (req, res) -> {
            res.type("application/json");
            this.addressService.delete(this.getParamInt(req.params("id")), this.getParamInt(req.params("idAddress")));
            return objectMapper.writeValueAsString(new Message("The costumer has been deleted!"));
        });
    }
}

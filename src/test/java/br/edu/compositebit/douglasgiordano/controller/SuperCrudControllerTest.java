package br.edu.compositebit.douglasgiordano.controller;

import br.edu.compositebit.douglasgiordano.controller.view.AddressView;
import br.edu.compositebit.douglasgiordano.controller.view.CustomerView;
import br.edu.compositebit.douglasgiordano.model.entities.Address;
import br.edu.compositebit.douglasgiordano.model.entities.Customer;
import br.edu.compositebit.douglasgiordano.model.entities.Message;
import com.despegar.http.client.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@lombok.Data
public abstract class SuperCrudControllerTest extends SuperControllerTest {

    protected abstract Customer getSimpleCostumer();

    public Customer createCostumer(Customer customer, int expectedCode) throws HttpClientException, IOException {
        return super.create(Path.CUSTOMERS, customer, CustomerView.CREATE.class, CustomerView.DEFAULT.class, expectedCode, Customer.class);
    }

    public Customer updateCostumer(Customer customer, int expectedCode) throws HttpClientException, IOException {
        String path = Path.CUSTOMERS+"/" + customer.getId();
        return super.update(path, customer, CustomerView.CREATE.class, CustomerView.DEFAULT.class, expectedCode, Customer.class);
    }

    public Message deleteCostumer(Customer customer, int expectedCode) throws HttpClientException, IOException {
        Integer idCostumer = customer.getId();
        return super.delete(Path.CUSTOMERS+"/"+idCostumer, 200);
    }

    public List<Customer> searchListCostumer(int expectedCode) throws HttpClientException, IOException {
        return Arrays.asList(super.searchList(Path.CUSTOMERS, CustomerView.DEFAULT.class, 200, Customer[].class));
    }

    public Customer searchCostumer(int idCostumer, int expectedCode) throws HttpClientException, IOException {
        return super.search(Path.CUSTOMERS+"/"+idCostumer, CustomerView.DEFAULT.class, 200, Customer.class);
    }

    public Address createAddress(int idCustomer, Address address, int expectedCode) throws HttpClientException, IOException {
        String path = Path.ADDRESSES.replace(":id", String.valueOf(idCustomer));
        return super.create(path, address, AddressView.CREATE.class, AddressView.DEFAULT.class, expectedCode, Address.class);
    }

    public Address updateAddress(int idCustomer, Address address, int expectedCode) throws HttpClientException, IOException {
        String path = Path.ADDRESSES.replace(":id", String.valueOf(idCustomer))+"/"+address.getId();
        return super.update(path, address, AddressView.CREATE.class, AddressView.DEFAULT.class, expectedCode, Address.class);
    }

    public Message deleteAddress(int idCustomer, Address address, int expectedCode) throws HttpClientException, IOException {
        Integer idAddress = address.getId();
        String path = Path.ADDRESSES.replace(":id", String.valueOf(idCustomer))+"/"+address.getId();

        return super.delete(path, 200);
    }

    public List<Address> searchListAddress(Customer costumer, int expectedCode) throws HttpClientException, IOException {
        String path = Path.ADDRESSES.replace(":id", costumer.getId()+"");
        return Arrays.asList(super.searchList(path, AddressView.DEFAULT.class, 200, Address[].class));
    }

    public Address searchAddress(int idCostumer, int idAddress, int expectedCode) throws HttpClientException, IOException {
        String path = Path.ADDRESSES.replace(":id", idCostumer+"") +"/"+idAddress;
        return super.search(path, AddressView.DEFAULT.class, 200, Address.class);
    }
}

package br.edu.compositebit.douglasgiordano.service;

import br.edu.compositebit.douglasgiordano.dao.CustomerDao;
import br.edu.compositebit.douglasgiordano.dao.filter.CustomerFilter;
import br.edu.compositebit.douglasgiordano.model.entities.Address;
import br.edu.compositebit.douglasgiordano.model.entities.Customer;
import br.edu.compositebit.douglasgiordano.model.entities.exception.BadEntityException;
import br.edu.compositebit.douglasgiordano.model.entities.exception.EntityNotFoundException;
import com.google.inject.Inject;
import lombok.Data;
import lombok.extern.java.Log;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * @author Douglas Montanha Giordano
 * Business logic of the Customer entity.
 */
@Data
@Log
public class CustomerService extends SuperService<Customer> {
    @Inject
    private CustomerDao customerDao;

    @Inject
    private AddressService addressesService;

    /**
     * Adds a new customer and its main address. If the entity is in the wrong format, an exception is thrown.
     * @param entity
     * @return customer with ID
     * @throws BadEntityException
     */
    @Override
    public Customer add(Customer entity) throws BadEntityException {
        this.validate(entity);
        log.info("Adding customer " + entity.getName() + ".");
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdateAt(LocalDateTime.now());
        int idcustomer = this.customerDao.insert(entity);
        entity.setId(idcustomer);

        Address address = entity.getMainAddress();
        address.setCustomer(idcustomer);
        address = this.addressesService.add(address);
        entity.setMainAddress(address);
        entity.setAddresses(new ArrayList<>());
        entity.getAddresses().add(address);
        return entity;
    }

    /**
     * Search customer by ID. If the customer is not found, an exception is thrown.
     * @param id
     * @return customer
     * @throws EntityNotFoundException
     */
    @Override
    public Customer get(int id) throws EntityNotFoundException {
        log.info("Searching customer " + id + ".");
        Customer c = this.customerDao.getById(id);
        this.setAddress(c);
        if (c == null) {
            throw new EntityNotFoundException();
        }
        return c;
    }

    /**
     * Search all customers.
     * @return list customer
     */
    @Override
    public ArrayList<Customer> get() {
        return (ArrayList<Customer>) this.customerDao.list();
    }

    /**
     * Delete customer by ID.
     * @return list customer
     */
    @Override
    public void delete(Integer id) {
        this.customerDao.delete(id);
    }

    /**
     * Updates a customer and their primary address. If the entity is in the wrong format, an exception is thrown.
     * If the entity informed to be updated does not exist, an exception is thrown.
     * @param id
     * @param entity
     * @return customer
     * @throws EntityNotFoundException
     * @throws BadEntityException
     */
    @Override
    public Customer update(Integer id, Customer entity) throws EntityNotFoundException, BadEntityException {
        this.validate(entity);
        Address address = null;
        try {
            address = this.getAddressesService().getByMaincustomer(id);
            entity.getMainAddress().setId(address.getId());
            address = entity.getMainAddress();
            address.setCustomer(id);
            log.info("Updating address " + address.getId());
            this.addressesService.update(address.getId(), entity.getMainAddress());
        } catch (EntityNotFoundException ex) {
            address = entity.getMainAddress();
            address.setCustomer(id);
            address = this.getAddressesService().add(address);
        }

        log.info("Updating customer " + entity.getId() + ".");
        Customer entityNow = get(id);
        entity.setId(id);
        entity.setAddresses(entityNow.getAddresses());
        entity.setMainAddress(address);
        entity.setUpdateAt(LocalDateTime.now());
        this.customerDao.update(entity);

        return entity;
    }

    /**
     * Get customer for filter.
     * @param filter
     * @return customer filtered
     */
    public ArrayList<Customer> get(CustomerFilter filter) {
        log.info("Searching customers.");
        ArrayList<Customer> customers = (ArrayList<Customer>) this.customerDao.listFilter(filter);
        for (Customer c : customers) {
            setAddress(c);
        }
        return customers;
    }

    /**
     * Search and add the main address to the customer object.
     * @param c
     */
    private void setAddress(Customer c) {
        if (c != null) {
            ArrayList<Address> addresses = this.addressesService.getBycustomer(c.getId());
            c.setAddresses(addresses);
            Address main;
            try {
                main = addresses.stream().filter(address -> address.isMain()).findFirst().get();
            } catch (NoSuchElementException ex) {
                main = null;
            }
            c.setMainAddress(main);
        }
    }

    /**
     * Validates the customer object.
     * @param customer
     * @return
     * @throws BadEntityException
     */
    private boolean validate(Customer customer) throws BadEntityException {
        if (customer == null) {
            throw new BadEntityException("Empty customer.");
        } else if (customer.getName() == null || customer.getName().isEmpty()) {
            throw new BadEntityException("Name customer required.");
        } else if (!this.addressesService.validade(customer.getMainAddress())) {
            throw new BadEntityException("Main address customer required.");
        } else if(customer.getBirthDate() != null){
            long diff = ChronoUnit.YEARS.between(customer.getBirthDate(), LocalDate.now());
            if(diff >= 100){
                throw new BadEntityException("Customer cannot be more than 100 years old.");
            }
        } else if(customer.getEmail() != null){
            EmailValidator validator = new EmailValidator();
            if(!validator.isValid(customer.getEmail(), null)){
                throw new BadEntityException("The customer's email is invalid.");
            }
        }
        if(customer.getCpf() != null){
            if(!CpfCnpjService.isValidSsn(customer.getCpf()) || customer.getCpf().length() >= 14){
                throw new BadEntityException("The customer's CPF is invalid.");
            }
        }
        return true;
    }
}

package br.edu.compositebit.douglasgiordano.service;

import br.edu.compositebit.douglasgiordano.dao.AddressDao;
import br.edu.compositebit.douglasgiordano.model.entities.Address;
import br.edu.compositebit.douglasgiordano.model.entities.exception.BadEntityException;
import br.edu.compositebit.douglasgiordano.model.entities.exception.EntityNotFoundException;
import com.google.inject.Inject;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author Douglas Montanha Giordano
 * Business logic of the Address entity.
 */
public class AddressService extends SuperService<Address> {
    @Inject
    private AddressDao addressDao;

    /**
     * Adds a new address. If the entity is in the wrong format, an exception is thrown.
     * @param entity
     * @return address with ID
     */
    @Override
    public Address add(Address entity) {
        this.updateNewMain(entity);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdateAt(LocalDateTime.now());
        int id = this.addressDao.insert(entity);
        entity.setId(id);
        return entity;
    }

    /**
     * Search address by ID.
     * @param id
     * @return address
     */
    @Override
    public Address get(int id) {
        return this.addressDao.getById(id);
    }

    /**
     * Search all addressess.
     * @return list address
     */
    @Override
    public ArrayList<Address> get() {
        return (ArrayList<Address>) this.addressDao.list();
    }

    /**
     * Delete address by ID.
     */
    @Override
    public void delete(Integer id) {
        this.addressDao.delete(id);
    }

    /**
     * Delete address by ID and customer.
     */
    public void delete(Integer customerId, Integer addressId) throws EntityNotFoundException {
        Address address = this.addressDao.getByCustomer(customerId, addressId);
        if (address == null) {
            throw new EntityNotFoundException();
        }
        this.addressDao.delete(addressId);
    }

    @Override
    public Address update(Integer id, Address entity) {
        Address entityNow = this.get(id);
        this.updateNewMain(entity);
        entity.setId(id);
        entity.setCreatedAt(entityNow.getCreatedAt());
        entity.setUpdateAt(LocalDateTime.now());
        this.addressDao.update(entity);
        return entity;
    }

    /**
     * Updates a address. If the entity is in the wrong format, an exception is thrown.
     * If the entity informed to be updated does not exist, an exception is thrown.
     * @param entity
     * @return customer
     */
    public void updateNewMain(Address entity) {
        if (entity.isMain()) {
            Address addresMain = this.addressDao.getByMainCustomer(entity.getCustomer());
            if (addresMain == null) {
                return;
            }
            addresMain.setMain(false);
            this.addressDao.update(addresMain);
        }
    }

    /**
     * Get addres by costumer
     * @param customerId
     * @return address list costumer
     */
    public ArrayList<Address> getBycustomer(Integer customerId) {
        return (ArrayList<Address>) this.addressDao.listByCustomer(customerId);
    }

    /**
     * Get address costumer.
     * @param customerId
     * @param addressId
     * @return address
     * @throws EntityNotFoundException
     */
    public Address getBycustomer(Integer customerId, Integer addressId) throws EntityNotFoundException {
        Address address = this.addressDao.getByCustomer(customerId, addressId);
        if (address == null) {
            throw new EntityNotFoundException();
        }
        return address;
    }

    /**
     * Get main address costumer
     * @param customerId
     * @return
     * @throws EntityNotFoundException
     */
    public Address getByMaincustomer(Integer customerId) throws EntityNotFoundException {
        Address address = this.addressDao.getByMainCustomer(customerId);
        if (address == null) {
            throw new EntityNotFoundException();
        }
        return address;
    }

    /**
     * Validate address
     * @param address
     * @return
     * @throws BadEntityException
     */
    public boolean validade(Address address) throws BadEntityException {
        if (address == null) {
            throw new BadEntityException("Empty address.");
        }
        return true;
    }
}

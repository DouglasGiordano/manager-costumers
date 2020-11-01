package br.edu.compositebit.douglasgiordano.service;

import br.edu.compositebit.douglasgiordano.model.entities.Address;
import br.edu.compositebit.douglasgiordano.model.entities.exception.EntityNotFoundException;
import br.edu.compositebit.douglasgiordano.dao.AddressDao;
import com.google.inject.Inject;

import java.util.ArrayList;

public class AddressService extends SuperService<Address> {
    @Inject
    private AddressDao addressDao;

    @Override
    public Address add(Address entity) {
        this.updateNewMain(entity);
        int id = this.addressDao.insert(entity);
        entity.setId(id);
        return entity;
    }

    @Override
    public Address get(int id) {
        return this.addressDao.getById(id);
    }

    @Override
    public ArrayList<Address> get() {
        return (ArrayList<Address>) this.addressDao.list();
    }

    @Override
    public void delete(Integer id) {
        this.addressDao.delete(id);
    }

    public void delete(Integer costumerId, Integer addressId) throws EntityNotFoundException {
        Address address = this.addressDao.getByCostumer(costumerId, addressId);
        if(address == null){
            throw new EntityNotFoundException();
        }
        this.addressDao.delete(addressId);
    }

    @Override
    public Address update(Integer id, Address entity) {
        this.updateNewMain(entity);
        entity.setId(id);
        this.addressDao.update(entity);
        return entity;
    }

    public void updateNewMain(Address entity){
        if(entity.isMain()){
            Address addresMain = this.addressDao.getByMainCostumer(entity.getCostumer());
            addresMain.setMain(false);
            this.addressDao.update(addresMain);
        }
    }

    public ArrayList<Address> getByCostumer(Integer costumerId) {
        return (ArrayList<Address>) this.addressDao.listByCostumer(costumerId);
    }

    public Address getByCostumer(Integer costumerId, Integer addressId) throws EntityNotFoundException {
        Address address = this.addressDao.getByCostumer(costumerId, addressId);
        if(address == null){
            throw new EntityNotFoundException();
        }
        return address;
    }

    public Address getByMainCostumer(Integer costumerId) throws EntityNotFoundException {
        Address address = this.addressDao.getByMainCostumer(costumerId);
        if(address == null){
            throw new EntityNotFoundException();
        }
        return address;
    }
}

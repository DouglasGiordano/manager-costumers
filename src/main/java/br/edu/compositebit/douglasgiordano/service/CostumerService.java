package br.edu.compositebit.douglasgiordano.service;
import br.edu.compositebit.douglasgiordano.model.entities.Address;
import br.edu.compositebit.douglasgiordano.model.entities.Costumer;
import br.edu.compositebit.douglasgiordano.model.entities.exception.BadEntityException;
import br.edu.compositebit.douglasgiordano.model.entities.exception.EntityNotFoundException;
import br.edu.compositebit.douglasgiordano.dao.CostumerDao;
import br.edu.compositebit.douglasgiordano.dao.filter.CostumerFilter;
import com.google.inject.Inject;
import lombok.Data;
import lombok.extern.java.Log;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

@Data
@Log
public class CostumerService extends SuperService<Costumer> {
    @Inject
    private CostumerDao costumerDao;

    @Inject
    private AddressService addressesService;

    @Override
    public Costumer add(Costumer entity) throws BadEntityException {
        this.validCostumer(entity);
        log.info("Adding customer "+entity.getName()+".");
        int idCostumer = this.costumerDao.insert(entity);
        entity.setId(idCostumer);

        Address address = entity.getMainAddress();
        address.setCostumer(idCostumer);
        address = this.addressesService.add(address);
        entity.setMainAddress(address);
        entity.setAddresses(new ArrayList<>());
        entity.getAddresses().add(address);
        return entity;
    }

    @Override
    public Costumer get(int id) throws EntityNotFoundException {
        log.info("Searching customer "+id+".");
        Costumer c = this.costumerDao.getById(id);
        this.setAddress(c);
        if(c == null){
            throw new EntityNotFoundException();
        }
        return c;
    }

    @Override
    public ArrayList<Costumer> get() {
        return (ArrayList<Costumer>) this.costumerDao.list();
    }

    @Override
    public void delete(Integer id) {
        this.costumerDao.delete(id);
    }

    @Override
    public Costumer update(Integer id, Costumer entity) throws EntityNotFoundException, InvocationTargetException, IllegalAccessException, BadEntityException {
        this.validCostumer(entity);
        Address address = this.getAddressesService().getByMainCostumer(id);
        entity.getMainAddress().setCostumer(id);
        log.info("Updating address "+address.getId());
        this.addressesService.update(address.getId(), entity.getMainAddress());

        log.info("Updating customer "+entity.getId()+".");
        Costumer entityNow = get(id);
        entity.setId(id);
        entity.setAddresses(entityNow.getAddresses());
        this.costumerDao.update(entity);

        return entity;
    }

    public ArrayList<Costumer> get(CostumerFilter filter) {
        log.info("Searching customers.");
        ArrayList<Costumer> costumers = (ArrayList<Costumer>) this.costumerDao.listFilter(filter);
        for(Costumer c: costumers){
            setAddress(c);
        }
        return costumers;
    }

    private void setAddress(Costumer c){
        if(c != null){
            ArrayList<Address> addresses = this.addressesService.getByCostumer(c.getId());
            c.setAddresses(addresses);
            Address main;
            try{
                main = addresses.stream().filter(address -> address.isMain()).findFirst().get();
            }catch (NoSuchElementException ex){
                main = null;
            }
            c.setMainAddress(main);
        }
    }

    public boolean validCostumer(Costumer costumer) throws BadEntityException {
        if(costumer == null){
            throw new BadEntityException("Empty costumer.");
        } else if(costumer.getName() == null || costumer.getName().isEmpty()){
            throw new BadEntityException("Name costumer required.");
        } else if(!this.addressesService.validAddress(costumer.getMainAddress())){
            throw new BadEntityException("Main address costumer required.");
        }
        return true;
    }
}

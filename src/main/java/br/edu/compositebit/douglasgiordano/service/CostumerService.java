package br.edu.compositebit.douglasgiordano.service;
import br.edu.compositebit.douglasgiordano.model.entities.Costumer;
import br.edu.compositebit.douglasgiordano.repository.ConfigDao;
import br.edu.compositebit.douglasgiordano.repository.CostumerDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;
@Data
public class CostumerService extends SuperService<Costumer> {
    @Inject
    private ConfigDao configDao;


    @Override
    public Costumer add(Costumer entity) {
        return null;
    }

    @Override
    public Costumer get(int id) {
        return null;
    }

    @Override
    public ArrayList<Costumer> get() {
        List<Costumer> costumers = configDao.getJdbi().withExtension(CostumerDao.class, dao -> {
            return dao.list();
        });
        return (ArrayList<Costumer>) costumers;
    }

    @Override
    public void delete() {

    }

    @Override
    public Costumer update() {
        return null;
    }
}

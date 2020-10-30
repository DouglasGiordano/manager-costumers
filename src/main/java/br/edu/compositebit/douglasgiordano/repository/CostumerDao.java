package br.edu.compositebit.douglasgiordano.repository;

import br.edu.compositebit.douglasgiordano.model.entities.Costumer;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;

@RegisterBeanMapper(Costumer.class)
public interface CostumerDao extends CrudDao<Costumer, Integer>{

}

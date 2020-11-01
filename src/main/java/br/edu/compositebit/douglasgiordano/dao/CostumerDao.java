package br.edu.compositebit.douglasgiordano.dao;

import br.edu.compositebit.douglasgiordano.model.entities.Costumer;
import br.edu.compositebit.douglasgiordano.dao.filter.CostumerFilter;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

@RegisterBeanMapper(Costumer.class)
public interface CostumerDao extends CrudDao<Costumer, Integer>{

    @SqlQuery
    public List<Costumer> listFilter(@BindBean CostumerFilter filter);

}

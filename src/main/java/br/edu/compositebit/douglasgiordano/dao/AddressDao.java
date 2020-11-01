package br.edu.compositebit.douglasgiordano.dao;

import br.edu.compositebit.douglasgiordano.model.entities.Address;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.AllowUnusedBindings;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

@RegisterBeanMapper(value = Address.class)
@AllowUnusedBindings
public interface AddressDao extends CrudDao<Address, Integer>{

    @SqlQuery
    public List<Address> listByCostumer(@Bind("costumerId") long id);

    @SqlQuery
    public Address getByMainCostumer(@Bind("costumerId") long id);

    @SqlQuery
    public Address getByCostumer(@Bind("idCostumer") long idCostumer, @Bind("idAddress") long idAddress);
}

package br.edu.compositebit.douglasgiordano.dao;

import br.edu.compositebit.douglasgiordano.model.entities.Address;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.AllowUnusedBindings;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

/**
 * @author Douglas Montanha Giordano
 * Class responsible for manage table Address in database.
 */
@RegisterBeanMapper(value = Address.class)
@AllowUnusedBindings
public interface AddressDao extends CrudDao<Address, Integer> {

    /**
     * Search all address customer
     * Query defined in resources (br.edu.compositebit.douglasgiordano.dao...listByCustomer.sql)
     * @param id
     * @return list address by customer
     */
    @SqlQuery
    public List<Address> listByCustomer(@Bind("customerId") long id);

    /**
     * Search main address customer
     * Query defined in resources (br.edu.compositebit.douglasgiordano.dao...getByMainCustomer.sql)
     * @param id
     * @return address main customer
     */
    @SqlQuery
    public Address getByMainCustomer(@Bind("customerId") long id);

    /**
     * Search address (main or not) customer by ID customer and ID address
     * Query defined in resources (br.edu.compositebit.douglasgiordano.dao...getByCustomer.sql)
     * @param idCustomer
     * @param idAddress
     * @return address
     */
    @SqlQuery
    public Address getByCustomer(@Bind("idCustomer") long idCustomer, @Bind("idAddress") long idAddress);
}

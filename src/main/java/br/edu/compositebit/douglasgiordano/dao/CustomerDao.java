package br.edu.compositebit.douglasgiordano.dao;

import br.edu.compositebit.douglasgiordano.dao.filter.CustomerFilter;
import br.edu.compositebit.douglasgiordano.model.entities.Customer;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

/**
 * @author Douglas Montanha Giordano
 * Class responsible for manage table Customer in database.
 */
@RegisterBeanMapper(Customer.class)
public interface CustomerDao extends CrudDao<Customer, Integer> {

    /**
     * List all customers by filters
     * Query defined in resources (br.edu.compositebit.douglasgiordano.dao...listFilter.sql)
     * @param filter
     * @return customers filtered
     */
    @SqlQuery
    public List<Customer> listFilter(@BindBean CustomerFilter filter);

}

package br.edu.compositebit.douglasgiordano.dao;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

/**
 * @author Douglas Montanha Giordano
 * Super class responsible for encapsulation and definition of attributes and methods in common among DAO.
 */
@UseClasspathSqlLocator
public abstract interface CrudDao<T, ID> {
    /**
     * Add entity in database
     * Query defined in resources (br.edu.compositebit.douglasgiordano.dao...insert.sql)
     * @param entity
     * @return ID generated
     */
    @SqlUpdate
    @GetGeneratedKeys
    public int insert(@BindBean T entity);

    /**
     * Search entity by id
     * Query defined in resources (br.edu.compositebit.douglasgiordano.dao...getById.sql)
     * @param id
     * @return entity
     */
    @SqlQuery
    public T getById(@Bind("id") ID id);

    /**
     * Search all entities
     * Query defined in resources (br.edu.compositebit.douglasgiordano.dao...list.sql)
     * @return list entity
     */
    @SqlQuery
    public List<T> list();

    /**
     * Update entity in database
     * Query defined in resources (br.edu.compositebit.douglasgiordano.dao...update.sql)
     * @param entity
     */
    @SqlUpdate
    public void update(@BindBean T entity);

    /**
     * Delete entity in database by ID
     * Query defined in resources (br.edu.compositebit.douglasgiordano.dao...delete.sql)
     * @param id
     */
    @SqlUpdate
    public void delete(@Bind("id") ID id);
}

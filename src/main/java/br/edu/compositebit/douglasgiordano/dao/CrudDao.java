package br.edu.compositebit.douglasgiordano.dao;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@UseClasspathSqlLocator
public abstract interface CrudDao<T, ID> {
    @SqlUpdate
    public int insert(@BindBean T entity);

    @SqlQuery
    public T getById(@Bind("id") ID id);

    @SqlQuery
    public List<T> list();

    @SqlUpdate
    public void update(@BindBean T entity);

    @SqlUpdate
    public void delete(@Bind("id") ID id);
}

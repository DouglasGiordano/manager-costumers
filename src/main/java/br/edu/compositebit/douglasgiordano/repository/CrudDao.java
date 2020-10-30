package br.edu.compositebit.douglasgiordano.repository;

import br.edu.compositebit.douglasgiordano.model.entities.Costumer;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface CrudDao<T, ID> {
    @SqlUpdate
    @UseClasspathSqlLocator
    void insert(T entity);

    @SqlQuery
    @UseClasspathSqlLocator
    T getById(ID id);

    @SqlQuery
    @UseClasspathSqlLocator
    List<T> list();

    @SqlUpdate
    @UseClasspathSqlLocator
    void update(T entity);

    @SqlUpdate
    @UseClasspathSqlLocator
    void delete(ID id);
}

package br.edu.compositebit.douglasgiordano.repository;

import lombok.Data;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

/**
 * @author Douglas Montanha Giordano
 * Config JDBI
 */
@Data
public class ConfigDao {
    private Jdbi jdbi;

    public ConfigDao(){
        Jdbi jdbi = Jdbi.create("jdbc:mysql//localhost/costumermanagerdb");
        jdbi.installPlugin(new SqlObjectPlugin());
    }

}

package br.edu.compositebit.douglasgiordano.dao;

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
        this.jdbi = Jdbi.create("jdbc:mysql://localhost:3306/costumermanagerdb?useTimezone=true&serverTimezone=UTC", "root", "root");
        this.jdbi.installPlugin(new SqlObjectPlugin());
    }

}

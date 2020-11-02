package br.edu.compositebit.douglasgiordano.dao;

import lombok.Data;
import org.flywaydb.core.Flyway;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Douglas Montanha Giordano
 * Config JDBI
 */
@Data
public class ConfigDao {
    private Jdbi jdbi;
    private String username = "root";
    private String password = "root";
    private String database = "costumermanagerdbnew";

    public ConfigDao() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/"+database+"?useTimezone=true&serverTimezone=UTC";
        this.createDatabase();
        this.jdbi = Jdbi.create(url, username, password);
        this.jdbi.installPlugin(new SqlObjectPlugin());
        Flyway flyway = Flyway.configure().baselineOnMigrate(true).dataSource(url, username, password).load();
        flyway.migrate();
    }

    public void createDatabase() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mysql?useTimezone=true&serverTimezone=UTC";
        Connection connection = DriverManager.getConnection(url,username, password);
        String sql = "CREATE SCHEMA IF NOT EXISTS "+database+" DEFAULT CHARACTER SET utf8";

        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.close();
    }
}

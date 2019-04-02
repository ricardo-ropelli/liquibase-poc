package feature.liquibase.services;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class LiquibaseService {

    @Autowired
    private DataSource ds;

    public String update() {

        Connection c = null;
        Liquibase liquibase = null;
        try {
            c = ds.getConnection();

            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(c));
            liquibase = new Liquibase("changelogs/master-change-log.xml", new ClassLoaderResourceAccessor(), database);

            StringWriter sw = new StringWriter();
            liquibase.update("");

            return sw.getBuffer().toString();

        } catch (Exception e) {
            throw new RuntimeException();

        } finally {
            if (liquibase != null) {
                try {
                    liquibase.forceReleaseLocks();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (c != null) {
                try {
                    c.rollback();
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

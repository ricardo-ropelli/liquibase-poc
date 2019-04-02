package feature.liquibase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;


@SpringBootApplication
@EnableAutoConfiguration(exclude = LiquibaseAutoConfiguration.class)
public class LiquibaseApplication {

    @Transactional
    public static void main(String[] args) {
        SpringApplication.run(LiquibaseApplication.class, args);
    }
}

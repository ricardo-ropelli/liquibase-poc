package feature.liquibase.controllers;

import feature.liquibase.services.LiquibaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LiquibaseController {

    @Autowired
    private LiquibaseService liquibaseService;

    @PostMapping(path = "/")
    public void atualizaLiquibase() {
        liquibaseService.update();
    }
}

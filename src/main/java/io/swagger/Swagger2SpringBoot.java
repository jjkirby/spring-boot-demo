package io.swagger;

/*
 * Author: jkirby
 * Date: 7/22/18
 * Time: 11:41 AM
 * Description:  This class is used by spring to boot the application and populate  H2 database with sample data
 *
 */

import io.swagger.model.Employee;
import io.swagger.model.EmployeeRepository;
import io.swagger.model.User;
import io.swagger.model.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableJpaRepositories
@EnableSwagger2
@ComponentScan(basePackages = { "io.swagger", "io.swagger.api" })
public class Swagger2SpringBoot implements CommandLineRunner {

    @Override
    public void run(String... arg0) throws Exception {
        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Swagger2SpringBoot.class, args);
    }


    /**
     * Save employees to H2 DB for testing
     * @param repository
     * @return
     */
    @Bean
    public CommandLineRunner demo(EmployeeRepository repository, UserRepository urepository) {
        return (args) -> {

            // add someemployees

            repository.save(new Employee("Jon", "Jacobson", "", "ACTIVE", "1995-8-27", "2017-9-27"));
            repository.save(new Employee("Mary", "Smith", "K", "ACTIVE", "1985-10-21","2015-9-18"));
            repository.save(new Employee("Frank", "Jones", "K", "ACTIVE", "1990-7-21","2017-4-18"));

            // Create users with BCrypt encoded password (user/user, admin/admin)
            User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
            User user2 = new User("admin", "$2a$08$bCCcGjB03eulCWt3CY0AZew2rVzXFyouUolL5dkL/pBgFkUH9O4J2", "ADMIN");
            urepository.save(user1);
            urepository.save(user2);
        };
    }

    class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }

    }
}

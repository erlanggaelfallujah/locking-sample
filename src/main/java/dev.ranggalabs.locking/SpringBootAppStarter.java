package dev.ranggalabs.locking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

/**
 * Created by erlangga on 09/12/18.
 */
@SpringBootApplication
public class SpringBootAppStarter implements CommandLineRunner {
    @Autowired
    DataSource dataSource;

    /*
        TODO
          - select ... for update (DONE)
          - select count insert
          - optimistic locking
          - reactive
     */

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootAppStarter.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        System.out.println("DataSource = " + dataSource);
    }
}

package core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springdb.BooksRepository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@EnableJpaRepositories(basePackages = {"springdb"})
@EntityScan(basePackages = {"db"})
@SpringBootApplication
@RestController
public class Application implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    @Autowired
    private DataSource oracleDataSource;

    @Autowired
    private BooksRepository booksRepository;

    @RequestMapping("/resource")
    public Map<String, Object> home() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... arg0) throws Exception {
        LOGGER.info("Hello World!");

        String temporaryBuffer = (booksRepository.findByIsbn("0465026562") != null) ? booksRepository.findByIsbn("0465026562").getTitle() : "Value not found!";
        LOGGER.info(temporaryBuffer);
        Thread.sleep(100000);
    }

}

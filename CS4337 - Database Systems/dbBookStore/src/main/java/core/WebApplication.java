package core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springdb.BooksRepository;

import javax.sql.DataSource;

@EnableJpaRepositories(basePackages = {"springdb"})
@EntityScan(basePackages = {"db"})
@ServletComponentScan(basePackages = {"web/servlets"})
@ComponentScan(basePackages = {"web/controllers"})
@SpringBootApplication
public class WebApplication extends SpringBootServletInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebApplication.class);

    @Autowired
    private DataSource oracleDataSource;

    @Autowired
    private BooksRepository booksRepository;

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApplication.class);
    }

}

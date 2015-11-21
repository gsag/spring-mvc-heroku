package example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by guilherme on 21/11/15.
 */
@Configuration
@ComponentScan(basePackages = {"example"})
public class MainConfig {
    @Bean
    @Profile("dev")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://ec2-54-83-202-64.compute-1.amazonaws.com:5432/dbbfp37e4gg3qo");
        dataSource.setUsername("okavfpygwwyhsl");
        dataSource.setPassword("hMa896BLSRO0rpaVaNZcAXpp1l");
        return dataSource;
    }
}

package config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

/*
 * Created by guilherme on 24/11/15.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"config", "entity", "repository", "repository.service", "util"})
public class ProductionDatabaseConfiguration{

    @Autowired
    private Environment env;
    @Autowired
    private HibernateConfiguration hibernateConfiguration;

    private static final Logger logger = Logger.getLogger(ProductionDatabaseConfiguration.class);

    //    * Data Source Production
    @Bean(name = "dataSource")
    @Profile("production")
    public DataSource dataSource() throws URISyntaxException{
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.postgresql.Driver");
        URI dbUrl = new URI(env.getProperty("DATABASE_URL"));
        dataSource.setUrl("jdbc:postgresql://" + dbUrl.getHost() + ":" + dbUrl.getPort() + dbUrl.getPath());
        dataSource.setUsername(dbUrl.getUserInfo().split(":")[0]);
        dataSource.setPassword(dbUrl.getUserInfo().split(":")[1]);

        return dataSource;
    }

    //    * Session Factory
    @Bean(name = "sessionFactory")
    @Profile("production")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        try{
            sessionFactoryBean.setDataSource(dataSource());
        } catch (URISyntaxException uriex){
            logger.error(uriex.getMessage(),uriex);
        }finally {
            sessionFactoryBean.setPackagesToScan(hibernateConfiguration.getEntityManagerPackage());
            sessionFactoryBean.setHibernateProperties(hibernateConfiguration.getHibernateProperties());
        }
        return sessionFactoryBean;
    }

    //    * Entity Manager Factory
    @Bean(name = "entityManagerFactory")
    @Profile("production")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        try {
            em.setDataSource(dataSource());
        } catch (URISyntaxException uriex) {
            logger.error(uriex.getMessage(),uriex);
        } finally {
            em.setPackagesToScan(hibernateConfiguration.getEntityManagerPackage());
            em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
            em.setJpaProperties(hibernateConfiguration.getHibernateProperties());
        }
        return em;
    }
}

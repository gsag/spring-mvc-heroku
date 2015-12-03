package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Properties;

/*
 * Created by guilherme on 24/11/15.
 */
@Configuration
@PropertySource({"classpath:hibernate.properties"})
public class HibernateConfiguration {
    private final String PROPERTY_HIBERNATE_DIALECT = "hibernate.dialect";
    private final String PROPERTY_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private final String PROPERTY_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private final String PROPERTY_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private final String PROPERTY_ENTITYMANAGER_PACKAGE = "entitymanager.package";

    @Autowired
    private Environment env;

    public Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty(PROPERTY_HIBERNATE_HBM2DDL_AUTO, env.getRequiredProperty(PROPERTY_HIBERNATE_HBM2DDL_AUTO));
        properties.setProperty(PROPERTY_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_HIBERNATE_DIALECT));
        properties.setProperty(PROPERTY_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_HIBERNATE_SHOW_SQL));
        properties.setProperty(PROPERTY_HIBERNATE_FORMAT_SQL, env.getRequiredProperty(PROPERTY_HIBERNATE_FORMAT_SQL));
        return properties;
    }

    public String getEntityManagerPackage(){
        return env.getRequiredProperty(PROPERTY_ENTITYMANAGER_PACKAGE);
    }

    public String getHibernateDialect(){
        return env.getRequiredProperty(PROPERTY_HIBERNATE_DIALECT);
    }

    public Boolean getHibernateShowSql() {
        return Boolean.parseBoolean(env.getRequiredProperty(PROPERTY_HIBERNATE_SHOW_SQL));
    }

    public Boolean getHibernateFormatSql() {
        return Boolean.parseBoolean(env.getRequiredProperty(PROPERTY_HIBERNATE_FORMAT_SQL));
    }
}

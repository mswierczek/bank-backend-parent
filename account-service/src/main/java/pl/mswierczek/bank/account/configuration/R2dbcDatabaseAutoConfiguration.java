package pl.mswierczek.bank.account.configuration;

import java.sql.SQLException;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ResourceLoader;

import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import pl.mswierczek.bank.commons.annotation.InDevelopment;

@Configuration
@AutoConfigureAfter(R2dbcAutoConfiguration.class)
@ComponentScan(basePackageClasses = R2dbcProperties.class)
@Profile("r2dbc")
@InDevelopment
public class R2dbcDatabaseAutoConfiguration {

    @Bean
    public ClassLoaderResourceAccessor liquibaseR2dbcResourceAccessor(ResourceLoader resourceLoader) {
        return new ClassLoaderResourceAccessor(resourceLoader.getClassLoader());
    }

    @Bean
    public DataSource liquibaseR2dbcDataSource(R2dbcProperties r2dbcProperties) {
        var h2Properties = r2dbcProperties.getProperties().entrySet().stream()
            .map(entry -> entry.getKey().concat("=").concat(entry.getValue()))
            .collect(Collectors.joining(";"));
        return DataSourceBuilder.create()
            .username(r2dbcProperties.getUsername())
            .password(r2dbcProperties.getPassword())
            .url(r2dbcProperties.getUrl().replace("r2dbc:h2:mem:///", "jdbc:h2:mem:").concat(";").concat(h2Properties))
            .build();
    }

    @Bean(destroyMethod = "close")
    public JdbcConnection liquibaseR2dbcConnection(DataSource liquibaseR2dbcDataSource) throws SQLException {
        return new JdbcConnection(liquibaseR2dbcDataSource.getConnection());
    }

    @Bean(destroyMethod = "close")
    public Database liquibaseR2dbcDatabase(JdbcConnection liquibaseR2dbcJdbcConnection) throws DatabaseException {
        return DatabaseFactory.getInstance().findCorrectDatabaseImplementation(liquibaseR2dbcJdbcConnection);
    }
}

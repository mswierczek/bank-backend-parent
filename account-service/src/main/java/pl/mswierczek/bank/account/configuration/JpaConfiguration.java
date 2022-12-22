package pl.mswierczek.bank.account.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import pl.mswierczek.bank.account.AccountApplication;

@Configuration
@Profile("!r2dbc")
@EnableJpaRepositories(basePackageClasses = AccountApplication.class)
@EnableAutoConfiguration(exclude = R2dbcAutoConfiguration.class)
public class JpaConfiguration {

}

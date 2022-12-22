package pl.mswierczek.bank.account.persistence.repository.r2dbc;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import pl.mswierczek.bank.account.persistence.model.r2dbc.Customer;
import pl.mswierczek.bank.commons.annotation.InDevelopment;

@InDevelopment
@Profile("r2dbc")
public interface CustomerReactiveRepository extends ReactiveCrudRepository<Customer, Long> {

}

package pl.mswierczek.bank.account.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import pl.mswierczek.bank.account.api.model.CreateCustomerRequest;
import pl.mswierczek.bank.account.persistence.model.Customer;
import pl.mswierczek.bank.account.persistence.repository.CustomerRepository;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public Mono<Customer> save(CreateCustomerRequest request) {
        Customer customer = new Customer();
        customer.setBirthDate(LocalDate.now());
        customer.setFirstName(request.getFirstName());
        customer.setSecondName(request.getSecondName());
        customer.setLastName(request.getLastName());
        return Mono.just(customerRepository.save(customer));
    }

    public Mono<Optional<Customer>> findById(Long id) {
        return Mono.just(customerRepository.findById(id));
    }

}

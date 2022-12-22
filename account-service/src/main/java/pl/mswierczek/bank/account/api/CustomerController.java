package pl.mswierczek.bank.account.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.mswierczek.bank.account.api.model.CreateCustomerRequest;
import pl.mswierczek.bank.account.persistence.model.Customer;
import pl.mswierczek.bank.account.service.CustomerService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customer")
@Slf4j
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public Mono<Customer> create(@RequestBody CreateCustomerRequest request) {
        return customerService.save(request);
    }

}

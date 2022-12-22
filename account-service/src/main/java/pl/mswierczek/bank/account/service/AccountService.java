package pl.mswierczek.bank.account.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pl.mswierczek.bank.account.api.model.CreateCurrentAccountRequest;
import pl.mswierczek.bank.account.api.model.CreateCurrentAccountResponse;
import pl.mswierczek.bank.account.common.exception.CustomerNotFoundException;
import pl.mswierczek.bank.account.common.external.model.RegisterTransactionRequest;
import pl.mswierczek.bank.account.common.external.model.RegisterTransactionResponse;
import pl.mswierczek.bank.account.persistence.model.Account;
import pl.mswierczek.bank.account.persistence.model.Customer;
import pl.mswierczek.bank.account.persistence.repository.AccountRepository;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final IbanService ibanService;
    private final CustomerService customerService;
    private final AccountRepository accountRepository;
    private final TransactionServiceClient transactionServiceClient;

    public Mono<CreateCurrentAccountResponse> create(CreateCurrentAccountRequest request) {
        return customerService.findById(request.getCustomerId())
            .map(maybeCustomer -> maybeCustomer.orElseThrow(() -> new CustomerNotFoundException(request.getCustomerId())))
            .flatMap(customer -> prepareNewAccount(customer, request.getInitialCredit()))
            .map(accountRepository::save)
            .map(this::mapAccountToResponse)
            .map(response -> Tuples.of(response, RegisterTransactionRequest.builder()
                .iban(response.getIban())
                .balanceAfter(response.getInitialCredit())
                .balanceBefore(response.getInitialCredit())
                .operationType("OPEN_ACCOUNT")
                .build()))
            .map(tuple -> Tuples.of(tuple.getT1(), transactionServiceClient.register(tuple.getT2())))
            .flatMap(tuple -> tuple.getT2().map(response -> tuple.getT1().toBuilder().transaction(response).build()));
    }

    private CreateCurrentAccountResponse mapAccountToResponse(Account account) {
        return CreateCurrentAccountResponse.builder()
            .iban(account.getIban())
            .initialCredit(account.getBalance())
            .customerId(account.getCustomer().getId())
            .build();
    }

    private Mono<Account> prepareNewAccount(Customer customer, BigDecimal balance) {
        return Mono.just(new Account())
            .zipWith(ibanService.generateRandomIban())
            .flatMap(this::populateIban)
            .doOnNext(account -> account.setCustomer(customer))
            .doOnNext(account -> account.setBalance(balance));
    }

    private Mono<Account> populateIban(Tuple2<Account, String> accountAndIban) {
        var account = accountAndIban.getT1();
        var iban = accountAndIban.getT2();
        account.setIban(iban);
        return Mono.just(account);
    }
}

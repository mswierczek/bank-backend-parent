package pl.mswierczek.bank.transaction.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pl.mswierczek.bank.transaction.api.model.RegisterTransactionRequest;
import pl.mswierczek.bank.transaction.persistence.model.Transaction;
import pl.mswierczek.bank.transaction.service.TransactionService;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/register")
    public Mono<Transaction> register(@RequestBody RegisterTransactionRequest request) {
        return transactionService.register(request);
    }

}

package pl.mswierczek.bank.transaction.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pl.mswierczek.bank.transaction.api.model.RegisterTransactionRequest;
import pl.mswierczek.bank.transaction.persistence.model.Transaction;
import pl.mswierczek.bank.transaction.persistence.repository.TransactionRepository;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public Mono<Transaction> register(RegisterTransactionRequest request) {
        var transaction = new Transaction();
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setIban(request.getIban());
        transaction.setBalanceBefore(request.getBalanceBefore());
        transaction.setBalanceAfter(request.getBalanceAfter());
        transaction.setOperationType(request.getOperationType());
        return Mono.just(transactionRepository.save(transaction));
    }
}

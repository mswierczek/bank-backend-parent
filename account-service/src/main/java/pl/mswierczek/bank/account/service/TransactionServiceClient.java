package pl.mswierczek.bank.account.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import pl.mswierczek.bank.account.common.external.model.RegisterTransactionRequest;
import pl.mswierczek.bank.account.common.external.model.RegisterTransactionResponse;
import reactor.core.publisher.Mono;

@Component
public class TransactionServiceClient {

    private final WebClient transactionServiceWebClient;

    public TransactionServiceClient(@Value("${service.transaction.url}") String transactionServiceUrl) {
        this.transactionServiceWebClient = WebClient.builder()
            .baseUrl(transactionServiceUrl)
            .build();
    }

    public Mono<RegisterTransactionResponse> register(RegisterTransactionRequest request) {
        return transactionServiceWebClient
            .post()
            .uri("/register")
            .body(BodyInserters.fromValue(request))
            .retrieve()
            .bodyToMono(RegisterTransactionResponse.class);
    }
}

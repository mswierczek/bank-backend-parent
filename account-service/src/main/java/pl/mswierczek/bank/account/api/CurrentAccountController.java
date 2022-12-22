package pl.mswierczek.bank.account.api;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.mswierczek.bank.account.api.model.CreateCurrentAccountRequest;
import pl.mswierczek.bank.account.api.model.CreateCurrentAccountResponse;
import pl.mswierczek.bank.account.service.AccountService;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@Validated
@RequiredArgsConstructor
public class CurrentAccountController {

    private final AccountService currentAccountService;

    @PostMapping("/create")
    public Mono<CreateCurrentAccountResponse> createCurrentAccount(@RequestBody @Valid CreateCurrentAccountRequest request) {
        log.info("Incoming {}: [{}]", CreateCurrentAccountRequest.class.getName(), request);
        return currentAccountService.create(request);
    }

}

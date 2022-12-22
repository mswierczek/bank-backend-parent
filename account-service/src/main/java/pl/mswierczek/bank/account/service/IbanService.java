package pl.mswierczek.bank.account.service;

import java.util.Arrays;

import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class IbanService {

    private final CountryCode resolvedCountryCode;

    private final String bankCode;

    public IbanService(@Value("${iban.country-code}") String countryCode, @Value("${iban.bank-code}") String bankCode) {
        this.resolvedCountryCode = Arrays.stream(CountryCode.values())
            .filter(countryCodeValue -> countryCodeValue.toString().equals(countryCode))
            .findFirst()
            .orElseThrow(RuntimeException::new);
        this.bankCode = bankCode;
    }

    public Mono<String> generateRandomIban() {
        return Mono.just(new Iban.Builder()
            .countryCode(resolvedCountryCode)
            .bankCode(bankCode)
            .buildRandom()
            .toString());
    }
}

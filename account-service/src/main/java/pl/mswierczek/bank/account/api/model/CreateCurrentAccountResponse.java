package pl.mswierczek.bank.account.api.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import pl.mswierczek.bank.account.common.external.model.RegisterTransactionResponse;

@Getter
@Builder(toBuilder = true)
public class CreateCurrentAccountResponse {
    private BigDecimal initialCredit;
    private String iban;
    private Long customerId;
    private RegisterTransactionResponse transaction;
}

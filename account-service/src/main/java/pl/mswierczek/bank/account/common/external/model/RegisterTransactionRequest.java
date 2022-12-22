package pl.mswierczek.bank.account.common.external.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class RegisterTransactionRequest {
    private String iban;
    private String operationType;
    private BigDecimal balanceBefore;
    private BigDecimal balanceAfter;
}

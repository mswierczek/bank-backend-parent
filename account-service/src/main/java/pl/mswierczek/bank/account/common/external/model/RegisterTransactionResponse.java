package pl.mswierczek.bank.account.common.external.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterTransactionResponse {
    private Long id;
    private String iban;
    private String operationType;
    private BigDecimal balanceBefore;
    private BigDecimal balanceAfter;
    private LocalDateTime timestamp;
}

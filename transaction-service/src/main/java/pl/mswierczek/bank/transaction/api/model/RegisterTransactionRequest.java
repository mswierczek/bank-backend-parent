package pl.mswierczek.bank.transaction.api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.mswierczek.bank.transaction.persistence.model.OperationType;

@Getter
@Setter
@ToString
public class RegisterTransactionRequest {
    private String iban;
    private OperationType operationType;
    private BigDecimal balanceBefore;
    private BigDecimal balanceAfter;
}

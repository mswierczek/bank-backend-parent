package pl.mswierczek.bank.account.api.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateCurrentAccountRequest {
    @NotNull
    private Long customerId;
    @InitialCreditConstraint
    @NotNull
    private BigDecimal initialCredit;
}

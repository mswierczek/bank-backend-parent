package pl.mswierczek.bank.account.api.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateCustomerRequest {
    private String firstName;
    private String lastName;
    private String secondName;
    private LocalDate birthDate;
}

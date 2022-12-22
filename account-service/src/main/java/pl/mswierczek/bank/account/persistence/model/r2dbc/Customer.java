package pl.mswierczek.bank.account.persistence.model.r2dbc;

import java.time.LocalDate;

import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import pl.mswierczek.bank.commons.annotation.InDevelopment;

@Table
@Getter
@Setter
@InDevelopment
public class Customer {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String secondName;
    private LocalDate birthDate;
}

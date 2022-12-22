package pl.mswierczek.bank.account.api.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = InitialCreditValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InitialCreditConstraint {
    String message() default "Validation of the field [initialCredit] failed";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

package pl.mswierczek.bank.account.api.model;

import java.math.BigDecimal;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class InitialCreditValidator implements ConstraintValidator<InitialCreditConstraint, BigDecimal> {

    private final String INITIAL_CREDIT_FIELD_NAME = "initialCredit";

    @Override
    public void initialize(InitialCreditConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    private int extractDecimalPlacesAmount(BigDecimal initialCredit) {
        return Math.max(0, initialCredit.stripTrailingZeros().scale());
    }

    @Override
    public boolean isValid(BigDecimal initialCredit, ConstraintValidatorContext constraintValidatorContext) {
        validateAtMostTwoDecimalPlaces(initialCredit);
        validateNotNegative(initialCredit);
        return true;
    }

    private void validateNotNegative(BigDecimal initialCredit) {
        if (initialCredit.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationException(INITIAL_CREDIT_FIELD_NAME, "value must be grater than or equal to 0");
        }
    }

    private void validateAtMostTwoDecimalPlaces(BigDecimal initialCredit) {
        if (extractDecimalPlacesAmount(initialCredit) > 2) {
            throw new ValidationException(INITIAL_CREDIT_FIELD_NAME,
                "Invalid initial credit provided: expected value greater than or equal 0 with with at most two decimal places");
        }
    }

}

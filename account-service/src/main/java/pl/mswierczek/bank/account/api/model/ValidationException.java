package pl.mswierczek.bank.account.api.model;

public class ValidationException extends RuntimeException {

    private static final String ERROR_MESSAGE_PATTERN = "There was an error during the validation of the field [%s]. Detailed error message: %s";

    public ValidationException(String fieldName, String validationErrorMessage) {
        super(ERROR_MESSAGE_PATTERN.formatted(fieldName, validationErrorMessage));
    }

}

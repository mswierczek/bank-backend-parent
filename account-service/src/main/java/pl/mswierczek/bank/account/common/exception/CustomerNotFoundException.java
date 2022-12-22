package pl.mswierczek.bank.account.common.exception;

public class CustomerNotFoundException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Customer with id [%d] not found";

    public CustomerNotFoundException(Long customerId) {
        super(ERROR_MESSAGE.formatted(customerId));
    }
}

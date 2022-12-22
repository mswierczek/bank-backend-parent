package pl.mswierczek.bank.account.api;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import pl.mswierczek.bank.account.api.model.GenericErrorResponse;
import pl.mswierczek.bank.account.api.model.InvalidRequestErrorResponse;
import pl.mswierczek.bank.account.api.model.ValidationException;
import pl.mswierczek.bank.account.common.exception.CustomerNotFoundException;

@RestController
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public InvalidRequestErrorResponse handleCustomValidationFailure(ValidationException validationException, HttpServletRequest request) {
        return InvalidRequestErrorResponse.builder()
            .responseCode(HttpStatus.BAD_REQUEST.value())
            .path(request.getContextPath().concat(request.getServletPath()))
            .message(validationException.getMessage())
            .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public InvalidRequestErrorResponse handleNativeValidationFailure(MethodArgumentNotValidException exception, HttpServletRequest request) {
        var fieldName = Objects.requireNonNull(exception.getFieldError()).getField();
        var errorDetailsMessage = exception.getFieldError().getDefaultMessage();
        var errorMessage = "Unable to validate the field: [%s]. Error detail: %s".formatted(fieldName, errorDetailsMessage);
        return InvalidRequestErrorResponse.builder()
            .responseCode(HttpStatus.BAD_REQUEST.value())
            .path(request.getContextPath().concat(request.getServletPath()))
            .message(errorMessage)
            .build();
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GenericErrorResponse handleCustomerNotFound(CustomerNotFoundException exception, HttpServletRequest request) {
        return GenericErrorResponse.builder()
            .responseCode(HttpStatus.NOT_FOUND.value())
            .message(exception.getMessage())
            .build();
    }
}

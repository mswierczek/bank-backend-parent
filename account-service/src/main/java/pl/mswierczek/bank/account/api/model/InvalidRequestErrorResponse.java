package pl.mswierczek.bank.account.api.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class InvalidRequestErrorResponse extends GenericErrorResponse {
    private String path;
}

package com.java.gestaoemprestimopix.exception;

/**
 * Exceção genérica para toda a aplicação.
 * Pode representar desde erros de validação até falhas de regra de negócio.
 */
public class ApplicationException extends RuntimeException {

    private final String errorCode; // Ex: "VALIDATION_ERROR", "DATABASE_ERROR"
    private final int httpStatus;   // Código HTTP associado (400, 404, 500 etc)

    public ApplicationException(String message) {
        super(message);
        this.errorCode = "APPLICATION_ERROR";
        this.httpStatus = 500;
    }

    public ApplicationException(String message, String errorCode, int httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}
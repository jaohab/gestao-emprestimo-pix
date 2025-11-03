package com.java.gestaoemprestimopix.exception;

/**
 * Exceção personalizada para entidades não encontradas no banco de dados.
 * Pode ser usada em qualquer camada de serviço.
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

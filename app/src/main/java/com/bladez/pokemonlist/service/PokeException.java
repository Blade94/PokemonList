package com.bladez.pokemonlist.service;

public class PokeException extends Exception {

    private String errorMessage;

    public PokeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public PokeException(String message, String errorMessage) {
        super(message);
        this.errorMessage = errorMessage;
    }

    public PokeException(String message, Throwable cause, String errorMessage) {
        super(message, cause);
        this.errorMessage = errorMessage;
    }

    public PokeException(Throwable cause, String errorMessage) {
        super(cause);
        this.errorMessage = errorMessage;
    }

    public PokeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorMessage) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorMessage = errorMessage;
    }
}

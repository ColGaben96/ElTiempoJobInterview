package com.eltiempo.bl.utils.exception;

public class AlreadyExistsException extends Exception {
    public AlreadyExistsException() {
        super("The entity you are searching already exists");
    }
}

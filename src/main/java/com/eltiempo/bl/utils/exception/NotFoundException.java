package com.eltiempo.bl.utils.exception;

public class NotFoundException extends Exception {
    public NotFoundException() {
        super("The entity you are searching does not exist");
    }
}

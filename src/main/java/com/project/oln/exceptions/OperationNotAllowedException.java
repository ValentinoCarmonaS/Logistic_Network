package com.project.oln.exceptions;

public class OperationNotAllowedException extends RuntimeException{
    public OperationNotAllowedException(String err) {
        super(err);
    }
}

package com.mycompany.exception;

public class CompanyDaoException extends RuntimeException{
    public CompanyDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}

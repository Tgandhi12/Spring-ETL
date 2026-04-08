package com.example.employeeetlspringboot.exception;

public class InvalidEmployeeException extends RuntimeException{
    public InvalidEmployeeException(String message){
        super(message);
    }
}

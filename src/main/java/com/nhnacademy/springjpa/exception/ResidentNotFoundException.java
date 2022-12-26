package com.nhnacademy.springjpa.exception;

public class ResidentNotFoundException extends RuntimeException {
    public ResidentNotFoundException(Long serialNumber) {
        super("Resident doesn't exist : Serial Number = " + serialNumber);
    }
}

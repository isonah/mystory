package com.isonah.usermanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static java.time.LocalDateTime.now;

/**
 * @author ielksseyer
 */
public class MyStoryException extends RuntimeException {

    public MyStoryException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.status = httpStatus.value();

    }

    private LocalDateTime localDateTime = now();

    private String message;

    private int status;

    private String ticketId;

    private String origin;

    private String path;

    private Set<FieldError> fieldErrors = new HashSet<>();

    public String getTicketId() {
        return ticketId;
    }

    public String getOrigin() {
        return origin;
    }

    public Set<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public MyStoryException setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
        return this;
    }

    public String getPath() {
        return path;
    }

    public MyStoryException setPath(String path) {
        this.path = path;
        return this;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public MyStoryException setMessage(String message) {
        this.message = message;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public MyStoryException setStatus(int status) {
        this.status = status;
        return this;
    }

    public MyStoryException setTicketId(String ticketId) {
        this.ticketId = ticketId;
        return this;
    }

    public MyStoryException setOrigin(String origin) {
        this.origin = origin;
        return this;
    }

    public MyStoryException setFieldErrors(Set<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
        return this;
    }
}


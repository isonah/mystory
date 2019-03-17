package com.isonah.usermanagement.exception;

import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ielksseyer
 */
public class MyStoryExceptionDTO {

    private String message;

    private String ticketId;

    private String origin;

    private Set<FieldError> fieldErrors = new HashSet<>();

    private LocalDateTime localDateTime;

    private String path;

    private int status;

    public MyStoryExceptionDTO setInternalMessage(String internalMessage) {
        this.message = internalMessage;
        return this;
    }

    public String getTicketId() {
        return ticketId;
    }

    public MyStoryExceptionDTO setTicketId(String ticketId) {
        this.ticketId = ticketId;
        return this;
    }

    public String getOrigin() {
        return origin;
    }

    public MyStoryExceptionDTO setOrigin(String origin) {
        this.origin = origin;
        return this;
    }

    public Set<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public MyStoryExceptionDTO setFieldErrors(Set<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public MyStoryExceptionDTO setMessage(String message) {
        this.message = message;
        return this;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public MyStoryExceptionDTO setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public MyStoryExceptionDTO setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getPath() {
        return path;
    }

    public MyStoryExceptionDTO setPath(String path) {
        this.path = path;
        return this;
    }

    public static MyStoryExceptionDTO map(MyStoryException e) {
        return new MyStoryExceptionDTO().setLocalDateTime(e.getLocalDateTime())
                                        .setFieldErrors(e.getFieldErrors())
                                        .setInternalMessage(e.getMessage())
                                        .setOrigin(e.getOrigin())
                                        .setTicketId(e.getTicketId())
                                        .setStatus(e.getStatus())
                                        .setPath(e.getPath());

    }
}

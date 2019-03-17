package com.isonah.usermanagement.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Stream;

/**
 * @author ielksseyer
 */
@ControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class ExceptionHandler {

    Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @Value("${spring.application.name:unknown}")
    private String origin;

    @org.springframework.web.bind.annotation.ExceptionHandler(MyStoryException.class)
    public ResponseEntity<MyStoryExceptionDTO> notFoundException(HttpServletRequest request, final MyStoryException exception) {
        return Stream.of(exception)
                     .peek(e -> e.setOrigin(origin))
                     .peek(e -> e.setPath(request.getContextPath().concat(request.getServletPath())))
                     .map(MyStoryExceptionDTO::map)
                     .map(e -> new ResponseEntity<>(e, HttpStatus.valueOf(exception.getStatus())))
                     .findFirst().orElseThrow(null);
    }

}

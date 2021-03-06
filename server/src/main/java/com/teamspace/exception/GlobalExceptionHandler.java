package com.teamspace.exception;

import com.teamspace.dto.ExceptionResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({OauthException.class, AccessTokenNotFoundException.class,
            JWTTokenException.class, UserNotFoundException.class, UserIllegalException.class})
    protected ResponseEntity handleException(Exception e) {
        logger.error(e.getMessage());
        return new ResponseEntity(new ExceptionResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}

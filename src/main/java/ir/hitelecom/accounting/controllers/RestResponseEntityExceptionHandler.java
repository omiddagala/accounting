package ir.hitelecom.accounting.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.List;

@PropertySource(value = "classpath:messages.properties", encoding = "UTF-8")
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private Environment env;

    @ExceptionHandler(value = RuntimeException.class)
    protected ResponseEntity<Object> handleBusinessException(RuntimeException exception, WebRequest request) {
        List<String> exceptions = Arrays.asList("productCountOutOfRage");
        if (exceptions.contains(exception.getMessage()))
            return handleExceptionInternal(exception, getErrorMessage(exception.getMessage()), new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
        else if (exception.getCause().getCause().toString().contains("Detail: Key (mobile)=("))
            return handleExceptionInternal(exception, getErrorMessage("foundMobile"), new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
        else if (exception.getCause().getCause().toString().contains("Detail: Key (national_code)=("))
            return handleExceptionInternal(exception, getErrorMessage("foundNationalCode"), new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
        throw exception;
    }

    @ExceptionHandler(value = NullPointerException.class)
    protected ResponseEntity<Object> handleBusinessException(NullPointerException exception, WebRequest request) {
        List<String> exceptions = Arrays.asList("productNotFound", "customerNotFound");
        if (exceptions.contains(exception.getMessage()))
            return handleExceptionInternal(exception, getErrorMessage(exception.getMessage()), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
        throw exception;
    }

    protected String getErrorMessage(String key) {
        return env.getProperty(key);
    }
}

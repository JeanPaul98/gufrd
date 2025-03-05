package com.acl.vbs.handlers;

import com.acl.vbs.exceptions.*;
import com.acl.vbs.responses.ErrorFieldResponse;
import com.acl.vbs.responses.ErrorResponse;
import com.acl.vbs.responses.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class ExceptionsHandler implements ErrorController {

    private static final String ACCOUNT_LOCKED = "Vôtre compte a été bloqué. Veuillez contacter l'administration";
    private static final String METHOD_IS_NOT_ALLOWED = "Cette méthode de demande n'est pas autorisée sur ce point de terminaison. Veuillez envoyer une demande %s";
    private static final String INCORRECT_CREDENTIALS = "Nom d'utilisateur/mot de passe incorrect. Veuillez réessayer";
    private static final String ACCOUNT_DISABLED = "Votre compte a été désactivé. S'il s'agit d'une erreur, veuillez contacter l'administration";
    private static final String ERROR_PROCESSING_FILE = "Une erreur s'est produite lors du traitement du fichier";
    private static final String NOT_ENOUGH_PERMISSION = "Vous n'avez pas assez d'autorisation";

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<HttpResponse> accountDisabledException() {
        return createHttpResponse(BAD_REQUEST, ACCOUNT_DISABLED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<HttpResponse> badCredentialsException() {
        return createHttpResponse(BAD_REQUEST, INCORRECT_CREDENTIALS);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<HttpResponse> accessDeniedException() {
        return createHttpResponse(FORBIDDEN, NOT_ENOUGH_PERMISSION);
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<HttpResponse> lockedException() {
        return createHttpResponse(UNAUTHORIZED, ACCOUNT_LOCKED);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<HttpResponse> noHandlerFoundException(NoHandlerFoundException exception) {
        log.error(exception.getMessage());
        return createHttpResponse(BAD_REQUEST, "This page was not found");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HttpResponse> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        System.out.println(exception.getAllErrors());
        return createValidationHttpResponse(exception.getFieldErrors());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<HttpResponse> methodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        HttpMethod httpMethod = Objects.requireNonNull(exception.getSupportedHttpMethods()).iterator().next();
        return createHttpResponse(METHOD_NOT_ALLOWED, String.format(METHOD_IS_NOT_ALLOWED, httpMethod));
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<HttpResponse> ioException(IOException exception) {
        log.error(exception.getMessage());
        return createHttpResponse(INTERNAL_SERVER_ERROR, ERROR_PROCESSING_FILE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpResponse> internalServerErrorException(Exception exception) {
        log.error(exception.getMessage());
        return createHttpResponse(INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<HttpResponse> internalServerErrorException(RuntimeException exception) {
        log.error(exception.getMessage());
        return createHttpResponse(INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    @ExceptionHandler(CamionNotFoundException.class)
    public ResponseEntity<HttpResponse> truckNotFoundException(CamionNotFoundException exception) {
        log.error(exception.getMessage());
        return createHttpResponse(NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(PaysNotFoundException.class)
    public ResponseEntity<HttpResponse> countryNotFoundException(PaysNotFoundException exception) {
        log.error(exception.getMessage());
        return createHttpResponse(NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<HttpResponse> unauthorizedAccessException(UnauthorizedAccessException exception) {
        log.error(exception.getMessage());
        return createHttpResponse(FORBIDDEN, exception.getMessage());
    }

    @ExceptionHandler(CamionAlreadyExistsException.class)
    public ResponseEntity<HttpResponse> camionAlreadyExistsException(CamionAlreadyExistsException exception) {
        log.error(exception.getMessage());
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(BonEntreeCamionNotFoundException.class)
    public ResponseEntity<HttpResponse> bonEntreeCamionNotFoundException(BonEntreeCamionNotFoundException exception) {
        log.error(exception.getMessage());
        return createHttpResponse(NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(TransitaireNotFoundException.class)
    public ResponseEntity<HttpResponse> transitaireNotFoundException(TransitaireNotFoundException exception) {
        log.error(exception.getMessage());
        return createHttpResponse(NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(TransporteurNotFoundException.class)
    public ResponseEntity<HttpResponse> transporterNotFoundException(TransporteurNotFoundException exception) {
        log.error(exception.getMessage());
        return createHttpResponse(NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(BecLigneMseNotEmptyException.class)
    public ResponseEntity<HttpResponse> becLigneMseNotEmptyException(BecLigneMseNotEmptyException exception) {
        log.error(exception.getMessage());
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(AireStockageNotFoundException.class)
    public ResponseEntity<HttpResponse> truckNotFoundException(AireStockageNotFoundException exception) {
        log.error(exception.getMessage());
        return createHttpResponse(NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(SensTraficNotFoundException.class)
    public ResponseEntity<HttpResponse> countryNotFoundException(SensTraficNotFoundException exception) {
        log.error(exception.getMessage());
        return createHttpResponse(NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(SiteNotFoundException.class)
    public ResponseEntity<HttpResponse> transporterNotFoundException(SiteNotFoundException exception) {
        log.error(exception.getMessage());
        return createHttpResponse(NOT_FOUND, exception.getMessage());
    }

    protected ResponseEntity<HttpResponse> createHttpResponse(HttpStatus status, String message) {
        HttpResponse response = ErrorResponse.builder().reason(status.getReasonPhrase()).build();
        response.setStatus(status.value());
        response.setMessage(message);
        return new ResponseEntity<>(response, status);
    }

    protected ResponseEntity<HttpResponse> createValidationHttpResponse(List<FieldError> errors) {
        List<ErrorFieldResponse> fieldResponses = errors.stream()
                .map(fieldError -> {
                    ErrorFieldResponse response = new ErrorFieldResponse();
                    BeanUtils.copyProperties(fieldError, response);
                    return response;
                }).toList();
        HttpResponse response = ErrorResponse.builder().reason(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .validations(fieldResponses).build();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage("Validation errors");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
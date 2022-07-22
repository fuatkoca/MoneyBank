package com.tpebank.exception;
import com.tpebank.exception.message.ApiResponseError;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TpeBankExceptionHandler extends ResponseEntityExceptionHandler {
    private ResponseEntity<Object> buildResponseEntity(ApiResponseError error){
        return new ResponseEntity<>(error,error.getStatus());
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
        ApiResponseError error=new ApiResponseError(HttpStatus.NOT_FOUND,ex.getMessage(),request.getDescription(false));
        return buildResponseEntity(error);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiResponseError error=new ApiResponseError(status,ex.getMessage(),request.getDescription(false));
        return buildResponseEntity(error);
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiResponseError error=new ApiResponseError(status,ex.getMessage(),request.getDescription(false));
        return buildResponseEntity(error);
    }

}

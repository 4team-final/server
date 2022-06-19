package com.douzone.server.exception;

import com.douzone.server.config.utils.ErrorResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({EmpAlreadyExistException.class})
    public ResponseEntity<ErrorResponseDTO> EmpAlreadyExistException(EmpAlreadyExistException e) {
        return CustomExceptionReturn.returnException(e);
    }

    @ExceptionHandler({EmpNotFoundException.class})
    public ResponseEntity<ErrorResponseDTO> EmpNotExistException(EmpAlreadyExistException e) {
        return CustomExceptionReturn.returnException(e);
    }
    @ExceptionHandler({reservationNotFoundException.class})
    public ResponseEntity<ErrorResponseDTO> ResNotExistException(reservationNotFoundException e) {
        return CustomExceptionReturn.returnException(e);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValidException(BindException e) {
        log.error("MethodArgumentNotValidException : ", e.getMessage());
        ErrorResponseDTO response = new ErrorResponseDTO(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DshareServerException.class)
    public ResponseEntity<ErrorResponseDTO> serverExceptionHandler(DshareServerException e) {
        return new CustomExceptionReturn().returnException(e);
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    protected ResponseEntity<ErrorResponseDTO> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("HttpRequestMethodNotSupportedException : ", e.getMessage());
        ErrorResponseDTO response = new ErrorResponseDTO(ErrorCode.METHOD_NOT_ALLOWED);
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<ErrorResponseDTO> MissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("MissingServletRequestParameterException : ", e.getMessage());
        ErrorResponseDTO response = new ErrorResponseDTO(ErrorCode.MISSING_REQUEST_PARAMETER);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorResponseDTO> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("MethodArgumentTypeMismatchException : ", e.getMessage());
        ErrorResponseDTO response = new ErrorResponseDTO(ErrorCode.TYPE_MISMATCH);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorResponseDTO> handleMaxUploadSizeExceedException(MaxUploadSizeExceededException e) {
        log.error(String.format("MaxUploadSizeExceededException %s", e.getMessage()), e);
        ErrorResponseDTO response = new ErrorResponseDTO(ErrorCode.MAX_UPLOAD_SIZE_EXCEEDED);
        return new ResponseEntity<>(response, HttpStatus.PAYLOAD_TOO_LARGE);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponseDTO> handleException(final Exception e) {
        log.error("Exception : " + e.getMessage());
        ErrorResponseDTO response = new ErrorResponseDTO(ErrorCode.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

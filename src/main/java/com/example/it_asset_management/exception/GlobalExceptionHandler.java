package com.example.it_asset_management.exception;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.example.it_asset_management.dto.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
		var fieldErrors = ex.getBindingResult().getFieldErrors().stream()
			.map(fe -> new ErrorResponse.FieldError(fe.getField(), fe.getDefaultMessage()))
			.toList();
		return new ErrorResponse(LocalDateTime.now(), 400, "Bad Request",
			"Validation failed — see fieldErrors for details", fieldErrors, request.getRequestURI());
	}

	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handleNotFound(NoSuchElementException ex, HttpServletRequest request) {
		return new ErrorResponse(LocalDateTime.now(), 404, "Not Found",
			ex.getMessage(), null, request.getRequestURI());
	}

	@ExceptionHandler(DuplicateResourceException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ErrorResponse handleDuplicate(DuplicateResourceException ex, HttpServletRequest request) {
		return new ErrorResponse(LocalDateTime.now(), 409, "Conflict",
			ex.getMessage(), null, request.getRequestURI());
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleUnreadable(HttpMessageNotReadableException ex, HttpServletRequest request) {
		String message = "Malformed request body — check the JSON syntax and value formats";
		Throwable cause = ex.getMostSpecificCause();
		if (cause instanceof IllegalArgumentException && cause.getMessage() != null) {
			message = cause.getMessage();
		}
		return new ErrorResponse(LocalDateTime.now(), 400, "Bad Request", message, null, request.getRequestURI());
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleTypeMismatch(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
		return new ErrorResponse(LocalDateTime.now(), 400, "Bad Request",
			"Invalid value '" + ex.getValue() + "' for parameter '" + ex.getParameter().getParameterName() + "'",
			null, request.getRequestURI());
	}

	@ExceptionHandler(NoResourceFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handleNoResource(NoResourceFoundException ex, HttpServletRequest request) {
		return new ErrorResponse(LocalDateTime.now(), 404, "Not Found",
			"No endpoint at " + request.getRequestURI(), null, request.getRequestURI());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handleUnexpected(Exception ex, HttpServletRequest request) {
		log.error("Unexpected error serving {}", request.getRequestURI(), ex);
		return new ErrorResponse(LocalDateTime.now(), 500, "Internal Server Error",
			"An unexpected error occurred — please try again later", null, request.getRequestURI());
	}
}

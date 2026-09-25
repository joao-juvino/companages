package com.companages.exception;
import jakarta.servlet.http.HttpServletRequest; import org.springframework.dao.DataIntegrityViolationException; import org.springframework.http.*; import org.springframework.security.authentication.BadCredentialsException; import org.springframework.web.bind.MethodArgumentNotValidException; import org.springframework.web.bind.annotation.*; import java.time.Instant; import java.util.*;
@RestControllerAdvice
public class GlobalExceptionHandler {
 @ExceptionHandler(ResourceNotFoundException.class) ResponseEntity<ApiError> notFound(RuntimeException e,HttpServletRequest r){return error(HttpStatus.NOT_FOUND,e.getMessage(),r,Map.of());}
 @ExceptionHandler({BusinessException.class,DataIntegrityViolationException.class}) ResponseEntity<ApiError> conflict(Exception e,HttpServletRequest r){return error(HttpStatus.CONFLICT,e instanceof BusinessException?e.getMessage():"Resource conflicts with existing data",r,Map.of());}
 @ExceptionHandler(EmailAlreadyExistsException.class) ResponseEntity<ApiError> email(RuntimeException e,HttpServletRequest r){return error(HttpStatus.CONFLICT,e.getMessage(),r,Map.of());}
 @ExceptionHandler(BadCredentialsException.class) ResponseEntity<ApiError> credentials(RuntimeException e,HttpServletRequest r){return error(HttpStatus.UNAUTHORIZED,"Invalid email or password",r,Map.of());}
 @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<ApiError> validation(MethodArgumentNotValidException e,HttpServletRequest r){Map<String,String> fields=new LinkedHashMap<>(); e.getBindingResult().getFieldErrors().forEach(x->fields.putIfAbsent(x.getField(),x.getDefaultMessage())); return error(HttpStatus.BAD_REQUEST,"Validation failed",r,fields);}
 @ExceptionHandler(Exception.class) ResponseEntity<ApiError> unexpected(Exception e,HttpServletRequest r){return error(HttpStatus.INTERNAL_SERVER_ERROR,"An unexpected error occurred",r,Map.of());}
 private ResponseEntity<ApiError> error(HttpStatus s,String m,HttpServletRequest r,Map<String,String> v){return ResponseEntity.status(s).body(new ApiError(Instant.now(),s.value(),s.getReasonPhrase(),m,r.getRequestURI(),v));}
}

package dev.wonsama.issue.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class IssueSystemExceptionHandler {
  @ExceptionHandler(IssueSystemException.class)
  protected ResponseEntity<IssueSystemResponse> handleCustomException(IssueSystemException e) {
    return IssueSystemResponse.toResponseEntity(e.getErrorCode());
  }
}

package dev.wonsama.issue.exception;

import org.springframework.http.ResponseEntity;

import dev.wonsama.issue.enums.IssueSystemErrorCode;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IssueSystemResponse {

  private int status;
  private String code;
  private String message;

  public static ResponseEntity<IssueSystemResponse> toResponseEntity(IssueSystemErrorCode e) {
    return ResponseEntity
        .status(e.getHttpStatus())
        .body(IssueSystemResponse.builder()
            .status(e.getHttpStatus().value())
            .code(e.name())
            .message(e.getMessage())
            .build());
  }

}

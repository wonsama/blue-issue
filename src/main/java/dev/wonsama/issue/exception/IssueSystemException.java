package dev.wonsama.issue.exception;

import dev.wonsama.issue.enums.IssueSystemErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class IssueSystemException extends RuntimeException {
  IssueSystemErrorCode errorCode;
}

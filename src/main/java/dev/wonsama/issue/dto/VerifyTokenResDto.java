package dev.wonsama.issue.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VerifyTokenResDto {
  private boolean isValid;
  private String cause;
  private String cardNo;
  private String ci;

  @Builder
  public VerifyTokenResDto(boolean isValid, String cause, String ci, String cardNo) {
    this.isValid = isValid;
    this.cause = cause;
    this.ci = ci;
    this.cardNo = cardNo;
  }

}

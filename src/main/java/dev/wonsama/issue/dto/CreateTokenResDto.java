package dev.wonsama.issue.dto;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateTokenResDto {

  private String id;
  private boolean expiredYn;
  private LocalDateTime expiredAt;
  private LocalDateTime createdAt;

  @Builder
  public CreateTokenResDto(String id, boolean expiredYn, LocalDateTime expiredAt, LocalDateTime createdAt) {
    this.id = id;
    this.expiredYn = expiredYn;
    this.expiredAt = expiredAt;
    this.createdAt = createdAt;

  }

}

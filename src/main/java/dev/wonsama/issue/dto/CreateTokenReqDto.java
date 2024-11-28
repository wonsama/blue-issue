package dev.wonsama.issue.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateTokenReqDto {

  private String cardId;

  @Builder
  public CreateTokenReqDto(String cardId) {
    this.cardId = cardId;
  }
}

package dev.wonsama.issue.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VerifyTokenReqDto {
  private String tokenId;

  @Builder
  public VerifyTokenReqDto(String tokenId) {
    this.tokenId = tokenId;
  }

}

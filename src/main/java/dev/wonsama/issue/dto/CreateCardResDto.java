package dev.wonsama.issue.dto;

import dev.wonsama.issue.entity.Card;
import lombok.AccessLevel;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateCardResDto {

  private String id;

  public CreateCardResDto(Card card) {
    this.id = card.getId();
  }
}

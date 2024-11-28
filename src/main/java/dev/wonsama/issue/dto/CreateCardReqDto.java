package dev.wonsama.issue.dto;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateCardReqDto {
  private String ci;

  @Length(min = 15, max = 16, message = "카드번호는 15자 이상 16자 이하로 입력해주세요.")
  private String cardNo;

  @Builder
  public CreateCardReqDto(String ci, String cardNo) {
    this.ci = ci;
    this.cardNo = cardNo;
  }

  public String toJson() {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.writeValueAsString(this);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("JsonProcessingException");
    }
    // return String.format("{\"ci\":\"%s\", \"cardNo\":\"%s\"}", ci, cardNo);
  }
}

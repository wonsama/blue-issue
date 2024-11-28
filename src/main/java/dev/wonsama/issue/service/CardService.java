package dev.wonsama.issue.service;

import dev.wonsama.issue.dto.CreateCardReqDto;
import dev.wonsama.issue.dto.CreateCardResDto;

/**
 * 카드 관리 서비스
 */
public interface CardService {

  /**
   * 카드 정보 생성
   * 
   * @param dto 카드 정보
   * @return 카드 참조 ID
   */
  public CreateCardResDto createCard(CreateCardReqDto dto);
}
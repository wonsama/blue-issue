package dev.wonsama.issue.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import dev.wonsama.issue.dto.CreateCardReqDto;
import dev.wonsama.issue.dto.CreateCardResDto;
import dev.wonsama.issue.entity.Card;
import dev.wonsama.issue.enums.IssueSystemErrorCode;
import dev.wonsama.issue.exception.IssueSystemException;
import dev.wonsama.issue.repository.CardRepository;
import dev.wonsama.issue.service.CardService;
import dev.wonsama.issue.util.EncryptUtil;

@Service
public class CardServiceImpl implements CardService {

  @Autowired
  private CardRepository cardRepository;

  @Autowired
  private EncryptUtil encryptUtil;

  @Value("${security.aes256.privatekey}")
  private String privatekey;

  // public List<Card> getAllCards() {
  // return cardRepository.findAll();
  // }

  // public Card getCardById(String id) {
  // return cardRepository.findById(id).orElse(null);
  // }

  /**
   * 카드 정보 생성
   * 
   * @param dto 카드 정보
   * @return 카드 참조 ID
   */
  public CreateCardResDto createCard(CreateCardReqDto dto) {

    // 카드 번호 암호화
    String cardNoEnc;
    try {
      cardNoEnc = encryptUtil.encryptString(dto.getCardNo(), privatekey);
    } catch (Exception e) {
      throw new IssueSystemException(IssueSystemErrorCode.ENCRYPT_FAIL);
    }

    // 카드 번호 기등록 검증
    cardRepository.findByCardNoEnc(cardNoEnc).ifPresent(c -> {
      throw new IssueSystemException(IssueSystemErrorCode.ALREADY_REGISTERED_CARD);
    });

    // 카드 정보 생성
    Card item = Card.builder().ci(dto.getCi()).cardNoEnc(cardNoEnc).build();

    // 카드 정보 저장/반환
    Card card = cardRepository.save(item);
    return new CreateCardResDto(card);
  }

  // public void deleteCard(String id) {
  // cardRepository.deleteById(id);
  // }
}
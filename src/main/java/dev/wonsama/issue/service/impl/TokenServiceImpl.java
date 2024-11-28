package dev.wonsama.issue.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.wonsama.issue.dto.CreateTokenReqDto;
import dev.wonsama.issue.dto.CreateTokenResDto;
import dev.wonsama.issue.dto.VerifyTokenReqDto;
import dev.wonsama.issue.dto.VerifyTokenResDto;
import dev.wonsama.issue.entity.Card;
import dev.wonsama.issue.entity.Token;
import dev.wonsama.issue.enums.IssueSystemErrorCode;
import dev.wonsama.issue.exception.IssueSystemException;
import dev.wonsama.issue.repository.CardRepository;
import dev.wonsama.issue.repository.TokenRepository;
import dev.wonsama.issue.service.TokenService;
import dev.wonsama.issue.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;

@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

  @Autowired
  private TokenRepository tokenRepository;

  @Autowired
  private CardRepository cardRepository;

  @Autowired
  private EncryptUtil encryptUtil;

  @Value("${security.aes256.privatekey}")
  private String privatekey;

  @Value("${token.validity.minutes}")
  private Long TOKEN_VALIDITY_MINUTES; // 기본 1분

  private final String TOKEN_ALREADY_EXPIRED = "TOKEN_ALREADY_EXPIRED";
  private final String TOKEN_EXPIRED = "TOKEN_EXPIRED";
  private final String TOKEN_NOT_FOUND = "TOKEN_NOT_FOUND";
  private final String TOKEN_SUCCESS = "TOKEN_SUCCESS";
  private final String DECRYPT_FAIL = "DECRYPT_FAIL";

  /**
   * 토큰 검증
   * 
   * @param token 토큰 정보
   * @return 토큰 검증 결과
   */
  public VerifyTokenResDto verifyToken(VerifyTokenReqDto token) {

    // 토큰 정보 조회
    Optional<Token> t = tokenRepository.findById(token.getTokenId());

    // 토큰이 존재하는 경우
    if (t.isPresent()) {
      Token tokenEntity = t.get();
      if (tokenEntity.isExpiredYn()) {

        // 실패 - 이미 만료된 토큰인 경우
        log.info("Token already expired : {}", token.getTokenId());
        return VerifyTokenResDto.builder().cause(TOKEN_ALREADY_EXPIRED).isValid(false).build();
      }
      if (tokenEntity.getExpiredAt().isBefore(LocalDateTime.now())) {

        // 실패 - 만료 시간이 지난 경우
        log.info("Token expired : {}", token.getTokenId());
        tokenEntity.setExpiredYn(true);
        tokenRepository.save(tokenEntity);
        return VerifyTokenResDto.builder().cause(TOKEN_EXPIRED).isValid(false).build();
      }

      // 성공 - 토큰 사용 성공
      String ci = tokenEntity.getCard().getCi();
      String cardNoEnc = tokenEntity.getCard().getCardNoEnc();
      String cardNo = null;
      try {
        cardNo = encryptUtil.decryptString(cardNoEnc, privatekey);
      } catch (Exception e) {
        VerifyTokenResDto.builder().cause(DECRYPT_FAIL).isValid(false).build();
      }
      tokenEntity.setExpiredYn(true);
      tokenRepository.save(tokenEntity);
      return VerifyTokenResDto.builder().cause(TOKEN_SUCCESS).isValid(true).ci(ci).cardNo(cardNo)
          .build();
    }

    // 실패 - 토큰이 존재하지 않는 경우
    log.info("Token not found: {}", token.getTokenId());
    return VerifyTokenResDto.builder().cause(TOKEN_NOT_FOUND).isValid(false).build();
  }

  /**
   * 토큰 생성
   * 
   * @param dto 토큰 생성 요청 정보
   * @return 토큰 생성 결과
   */
  @Transactional
  public CreateTokenResDto createToken(CreateTokenReqDto dto) {

    // 등록된 카드인지 여부 확인
    Optional<Card> c = cardRepository.findById(dto.getCardId());
    if (!c.isPresent()) {
      log.info("not registered card : {}", dto.getCardId());
      throw new IssueSystemException(IssueSystemErrorCode.NOT_REGISTERED_CARD);
    }

    // 카드 기준 만료되지 않은 토큰 만료 처리
    tokenRepository.findByCardIdAndExpiredYn(dto.getCardId(), false).stream().forEach(t -> {
      t.setExpiredYn(true);
      tokenRepository.save(t);
    });

    // 카드 참조 아이디 기반으로 1회성성 토큰 생성
    LocalDateTime now = LocalDateTime.now();
    Token token = Token.builder().card(c.get()).expiredAt(now.plusMinutes(TOKEN_VALIDITY_MINUTES)).build();
    tokenRepository.save(token);

    return CreateTokenResDto.builder().id(token.getId()).expiredYn(token.isExpiredYn())
        .expiredAt(token.getExpiredAt()).createdAt(token.getCreatedAt()).build();
  }
}

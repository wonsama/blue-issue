package dev.wonsama.issue.service;

import dev.wonsama.issue.dto.CreateTokenReqDto;
import dev.wonsama.issue.dto.CreateTokenResDto;
import dev.wonsama.issue.dto.VerifyTokenReqDto;
import dev.wonsama.issue.dto.VerifyTokenResDto;

/**
 * 토큰 관리 서비스
 */
public interface TokenService {

  /**
   * 토큰 검증
   * 
   * @param token 토큰 정보
   * @return 토큰 검증 결과
   */
  public VerifyTokenResDto verifyToken(VerifyTokenReqDto token);

  /**
   * 토큰 생성
   * 
   * @param dto 토큰 생성 요청 정보
   * @return 토큰 생성 결과
   */
  public CreateTokenResDto createToken(CreateTokenReqDto dto);
}

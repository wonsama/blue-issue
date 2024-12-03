package dev.wonsama.issue.controller;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.wonsama.issue.dto.CreateTokenReqDto;
import dev.wonsama.issue.dto.CreateTokenResDto;
import dev.wonsama.issue.dto.VerifyTokenReqDto;
import dev.wonsama.issue.dto.VerifyTokenResDto;
import dev.wonsama.issue.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/issue/token")
@Tag(name = "Token", description = "Token 관련 API")
public class TokenController {

  @Autowired
  private TokenService tokenService;

  /**
   * Token 검증
   * 
   * @param dto           검증할 Token 정보
   * @param bindingResult 검증 결과
   * @return Token 검증 결과
   */
  @PostMapping("/validate")
  @Operation(summary = "Token 검증", description = "Token 이 유효한지 검증합니다. 1회용 Token 으로 사용 후 만료됩니다.")
  public VerifyTokenResDto verifyToken(@RequestBody VerifyTokenReqDto dto, BindingResult bindingResult) {

    log.info("🟢 2.3. /api/issue/token/validate : ", ToStringBuilder.reflectionToString(dto));

    return tokenService.verifyToken(dto);
  }

  /**
   * Token 생성
   * 
   * @param dto           생성할 Token 정보
   * @param bindingResult 검증 결과
   * @return Token 생성 결과
   */
  @PostMapping
  @Operation(summary = "Token 생성", description = "Token 을 생성합니다. 1회용, 유효시간(기본 : 1분)")
  public CreateTokenResDto createToken(@RequestBody CreateTokenReqDto dto, BindingResult bindingResult) {

    log.info("🟢 2.2. /api/issue/token : ", ToStringBuilder.reflectionToString(dto));

    return tokenService.createToken(dto);
  }

}
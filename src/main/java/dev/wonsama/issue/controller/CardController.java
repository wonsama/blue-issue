package dev.wonsama.issue.controller;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.wonsama.issue.dto.CreateCardReqDto;
import dev.wonsama.issue.dto.CreateCardResDto;
import dev.wonsama.issue.enums.IssueSystemErrorCode;
import dev.wonsama.issue.exception.IssueSystemException;
import dev.wonsama.issue.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/issue/card")
@Tag(name = "Card", description = "Card 관련 API")
public class CardController {

  @Autowired
  private CardService cardService;

  // @GetMapping
  // public List<Card> getAllUsers() {
  // return cardService.getAllCards();
  // }

  // @GetMapping("/{id}")
  // public Card getCardById(@PathVariable String id) {
  // return cardService.getCardById(id);
  // }

  @PostMapping
  @Operation(summary = "Card 생성", description = "Card 정보를 생성합니다.")
  public CreateCardResDto createCard(@Valid @RequestBody CreateCardReqDto dto, BindingResult bindingResult) {

    log.info("2.1. /api/issue/card : ", ToStringBuilder.reflectionToString(dto));

    if (bindingResult.hasErrors()) {
      bindingResult.getAllErrors().forEach(error -> {
        log.error("Error: {}", error.getDefaultMessage());
      });
      throw new IssueSystemException(IssueSystemErrorCode.INVALID_REQUEST_PARAMETER);
    }

    return cardService.createCard(dto);
  }

  // @DeleteMapping("/{id}")
  // public void deleteUser(@PathVariable String id) {
  // cardService.deleteCard(id);
  // }
}
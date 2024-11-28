package dev.wonsama.issue.enums;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum IssueSystemErrorCode {
  /* 400 BAD_REQUEST : 잘못된 요청 */
  /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
  INVALID_AUTH_TOKEN(HttpStatus.UNAUTHORIZED, "권한 정보가 없는 토큰입니다."),
  NOT_REGISTERED_CARD(HttpStatus.UNAUTHORIZED, "미등록 카드정보 입니다."),
  // INVALID_CARD_NUMBER_LENGTH(HttpStatus.BAD_REQUEST, "카드 자릿수는 15자리 이상 16자리 이하만
  // 가능합니다."),
  INVALID_REQUEST_PARAMETER(HttpStatus.BAD_REQUEST, "잘못된 요청 정보입니다. 입력 파라미터를 확인 바랍니다."),

  /* 404 NOT_FOUND : Resource를 찾을 수 없음 */
  USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 정보의 사용자를 찾을 수 없습니다."),

  /* 409 : CONFLICT : Resource의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
  ALREADY_REGISTERED_CARD(HttpStatus.CONFLICT, "이미 등록된 카드정보 입니다"),

  /* 501 : NOT_IMPLEMENTED : 요청을 수행할 수 있는 기능을 서버가 지원하지 않는다는 것 */
  ENCRYPT_FAIL(HttpStatus.NOT_IMPLEMENTED, "데이터 암호화에 실패하였습니다"),
  ;

  private final HttpStatus httpStatus;
  private final String message;
}

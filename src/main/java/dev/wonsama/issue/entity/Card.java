package dev.wonsama.issue.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 등록된 카드 정보 엔티티
 */
@Entity
@Getter
@Setter
@Table(name = "tb_card")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Card {

  /**
   * 카드 아이디 - TokenSystem 에서 생성된 카드 아이디를 사용한다
   */
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  /**
   * 고객 CI ( Connecting Information )
   */
  @Column(nullable = false, name = "ci")
  private String ci;

  /**
   * 마스킹 된 카드 번호
   */
  @Column(nullable = false, unique = true, name = "card_no_enc")
  private String cardNoEnc;

  /**
   * 등록일
   */
  @CreatedDate
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @OneToMany
  @JoinColumn(name = "card_id")
  List<Token> tokens;

  @Builder
  Card(String ci, String cardNoEnc) {
    this.ci = ci;
    this.cardNoEnc = cardNoEnc;
  }
}

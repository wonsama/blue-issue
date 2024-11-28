package dev.wonsama.issue.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_token")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Token {

  /**
   * 토큰 아이디
   */
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  /**
   * 만료여부
   */
  @Column(nullable = false, name = "expired_yn")
  @ColumnDefault("false")
  private boolean expiredYn;

  /**
   * 만료일
   */
  @Column(name = "expired_at")
  private LocalDateTime expiredAt;

  /**
   * 등록일
   */
  @CreatedDate
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @ManyToOne
  private Card card;

  /**
   * 토큰 생성자
   * 
   * @param token     토큰
   * @param expiredAt 만료일
   */
  @Builder
  Token(LocalDateTime expiredAt, Card card) {
    this.expiredAt = expiredAt;
    this.card = card;

  }
}

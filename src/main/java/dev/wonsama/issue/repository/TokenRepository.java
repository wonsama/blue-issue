package dev.wonsama.issue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.wonsama.issue.entity.Token;

public interface TokenRepository extends JpaRepository<Token, String> {
  // Optional<Token> findByToken(String tokenValue);
  // void deleteByExpiresAtBefore(LocalDateTime dateTime);

  @Query("SELECT t FROM Token t WHERE t.card.id = :cardId AND t.expiredYn = :expiredYn")
  List<Token> findByCardIdAndExpiredYn(String cardId, boolean expiredYn);
}

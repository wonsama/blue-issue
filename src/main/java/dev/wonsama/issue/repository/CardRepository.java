package dev.wonsama.issue.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.wonsama.issue.entity.Card;

public interface CardRepository extends JpaRepository<Card, String> {

  @Query("SELECT c FROM Card c WHERE c.cardNoEnc = :cardNoEnc")
  public Optional<Card> findByCardNoEnc(@Param("cardNoEnc") String cardNoEnc);
}

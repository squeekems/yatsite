package com.squeekems.yat.repositories;

import com.squeekems.yat.entities.Sentence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SentenceRepository extends JpaRepository<Sentence, Long> {
  public List<Sentence> findAllByFlag(String flag);
}

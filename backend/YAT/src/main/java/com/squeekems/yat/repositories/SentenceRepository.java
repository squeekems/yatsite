package com.squeekems.yat.repositories;

import com.squeekems.yat.entities.Sentence;
import com.squeekems.yat.util.SentenceFlag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SentenceRepository extends JpaRepository<Sentence, Long> {
  public List<Sentence> findAllByFlag(String flag);

  @Query(value="SELECT DISTINCT s.flag FROM sentences s", nativeQuery = true)
  List<SentenceFlag> findUniqueFlags();
}

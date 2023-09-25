package com.squeekems.yat.repositories;

import com.squeekems.yat.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
  @Modifying
  @Query(value = "DROP TABLE events CASCADE", nativeQuery = true)
  void dropTable();
}

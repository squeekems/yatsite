package com.squeekems.yat.util.comparators;

import com.squeekems.yat.entities.Option;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class OptionComparatorTest {

  private final OptionComparator optionComparator = new OptionComparator();

  @Test
  void compareTestEqual() {
    Option a = new Option();
    Option b = new Option();
    a.setOptionId(0L);
    b.setOptionId(0L);
    int result = optionComparator.compare(a, b);
    assertEquals(0, result);
  }

  @Test
  void compareTestGreater() {
    Option a = new Option();
    Option b = new Option();
    a.setOptionId(1L);
    b.setOptionId(0L);
    int result = optionComparator.compare(a, b);
    assert(result > 0);
  }

  @Test
  void compareTestLesser() {
    Option a = new Option();
    Option b = new Option();
    a.setOptionId(0L);
    b.setOptionId(1L);
    int result = optionComparator.compare(a, b);
    assert(result < 0);
  }

}
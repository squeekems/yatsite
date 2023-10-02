package com.squeekems.yat.util;

import org.assertj.core.internal.Numbers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ConstantsTest {

  @ParameterizedTest
  @CsvSource({"1, 20", "2, 6"})
  void rollDice(int number, int sides) {
    // Given:
    // Parameters

    // When:
    int actual = Constants.rollDice(number, sides);
    System.out.println("Given: number: " + number + " sides: " + sides + " actual: " + actual);

    // Then:
    assertTrue(actual >= number);
    assertTrue(actual <= sides * number);
  }
}
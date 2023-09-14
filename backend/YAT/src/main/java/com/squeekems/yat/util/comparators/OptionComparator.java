package com.squeekems.yat.util.comparators;

import com.squeekems.yat.entities.Option;

import java.util.Comparator;

public class OptionComparator implements Comparator<Option> {
  @Override
  public int compare(Option a, Option b) {
    return (int) (a.getOptionId() - b.getOptionId());
  }
}

package com.tarun;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.jupiter.api.Test;


class DataFiltererTest {

  @Test
  void shouldReturnEmptyList_WhenLogFileIsEmpty() throws FileNotFoundException {
    assertTrue(DataFilterer.filterByCountry(openFile("src/test/resources/empty"), "GB").isEmpty());
  }

  @Test
  void shouldFilterByCountry_WhenLogFileIsNotEmpty() throws FileNotFoundException {
    assertTrue(DataFilterer.filterByCountry(openFile("src/test/resources/single-line"), "GB").size() == 1);
    assertTrue(DataFilterer.filterByCountry(openFile("src/test/resources/single-line"), "US").size() == 0);
    assertTrue(DataFilterer.filterByCountry(openFile("src/test/resources/single-line"), "DE").size() == 0);

    assertTrue(DataFilterer.filterByCountry(openFile("src/test/resources/multi-lines"), "GB").size() == 1);
    assertTrue(DataFilterer.filterByCountry(openFile("src/test/resources/multi-lines"), "US").size() == 3);
    assertTrue(DataFilterer.filterByCountry(openFile("src/test/resources/multi-lines"), "DE").size() == 1);
  }

  @Test
  void shouldFilterByCountryAndLimit_WhenLogFileIsNotEmpty() throws FileNotFoundException {
    assertTrue(DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/single-line"), "GB", 500).size() == 0);
    assertTrue(DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/single-line"), "DE", 500).size() == 0);
    assertTrue(DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/single-line"), "US", 500).size() == 0);

    assertTrue(DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/multi-lines"), "GB", 500).size() == 0);
    assertTrue(DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/multi-lines"), "US", 500).size() == 3);
    assertTrue(DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/multi-lines"), "DE", 500).size() == 0);
  }

  @Test
  void shouldFilterByAverageResponsTime_WhenLogFileIsNotEmpty() throws FileNotFoundException {
    assertTrue(DataFilterer.filterByResponseTimeAboveAverage(openFile("src/test/resources/single-line")).size() == 0);
    assertTrue(DataFilterer.filterByResponseTimeAboveAverage(openFile("src/test/resources/multi-lines")).size() == 3);
  }

  private FileReader openFile(String filename) throws FileNotFoundException {
    return new FileReader(filename);
  }
}

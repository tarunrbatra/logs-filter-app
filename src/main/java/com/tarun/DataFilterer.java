package com.tarun;

import com.tarun.model.LogLine;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataFilterer {
  public static List<?> filterByCountry(Reader source, String country) {
    List<LogLine> logLines = extractLogLines(source);
    List<LogLine> filteredLines = logLines
            .stream()
            .filter(l ->
                    l.getCountryCode().equals(country))
            .collect(Collectors.toList());

    return filteredLines;
  }

  public static List<?> filterByCountryWithResponseTimeAboveLimit(Reader source, String country, long limit) {
    List<LogLine> logLines = extractLogLines(source);
    List<LogLine> filteredLines = logLines
            .stream()
            .filter(l ->
                    l.getCountryCode().equals(country) && l.getResponseTime() > limit )
            .collect(Collectors.toList());

    return filteredLines;
  }

  public static List<?> filterByResponseTimeAboveAverage(Reader source) {
    List<LogLine> logLines = extractLogLines(source);
    int responseTimeAvg = calculateResponseTimeAvg(logLines);

    List<LogLine> filteredLines = logLines
            .stream()
            .filter(l ->
                    l.getResponseTime() > responseTimeAvg)
            .collect(Collectors.toList());

    return filteredLines;
  }

  private static List<LogLine> extractLogLines(Reader reader) {
    List<LogLine> logLines = new ArrayList<>();
    String line = "";

    try (BufferedReader bufferedReader = new BufferedReader(reader)) {
      //skipping the header
      bufferedReader.readLine();
      while ((line = bufferedReader.readLine()) != null) {
        String[] values = line.split(",");
        logLines.add(new LogLine(values[0], values[1], Integer.parseInt(values[2])));
      }
    } catch (IOException e) {
      //log the error message
    }
    return logLines;
  }

  private static int calculateResponseTimeAvg(List<LogLine> logLines) {
    int sum = 0;
    for(LogLine l : logLines) {
      sum += l.getResponseTime();
    }
    return logLines.isEmpty() ? 0 : sum / logLines.size();
  }

}

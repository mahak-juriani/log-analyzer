package com.devops.log_analyzer.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LogAnalyzerController {

    @GetMapping("/ping")
    public Map<String, String> ping() {
        return Map.of("status", "OK");
    }

    @PostMapping("/analyze")
    public Map<String, Object> analyzeLogs(@RequestBody String logs) {

        String[] lines = logs.split("\n");
        int totalLines = lines.length;

        int errorCount = 0;
        Map<String, Integer> errorMap = new HashMap<>();

        for (String line : lines) {
            if (line.startsWith("ERROR")) {
                errorCount++;

                String message = line.replaceFirst("ERROR", "").trim();
                errorMap.put(message, errorMap.getOrDefault(message, 0) + 1);
            }
        }

        return Map.of(
                "totalLines", totalLines,
                "errorCount", errorCount,
                "topErrors", errorMap
        );
    }

}
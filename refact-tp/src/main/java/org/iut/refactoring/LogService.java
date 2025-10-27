package org.iut.refactoring;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LogService {
    private final List<String> logs;
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LogService() {
        this.logs = new ArrayList<>();
    }

    public void log(String message) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        logs.add(timestamp + " - " + message);
    }

    public void afficherLogs() {
        System.out.println("=== LOGS ===");
        logs.forEach(System.out::println);
    }
}
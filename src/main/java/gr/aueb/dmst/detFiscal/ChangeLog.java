package gr.aueb.dmst.detFiscal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ChangeLog {
    private List<String> entries;

    public ChangeLog() {
        this.entries = new ArrayList<>();
        // έναρξη εφαρμογής
        log("Application started.");
    }

    // προσθήκη νέας εγγραφής
    public void log(String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        entries.add("[" + timestamp + "] " + message);
    }

    public String getFormattedLog() {
        StringBuilder sb = new StringBuilder();
    }
}
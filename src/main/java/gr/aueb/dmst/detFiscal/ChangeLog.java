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
}
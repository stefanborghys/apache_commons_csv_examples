package be.seriousbusiness.apache_commons_csv_examples;

import java.time.LocalDateTime;

public class CsvRecord {

    private final long number;
    private final LocalDateTime creationDateTime;
    private final String message;
    private final String author;

    public CsvRecord(final long number, final LocalDateTime creationDateTime, final String message, final String author) {
        this.number = number;
        this.creationDateTime = creationDateTime;
        this.message = message;
        this.author = author;
    }

    public long getNumber() {
        return number;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }
}

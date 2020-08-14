package be.seriousbusiness.apache_commons_csv_examples;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CSVPrinterExamplesTest {

    /**
     * Logger declared using the declaration pattern.
     *
     * @see <a href="http://www.slf4j.org/faq.html#declaration_pattern">SLF4J Declaration Pattern</a>
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * <p>Creates a CSV.</p>
     * <p>
     * Result:
     * <ul style="list-style-type:none;">
     *     <li>Number,Created On,Message,From</li>
     *     <li>1,2020-08-14 20:20:00,Hello world,Stefan Borghys</li>
     *     <li>2,2020-08-14 20:28:00,This example is made using Apache Commons CSV!,The Oracle</li>
     *     <li>3,2020-08-14 20:33:00,"Don’t think you are, know you are",Morpheus<br></li>
     * </ul>
     * </p>
     *
     * @throws IOException
     * @see CSVPrinter
     */
    @Test
    public void default_csv() throws IOException {
        // For demonstration purposes, we use a 'StringBuilder' to print our CSV to:
        final StringBuilder csvStringBuilder = new StringBuilder();

        // Let's create chat messages to print and sort them:
        final List<CsvRecord> csvRecords = new ArrayList<>();
        csvRecords.add(new CsvRecord(1, LocalDateTime.of(2020, 8, 14, 20, 20), "Hello world", "Stefan Borghys"));
        csvRecords.add(new CsvRecord(2, LocalDateTime.of(2020, 8, 14, 20, 28), "This example is made using Apache Commons CSV!", "The Oracle"));
        csvRecords.add(new CsvRecord(3, LocalDateTime.of(2020, 8, 14, 20, 33), "Don’t think you are, know you are", "Morpheus"));
        csvRecords.sort(Comparator.comparing(CsvRecord::getCreationDateTime));

        // The 'CSVPrinter' needs String values to print a single line.
        // So in our case we create a two dimensional array. Containing the string field values of each CSVRecord:
        final String[][] records = csvRecords.stream().map(csvRecord -> {
            // Values have to be converted to String!
            final String number = String.valueOf(csvRecord.getNumber());
            final String creationDateTime = DATE_TIME_FORMATTER.format(csvRecord.getCreationDateTime());
            final String message = csvRecord.getMessage();
            final String author = csvRecord.getAuthor();
            return new String[] { number, creationDateTime, message, author };
        }).toArray(String[][]::new);

        // We chose a 'default' styled CSV, with a header. The latter is optional.
        CSVFormat.DEFAULT.withHeader("Number", "Created On", "Message", "From").print(csvStringBuilder).printRecords(records);

        LOGGER.info("Default CSV:\n{}", csvStringBuilder.toString());
    }
}

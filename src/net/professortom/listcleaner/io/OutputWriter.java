package net.professortom.listcleaner.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import net.professortom.listcleaner.datastructures.BookRecord;

public class OutputWriter {

    public static void writeCSVFile(final List<BookRecord> books, final String outputName) throws IOException {
        final BookRecord columnHeaders = new BookRecord();
        columnHeaders.author = "Author";
        columnHeaders.title = "Title";
        columnHeaders.volume = -1; //-1 sentinel value for header row

        books.add(0, columnHeaders);

        final File f = new File(outputName);
        f.createNewFile(); // only creates file if file does not exist. See Java documentation for more details

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(f))) {
            for(final BookRecord book : books) {
                final StringBuilder sb = new StringBuilder();
                sb.append(book.title).append(",");
                sb.append(book.author).append(",");
                sb.append(book.volume == -1 ? "Volume" : String.valueOf(book.volume)); //-1 sentinel value for header row
                sb.append("\n");

                bufferedWriter.write(sb.toString());
            }
        }
    }
}

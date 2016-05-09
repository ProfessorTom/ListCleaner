package net.professortom.listcleaner.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.professortom.listcleaner.datastructures.BookRecord;

public class InputParser {

    public static List<BookRecord> parse(final String file) throws IOException {
        final File f = new File(file);
        final List<BookRecord> books = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            //example line: Prelude to War by Robert T. Elson, 1
            for(String line; (line = br.readLine()) != null; ) {
                String[] columns = line.split(",");
                columns = InputParser.removeBlankLines(columns);

                final String[] authorAndTitle = columns[0].split(" by ");

                final BookRecord book = new BookRecord();
                book.title = authorAndTitle[0];
                book.author = authorAndTitle[1];
                book.volume = Integer.parseInt(columns[1]);

                books.add(book);
            }
        }

        return books;
    }

    private static String[] removeBlankLines(final String[] lines) {
        final List<String> nonBlankLines = new ArrayList<>();

        for (final String current : lines) {
            if(!current.isEmpty()) {
                nonBlankLines.add(current);
            }
        }

        return (String[])nonBlankLines.toArray();
    }
}

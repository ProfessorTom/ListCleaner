package listcleaner;

import java.io.IOException;
import java.util.List;

import net.professortom.listcleaner.datastructures.BookRecord;
import net.professortom.listcleaner.io.InputParser;
import net.professortom.listcleaner.io.OutputWriter;

public class ListCleaner {

    public static void main(final String[] args) throws IOException {
        // Java doesn't add the name of the application as the first argument passed in apparently.
        // At least Eclipse doesn't.

        if (args.length != 2) {
            throw new IllegalArgumentException("expected argument count of 2 received " + args.length);
        }

        System.out.println(System.getProperty("user.dir"));

        final List<BookRecord> books = InputParser.parse(args[0]);
        OutputWriter.writeCSVFile(books, args[1]);
    }

}

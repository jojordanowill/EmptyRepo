import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BookReader {
    public String book;
    public MyLinkedList<String> words = new MyLinkedList<>();

    public BookReader(final String fileName) {
        readBook(fileName);
        parseWords();
    }
    public String getBook() {
        return book;
    }
    public String readBook(final String fileName) {
        final long startTime = System.currentTimeMillis();

        final StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int ch;
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        book = sb.toString();

        final long endTime = System.currentTimeMillis();
        System.out.println("Reading input file \"" + fileName + "\"..."
                + book.length() + " characters read in "
                + (endTime - startTime) + " milliseconds.\n");
        return fileName;
    }

    public void parseWords() {
        final long startTime = System.currentTimeMillis();

        final StringBuilder sb = new StringBuilder();
        char ch;
        boolean result = false;

        for (int i = 0; i < book.length(); i++) {
            ch = book.charAt(i);
            if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')
                    || (ch >= '0' && ch <= '9') || ch == '\'') {
                sb.append(ch);
                result = true;
            } else if (result) {
                words.addBefore(sb.toString());
                result = false;
                sb.setLength(0);
            }
        }

        final long endTime = System.currentTimeMillis();
        System.out.println("Finding words and addint them to a linked list " + (endTime - startTime) + " milliseconds");
        System.out.println("The linked list has a length of " + words.size() + ".\n");
    }
    public int countCharacters() {
        return book.length();
    }

    public char charAt(int index) {
        return book.charAt(index);
    }
}

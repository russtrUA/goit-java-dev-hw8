package ua.goit.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SQLReader {
    public String readContent(Path pathToFile) {
        String result = null;
        try {
            result = Files.readString(pathToFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}

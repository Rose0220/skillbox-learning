import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;

public class WriterJSON {

    public static void createJsonFile(String file, Metro metro) {
        ObjectMapper obj = new ObjectMapper();
        try {
            obj.writeValue(new FileWriter(file), metro);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package FileHandling;
import java.io.FileReader;
import java.io.LineNumberReader;
public class LineCounter {

    public static int countLines(String filename){
        int counter = 0;
        try {
            FileReader fileReader = new FileReader(filename);
            try (LineNumberReader lineNumberReader = new LineNumberReader(fileReader)) {
                while (lineNumberReader.readLine() != null){
                    counter++;
                }
            }
       } catch (Exception e) {
        // TODO: handle exception
       }
       return counter;
    }
}

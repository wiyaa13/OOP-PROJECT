package FileHandling;
import java.io.FileWriter;
import java.io.IOException;
import LibraryPack.Books;
import LibraryPack.Library;

public class WriteOutputToBookFile {
    static int counter = 0;
    final static int compareCounter = LineCounter.countLines("Project\\src\\Data\\Books.txt")-1;
    public static void writeToFile(){
      
         if (counter > compareCounter) {
            try {
                FileWriter fw = new FileWriter("Project\\src\\Data\\Books.txt", true);
                Books book = Library.getBooks().get(Library.getBooks().size()-1);
                fw.write(book.getTitle() + "," + book.getGenre() + "," + book.getAuthor() + "," + book.getISBN() + "\n");
                fw.close();
            }
            catch(IOException e) {
            }
    
        }
        counter++;

    }
    
}
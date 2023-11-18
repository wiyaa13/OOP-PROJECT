package FileHandling;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import LibraryPack.Books;
import LibraryPack.Library;

public class ReadBooksFromFile {

   public static void readBooks(String fileName) {

      String line = null;

      try {
         FileReader fileReader = new FileReader(fileName);
         BufferedReader bufferedReader = new BufferedReader(fileReader);

         while((line = bufferedReader.readLine()) != null) {
            String[] bookData = line.split(","); 
            
            String title = bookData[0];
            String genre = bookData[1];
            String author = bookData[2];
            String isbn = bookData[3];
            Library.AddBook(new Books(title, genre, author, isbn));
          
         }

         bufferedReader.close();
      }
      catch(IOException ex) {
         System.out.println("Error reading file: " + fileName);
      }
   }
}

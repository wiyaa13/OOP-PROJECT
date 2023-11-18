package FileHandling;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import LibraryPack.Books;
import LibraryPack.Library;
import LibraryPack.Loan;
import LibraryPack.Users;


public class ReadLoansFromFile {
    public static void readLoans(String fileName) {

        String line = null;
  
        try {
           FileReader fileReader = new FileReader(fileName);
           BufferedReader bufferedReader = new BufferedReader(fileReader);
  
           while((line = bufferedReader.readLine()) != null) {
              String[] loanData = line.split(","); 
              
              String User = loanData[0];
              String Book = loanData[1];
              
              Users user = Library.getUsers().stream().filter(u -> u.getFirstName().equals(User)).findFirst().orElse(null);
              Books book = Library.getBooks().stream().filter(b -> b.getTitle().equals(Book)).findFirst().orElse(null);

              
              Library.rentBook(user, book);
           }
  
           bufferedReader.close();
        }
        catch(IOException ex) {
           System.out.println("Error reading file: " + fileName);
        }
     }
}

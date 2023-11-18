package FileHandling;
import java.io.FileWriter;
import java.io.IOException;
import LibraryPack.Library;
import LibraryPack.Loan;
public class WriteOutputToLoanFile {
    static int counter = 0;
    final static int compareCounter = LineCounter.countLines("Project\\src\\Data\\Loans.txt")-1;
    public static void writeToFile(){
      
         if (counter > compareCounter) {
            try {
                FileWriter fw = new FileWriter("Project\\src\\Data\\Loans.txt", true);
                Loan loan = Library.getLoans().get(Library.getLoans().size()-1);
                fw.write(loan.getUser().getFirstName() + "," + loan.getBook().getTitle() + "\n");
                fw.close();
            }
            catch(IOException e) {
            }
    
        }
        counter++;

    }
}

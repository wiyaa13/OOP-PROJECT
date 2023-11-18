import FileHandling.*;
import GUI.*;
import javafx.application.Application;

public class Main extends Library_Managment_System   {   
    public static void main(String[] args) throws Exception {
        
        String BookData = "Project\\src\\Data\\Books.txt"; 
        String ReaderData = "Project\\src\\Data\\ReadersData.txt";
        String LibrarianData = "Project\\src\\Data\\LibrariansData.txt";
        String OrderData = "Project\\src\\Data\\Orders.txt";
        String LoanData = "Project\\src\\Data\\Loans.txt";
        ReadBooksFromFile.readBooks(BookData);
        ReadReadersFromFile.readReaders(ReaderData);
        ReadLibrariansFromFile.readLibrarians(LibrarianData);
        ReadOrdersFromFile.readOrders(OrderData);
        ReadLoansFromFile.readLoans(LoanData);
        Application.launch(args);
    }
}

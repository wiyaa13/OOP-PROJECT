package FileHandling;
import java.io.FileWriter;
import java.io.IOException;
import LibraryPack.Library;
import LibraryPack.Order;
public class WriteOutputToOrderFile {
    static int counter = 0;
    final static int compareCounter = LineCounter.countLines("Project\\src\\Data\\Orders.txt")-1;
    public static void writeToFile(){
      
         if (counter > compareCounter) {
            try {
                FileWriter fw = new FileWriter("Project\\src\\Data\\Orders.txt", true);
                Order order = Library.getOrders().get(Library.getOrders().size()-1);
                fw.write(order.getUser().getFirstName() + "," + order.getBook().getTitle() + "\n");
                fw.close();
            }
            catch(IOException e) {
            }
    
        }
        counter++;

    }
}

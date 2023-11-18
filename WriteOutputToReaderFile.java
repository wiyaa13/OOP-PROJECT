package FileHandling;
import java.io.FileWriter;
import java.io.IOException;
import LibraryPack.*;
public class WriteOutputToReaderFile {
    static int counter = 0;
    final static int compareCounter = LineCounter.countLines("Project\\src\\Data\\ReadersData.txt")-1;
    public static void writeToFile(){
      
         if (counter > compareCounter) {
            try {
                FileWriter fw = new FileWriter("Project\\src\\Data\\ReadersData.txt", true);
                Users readers = Library.getUsers().get(Library.getUsers().size() - 1);
                if (readers.getBlocked()) {
                    fw.write(readers.getUsername()+","+readers.getPassword()+","+readers.getFirstName()+","+readers.getLastName() +","+readers.getAddress()+","+readers.getCellPhone()+","+readers.getEmail()+","+"true"+ "\n");
                }
                else{
                    fw.write(readers.getUsername()+","+readers.getPassword()+","+readers.getFirstName()+","+readers.getLastName() +","+readers.getAddress()+","+readers.getCellPhone()+","+readers.getEmail()+","+"false"+ "\n");
                }
                fw.close();
            }
            catch(IOException e) {
            }
    
        }
        counter++;

    }
}

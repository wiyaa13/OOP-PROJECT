package FileHandling;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import LibraryPack.Loan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;

public class RemoveLoansFromFile {
    public static void removeloanFromFile(String filename, Loan LoanToRemove) {
        String tmpFile = "Project\\src\\Data\\tmpFile.txt";
        File oldFile = new File(filename);
        File newFile = new File(tmpFile);
        String currentLine;
        try {
            FileWriter fw = new FileWriter(tmpFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
    
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
    
            while ((currentLine = br.readLine()) != null) {
                if (!currentLine.contains(LoanToRemove.getUser().getFirstName() + "," + LoanToRemove.getBook().getTitle())) {
                    if (currentLine.contains("\n") ) {
                        pw.print(currentLine);
                    }
                    else {
                        pw.println(currentLine);
                    }
                
                }
                
                
            }
    
            pw.flush();
            pw.close();
            fr.close();
            br.close();
            bw.close();
            fw.close();
    
            oldFile.delete();
            newFile.renameTo(new File(filename));
    
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

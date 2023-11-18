package FileHandling;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import LibraryPack.Users;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;

public class BlockedFile{
    public static void makeBlockOrUnBlock(String filename, Users user) {
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
            String changedStatus;
            if (user.getBlocked()) {
                changedStatus = user.getUsername()+","+user.getPassword()+","+user.getFirstName()+","+user.getLastName()+","+user.getAddress()+","+user.getCellPhone()+","+user.getEmail()+","+"true";
            }
            else{
                changedStatus = user.getUsername()+","+user.getPassword()+","+user.getFirstName()+","+user.getLastName()+","+user.getAddress()+","+user.getCellPhone()+","+user.getEmail()+","+"false";
            }
            while ((currentLine = br.readLine()) != null) {
                if (currentLine.contains(user.getUsername())) {
                    if (currentLine.contains("\n") ) {
                        pw.print(changedStatus);
                    }
                    else {
                        pw.println(changedStatus);
                    }
                
                }
                else{
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

package LibraryPack;
public class Readers extends Users {
    private String ID;
    private String Type;
    private static int ReaderCount = 0;
    public Readers(String Username,String Password, String FirstName, String LastName, String Address, String CellPhone, String Email, boolean Blocked) {
        super(Username,Password, FirstName, LastName, Address, CellPhone, Email, Blocked);
        ReaderCount++;
        this.Type = "Reader";
        this.ID = "R" + ReaderCount + FirstName.charAt(0) + LastName.charAt(0);
        Library.readers.add(this);
        
    }
    public String getID() {
        return ID;
    }
    public String getType() {
        return Type;
    }

    public static int getReaderCount() {
        return ReaderCount;
    }
    @Override
    public String toString() {
        return "Reader ID: " + this.ID +
                ", Name: " + super.getFirstName() + " " + super.getLastName() +
                ", Address: " + super.getAddress() +
                ", Cellphone: " + super.getCellPhone() +
                ", Email: " + super.getEmail() +
                ", Blocked: " + (super.getBlocked() ? "Yes" : "No");
    }
    
    
}

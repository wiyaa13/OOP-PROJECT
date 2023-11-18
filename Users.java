package LibraryPack;

import FileHandling.BlockedFile;

public class Users {
    
    private String Username;
    private String Password;
    private String FirstName;
    private String LastName;
    private String Address;
    private String CellPhone;
    private String Email;
    private boolean Blocked;
    private static int UserCount = 0;
    public Users(String Username,String Password, String FirstName, String LastName, String Address, String CellPhone, String Email, boolean Blocked) {
        this.Username = Username;
        this.Password = Password;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Address = Address;
        this.CellPhone = CellPhone;
        this.Email = Email;
        this.Blocked = Blocked;
        UserCount++;
    }
    
    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getAddress() {
        return Address;
    }

    public String getCellPhone() {
        return CellPhone;
    }

    public String getEmail() {
        return Email;
    }

    public boolean getBlocked() {
        return Blocked;
    }
    public void setBlocked(boolean blocked,Users user) {
        Blocked = blocked;
        BlockedFile.makeBlockOrUnBlock("Project\\src\\Data\\ReadersData.txt", user);
    }

    public static int getUserCount() {
        return UserCount;
    }

    @Override
    public String toString() {
        return 
                "Name: " + FirstName + " " + LastName +
                ", Address: " + Address +
                ", Cellphone: " + CellPhone +
                ", Email: " + Email +
                ", Blocked: " + Blocked;
    }
}

package LibraryPack;
public class Books {
    private String Title;
    private String Genre;
    private String Author;
    private String ISBN;
    private static int BookCount = 0;
    public Books(String Title, String Genre, String Author,String ISBN) {
        this.Title = Title;
        this.Genre = Genre;
        this.Author = Author;
        this.ISBN = ISBN;
        BookCount++;
    }

    public String getTitle() {
        return Title;
    }

    public String getGenre() {
        return Genre;
    }

    public String getAuthor() {
        return Author;
    }

    public String getISBN() {
        return ISBN;
    }

    public static int getBookCount() {
        return BookCount;
    }

    @Override
    public String toString() {
        return "ISBN: " + ISBN +
                ", Title: " + Title +
                ", Author: " + Author +
                ", Genre: " + Genre ;
    }

}

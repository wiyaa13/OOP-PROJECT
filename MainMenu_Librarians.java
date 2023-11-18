package GUI;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import LibraryPack.Library;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.text.Text;


public class MainMenu_Librarians implements EventHandler<ActionEvent> {
    private Stage primaryStage;
    public MainMenu_Librarians(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    @Override
    public void handle(ActionEvent event){
        Image backgroundImage = new Image("GUI_Material/login.jpg");
        BackgroundImage backgroundImg = new BackgroundImage(backgroundImage,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false));
        Background background = new Background(backgroundImg);
        GridPane mainMenuPane = new GridPane();
        mainMenuPane.setBackground(background);
        mainMenuPane.setAlignment(Pos.CENTER);
        mainMenuPane.setPadding(new Insets(10, 10, 10, 10));
        mainMenuPane.setVgap(10);
        mainMenuPane.setHgap(10);

        Text welcome = new Text("Welcome " + Library.getLoggedUser().getFirstName());
        welcome.setStyle("-fx-font-size: 60px;" +
        "-fx-text-align: center;"+ 
        "-fx-text-fill:rgba(50,50,50);"+
        "-fx-font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;"+
        "-fx-font-weight: bold");
        

        Button addBookButton = new Button("Add Book");
        addBookButton.setOnAction(new AddBook(primaryStage));
        
        
        Button manageBooks = new Button("Manage Books");
        manageBooks.setOnAction(new ManageBooks(primaryStage));
        
        Button addUserButton = new Button("Add User");
        addUserButton.setOnAction(new AddUser(primaryStage));
        
        Button manageUsers = new Button("Manage Users");
        manageUsers.setOnAction(new ManageUsers(primaryStage));

        Button bookOrders = new Button("Manage Book Orders");
        bookOrders.setOnAction(new BookOrderList(primaryStage));

        Button myRentedBooks = new Button("My Rented Books");
        myRentedBooks.setOnAction(new UserRentedBooks(primaryStage, Library.getLoggedUser()));


        Button logoutButton = new Button("Log Out");
        logoutButton.setOnAction(e -> {
            Library.setLoggedUser(null);
            Library_Managment_System Home = new Library_Managment_System();
            Home.start(primaryStage);
        });
        GridPane.setHalignment(logoutButton, HPos.CENTER);
        GridPane.setColumnSpan(logoutButton, 2);
        
        GridPane.setConstraints(welcome, 0, 0, 2, 1);
        GridPane.setConstraints(addBookButton, 0, 1);
        GridPane.setConstraints(addUserButton, 1, 1);
        GridPane.setConstraints(manageBooks, 0, 2);
        GridPane.setConstraints(manageUsers, 1, 2);
        GridPane.setConstraints(bookOrders, 0, 3);
        GridPane.setConstraints(myRentedBooks, 1, 3);
        GridPane.setConstraints(logoutButton, 0, 4, 2, 1);
        
        mainMenuPane.getChildren().addAll(welcome,addBookButton,manageBooks,addUserButton,manageUsers,bookOrders,myRentedBooks,logoutButton);
        Scene mainMenuScene = new Scene(mainMenuPane,  primaryStage.widthProperty().doubleValue(), primaryStage.heightProperty().doubleValue());
        mainMenuScene.getStylesheets().add("GUI_Material/buttonStyle.css");
        primaryStage.setScene(mainMenuScene);
        primaryStage.show();
    }

}

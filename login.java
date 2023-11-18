package GUI;
import LibraryPack.Library;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import LibraryPack.Users;

public class login implements EventHandler<ActionEvent>{
    private Stage primaryStage;

    public login(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    @Override
    public void handle(ActionEvent event) {
        Image backgroundImage = new Image("GUI_Material/login.jpg");
        BackgroundImage backgroundImg = new BackgroundImage(backgroundImage,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false));
        Background background = new Background(backgroundImg);
        GridPane loginPane = new GridPane();
        loginPane.setBackground(background);
        loginPane.setAlignment(Pos.CENTER);
        loginPane.setPadding(new Insets(10, 10, 10, 10));
        loginPane.setVgap(10);
        loginPane.setHgap(10);
        Label UsernameLabel = new Label("Username:");
        UsernameLabel.setFont(Font.font(20));
        TextField UsernameField = new TextField();
        UsernameField.setPromptText("Enter your Username");
        UsernameField.setFont(Font.font(20));
        GridPane.setConstraints(UsernameLabel, 0, 0);
        GridPane.setConstraints(UsernameField, 1, 0);
        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font(20));
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");
        passwordField.setFont(Font.font(20));
        GridPane.setConstraints(passwordLabel, 0, 1);
        GridPane.setConstraints(passwordField, 1, 1);
        Button loginButton = new Button("Login");
        // Add event listener to username and password fields
        UsernameField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                loginButton.fire();
            }
        });

        passwordField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                loginButton.fire();
            }
        });

        loginButton.setOnAction(e -> {
            String username = UsernameField.getText();
            String password = passwordField.getText();
            
            
            
            if(Library.getLibrarians().stream().anyMatch(librarian -> librarian.getUsername().equals(username) && librarian.getPassword().equals(password) && librarian.getBlocked() == false)) {
                Users loggedUser = Library.getUsers().stream().filter(user -> user.getUsername().equals(username)).findFirst().get();
                Library.setLoggedUser(loggedUser);
                MainMenu_Librarians mainMenu_Librarians = new MainMenu_Librarians(primaryStage);
                mainMenu_Librarians.handle(event);
            } 
            else if(Library.getReaders().stream().anyMatch(reader -> reader.getUsername().equals(username) && reader.getPassword().equals(password) && reader.getBlocked() == false)) {
                Users loggedUser = Library.getUsers().stream().filter(user -> user.getUsername().equals(username)).findFirst().get();
                Library.setLoggedUser(loggedUser);
                MainMenu_Readers mainMenu_Readers = new MainMenu_Readers(primaryStage);
                mainMenu_Readers.handle(event);
            }
            else if(Library.getLibrarians().stream().anyMatch(librarian -> librarian.getUsername().equals(username) && librarian.getPassword().equals(password) && librarian.getBlocked() == true)){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Blocked Account");
                alert.setHeaderText("This account is blocked");
                alert.setContentText("Please contact the library manager to unblock your account");
                alert.showAndWait();
            }
            else if(Library.getReaders().stream().anyMatch(reader -> reader.getUsername().equals(username) && reader.getPassword().equals(password) && reader.getBlocked() == true)){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Blocked Account");
                alert.setHeaderText("This account is blocked");
                alert.setContentText("Please contact the library manager to unblock your account");
                alert.showAndWait();
            }
            else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Username or Password");
                alert.setContentText("Please try again");
                alert.showAndWait();
            }
            
        });
        Button backButton = new Button("Back");
        backButton.setFont(Font.font(20));
        backButton.setOnAction(e -> {
            Library_Managment_System Home = new Library_Managment_System();
            Home.start(primaryStage);
        });
        
        GridPane.setConstraints(loginButton, 1, 2);
        GridPane.setConstraints(backButton, 1, 3);
        loginPane.getChildren().addAll(UsernameLabel, UsernameField, passwordLabel, passwordField, loginButton, backButton);
        Scene loginScene = new Scene(loginPane, primaryStage.widthProperty().doubleValue(), primaryStage.heightProperty().doubleValue());
        loginScene.getStylesheets().add("GUI_Material/buttonStyle.css");
        
        primaryStage.setScene(loginScene); 
        
    }
}

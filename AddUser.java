package GUI;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import LibraryPack.Library;
import LibraryPack.Readers;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.awt.event.ActionEvent;

class AddUser implements EventHandler<ActionEvent> {
    private Stage primaryStage;
    Readers readers;

    public AddUser(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    @Override
    public void handle(ActionEvent event) {
        Image backgroundImage = new Image("GUI_Material/login.jpg");
        BackgroundImage backgroundImg = new BackgroundImage(backgroundImage,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false));
        Background background = new Background(backgroundImg);
        GridPane AddUserPane = new GridPane();
        AddUserPane.setBackground(background);
        AddUserPane.setAlignment(Pos.CENTER);
        AddUserPane.setPadding(new Insets(10, 10, 10, 10));
        AddUserPane.setVgap(10);
        AddUserPane.setHgap(10);
        Label emailLabel = new Label("Email:");
        emailLabel.setFont(Font.font(20));
        TextField emailField = new TextField();
        emailField.setPromptText("Enter your email");
        emailField.setFont(Font.font(20));
        GridPane.setConstraints(emailLabel, 0, 0);
        GridPane.setConstraints(emailField, 1, 0);
        Label usernamLabel = new Label("Username:");
        usernamLabel.setFont(Font.font(20));
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username");
        usernameField.setFont(Font.font(20));
        GridPane.setConstraints(usernamLabel, 0, 1);
        GridPane.setConstraints(usernameField, 1, 1);
        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font(20));
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");
        passwordField.setFont(Font.font(20));
        GridPane.setConstraints(passwordLabel, 0, 2);
        GridPane.setConstraints(passwordField, 1, 2);

        Label confirmPasswordLabel = new Label("Confirm Password:");
        confirmPasswordLabel.setFont(Font.font(20));
        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirm your password");
        confirmPasswordField.setFont(Font.font(20));
        GridPane.setConstraints(confirmPasswordLabel, 0, 3);
        GridPane.setConstraints(confirmPasswordField, 1, 3);

        Label firstNameLabel = new Label("First Name:");
        firstNameLabel.setFont(Font.font(20));
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("Enter your first name");
        firstNameField.setFont(Font.font(20));
        GridPane.setConstraints(firstNameLabel, 0, 4);
        GridPane.setConstraints(firstNameField, 1, 4);
        Label lastNameLabel = new Label("Last Name:");
        lastNameLabel.setFont(Font.font(20));
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Enter your last name");
        lastNameField.setFont(Font.font(20));
        GridPane.setConstraints(lastNameLabel, 0, 5);
        GridPane.setConstraints(lastNameField, 1, 5);
        Label phoneNumberLabel = new Label("Phone Number:");
        phoneNumberLabel.setFont(Font.font(20));
        TextField phoneNumberField = new TextField();
        phoneNumberField.setPromptText("Enter your phone number");
        phoneNumberField.setFont(Font.font(20));
        GridPane.setConstraints(phoneNumberLabel, 0, 6);
        GridPane.setConstraints(phoneNumberField, 1, 6);
        Label addressLabel = new Label("Address:");
        addressLabel.setFont(Font.font(20));
        TextField addressField = new TextField();
        addressField.setPromptText("Enter your address");
        addressField.setFont(Font.font(20));
        GridPane.setConstraints(addressLabel, 0, 7);
        GridPane.setConstraints(addressField, 1, 7);

        Button AddUserButton = new Button("Add User");
        
        Button backButton = new Button("Back");
        backButton.setFont(Font.font(20));
        backButton.setOnAction(new MainMenu_Librarians(primaryStage));
        AddUserButton.setFont(Font.font(20));
        GridPane.setConstraints(AddUserButton, 1, 8);
        GridPane.setConstraints(backButton, 1, 9);
        AddUserButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String username = usernameField.getText();
                String email = emailField.getText();
                String password = passwordField.getText();
                String confirmPassword = confirmPasswordField.getText();
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String phoneNumber = phoneNumberField.getText();
                try {
                    int phoneNumberInt = Integer.parseInt(phoneNumber);
                    String address = addressField.getText();
                    for (int i = 0; i < Library.getReaders().size(); i++) {
                        if (Library.getUsers().get(i).getUsername().equals(username)) {
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Error");
                            alert.setContentText("Username already exists");
                            alert.showAndWait();
                            return;
                        }
                    }
                    if (email.equals("") || password.equals("") || confirmPassword.equals("") || firstName.equals("") || lastName.equals("") || phoneNumber.equals("") || address.equals("")) {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Error");
                        alert.setContentText("Please fill in all the fields");
                        alert.showAndWait();
                    } else if (!password.equals(confirmPassword)) {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Error");
                        alert.setContentText("Passwords do not match");
                        alert.showAndWait();
                    } else if (email.contains("@") == false || email.contains(".com") == false) {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Error");
                        alert.setContentText("Invalid email");
                        alert.showAndWait();
                    } 
                    else {
                        Library.addReaders(new Readers(username,password, firstName, lastName, address, phoneNumber, email,false));
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText("Success");
                        alert.setContentText("User added successfully " + "ID: " + Library.getReaders().get(Library.getReaders().size() - 1).getID());
                        alert.showAndWait();
                        MainMenu_Librarians mainMenu_Librarians = new MainMenu_Librarians(primaryStage);
                        mainMenu_Librarians.handle(event);     
                        
                    }
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error");
                    alert.setContentText("Invalid phone number");
                    alert.showAndWait();
                    
                    
                }
                
            }
        });

        AddUserPane.getChildren().addAll(emailLabel, emailField, passwordLabel, passwordField, confirmPasswordLabel, confirmPasswordField, firstNameLabel, firstNameField, lastNameLabel, lastNameField, phoneNumberLabel, phoneNumberField, addressLabel,usernamLabel,usernameField ,addressField, AddUserButton, backButton);
        Scene AddUserScene = new Scene(AddUserPane, primaryStage.widthProperty().doubleValue(), primaryStage.heightProperty().doubleValue());
        AddUserScene.getStylesheets().add("GUI_Material/buttonStyle.css");
        primaryStage.setMaximized(true);
        primaryStage.setScene(AddUserScene);
    }
}
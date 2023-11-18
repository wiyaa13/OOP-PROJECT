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

class register implements EventHandler<ActionEvent> {
    private Stage primaryStage;
    Readers readers;

    public register(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    @Override
    public void handle(ActionEvent event) {
        Image backgroundImage = new Image("GUI_Material/login.jpg");
        BackgroundImage backgroundImg = new BackgroundImage(backgroundImage,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false));
        Background background = new Background(backgroundImg);
        GridPane registerPane = new GridPane();
        registerPane.setBackground(background);
        registerPane.setAlignment(Pos.CENTER);
        registerPane.setPadding(new Insets(10, 10, 10, 10));
        registerPane.setVgap(10);
        registerPane.setHgap(10);
        Label emailLabel = new Label("Email:");
        emailLabel.setFont(Font.font(20));
        TextField emailField = new TextField();
        emailField.setPromptText("Enter your email");
        emailField.setFont(Font.font(20));
        GridPane.setConstraints(emailLabel, 0, 0);
        GridPane.setConstraints(emailField, 1, 0);
        Label usernameLabel = new Label("Username:");
        usernameLabel.setFont(Font.font(20));
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username");
        usernameField.setFont(Font.font(20));
        GridPane.setConstraints(usernameLabel, 0, 1);
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

        Button registerButton = new Button("Register");
        registerButton.setFont(Font.font(20));
        GridPane.setConstraints(registerButton, 1, 8);
        registerButton.setOnAction(new EventHandler<ActionEvent>() {
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
                        alert.setContentText("You have successfully registered\n"+"Your Username is: "+ Library.getReaders().get(Library.getReaders().size()-1).getID() );
                        alert.showAndWait();
                        login LOG = new login(primaryStage);
                        LOG.handle(event);        
                        
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
        Button backButton = new Button("Back");
        backButton.setFont(Font.font(20));
        GridPane.setConstraints(backButton, 1, 9);
        backButton.setOnAction(e -> {
            Library_Managment_System Home = new Library_Managment_System();
            Home.start(primaryStage);
        });
           

        registerPane.getChildren().addAll(usernameLabel,usernameField,emailLabel, emailField, passwordLabel, passwordField, confirmPasswordLabel, confirmPasswordField, firstNameLabel, firstNameField, lastNameLabel, lastNameField, phoneNumberLabel, phoneNumberField, addressLabel, addressField, registerButton, backButton);
        Scene registerScene = new Scene(registerPane, primaryStage.widthProperty().doubleValue(), primaryStage.heightProperty().doubleValue());
        registerScene.getStylesheets().add("GUI_Material/buttonStyle.css");
        primaryStage.setMaximized(true);
        primaryStage.setScene(registerScene);
    }
}
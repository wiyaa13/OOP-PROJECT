package GUI;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import LibraryPack.Library;
import LibraryPack.Users;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class BlockUser implements EventHandler<ActionEvent> {
    private Stage primaryStage;
    

    public BlockUser(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    @Override
    public void handle(ActionEvent event) {
        ImageView background = new ImageView(new Image("GUI_Material/login.jpg"));
        StackPane blockpage = new StackPane(background);
        blockpage.setAlignment(Pos.CENTER);
        Rectangle rect = new Rectangle(0, 0, 1200, 0);
        rect.setFill(Color.rgb(0, 0, 0, 0.7));
        double centerX = primaryStage.getWidth() / 2.0;
        double centerY = primaryStage.getHeight() / 2.0;
        rect.setLayoutX(centerX - rect.getWidth() / 2.0);
        rect.setLayoutY(centerY - rect.getHeight() / 2.0);
        blockpage.getChildren().add(rect);
        
        Text header = new Text("Block");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        header.setFill(Color.WHITE);

        Text name = new Text("Name: ");
        name.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        name.setFill(Color.WHITE);

        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter username");
        usernameField.setMaxWidth(600);
        usernameField.setFont(Font.font(20));

        Button blockButton = new Button("Block");

        blockButton.setOnAction(e -> {
            String firstname = usernameField.getText();
            if (firstname.equals("")) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Username is empty");
                alert.setContentText("Please enter a username");
                alert.showAndWait();
            } else {
                Users user = Library.getUsers().stream().filter(u -> u.getFirstName().equals(firstname)).findFirst().orElse(null);
                if (user != null) {
                    user.setBlocked(true,user);
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("User blocked");
                    alert.setContentText("User " + user.getFirstName() + " has been blocked");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("User not found");
                    alert.setContentText("User " + firstname + " does not exist");
                    alert.showAndWait();
                }
            }
        });
        Button backButton = new Button("Back");
        backButton.setOnAction(new MainMenu_Librarians(primaryStage));

        VBox blockBox = new VBox(10);
        blockBox.setAlignment(Pos.CENTER);
        blockBox.setPadding(new Insets(10, 10, 10, 10));
        blockBox.getChildren().addAll(header, name, usernameField, blockButton, backButton);
        blockpage.getChildren().add(blockBox);

        Scene scene = new Scene(blockpage, primaryStage.widthProperty().doubleValue(), primaryStage.heightProperty().doubleValue());
        scene.getStylesheets().add("GUI_Material/buttonStyle.css");
        primaryStage.setTitle("Library Management System");
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        rect.heightProperty().bind(primaryStage.heightProperty());

    }
}

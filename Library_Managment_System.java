package GUI;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Library_Managment_System extends Application {
    @Override
    public void start(Stage primaryStage) {
        
        ImageView background = new ImageView(new Image("GUI_Material/books.jpg"));
        StackPane startPage = new StackPane(background);
        Image icon = new Image("GUI_Material/icon.png");
        primaryStage.getIcons().add(icon);
        startPage.setAlignment(Pos.CENTER);
        Rectangle rect = new Rectangle(0, 0, 600, 0);
        rect.setFill(Color.rgb(0, 0, 0, 0.7));
        double centerX = primaryStage.getWidth() / 2.0;
        double centerY = primaryStage.getHeight() / 2.0;
        rect.setLayoutX(centerX - rect.getWidth() / 2.0);
        rect.setLayoutY(centerY - rect.getHeight() / 2.0);
        startPage.getChildren().add(rect);
        
        Text header = new Text("Welcome to our Library");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        header.setFill(Color.WHITE);

        Button loginButton = new Button("Login");
        

        Button quitButton = new Button("Quit");
        
        Button registerButton = new Button("Register");
        
        loginButton.setFont(Font.font(20));
        loginButton.setOnAction(new login(primaryStage));
        registerButton.setFont(Font.font(20));
        registerButton.setOnAction(new register(primaryStage));
        quitButton.setFont(Font.font(20));
        quitButton.setOnAction(e -> primaryStage.close());

        VBox vbox = new VBox(header, loginButton, registerButton, quitButton);
        vbox.setSpacing(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-padding: 20px;");
        startPage.getChildren().add(vbox);
        
        Scene scene = new Scene(startPage, primaryStage.widthProperty().longValue(), primaryStage.heightProperty().longValue());
        scene.getStylesheets().add("GUI_Material/buttonStyle.css");
        primaryStage.setTitle("Library Management System");
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        rect.heightProperty().bind(primaryStage.heightProperty());
    }
}
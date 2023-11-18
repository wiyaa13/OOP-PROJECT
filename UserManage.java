package GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;

import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


import LibraryPack.Library;
import LibraryPack.Users;

public class UserManage implements EventHandler<ActionEvent> {
    private Stage primaryStage;
    private Users user;
    public UserManage(Stage primaryStage, Users user) {
        this.primaryStage = primaryStage;
        this.user = user;
    }
    @Override
    public void handle(ActionEvent event){
        ImageView background = new ImageView(new Image("GUI_Material/login.jpg"));
        StackPane searchPage = new StackPane(background);
        searchPage.setAlignment(Pos.CENTER);
        Rectangle rect = new Rectangle(0, 0, 1200, 0);
        rect.setFill(Color.rgb(0, 0, 0, 0.7));
        double centerX = primaryStage.getWidth() / 2.0;
        double centerY = primaryStage.getHeight() / 2.0;
        rect.setLayoutX(centerX - rect.getWidth() / 2.0);
        rect.setLayoutY(centerY - rect.getHeight() / 2.0);
        searchPage.getChildren().add(rect);
        
        Text header = new Text("User Details");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        header.setFill(Color.WHITE);

        Text name = new Text("Name:");
        name.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        name.setFill(Color.WHITE);

        Text name2 = new Text(user.getFirstName() + " " + user.getLastName());
        name2.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        name2.setFill(Color.WHITE);

        Text email = new Text("Email:");
        email.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        email.setFill(Color.WHITE);

        Text email2 = new Text(user.getEmail());
        email2.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        email2.setFill(Color.WHITE);

        Text phone = new Text("Phone:");
        phone.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        phone.setFill(Color.WHITE);

        Text phone2 = new Text(user.getCellPhone());
        phone2.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        phone2.setFill(Color.WHITE);

        Text address = new Text("Address:");
        address.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        address.setFill(Color.WHITE);

        Text address2 = new Text(user.getAddress());
        address2.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        address2.setFill(Color.WHITE);


        Text username = new Text("Username:");
        username.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        username.setFill(Color.WHITE);

        Text username2 = new Text(user.getUsername());
        username2.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        username2.setFill(Color.WHITE);


        Text password = new Text("Password:");
        password.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        password.setFill(Color.WHITE);

        Text password2 = new Text(user.getPassword());
        password2.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        password2.setFill(Color.WHITE);

        Text rentedBooks = new Text("Rented Books:");
        rentedBooks.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        rentedBooks.setFill(Color.WHITE);

        Text rentedBooks2 = new Text(Library.getLoanedBooks(user));
        rentedBooks2.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        rentedBooks2.setFill(Color.WHITE);

        Button remove = new Button("Remove User");
        remove.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        remove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Library.removeUser(user);
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("User Removed");
                alert.setHeaderText(null);
                alert.setContentText("User has been removed from the system.");
                alert.showAndWait();
                new ManageUsers(primaryStage).handle(event);;
            }
        });

        Button Block = new Button();
        if(user.getBlocked()==true){
            Block.setText("Unblock User");
        }
        else if(user.getBlocked()==false){
            Block.setText("Block User");
        }
        Block.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Block.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(user.getBlocked()==true){
                    user.setBlocked(false, user);
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("User Unblocked");
                    alert.setHeaderText(null);
                    alert.setContentText("User has been unblocked from the system.");
                    alert.showAndWait();
                    Block.setText("Block User");
                }
                else if(user.getBlocked()==false){
                    user.setBlocked(true,user);
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("User Blocked");
                    alert.setHeaderText(null);
                    alert.setContentText("User has been blocked from the system.");
                    alert.showAndWait();
                    Block.setText("Unblock User");
                }
                
            }
        });

        Button back = new Button("Back");
        back.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new ManageUsers(primaryStage).handle(event);;
            }
        });
        GridPane grid = new GridPane();
        grid.add(name, 0, 0);
        grid.add(name2, 1, 0);
        grid.add(email, 0, 1);
        grid.add(email2, 1, 1);
        grid.add(phone, 0, 2);
        grid.add(phone2, 1, 2);
        grid.add(address, 0, 3);
        grid.add(address2, 1, 3);
        grid.add(username, 0, 4);
        grid.add(username2, 1, 4);
        grid.add(password, 0, 5);
        grid.add(password2, 1, 5);
        grid.add(rentedBooks, 0, 6);
        grid.add(rentedBooks2, 1, 6);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(header,grid,remove, Block, back);
        vbox.maxWidth(800);

        vbox.setSpacing(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-padding: 20px;");
        searchPage.getChildren().add(vbox);

        Scene scene = new Scene(searchPage, primaryStage.widthProperty().doubleValue(), primaryStage.heightProperty().doubleValue());
        scene.getStylesheets().add("GUI_Material/buttonStyle.css");
        primaryStage.setTitle("Library Management System");
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        rect.heightProperty().bind(primaryStage.heightProperty());
    }
}

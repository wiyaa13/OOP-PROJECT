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
import LibraryPack.Books;

public class BookOrderManage implements EventHandler<ActionEvent> {
    private Stage primaryStage;
    private Users user;
    public BookOrderManage(Stage primaryStage, Users user) {
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

        Text name = new Text("Name: " + user.getFirstName() + " " + user.getLastName());
        name.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        name.setFill(Color.WHITE);


        Text email = new Text("Email: " + user.getEmail());
        email.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        email.setFill(Color.WHITE);

        

        Text phone = new Text("Phone: " + user.getCellPhone());
        phone.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        phone.setFill(Color.WHITE);

        

        Text address = new Text("Address: " + user.getAddress());
        address.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        address.setFill(Color.WHITE);

        


        Text username = new Text("Username: " + user.getUsername());
        username.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        username.setFill(Color.WHITE);



        Text password = new Text("Password: " + user.getPassword());
        password.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        password.setFill(Color.WHITE);

        

        Text orders = new Text("Orders:");
        orders.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        orders.setFill(Color.WHITE);
        GridPane grid = new GridPane();
        int row = 0;
        for (int i = 0; i < Library.getOrders().size(); i++) {
            if (Library.getOrders().get(i).getUser().getUsername().equals(user.getUsername())) {
                Books booki = Library.getOrders().get(i).getBook();
                Text book = new Text(Library.getOrders().get(i).getBook().getTitle());
                book.setFont(Font.font(30));
                book.setFill(Color.WHITE);
                Button removeOrderButton = new Button("Remove Order");
                removeOrderButton.setFont(Font.font(20));
                removeOrderButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Library.removeOrder(user, booki);
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Order Removed");
                        alert.setHeaderText(null);
                        alert.setContentText("Order Removed Successfully");
                        alert.showAndWait();
                        new BookOrderManage(primaryStage, user).handle(event);
                    }
                });
                
                
                
                GridPane.setConstraints(book, 0, row);
                GridPane.setConstraints(removeOrderButton, 1, row);
                grid.getChildren().addAll(book, removeOrderButton);
                row++;
            }
        }


        Button back = new Button("Back");
        back.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new BookOrderList(primaryStage).handle(event);;
            }
        });
        
        
        
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(header,name, email, phone, address, username, password, orders, grid, back);
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

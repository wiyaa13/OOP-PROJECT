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
import LibraryPack.Books;


public class BookManage implements EventHandler<ActionEvent> {
    private Stage primaryStage;
    private Books book;
    public BookManage(Stage primaryStage,Books book) {
        this.primaryStage = primaryStage;
        this.book = book;
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
        
        Text header = new Text("Book Details");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        header.setFill(Color.WHITE);

        Text title = new Text("Title:");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title.setFill(Color.WHITE);

        Text title2 = new Text(book.getTitle());
        title2.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title2.setFill(Color.WHITE);

        Text author = new Text("Author:");
        author.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        author.setFill(Color.WHITE);

        Text author2 = new Text(book.getAuthor());
        author2.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        author2.setFill(Color.WHITE);

        Text genre = new Text("Genre:");
        genre.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        genre.setFill(Color.WHITE);

        Text genre2 = new Text(book.getGenre());
        genre2.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        genre2.setFill(Color.WHITE);

        Text isbn = new Text("ISBN:");
        isbn.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        isbn.setFill(Color.WHITE);

        Text isbn2 = new Text(book.getISBN());
        isbn2.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        isbn2.setFill(Color.WHITE);

        Text rentedby = new Text("Rented By:");
        rentedby.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        rentedby.setFill(Color.WHITE);

        Text rentedby2 = new Text(Library.getRentedBy(book));
        rentedby2.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        rentedby2.setFill(Color.WHITE);


        Button rent = new Button("Rent");
        rent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (Library.isRentedBefore(book)) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Book Already Rented");
                    alert.setHeaderText(null);
                    alert.setContentText("Book has been rented before!");
                    alert.showAndWait();
                    return;
                }
                Library.rentBook(Library.getLoggedUser(),book);
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Book Rented");
                alert.setHeaderText(null);
                alert.setContentText("Book has been rented successfully!");
                alert.showAndWait();
                new ManageBooks(primaryStage).handle(event);
            }
        });
        
        Button remove = new Button("Remove");
        remove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Library.removeBook(book);
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Book Removed");
                alert.setHeaderText(null);
                alert.setContentText("Book has been removed successfully!");
                alert.showAndWait();
                new ManageBooks(primaryStage).handle(event);
            }
        });

        Button back = new Button("Back");
        back.setOnAction(new ManageBooks(primaryStage));

        GridPane grid = new GridPane();
        grid.add(title, 0, 0);
        grid.add(title2, 1, 0);
        grid.add(author, 0, 1);
        grid.add(author2, 1, 1);
        grid.add(genre, 0, 2);
        grid.add(genre2, 1, 2);
        grid.add(isbn, 0, 3);
        grid.add(isbn2, 1, 3);
        grid.add(rentedby, 0, 4);
        grid.add(rentedby2, 1, 4);

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        
        VBox vbox = new VBox(header,grid, rent, remove, back);
        vbox.setSpacing(20);
        vbox.setAlignment(Pos.CENTER);
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

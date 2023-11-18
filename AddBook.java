package GUI;
import LibraryPack.Books;
import LibraryPack.Library;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


class AddBook implements EventHandler<ActionEvent> {
    private Stage primaryStage;
    Books books;
    
    public AddBook(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    @Override
    public void handle(ActionEvent event) {
        Image backgroundImage = new Image("GUI_Material/login.jpg");
        BackgroundImage backgroundImg = new BackgroundImage(backgroundImage,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false));
        Background background = new Background(backgroundImg);
        GridPane AddBookPane = new GridPane();
        AddBookPane.setBackground(background);
        AddBookPane.setAlignment(Pos.CENTER);
        AddBookPane.setPadding(new Insets(10, 10, 10, 10));
        AddBookPane.setVgap(10);
        AddBookPane.setHgap(10);
        
        Label titLabel = new Label("Title:");
        titLabel.setFont(Font.font(20));
        TextField titleField = new TextField();
        titleField.setPromptText("Enter the title of the book");
        titleField.setFont(Font.font(20));
        GridPane.setConstraints(titLabel, 0, 0);
        GridPane.setConstraints(titleField, 1, 0);
        
        Label genreLabel = new Label("Genre:");
        genreLabel.setFont(Font.font(20));
        TextField genrField = new TextField();
        genrField.setPromptText("Enter the genre of the book");
        genrField.setFont(Font.font(20));
        GridPane.setConstraints(genreLabel, 0, 1);
        GridPane.setConstraints(genrField, 1, 1);

        Label authorLabel = new Label("Author:");
        authorLabel.setFont(Font.font(20));
        TextField authorField = new TextField();
        authorField.setPromptText("Enter the author of the book");
        authorField.setFont(Font.font(20));
        GridPane.setConstraints(authorLabel, 0, 2);
        GridPane.setConstraints(authorField, 1, 2);

        Label isbnLabel = new Label("ISBN:");
        isbnLabel.setFont(Font.font(20));
        TextField isbnField = new TextField();
        isbnField.setPromptText("Enter the ISBN of the book");
        isbnField.setFont(Font.font(20));
        GridPane.setConstraints(isbnLabel, 0, 3);
        GridPane.setConstraints(isbnField, 1, 3);

        Button AddBookButton = new Button("Add Book");
        Button backButton = new Button("Back");
        backButton.setOnAction(new MainMenu_Librarians(primaryStage));
        backButton.setFont(Font.font(20));
        AddBookButton.setFont(Font.font(20));
        GridPane.setConstraints(AddBookButton, 1, 7);
        GridPane.setConstraints(backButton, 1, 8);
        AddBookButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String title = titleField.getText();
                String genre = genrField.getText();
                String author = authorField.getText();
                String isbn = isbnField.getText();
                if (title.isEmpty() || genre.isEmpty() || author.isEmpty() || isbn.isEmpty()) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error");
                    alert.setContentText("Please fill in all the fields");
                    alert.showAndWait();
                }
                else{
                    try {
                        long isbn1 = Long.parseLong(isbn);
                        
                        Library.AddBook(new Books(title, genre, author, isbn));
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText("Success");
                        alert.setContentText("Book Has Been Added");
                        alert.showAndWait();
                        MainMenu_Librarians mainMenu = new MainMenu_Librarians(primaryStage);
                        mainMenu.handle(event);
                    } catch (Exception e) {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Error");
                        alert.setContentText("ISBN must be a number");
                        alert.showAndWait();
                    }
                }
                
                
                
                
                
            }
        });

        AddBookPane.getChildren().addAll(titLabel, titleField, genreLabel, genrField, authorLabel, authorField, isbnLabel, isbnField, AddBookButton, backButton);
        Scene AddBookScene = new Scene(AddBookPane, primaryStage.widthProperty().doubleValue(), primaryStage.heightProperty().doubleValue());
        AddBookScene.getStylesheets().add("GUI_Material/buttonStyle.css");
        primaryStage.setMaximized(true);
        primaryStage.setScene(AddBookScene);
    }
}
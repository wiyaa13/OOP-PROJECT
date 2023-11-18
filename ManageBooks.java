package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.util.List;


import LibraryPack.Library;
import LibraryPack.Readers;
import LibraryPack.Books;
import LibraryPack.Librarians;


public class ManageBooks implements EventHandler<ActionEvent> {
    private Stage primaryStage;
    public ManageBooks(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    @Override
    public void handle(ActionEvent event){
        Image backgroundImage = new Image("GUI_Material/pic.jpg");
        BackgroundImage backgroundImg = new BackgroundImage(backgroundImage,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false));
        Background background = new Background(backgroundImg);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setBackground(background);
        GridPane manageUsersPage = new GridPane();
        manageUsersPage.setBackground(background);
        manageUsersPage.setAlignment(Pos.CENTER);
        manageUsersPage.setPadding(new Insets(10, 10, 10, 10));
        manageUsersPage.setVgap(10);
        manageUsersPage.setHgap(10);
        
        
        
        Text header = new Text("Manage Books");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        header.setFill(Color.WHITE);

        HBox searchBox = new HBox();
        searchBox.setSpacing(10);
        
        Button searchButton = new Button("Search");
        TextField searchField = new TextField();
        searchField.setPromptText("Search");
        searchField.setFont(Font.font(20));
        searchField.setMaxWidth(600);

        Button backButton = new Button("Back");
        if (Library.getLoggedUser() instanceof Librarians){
            backButton.setOnAction(new MainMenu_Librarians(primaryStage));
        }
        else if (Library.getLoggedUser() instanceof Readers){
            backButton.setOnAction(new MainMenu_Readers(primaryStage));
        }

        searchBox.getChildren().addAll(searchField, searchButton, backButton);
        
        Text searchResults = new Text("Search Results");
        searchResults.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        searchResults.setFill(Color.WHITE);

        HBox[] searchResultsBox = new HBox[Library.getBooks().size()];
          
        searchButton.setFont(Font.font(20));
        
        
        VBox allBooksBox = new VBox(header, searchBox, searchResults);
        allBooksBox.setMaxWidth(1000);
        allBooksBox.setSpacing(20);
        allBooksBox.setAlignment(Pos.CENTER);
        allBooksBox.setPadding(new Insets(10, 10, 10, 10));
        GridPane.setConstraints(allBooksBox, 0, 0, 2, 1);
        
        manageUsersPage.getChildren().add(allBooksBox);
        int row = 1;
        for (int i=0;i<Library.getBooks().size();i++){
            Text book = new Text(Library.getBooks().get(i).getTitle());
            book.setFont(Font.font(30));
            book.setFill(Color.WHITE);
            Button detailsButton = new Button("Details");
            detailsButton.setFont(Font.font(20));
            if (Library.getLoggedUser() instanceof Librarians){
                detailsButton.setOnAction(new BookManage(primaryStage, Library.getBooks().get(i)));
            }
            else if (Library.getLoggedUser() instanceof Readers) {
                detailsButton.setOnAction(new BooksManageReaders(primaryStage, Library.getBooks().get(i)));
            }
            
            
            GridPane.setConstraints(book, 0, row);
            GridPane.setConstraints(detailsButton, 1, row);
            manageUsersPage.getChildren().addAll(book, detailsButton);
            row++;
        }
        
        

        searchButton.setOnAction(e -> {
            String search = searchField.getText();
            List<Books> results = Library.searchBooks(search);
            if (results.isEmpty()) {
                searchResults.setText("No Results Found");
                allBooksBox.getChildren().removeAll(searchResultsBox);
            } 
            else {
                searchResults.setText("Search Results");
                
                allBooksBox.getChildren().removeAll(searchResultsBox);
                for (Books book : results) {

                    Text searchResultsText = new Text(book.getTitle());
                    searchResultsText.setFont(Font.font(30));
                    searchResultsText.setFill(Color.WHITE);

                    Button detailsButton = new Button("Details");
                    detailsButton.setFont(Font.font(20));
                    if (Library.getLoggedUser() instanceof Librarians){
                        detailsButton.setOnAction(new BookManage(primaryStage, book));
                    }
                    else if (Library.getLoggedUser() instanceof Readers) {
                        detailsButton.setOnAction(new BooksManageReaders(primaryStage, book));
                    }

                    searchResultsBox[results.indexOf(book)] = new HBox();
                    searchResultsBox[results.indexOf(book)].setSpacing(10);
                    searchResultsBox[results.indexOf(book)].getChildren().addAll(searchResultsText, detailsButton);
                    searchResultsBox[results.indexOf(book)].setAlignment(Pos.CENTER);
                    allBooksBox.getChildren().add(searchResultsBox[results.indexOf(book)]);
                }
                
                

            }
        });
        
        
        scrollPane.setContent(manageUsersPage);
        scrollPane.setPadding(new Insets(50, 10, 50, 10));
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        
        
        
        

        Scene scene = new Scene(scrollPane, primaryStage.widthProperty().doubleValue(), primaryStage.heightProperty().doubleValue());
        scene.getStylesheets().add("GUI_Material/buttonStyle.css");
        primaryStage.setTitle("Library Management System");
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.util.List;

import LibraryPack.Books;
import LibraryPack.Library;


public class RentBook implements EventHandler<ActionEvent> {
    private Stage primaryStage;
    public RentBook(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void handle(ActionEvent Event){
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
        
        Text header = new Text("Search");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        header.setFill(Color.WHITE);

        Button searchButton = new Button("Search");
        TextField searchField = new TextField();
        searchField.setPromptText("Search");
        searchField.setFont(Font.font(20));
        searchField.setMaxWidth(600);
        
        Text searchResults = new Text("Search Results");
        searchResults.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        searchResults.setFill(Color.WHITE);

        
        Text searchResultsText = new Text();
        searchResultsText.setFont(Font.font(20));
        searchResultsText.setFill(Color.WHITE);
                
        searchButton.setFont(Font.font(20));
        searchButton.setOnAction(e -> {
            String search = searchField.getText();
            List<Books> searchResult = Library.searchBooks(search);
            if(searchResult.size() == 0){
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Search Results");
                alert.setHeaderText("No Results Found");
                alert.setContentText("No results found for " + search);
                alert.showAndWait();
            }
            else{
                String result = "";
                for(Books book : searchResult){
                    result += book.toString() + "\n";
                }
                searchResultsText.setText(result);
            }
        });
        TextField rentField = new TextField();
        rentField.setPromptText("Enter Book ID");
        rentField.setFont(Font.font(20));
        rentField.setMaxWidth(600);


        TextField rentField2 = new TextField();
        rentField2.setPromptText("Enter your ID");
        rentField2.setFont(Font.font(20));
        rentField2.setMaxWidth(600);

        Button RentButton = new Button("Rent");
        // RentButton.setOnAction(e -> {
        //     String bookID = rentField.getText();
        //     String userID = rentField2.getText();
        //     if(Library.rentBook(bookID, userID)){
        //         Alert alert = new Alert(AlertType.INFORMATION);
        //         alert.setTitle("Rent Book");
        //         alert.setHeaderText("Book Rented");
        //         alert.setContentText("Book with ID " + bookID + " has been rented to user with ID " + userID);
        //         alert.showAndWait();
        //     }
        //     else{
        //         Alert alert = new Alert(AlertType.INFORMATION);
        //         alert.setTitle("Rent Book");
        //         alert.setHeaderText("Book Not Rented");
        //         alert.setContentText("Book with ID " + bookID + " could not be rented to user with ID " + userID);
        //         alert.showAndWait();
        //     }
            
        // });
        Button backButton = new Button("Back");
        backButton.setOnAction(new MainMenu_Librarians(primaryStage));
        

        VBox vbox = new VBox(header, searchField ,searchButton, searchResults, searchResultsText,rentField,rentField2,RentButton, backButton);
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

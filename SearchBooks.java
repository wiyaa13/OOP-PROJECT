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




public class SearchBooks implements EventHandler<ActionEvent> {
    private Stage primaryStage;
    

    public SearchBooks(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    @Override
    public void handle(ActionEvent event) {
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
            if (search.equals("")) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Search Error");
                alert.setContentText("Please enter a search term.");
                alert.showAndWait();
            } 
            else {
                List<Books> results = Library.searchBooks(search);
                
                String resultsText = "";
                for (Books books : results) {
                    resultsText += books.toString()+ "\n";
                }
                if (resultsText.equals("")) {
                    searchResultsText.setText("No results found.");
                }
                else {
                    searchResultsText.setText(resultsText);
                }
                

            }
        });
        Button backButton = new Button("Back");
        backButton.setOnAction(new MainMenu_Readers(primaryStage));
        

        VBox vbox = new VBox(header, searchField ,searchButton, searchResults, searchResultsText, backButton);
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

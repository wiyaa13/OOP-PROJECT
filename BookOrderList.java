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
import LibraryPack.Users;
import LibraryPack.Order;
import LibraryPack.Readers;
import LibraryPack.Librarians;


public class BookOrderList implements EventHandler<ActionEvent> {
    private Stage primaryStage;
    public BookOrderList(Stage primaryStage) {
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
        
        
        
        Text header = new Text("Manage Orders");
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

        HBox[] searchResultsBox = new HBox[Library.getOrders().size()];
          
        searchButton.setFont(Font.font(20));
        
        
        VBox allBooksBox = new VBox(header, searchBox, searchResults);
        allBooksBox.setMaxWidth(1000);
        allBooksBox.setSpacing(20);
        allBooksBox.setAlignment(Pos.CENTER);
        allBooksBox.setPadding(new Insets(10, 10, 10, 10));
        GridPane.setConstraints(allBooksBox, 0, 0, 2, 1);
        
        manageUsersPage.getChildren().add(allBooksBox);
        int row = 1;
        
          
        for (int i = 0; i < Library.getUsers().size(); i++) {
            Users currentUser = Library.getUsers().get(i);
        
            for (int j = 0; j < Library.getOrders().size(); j++) {
                Users orderUser = Library.getOrders().get(j).getUser();
        
                if (currentUser.equals(orderUser)) {
                    Text book = new Text(currentUser.getFirstName() + " " + currentUser.getLastName());
                    book.setFont(Font.font(30));
                    book.setFill(Color.WHITE);
        
                    Button detailsButton = new Button("Details");
                    detailsButton.setFont(Font.font(20));
                    detailsButton.setOnAction(new BookOrderManage(primaryStage, currentUser));
        
                    GridPane.setConstraints(book, 0, row);
                    GridPane.setConstraints(detailsButton, 1, row);
                    manageUsersPage.getChildren().addAll(book, detailsButton);
        
                    row++;
                    break; 
                }
            }
        }
        
        
        
        

        searchButton.setOnAction(e -> {
            String search = searchField.getText();
            List<Order> results = Library.searchOrders(search);
            if (results == null) {
                searchResults.setText("No Results Found");
                allBooksBox.getChildren().removeAll(searchResultsBox);
            } 
            else {
                searchResults.setText("Search Results");
                
                allBooksBox.getChildren().removeAll(searchResultsBox);
                for (Order order : results) {

                    Text searchResultsText = new Text(order.getUser().getFirstName()+" "+order.getUser().getLastName());
                    searchResultsText.setFont(Font.font(30));
                    searchResultsText.setFill(Color.WHITE);

                    Button detailsButton = new Button("Details");
                    detailsButton.setFont(Font.font(20));
                    detailsButton.setOnAction(new BookOrderManage(primaryStage, order.getUser()));

                    searchResultsBox[results.indexOf(order)] = new HBox();
                    searchResultsBox[results.indexOf(order)].setSpacing(10);
                    searchResultsBox[results.indexOf(order)].getChildren().addAll(searchResultsText, detailsButton);
                    searchResultsBox[results.indexOf(order)].setAlignment(Pos.CENTER);
                    allBooksBox.getChildren().add(searchResultsBox[results.indexOf(order)]);
                    break;
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

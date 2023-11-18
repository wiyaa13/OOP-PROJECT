package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;



import LibraryPack.Library;
import LibraryPack.Users;
import LibraryPack.Readers;
import LibraryPack.Books;
import LibraryPack.Librarians;
public class UserRentedBooks implements EventHandler<ActionEvent> {
    private Stage primaryStage;
    private Users user;
    public UserRentedBooks(Stage primaryStage, Users user) {
        this.primaryStage = primaryStage;
        this.user = user;
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
        
        
        
        Text header = new Text("My Loans");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        header.setFill(Color.WHITE);

        HBox searchBox = new HBox();
        searchBox.setSpacing(10);
        
        Button addOrderButton = new Button("Rent a Book");
        addOrderButton.setFont(Font.font(20));
        addOrderButton.setOnAction(new ManageBooks(primaryStage));
        

        Button backButton = new Button("Back");
        if (Library.getLoggedUser() instanceof Librarians){
            backButton.setOnAction(new MainMenu_Librarians(primaryStage));
        }
        else if (Library.getLoggedUser() instanceof Readers){
            backButton.setOnAction(new MainMenu_Readers(primaryStage));
        }

        searchBox.getChildren().addAll(addOrderButton, backButton);


        addOrderButton.setFont(Font.font(20));
        
        
        VBox allBooksBox = new VBox(header, searchBox);
        allBooksBox.setMaxWidth(1000);
        allBooksBox.setSpacing(20);
        allBooksBox.setAlignment(Pos.CENTER);
        allBooksBox.setPadding(new Insets(10, 10, 10, 10));
        GridPane.setConstraints(allBooksBox, 0, 0, 2, 1);
        
        manageUsersPage.getChildren().add(allBooksBox);
        int row = 1;
        for (int i=0;i<Library.getLoans().size();i++){
            if (Library.getLoans().get(i).getUser().getUsername().equals(user.getUsername())) {
                Books booki = Library.getLoans().get(i).getBook();
                Text book = new Text(Library.getLoans().get(i).getBook().getTitle());
                book.setFont(Font.font(30));
                book.setFill(Color.WHITE);
                Button removeButton = new Button("Return");
                removeButton.setFont(Font.font(20));
                removeButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Library.returnBook(booki);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText(null);
                        alert.setContentText("Book returned successfull");
                        alert.showAndWait();

                        new UserRentedBooks(primaryStage, user).handle(event);
                    }
                });
                
                
                GridPane.setConstraints(book, 0, row);
                GridPane.setConstraints(removeButton, 1, row);
                manageUsersPage.getChildren().addAll(book, removeButton);
                row++;
            }
            
        }
    
        
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
